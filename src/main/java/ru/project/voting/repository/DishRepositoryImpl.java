package ru.project.voting.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.voting.model.Dish;
import ru.project.voting.model.Menu;
import ru.project.voting.repository.cruds.CrudDishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Transactional
    @Override
    public Dish save(Dish dish, Menu menu, int userId) {
        if(!dish.isNew() && get(dish.getId(), userId) == null) {
            return null;
        } else  {
                dish.setMenu(menu);
                return crudDishRepository.save(dish);
        }
    }

    @Override
    public List<Dish> getAllByMenu(Menu menu) {
        return crudDishRepository.findByMenu(menu);
    }

    @Override
    public List<Dish> getAllByDate(LocalDate date) {
        return crudDishRepository.findByDate(date);
    }

    @Override
    public Dish get(int id, int userId) {
        return crudDishRepository.findById(id).filter(dish
                -> dish.getMenu().getRestaurant().getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public boolean delete (int id, int userId) {
        if(get(id, userId) != null) {
            return crudDishRepository.delete(id) != 0;
        } else return false;
    }
}
