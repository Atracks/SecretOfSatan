package ru.bravery_and_stupidity.secretOfSatan.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.services.UserService;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public void addUser(@RequestBody UserDao user) {
        userService.addUser(user);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    @ResponseBody
    public void updateUser(@RequestBody UserDao user) {
        userService.updateUser(user);
    }

    @RequestMapping(value = "/getUser/{login}", method = RequestMethod.GET)
    @ResponseBody
    public UserDao getUser(@PathVariable("login") String login) {
        return userService.getUser(login);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDao> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/deleteUser/{login}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUser(@PathVariable("login") String login) {
        userService.deleteUser(login);
    }

    @RequestMapping(value = "/calculateTargets", method = RequestMethod.PUT)
    @ResponseBody
    public void calculateTargets() {
        userService.calculateTargets();
    }

    @RequestMapping(value = "/currentUserRole", method = RequestMethod.GET)
    @ResponseBody
    public List<GrantedAuthority> getCurrentUserRole() {
        return (List<GrantedAuthority>)SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities();
    }

    @RequestMapping(value = "/getCurrentUserLogin", method = RequestMethod.GET)
    @ResponseBody
    public String getCurrentUserLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        logger.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
