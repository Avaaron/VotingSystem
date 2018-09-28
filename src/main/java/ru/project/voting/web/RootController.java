package ru.project.voting.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.project.voting.model.Restaurant;

import java.util.List;

@Controller
public class RootController {

    @GetMapping
    public String start() {
        return "bgn";
    }
}
