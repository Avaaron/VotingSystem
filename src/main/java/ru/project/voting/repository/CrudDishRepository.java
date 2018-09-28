package ru.project.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import ru.project.voting.model.Dish;
import ru.project.voting.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {


    @Transactional
    @Query("SELECT d FROM Dish d WHERE d.menu.date=:date")
    List<Dish> findByDate(@Param("date")  LocalDate date);

    @Transactional
    @Query("SELECT d FROM Dish d WHERE d.menu=:menu")
    List<Dish> findByMenu(@Param("menu") Menu menu);

    @Transactional
    @Override
    @Secured("ROLE_ADMIN")
    Dish save(Dish entity);

    @Transactional
    @Override
    @Secured("ROLE_ADMIN")
    void deleteById(Integer id);
}
