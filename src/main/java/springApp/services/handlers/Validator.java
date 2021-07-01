package springApp.services.handlers;

import springApp.exception.FormatDataException;
import springApp.model.User;

public interface Validator {
    void validate(User user) throws FormatDataException;

    void setNext(Validator validator);
}
