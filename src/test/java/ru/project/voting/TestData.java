package ru.project.voting;

import ru.project.voting.model.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.project.voting.model.AbstractBaseEntity.START_SEQ;

public class TestData {
    public static final int ADMIN_ID1 = START_SEQ;     //100000
    public static final int ADMIN_ID2 = START_SEQ + 1; //100001
    public static final int USER_ID1 = START_SEQ + 2;  //100002
    public static final int USER_ID2 = START_SEQ + 3;  //100003
    public static final int USER_ID3 = START_SEQ + 4;  //100004

    public static final LocalDate TEST_DATE_USER_2 = LocalDate.of(2018, 9, 19);
    public static final LocalDate TEST_DATE_USER_1 = LocalDate.of(2018, 9, 23);
    public static final LocalDate TEST_DATE_MENU_1 = LocalDate.of(2018, 9, 8);

    public static final LocalTime TEST_SEPARATED_TIME_MAX = LocalTime.of(23, 59, 59);
    public static final LocalTime TEST_SEPARATED_TIME_MIN = LocalTime.of(0, 0, 1);

    public static final User USER_1 = new User(USER_ID1, "User1", "user1@yandex.ru","password", Role.ROLE_USER);
    public static final User USER_2 = new User(USER_ID2, "User2", "user2@yandex.ru","password", Role.ROLE_USER);
    public static final User USER_3 = new User(USER_ID3, "User3", "user3@yandex.ru","password", Role.ROLE_USER);
    public static final User ADMIN_1 = new User(ADMIN_ID1, "Admin1", "admin1@gmail.com","admin", Role.ROLE_ADMIN);
    public static final User ADMIN_2 = new User(ADMIN_ID2, "Admin2", "admin2@gmail.com","admin", Role.ROLE_ADMIN);

    public static final int REST_ID1 = 100005;
    public static final int REST_ID2 = 100006;
    public static final int REST_ID_CREATED = 100019;
    public static final Restaurant RESTAURANT_1 = new Restaurant(REST_ID1, "Roga and Kopita", ADMIN_1);
    public static final Restaurant RESTAURANT_2 = new Restaurant(REST_ID2, "Pupkin obshepit", ADMIN_2);


    public static final int MENU_ID1 = 100007;
    public static final int MENU_ID2 = 100008;
    public static final Menu MENU_1 = new Menu(MENU_ID1, RESTAURANT_1, TEST_DATE_USER_2);
    public static final Menu MENU_2 = new Menu(MENU_ID2, RESTAURANT_2, TEST_DATE_USER_1);

    public static final int DISH_ID1 = 100009;
    public static final int DISH_ID2 = 100010;
    public static final int DISH_ID3 = 100011;
    public static final int DISH_ID4 = 100012;
    public static final int DISH_ID5 = 100013;

    public static final Dish DISH_1 = getDishWithMenu(new Dish(DISH_ID1, "Meat", 150), MENU_1);
    public static final Dish DISH_2 = getDishWithMenu(new Dish(DISH_ID2, "Borsh", 333), MENU_1);
    public static final Dish DISH_3 = getDishWithMenu(new Dish(DISH_ID3, "Soup", 22), MENU_1);
    public static final Dish DISH_4 = getDishWithMenu(new Dish(DISH_ID4, "Egg", 111), MENU_1);
    public static final Dish DISH_5 = getDishWithMenu(new Dish(DISH_ID5, "cheese soup", 22), MENU_2);


    public static final int VOTE_ID1 = 100017;
    public static final int VOTE_ID2 = 100018;
    public static final Vote VOTE_1 = getVote1WithId(new Vote(USER_2, MENU_1, TEST_DATE_USER_2), VOTE_ID1);
    public static final Vote VOTE_2 = getVote1WithId(new Vote(USER_1, MENU_2, TEST_DATE_USER_1), VOTE_ID2);


    public TestData() {
    }

    public static Dish getDishWithMenu(Dish dish, Menu menu){
        dish.setMenu(menu);
        return dish;
    }

    public static Dish getCreatedDish() {
        Dish created = new Dish(null, "created", 100);
        return created;
    }

    public static Dish getUpdatedDish() {
        Dish updated = new Dish(100015, "update", 1000);
        return updated;
    }

    public static Menu getCreatedMenu() {
        Menu created = new Menu(100019, RESTAURANT_1, LocalDate.now());
        return created;
    }

    public static final int NEW_ID = 100019;
    public static Restaurant getCreatedRestaurant() {
        Restaurant rest = new Restaurant(null, "Created", null);
        return  rest;
    }

    public static Restaurant getUpdatedRestaurant() {
        Restaurant restaurant = RESTAURANT_1;
        restaurant.setName("updated");
        return restaurant;
    }

    public static Vote getVote1WithId(Vote vote, int id) {
        vote.setId(id);
        return vote;
    }

    public static Vote getUpdated(){
        Vote upd = new Vote(USER_1, MENU_1, TEST_DATE_USER_1);
        upd.setId(VOTE_ID2);
        return upd;
    }

    public static Vote getCreated() {
        Vote vote = new Vote(USER_3, MENU_2, LocalDate.now());
        vote.setId(100019);
        return vote;
    }
}
