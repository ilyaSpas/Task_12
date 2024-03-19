package org.example.joinview.service;

import org.example.joinview.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    User save(User user);

    User getById(Long id);

    List<User> getAll();

    User update(Long id, User user);

    void deleteById(Long id);
}
