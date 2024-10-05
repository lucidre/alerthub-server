
package com.alerthub.demo.users;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository eventRepository) {
        this.userRepository = eventRepository;
    }
    public Optional<User> getOptionalUser(String uid) {
        if (uid == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        Optional<User> optionalUser = userRepository.findByUserId(uid);
        return optionalUser;
    }
    public User getUser(String uid) {
        if (uid == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        Optional<User> optionalUser = userRepository.findByUserId(uid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user;
        } else {
            throw new IllegalStateException("Event not found");
        }
    }

    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        user.setMongoId(null);
        userRepository.save(user);
    }

    public void editUser(String uid, User user) {
        if (uid == null || user == null) {
            throw new IllegalArgumentException("User ID, and User must not be null");
        }

        Optional<User> optionalUser = userRepository.findByUserId(uid);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            user.setMongoId(existingUser.getMongoId());
            user.setUserId(existingUser.getUserId());

            userRepository.save(user);

        } else {
            throw new IllegalStateException("Event not found");
        }
    }

    public void deleteUser(String uid) {
        if (uid == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        Optional<User> optionalUser = userRepository.findByUserId(uid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // userRepository.deleteById(user.getMongoId()); 
        
        } else {
            throw new IllegalStateException("Event not found");
        }
    }

}