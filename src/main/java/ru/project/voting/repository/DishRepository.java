package ru.project.voting.repository;

import ru.project.voting.model.Dish;
import ru.project.voting.model.Menu;;


import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, Menu menu, int userId);

    List<Dish> getAllByMenu(Menu menu);

    List<Dish> getAllByDate(LocalDate date);

    Dish get(int id, int userId);

    boolean delete(int id, int userId);
}
