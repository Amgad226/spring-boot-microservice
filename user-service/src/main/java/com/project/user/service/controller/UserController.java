package com.project.user.service.controller;


import com.project.user.service.dto.UserResponse;
import com.project.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        Optional<UserResponse> userResponseOptional = userService.getUserById(id);
        if (userResponseOptional.isPresent()) {
            return ResponseEntity.ok(userResponseOptional.get());
        } else {
            return ResponseEntity.ok(userService.dummyUser());
//            return ResponseEntity.notFound().build();
        }
    }

}
