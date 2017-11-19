package ru.bravery_and_stupidity.secretOfSatan.security;


import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.bravery_and_stupidity.secretOfSatan.model.User;
import ru.bravery_and_stupidity.secretOfSatan.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
final public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(SecurityUserDetailsService.class);

    @Autowired
    private UserRepository repository;

    @NotNull
    @Override
    public UserDetails loadUserByUsername(String login) throws IllegalArgumentException {
        User user = repository.getUser(login);
        Assert.notNull(user,"user with login: " + login + " not found");
        LOGGER.info("Found user in database: " + user);
        return new org.springframework.security.core.userdetails.User(login, user.getPassword(), setUserRole(user.isAdmin()));
    }

    private List<GrantedAuthority>  setUserRole(boolean isAdmin) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = "ROLE_USER";
        if (isAdmin) {
            role = "ROLE_ADMIN";
        }
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
