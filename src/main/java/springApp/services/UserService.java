package springApp.services;

import springApp.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(long id);

    User update(User user, long id);

    User save(User newUser);

    void deleteById(long id);
}
