package ru.project.voting.repository;

import ru.project.voting.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save (Restaurant restaurant, int userId);

    Restaurant get (int id, int userId);

    List<Restaurant> getAll();
}
