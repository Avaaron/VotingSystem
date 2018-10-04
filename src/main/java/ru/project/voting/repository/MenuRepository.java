package ru.project.voting.repository;

import ru.project.voting.model.Menu;
import ru.project.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu);

    boolean delete(int id, int adminId);

    Menu getByDateAndRestaurant(LocalDate date, Restaurant restaurant);

    List<Menu> getByRestaurant(Restaurant restaurant);

    List<Menu> getByDate(LocalDate date);

    Menu get(int id, int adminId);
}
