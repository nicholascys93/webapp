package com.stb.sample.validator;

import com.stb.sample.model.User;
import com.stb.sample.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private String userName = "username";
	
	@Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, userName, "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue(userName, "Size.userForm.username");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "NotEmpty");
        if (user.getPass().length() < 8 || user.getPass().length() > 32) {
            errors.rejectValue("pass", "Size.userForm.password");
        }

        if (!user.getPassConfirm().equals(user.getPass())) {
            errors.rejectValue("passConfirm", "Diff.userForm.passwordConfirm");
        }
        
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue(userName, "Duplicate.userForm.username");
        }
        
        
        
    }
}
