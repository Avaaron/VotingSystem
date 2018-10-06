package ru.project.voting.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.project.voting.model.Restaurant;


import static org.junit.jupiter.api.Assertions.*;
import static ru.project.voting.TestData.*;


class RestauantServiceTest extends AbstractServiceTest {

    @Autowired
     RestaurantService restauantService;

    @Test
    void updateTest() {
        log.info("updateTest");
        Restaurant updated = getUpdatedRestaurant();
        restauantService.upate(updated, ADMIN_ID1);
        assertEquals(restauantService.get(REST_ID1, ADMIN_ID1), updated);
    }

    @Test
    void getTest() {
        log.info("getRestaurantTest id = {}, userId = {}", REST_ID2, ADMIN_ID2);
        assertEquals(restauantService.get(REST_ID2, ADMIN_ID2),RESTAURANT_2);
    }


}