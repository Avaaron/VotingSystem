package ru.project.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.project.voting.model.Dish;
import ru.project.voting.model.Menu;
import ru.project.voting.model.Restaurant;
import ru.project.voting.repository.CrudDishRepository;
import ru.project.voting.repository.CrudMenuRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class DishService {

    @Autowired
    protected CrudMenuRepository menuRepository;

    @Autowired
    protected CrudDishRepository dishRepository;

    @Transactional
    public List<Dish> getDihsesByDateAndRestaurant (LocalDate date, Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant can`t be null");
        Menu menu = menuRepository.getMenuByDateAndRestaurant(date, restaurant);
        return dishRepository.findByMenu(menu);
    }
}
