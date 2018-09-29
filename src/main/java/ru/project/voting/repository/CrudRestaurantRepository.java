package ru.project.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import ru.project.voting.model.Restaurant;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findById(int id);

    @Override
    @Secured("ROLE_ADMIN")
    Restaurant save(Restaurant restaurant);

    @Transactional(readOnly = true)
    @Query("SELECT r FROM Restaurant r WHERE r.id=:id and r.user.id =:userId")
    Restaurant getWithUser(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT r FROM  Restaurant  r WHERE r.id=:id")
    Optional<Restaurant> getById(@Param("id") int id);

    @Override
    List<Restaurant> findAll();
}
