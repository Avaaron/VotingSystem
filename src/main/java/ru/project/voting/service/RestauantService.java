package ru.project.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.project.voting.model.Restaurant;
import ru.project.voting.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestauantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant create (Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "Restaurant can`t be null");
        return restaurantRepository.save(restaurant, userId);
    }

    public void upate(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "Restaurant can`t be null");
        restaurantRepository.save(restaurant, userId);
    }

    public Restaurant get(int id, int userId) {
        return restaurantRepository.get(id, userId);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }
}
