package ru.project.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import ru.project.voting.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Restaurant findById(int id);

    @Override
    @Secured("ROLE_ADMIN")
    Restaurant save(Restaurant restaurant);

    @Override
    List<Restaurant> findAll();
}
