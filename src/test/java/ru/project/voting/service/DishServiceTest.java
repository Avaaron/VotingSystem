package ru.project.voting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.project.voting.model.Dish;
import ru.project.voting.repository.DishRepository;
import ru.project.voting.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.project.voting.TestData.*;


class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishRepository dishRepository;

    @Test
    void updateTest() {
        Dish updated = new Dish(100016, "updated", 103);
        dishService.update(updated, MENU_2, ADMIN_ID2);
        updated.setMenu(MENU_2);
        assertEquals(dishService.get(100016, ADMIN_ID2), updated);
    }

    @Test
    void getDihsesByDateAndRestaurant() {
        log.info("getDishesByDateAndRestaurant");
        List<Dish> expected = dishService.getDihsesByDateAndRestaurant(TEST_DATE_MENU_1, RESTAURANT_1);
        assertEquals(Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4), expected);
    }

    @Test
    void getDihsesByDateAndRestaurantWrongDate() {
        log.info("getDihsesByDateAndRestaurantWrongDate");
        boolean expected = false;
        List<Dish> flag = dishService.getDihsesByDateAndRestaurant(TEST_DATE_USER_1, RESTAURANT_1);
        if(flag.size() == 0) expected = true;
        assertEquals(true, expected);
    }

    @Test
    void getDihsesByDateAndRestaurantWrongRestaurant() {
        log.info("getDihsesByDateAndRestaurantWrongRestaurant");
        boolean expected = false;
        List<Dish> flag = dishService.getDihsesByDateAndRestaurant(TEST_DATE_MENU_1, RESTAURANT_2);
        if(flag.size() == 0) expected = true;
        assertEquals(true, expected);
    }

    @Test
    void createTest() {
        Dish created = getCreatedDish();
        dishService.create(created, MENU_1, ADMIN_ID1);
        Dish actual = dishService.get(NEW_ID, ADMIN_ID1);
        assertEquals(actual, created);

    }

    @Test
    void getTest() {
        assertEquals(dishService.get(DISH_ID1, ADMIN_ID1), DISH_1);
    }

    @Test
    void getWrongUserTest() {
        boolean flag = false;
        if(dishService.get(DISH_ID1, ADMIN_ID2) == null) flag = true;
        assertEquals(true, flag);
    }

    @Test
    void getNotFoundTest() {
        boolean flag = false;
        if (dishService.get(NEW_ID, ADMIN_ID1) == null) flag = true;
        assertEquals(true, flag);
    }

    @Test
    void deleteTest() throws Exception{
        dishService.delete(DISH_ID1, ADMIN_ID1);
        assertEquals(dishRepository.getAllByMenu(MENU_1), Arrays.asList(DISH_2, DISH_3, DISH_4));
    }

    @Test
    void deleteNotFoundTest() {
        assertThrows(NotFoundException.class, () -> dishService.delete(DISH_ID5, ADMIN_ID1));
    }


}