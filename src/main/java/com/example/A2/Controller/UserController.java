package com.example.A2.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

import com.example.A2.PollManager;
import com.example.A2.Components.User;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final PollManager pollManager;
    private static final Logger logger = LoggerFactory.getLogger(PollManager.class);

    public UserController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @GetMapping
    public Collection<User> getUsers() {
        logger.info("getting users");
        return pollManager.getUsers();
    }

    @PostMapping
    public  void createUser(@RequestBody User user) {
        pollManager.addUser(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable UUID userId) {
        return pollManager.getUser(userId);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable UUID userId) {
        pollManager.deleteUser(userId);
    }

    @PutMapping("/{username}")
    public void updateUser(@PathVariable UUID userId, @RequestBody User newUser) {
        User existingUser = pollManager.getUser(userId);

        if (existingUser != null) {
            logger.info("User exists, updating user");
            existingUser.setName(newUser.getName());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setPassword(newUser.getPassword());

            pollManager.updateUser(userId, existingUser);
        }
        else {
            // If the user does not exist, create a new user
            logger.info("User does not exist, creating new user");
            pollManager.addUser(newUser);
        }

    }



}
