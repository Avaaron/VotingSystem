package ru.project.voting.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.project.voting.model.Menu;
import ru.project.voting.model.Restaurant;
import ru.project.voting.repository.cruds.CrudMenuRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuRepositoyImpl implements MenuRepository {

    @Autowired
    private CrudMenuRepository crudMenuRepository;

    @Override
    public Menu save(Menu menu) {
        if(!menu.isNew()) return null;
        return crudMenuRepository.save(menu);
    }

    @Override
    public boolean delete(int id, int adminId) {
        if(get(id, adminId) == null) return false;
        return crudMenuRepository.delete(id) != 0;
    }

    @Override
    public Menu get(int id, int adminId) {
        return crudMenuRepository.findById(id).filter(menu -> menu.getRestaurant().getUser().getId() == adminId)
                .orElse(null);
    }

    @Override
    public Menu getByDateAndRestaurant(LocalDate date, Restaurant restaurant) {
        return crudMenuRepository.getMenuByDateAndRestaurant(date, restaurant).orElse(null);
    }

    @Override
    public List<Menu> getByRestaurant(Restaurant restaurant) {
        return crudMenuRepository.findByRestaurant(restaurant);
    }

    @Override
    public List<Menu> getByDate(LocalDate date) {
        return crudMenuRepository.findByDate(date);
    }
}
