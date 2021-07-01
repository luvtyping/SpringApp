package springApp.services;

import springApp.model.User;
import springApp.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findById(long id) {
        return userDao.findById(id);
    }

    public User update(User user, long id) {
       return userDao.update(user);
    }

    public User save(User newUser) {
        return userDao.create(newUser);
    }

    public void deleteById(long id) {
        userDao.remove(findById(id));
    }
}
