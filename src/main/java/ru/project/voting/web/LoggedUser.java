package ru.project.voting.web;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.project.voting.model.User;

import static java.util.Objects.requireNonNull;


public class LoggedUser extends org.springframework.security.core.userdetails.User {

    private int id;


    public LoggedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.id = user.getId();
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof LoggedUser) ? (LoggedUser) principal : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int getLoggedUserId () {
        return get().getId();
    }

    public int getId() {
        return id;
    }

}
