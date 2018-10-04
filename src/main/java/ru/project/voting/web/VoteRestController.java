package ru.project.voting.web;

import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.voting.model.Dish;
import ru.project.voting.model.Menu;
import ru.project.voting.model.Restaurant;
import ru.project.voting.repository.cruds.CrudMenuRepository;
import ru.project.voting.service.DishService;
import ru.project.voting.service.MenuService;
import ru.project.voting.service.RestaurantService;
import ru.project.voting.service.VoteService;
import ru.project.voting.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.project.voting.util.DateTimeUtil.SEPARATED_TIME;


@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest";

    @Autowired
    private VoteService voteService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @Autowired
    private MenuService menuService;


    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public List<Restaurant> getAllRestaurants() {
        log.info("getAll");
        return restaurantService.getAll();
    }


    @RequestMapping(value = "/vote/menu", method = RequestMethod.GET)
    public List<Dish> readMenu(@RequestBody Restaurant restaurant) {
        log.info("readMenu {}", restaurant.toString());
        return dishService.getDihsesByDateAndRestaurant( LocalDate.of(2018, 9, 8), restaurant);
    }

    @RequestMapping(value = "/vote/voting", method = RequestMethod.GET)
    public void voting(@RequestBody Menu menu){
        log.info("voting {}", menu.toString());
        voteService.saveOrUpdateUserVote(LoggedUser.getLoggedUserId(), menu, SEPARATED_TIME);
    }

    @RequestMapping(value = "/vote/cancel", method = RequestMethod.GET)
    public void deleteVote() {
        log.info("cancelVoice");
        voteService.cancelVote(LoggedUser.getLoggedUserId(), LocalDate.of(2018, 9, 19), SEPARATED_TIME);
    }

    @RequestMapping(value = "/admin/restaurant/add", method = RequestMethod.GET)
    public void createRestaurant(@RequestBody Restaurant restaurant) {
        log.info("createRestaurant {}", LoggedUser.getLoggedUserId());
        restaurantService.create(restaurant, LoggedUser.getLoggedUserId());
    }

    @RequestMapping(value = "/admin/menu/add", method = RequestMethod.GET)
    public void createMenu(@RequestBody Restaurant restaurant) {
        log.info("createMenu");
        Menu created = new Menu(null, restaurant, LocalDate.now());
        menuService.create(created);
    }

    @RequestMapping(value = "/admin/dish/add", method = RequestMethod.GET)
    public void createDish(@RequestBody Dish dish, @RequestParam("menuId") int menuId) {
        Menu menu = menuService.get(menuId, LoggedUser.getLoggedUserId());
        dishService.create(dish, menu, LoggedUser.getLoggedUserId());
    }

    @RequestMapping(value = "/admin/dish/update")
    public void updateDish(@RequestBody Dish dish, @RequestParam("menuId") int menuId) {
        Menu menu = menuService.get(menuId, LoggedUser.getLoggedUserId());
        dishService.update(dish, menu, LoggedUser.getLoggedUserId());
    }

    @RequestMapping(value = "/admin/dish/delete/{id}", method = RequestMethod.GET)
    public void deleteDish(@PathVariable("id") int id) {
        dishService.delete(id, LoggedUser.getLoggedUserId());
    }
}
