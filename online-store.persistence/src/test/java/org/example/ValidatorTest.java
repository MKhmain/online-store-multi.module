package org.example;

import org.example.entities.User;
import org.example.entities.impl.DefaultUser;
import org.example.utils.validator.Validator;
import org.example.utils.validator.impl.DefaultValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private Validator validator;
    private User user;
    @BeforeEach
    void setUp(){
        validator=new DefaultValidator();
    }
    @Test
    void shouldValidateName(){
        user=new DefaultUser("Mukhammadjon","Kholmukhamedov","fdgdsh","test@gmail.com");
        assertTrue(validator.isValid(user));
    }
}
