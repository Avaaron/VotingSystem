package ru.project.voting.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.project.voting.model.Restaurant;

import java.util.List;

@Repository

public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        if(!restaurant.isNew() && get(restaurant.getId(), userId) == null) {
            return null;
        }else {
            return crudRestaurantRepository.save(restaurant);
        }
    }

    @Override
    public Restaurant get(int id, int userId) {
        return crudRestaurantRepository.getById(id)
                .filter(r -> r.getId() == id && r.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }

}
