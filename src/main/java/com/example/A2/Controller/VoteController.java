package com.example.A2.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

import com.example.A2.PollManager;
import com.example.A2.Components.User;
import com.example.A2.Components.Vote;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
    
    private final PollManager pollManager;

    public VoteController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @GetMapping
    public Set<User> getVotes() {
        return pollManager.getVotes();
    }

    @PostMapping
    public  void createVote(Vote v) {
        pollManager.addVote(v);
    }

    @DeleteMapping("/{vote}")
    public void deleteVote(@PathVariable String vote) {
        pollManager.deleteVote(vote);
    }

    @PutMapping("/{username}")
    public void updateVote(@PathVariable String username, Vote newVote) {
        User existingUser = pollManager.getUser(username);
        if (existingUser != null) {
            pollManager.updateVote(existingUser, newVote);
        }
        else {
            // If the user does not exist, create a new user
            pollManager.addUser(new User());
        }
}
