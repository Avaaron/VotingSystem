package ru.project.voting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.project.voting.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.project.voting.TestData.*;

class RestauantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestauantService restauantService;

    @Test
    void createTest() {
        log.info("createTest");
        Restaurant created = getCreatedRestaurant();
        restauantService.create(created, ADMIN_ID1);
        assertThat(restauantService.get(REST_ID_CREATED, ADMIN_ID1)).isEqualTo(created);
    }

    @Test
    void updateTest() {
        log.info("updateTest");
        Restaurant updated = getUpdatedRestaurant();
        restauantService.upate(updated, ADMIN_ID1);
        assertThat(restauantService.get(REST_ID1, ADMIN_ID1)).isEqualTo(updated);
    }

    @Test
    void getTest() {
        assertThat(restauantService.get(REST_ID1, ADMIN_ID1)).isEqualTo(RESTAURANT_1);
    }

    @Test
    void getAllTest() {
        assertMatch(restauantService.getAll(), Arrays.asList(RESTAURANT_1, RESTAURANT_2));
    }
}