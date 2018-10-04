package ru.project.voting.repository.cruds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.project.voting.model.Menu;
import ru.project.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
    @Override
    Menu save(Menu menu);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Query("SELECT m FROM Menu m WHERE m.date=:date AND m.restaurant=:restaurant")
    Optional<Menu> getMenuByDateAndRestaurant(@Param("date") LocalDate date, @Param("restaurant") Restaurant restaurant);


    @Query("SELECT m FROM Menu m WHERE m.restaurant=:restaurant")
    List<Menu> findByRestaurant(@Param("restaurant") Restaurant restaurant);

    @Transactional
    @Query("SELECT m FROM Menu m WHERE m.date=:date")
    List<Menu> findByDate(@Param("date") LocalDate date);

    @Override
    Optional<Menu> findById(Integer id);

}
