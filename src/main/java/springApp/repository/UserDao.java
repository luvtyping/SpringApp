package springApp.repository;

import springApp.model.User;

import java.util.List;

public interface UserDao {
    User create(User user);

    User update(User user);

    void remove(User user);

    List<User> findAll();

    User findById(Long id);
}