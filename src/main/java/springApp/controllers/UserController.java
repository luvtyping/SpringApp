package springApp.controllers;

import springApp.exception.FormatDataException;
import springApp.model.User;
import springApp.services.UserServiceImpl;
import springApp.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ValidationService validationService;
    private final UserServiceImpl userService;

    @Autowired
    public UserController(ValidationService validationService, UserServiceImpl userService) {
        this.validationService = validationService;
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody User newUser) {
        try {
            validationService.validateUser(newUser);
        } catch (FormatDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable long id, @RequestBody User user) {
        try {
            validationService.validateUser(user);
        } catch (FormatDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.deleteById(id);
    }
}
