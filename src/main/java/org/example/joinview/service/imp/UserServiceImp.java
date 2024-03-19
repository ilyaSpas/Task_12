package org.example.joinview.service.imp;

import org.example.joinview.entity.User;
import org.example.joinview.exception.user.UserNotFoundException;
import org.example.joinview.repository.UserRepository;
import org.example.joinview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User update(Long id, User user) {
        User userFromDB = getById(id);
        userFromDB.setName(user.getName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setOrders(user.getOrders());
        userFromDB.setDateOfUpdate(LocalDateTime.now());
        return userRepository.save(userFromDB);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
