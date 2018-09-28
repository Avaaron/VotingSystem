package ru.project.voting.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.voting.model.User;
import ru.project.voting.repository.CrudUserRepository;
import ru.project.voting.web.LoggedUser;;

@Service("userService")
public class UserService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public UserService(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public LoggedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("Authenticating {}", email);
        User user = crudUserRepository.findByEmail(email.trim().toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new LoggedUser(user);
    }


}
