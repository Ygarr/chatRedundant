package com.company.chat.validator;

import com.company.chat.controller.Chat;
import com.company.chat.service.AccountService;
import com.company.chat.service.PasswordEncryptor;
import com.company.chat.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 */
@Component
public class LogInValidator implements Validator {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserProfile.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserProfile user = (UserProfile) o;
        UserProfile account = accountService.getAccountByLogin(user.getNickname());
        if ("".equals(user.getNickname())) {
            errors.rejectValue("nickname", "error.nickname", "You should enter nickname");
        } else if ("".equals(user.getPassword())) {
            errors.rejectValue("password", "error.password", "You should enter password");
        } else if (account == null) {
            errors.rejectValue("nickname", "error.nickname", "There is no user with nickname " + user.getNickname());
        } else if (isLoggedIn(user)) {
            errors.rejectValue("nickname", "error.nickname", "User " + user.getNickname() + " is already logged in!");
        } else {
            passwordEncryptor.encryptPassword(user);
            if (!account.getPassword().equals(user.getPassword())) {
                errors.rejectValue("password", "error.password", "Wrong password!");
            }
        }
    }
    public boolean isLoggedIn(UserProfile user) {
        return Chat.getConnections().containsKey(user.getNickname());
    }
}