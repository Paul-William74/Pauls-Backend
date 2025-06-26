package com.example.notes.Controller;

import com.example.notes.DTOs.User.RegisteringUser;
import com.example.notes.Model.User.User;
import com.example.notes.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling user-related HTTP requests.
 * <p>
 * Provides endpoints for user registration and login.
 * </p>
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Service containing user-related business logic.
     */
    @Autowired
    private UserService userService;

    /**
     * Registers a new user.
     *
     * @return a response entity with the registration result
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody final RegisteringUser user) {
        return this.userService.register(user);
    }

    /**
     * Logs in a user.
     *
     * @return a response entity with the login result
     */
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return this.userService.login(user);
    }
}