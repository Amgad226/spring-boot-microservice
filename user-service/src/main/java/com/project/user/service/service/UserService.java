package com.project.user.service.service;

import com.project.user.service.dto.UserResponse;
import com.project.user.service.model.User;
import com.project.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponse).toList();
    }
    public Optional<UserResponse> getUserById(Long id) {
        try{

       return userRepository.findById(id)
                .map(this::mapToUserResponse);

        }
    catch (Throwable e ){
        return Optional.ofNullable(dummyUser());

    }
    }


    public UserResponse dummyUser() {
        return UserResponse.builder()
                .id(0)
                .name("dummyUser")
                .role(null)
                .build();
    }
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}

