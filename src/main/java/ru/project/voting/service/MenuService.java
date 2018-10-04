package ru.project.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.project.voting.model.Menu;
import ru.project.voting.model.Restaurant;
import ru.project.voting.repository.MenuRepository;
import ru.project.voting.util.exception.NotFoundException;

import java.time.LocalDate;

import static ru.project.voting.web.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Menu create(Menu menu) {
        Assert.notNull(menu, "menu can`t be null");
        return menuRepository.save(menu);
    }

    public Menu getByRestaurantAndDate (LocalDate date, Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant can`t be null");
        return menuRepository.getByDateAndRestaurant(date, restaurant);
    }

    public void delete(int id, int adminId) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id, adminId), id);
    }

    public Menu get(int id, int userId) {
        return checkNotFoundWithId(menuRepository.get(id, userId), id);
    }
}
