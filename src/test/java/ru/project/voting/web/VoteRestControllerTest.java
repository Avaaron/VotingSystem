package ru.project.voting.web;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.project.voting.TestUtil;
import ru.project.voting.model.Dish;
import ru.project.voting.model.Restaurant;
import ru.project.voting.model.Vote;
import ru.project.voting.repository.cruds.CrudVoteRepository;
import ru.project.voting.service.DishService;
import ru.project.voting.service.RestaurantService;
import ru.project.voting.service.VoteService;
import ru.project.voting.web.json.JsonUtil;

import javax.annotation.PostConstruct;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.project.voting.TestData.*;
import static ru.project.voting.TestUtil.userHttpBasic;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class VoteRestControllerTest {
    private static final String REST_URL = VoteRestController.REST_URL + '/';

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

     MockMvc mockMvc;

    @Autowired
    private DishService dishService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .apply(springSecurity())
                .build();
    }

    @Test
    void getAllRestaurantsTest() throws Exception{
        log.info("getAllRestaurantsTest");
        TestUtil.print(
        mockMvc.perform(get(REST_URL + "restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
        );
    }

    @Test
    void readMenu() throws Exception {
        log.info("readMenu");
        TestUtil.print(
       mockMvc.perform(get(REST_URL + "vote/menu")
               .contentType(MediaType.APPLICATION_JSON)
               .content(JsonUtil.writeValue(RESTAURANT_1))
               .with(userHttpBasic(USER_1)))
               .andExpect(status().isOk())
               .andDo(print()));
    }

    @Test
    void votingCreateTest() throws Exception{
        log.info("votingCreateTest");
        Vote created = getCreated();
        mockMvc.perform(get(REST_URL + "vote/voting")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(MENU_2))
                .with(userHttpBasic(USER_3)))
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(voteService.get(USER_ID3, LocalDate.now()), created);
    }

    @Test
    void votingUpdateTest() throws Exception{
        log.info("votingUpdateTest");
        Vote updated = getUpdated();
        mockMvc.perform(get(REST_URL + "vote/voting")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(MENU_2))
                .with(userHttpBasic(USER_1)))
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(voteService.get(USER_ID1, TEST_DATE_USER_1), updated);
    }

    @Test
    void deleteVoteTest() throws Exception{
        log.info("deleteVoteTest");
        mockMvc.perform(get(REST_URL + "vote/cancel")
                .with(userHttpBasic(USER_2)))
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(crudVoteRepository.findAll(), Arrays.asList(VOTE_2));
    }

    @Test
    void createRestaurant() throws Exception {
        log.info("createRestaurantTest");
        Restaurant created = getCreatedRestaurant();
        mockMvc.perform(get(REST_URL + "admin/restaurant/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk());
        created.setId(NEW_ID);
        created.setUser(ADMIN_1);
        assertEquals(restaurantService.get(NEW_ID, ADMIN_ID1), created);
    }

    @Test
    void createDish() throws Exception {
        log.info("createDishTest");
        Dish created = getCreatedDish();
        mockMvc.perform(get(REST_URL + "admin/dish/add")
                .param("menuId", "100007")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk());
        created.setMenu(MENU_1);
        created.setId(NEW_ID);
        assertEquals(dishService.get(NEW_ID, ADMIN_ID1), created);

    }

    @Test
    void updateDish() throws Exception {
        Dish updated = getUpdatedDish();
        mockMvc.perform(get(REST_URL +"admin/dish/update")
                .param("menuId", "100008")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN_2)))
                .andDo(print())
                .andExpect(status().isOk());
        updated.setMenu(MENU_2);
        assertEquals(dishService.get(updated.getId(), ADMIN_ID2), updated);
    }

    @Test
    void deleteDish() throws Exception {
        mockMvc.perform(get(REST_URL + "admin/dish/update" + "/" + DISH_ID4)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk());
    }

}