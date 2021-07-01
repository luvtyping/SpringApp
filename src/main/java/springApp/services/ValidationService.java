package springApp.services;

import springApp.exception.FormatDataException;
import springApp.model.User;
import springApp.services.handlers.DateFormatValidator;
import springApp.services.handlers.UserValidator;
import springApp.services.handlers.Validator;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private static final Validator validator = new UserValidator();
    private static final DateFormatValidator dateFormatValidator = new DateFormatValidator();

    public void validateUser(User user) throws FormatDataException {
        validator.validate(user);
        dateFormatValidator.validate(user.getBirthDate().toString());
    }
}
