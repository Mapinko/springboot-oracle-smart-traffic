package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.dto.UserDTO;
import br.com.fiap.smarttraffic.model.User;
import br.com.fiap.smarttraffic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    // CREATE USER
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = mapToUser(userDTO);
        User createdUser = service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToUserDTO(createdUser));
    }

    // GET USER BY ID
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = service.findUserbyId(id);
        return ResponseEntity.ok(mapToUserDTO(user));
    }

    // GET USER BY EMAIL
    @GetMapping("/users/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        User user = service.findUserbyEmail(email);
        return ResponseEntity.ok(mapToUserDTO(user));
    }

    // GET USER BY USERNAME
    @GetMapping("/users/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        User user = service.findUserbyUsername(username);
        return ResponseEntity.ok(mapToUserDTO(user));
    }

    // GET ALL USERS
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users.stream().map(this::mapToUserDTO).collect(Collectors.toList()));
    }

    // UPDATE USER BY ID
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        User updatedUser = mapToUser(updatedUserDTO);
        updatedUser = service.updateUser(id, updatedUser);
        return ResponseEntity.ok(mapToUserDTO(updatedUser));
    }

    // DELETE USER
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping methods
    private User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setDeviceId(userDTO.getDeviceId());
        user.setPushNotificationEnabled(userDTO.isPushNotificationEnabled());
        return user;
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setDeviceId(user.getDeviceId());
        userDTO.setPushNotificationEnabled(user.isPushNotificationEnabled());
        return userDTO;
    }
}