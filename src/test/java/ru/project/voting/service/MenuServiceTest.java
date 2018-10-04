package ru.project.voting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.project.voting.model.Menu;
import ru.project.voting.repository.cruds.CrudMenuRepository;
import ru.project.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ru.project.voting.TestData.*;

class MenuServiceTest extends AbstractServiceTest{

    @Autowired
    private MenuService menuService;

    @Autowired
    private CrudMenuRepository crudMenuRepository;

    @Test
    void create() {
        Menu created = new Menu(null, RESTAURANT_1, LocalDate.now());
        Menu expected = menuService.create(created);
        created.setId(NEW_ID);
        assertEquals(crudMenuRepository.findAll(), Arrays.asList(MENU_1, MENU_2, created));
    }

    @Test
    void delete() {
        menuService.delete(MENU_ID1, ADMIN_ID1);
        assertEquals(crudMenuRepository.findAll(), Arrays.asList(MENU_2));
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> menuService.delete(MENU_ID1, ADMIN_ID2));
    }
}