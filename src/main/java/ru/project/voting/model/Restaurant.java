package ru.project.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "rest_unique")})
public class Restaurant extends AbstractNamedEntity{


    public Restaurant(){
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                '}';
    }
}