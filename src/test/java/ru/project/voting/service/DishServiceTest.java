package ru.project.voting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.project.voting.model.Dish;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.project.voting.TestData.*;


class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService dishService;

    @Test
    void getDihsesByDateAndRestaurant() {
        log.info("getDishesByDateAndRestaurant");
        List<Dish> expected = dishService.getDihsesByDateAndRestaurant(TEST_DATE_MENU_1, RESTAURANT_1);
        assertMatch(Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4), expected);
    }

    @Test
    void getDihsesByDateAndRestaurantWrongDate() {
        log.info("getDihsesByDateAndRestaurantWrongDate");
        boolean expected = false;
        List<Dish> flag = dishService.getDihsesByDateAndRestaurant(TEST_DATE_USER_1, RESTAURANT_1);
        if(flag.size() == 0) expected = true;
        assertThat(true).isEqualTo(expected);
    }

    @Test
    void getDihsesByDateAndRestaurantWrongRestaurant() {
        log.info("getDihsesByDateAndRestaurantWrongRestaurant");
        boolean expected = false;
        List<Dish> flag = dishService.getDihsesByDateAndRestaurant(TEST_DATE_MENU_1, RESTAURANT_2);
        if(flag.size() == 0) expected = true;
        assertThat(true).isEqualTo(expected);
    }
}