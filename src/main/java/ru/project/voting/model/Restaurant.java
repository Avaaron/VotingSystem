package ru.project.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "rest_unique")})
public class Restaurant extends AbstractNamedEntity{

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @NotNull
    private User user;

    public Restaurant(){
    }

    public Restaurant(Integer id, String name, User user) {
        super(id, name);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;
        if (!super.equals(o)) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUser());
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                '}';
    }
}