package ru.project.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.project.voting.model.Dish;
import ru.project.voting.model.Menu;
import ru.project.voting.model.Restaurant;
import ru.project.voting.model.User;
import ru.project.voting.repository.DishRepository;
import ru.project.voting.repository.MenuRepository;
import ru.project.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.project.voting.web.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DishRepository dishRepository;

    @Transactional
    public List<Dish> getDihsesByDateAndRestaurant (LocalDate date, Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant can`t be null");
        Menu menu = menuRepository.getByDateAndRestaurant(date, restaurant);
        return dishRepository.getAllByMenu(menu);
    }

    public Dish create(Dish dish, Menu menu, int userId) {
        Assert.notNull(dish, "Dish can`t be null");
        return dishRepository.save(dish, menu, userId);
    }

    public void update(Dish dish, Menu menu, int userId) {
        Assert.notNull(dish, "Dish can`t be null");
        dishRepository.save(dish, menu, userId);
    }

    public void delete(int id, int userId) throws NotFoundException{
        checkNotFoundWithId(dishRepository.delete(id, userId), id);
    }

    public Dish get(int id, int userId) {
        return dishRepository.get(id, userId);
    }
}
