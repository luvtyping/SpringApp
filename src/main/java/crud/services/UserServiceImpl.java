package crud.services;

import crud.model.User;
import crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(long id) {
        return repository.findById(id).orElse(new User());
    }

    public void update(User user, long id) {
        repository.findById(id)
                .map(u -> {
                    u.setEmail(user.getEmail());
                    u.setFirstName(user.getFirstName());
                    u.setLastName(user.getLastName());
                    u.setBirthDate(user.getBirthDate());
                    return repository.save(u);
                }).orElseGet(() -> {
            user.setId(id);
            return repository.save(user);
        });
    }

    public User save(User newUser) {
        return repository.save(newUser);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
