package ru.project.voting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.voting.model.Dish;
import ru.project.voting.model.Menu;
import ru.project.voting.model.Restaurant;
import ru.project.voting.repository.CrudRestaurantRepository;
import ru.project.voting.service.DishService;
import ru.project.voting.service.RestauantService;
import ru.project.voting.service.VoteService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static ru.project.voting.util.DateTimeUtil.SEPARATED_TIME;


@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/vote";

    @Autowired
    VoteService voteService;

    @Autowired
    RestauantService restauantService;

    @Autowired
    DishService dishService;

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public List<Restaurant> getAllRestaurants() {
        log.info("getAll");
        return restauantService.getAll();
    }


    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public List<Dish> readMenu(@RequestBody Restaurant restaurant) {
        log.info("readMenu {}", restaurant.toString());
        return dishService.getDihsesByDateAndRestaurant( LocalDate.of(2018, 9, 8), restaurant);
    }

    @RequestMapping(value = "/voting", method = RequestMethod.GET)
    public void voting(@RequestBody Menu menu){
        log.info("voting {}", menu.toString());
        voteService.saveOrUpdateUserVote(LoggedUser.getLoggedUserId(), menu, SEPARATED_TIME);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public void deleteVote() {
        log.info("cancelVoice");
        voteService.cancelVote(LoggedUser.getLoggedUserId(), LocalDate.of(2018, 9, 19), SEPARATED_TIME);
    }
}
