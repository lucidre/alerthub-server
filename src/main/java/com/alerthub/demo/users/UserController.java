
package com.alerthub.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import com.alerthub.demo.NetworkResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/v1/user")
@Tag(name = "User", description = "User APIs")
public class UserController {
    private final String fetchSuccessful = "Operation Successful";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "get_user/{uid}")
    @Operation(summary = "Get user details", description = "")
    public ResponseEntity<NetworkResult> getUserEvents(@PathVariable String uid) {
        User user = userService.getUser(uid);
        NetworkResult result = new NetworkResult(fetchSuccessful, user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping(path = "create_user")
    @Operation(summary = "Create user", description = "")
    public ResponseEntity<NetworkResult> createEvent(@RequestBody User user) {
        try {
            userService.createUser(user);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "update_user/{uid}")
    @Operation(summary = "Update user", description = "")
    public ResponseEntity<NetworkResult> editEvent(@PathVariable String uid, @RequestBody User user) {
        try {
            userService.editUser(uid, user);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "delete_user/{uid}")
    @Operation(summary = "Delete user", description = "")
    public ResponseEntity<NetworkResult> deleteEvent(@PathVariable String uid) {
        try {
            userService.deleteUser(uid);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
