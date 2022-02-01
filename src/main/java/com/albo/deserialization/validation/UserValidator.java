package com.albo.deserialization.validation;

import com.albo.deserialization.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (user.getName() == null) {
            errors.rejectValue("name", "Имя не заполнено", "Имя не заполнено");
        }
        if (user.getPassword() != null) {
            if (user.getPassword().length() < 10) {
                errors.rejectValue("password", "Слишком короткий пароль", "Слишком короткий пароль");
            }
        } else {
            errors.rejectValue("password", "Пароль не заполнен", "Пароль не заполнен");
        }
        if (user.getEmail() != null){
            String regex = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(user.getEmail());
            if (!matcher.matches()){
                errors.rejectValue("email","Email не валиден", "Email не валиден");
            }
        } else {
            errors.rejectValue("email","Email не заполнен", "Email не заполнен");
        }
    }
}
