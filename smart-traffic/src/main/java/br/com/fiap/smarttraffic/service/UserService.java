package br.com.fiap.smarttraffic.service;

import br.com.fiap.smarttraffic.model.User;
import br.com.fiap.smarttraffic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // CREATE USER
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // GET USER BY ID
    public User findUserbyId(Long id) {
        Optional<User> OptionalUser = userRepository.findById(id);

        if (OptionalUser.isPresent()) {
            return OptionalUser.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // GET USER BY EMAIL
    public User findUserbyEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // GET USER BY USERNAME
    public User findUserbyUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User
            userToUpdate = existingUser.get();
            // Update specific fields based on updatedUser object
            userToUpdate.setUsername(updatedUser.getUsername()); // Update username
            userToUpdate.setEmail(updatedUser.getEmail());   // Update email (or any other fields)
            return userRepository.save(userToUpdate);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // DELETE USERS
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
