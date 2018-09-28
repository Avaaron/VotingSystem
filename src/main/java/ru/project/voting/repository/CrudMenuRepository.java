package ru.project.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import ru.project.voting.model.Menu;
import ru.project.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
    @Override
    @Secured("ROLE_ADMIN")
    Menu save(Menu menu);

    @Override
    @Secured("ROLE_ADMIN")
    void deleteById(Integer id);

    @Transactional
    @Query("SELECT m FROM Menu m WHERE m.date=:date AND m.restaurant=:restaurant")
    Menu getMenuByDateAndRestaurant(@Param("date") LocalDate date, @Param("restaurant") Restaurant restaurant);


    @Query("SELECT m FROM Menu m WHERE m.restaurant=:restaurant")
    List<Menu> findByRestaurant(@Param("restaurant") Restaurant restaurant);

    @Transactional
    @Query("SELECT m FROM Menu m WHERE m.date=:date")
    List<Menu> findByDate(@Param("date") LocalDate date);

}
