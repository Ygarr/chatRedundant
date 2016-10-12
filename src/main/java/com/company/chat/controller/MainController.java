package com.company.chat.controller;

import com.company.chat.model.UserProfile;
import com.company.chat.service.AccountService;
import com.company.chat.service.PasswordEncryptor;
import com.company.chat.validator.LogInValidator;
import com.company.chat.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 17.02.2016.
 */
@Controller
public class MainController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private LogInValidator logInValidator;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @RequestMapping("/")
    public ModelAndView index(HttpSession session) {
        UserProfile user = accountService.getAccountBySession(session.getId());
        if (user == null || logInValidator.isLoggedIn(user)) {
            return new ModelAndView("index", "user", new UserProfile());
        }
        return new ModelAndView("chat", "user", user);
    }

    @RequestMapping(value = "chat", method = RequestMethod.POST)
    public ModelAndView chat(HttpSession session, @ModelAttribute("user") UserProfile user, BindingResult bindingResult) {
        logInValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("index", "user", user);
        }
        accountService.addSessionForUser(user, session.getId());
        return new ModelAndView("chat", "user", user);
    }

    @RequestMapping(value = "regForm")
    public ModelAndView registration() {
        return new ModelAndView("regform", "user", new UserProfile());
    }

    @RequestMapping(value = "logout")
    public String logOut(HttpSession session) {
        accountService.deleteSession(session.getId());
        return "redirect:/";
    }

    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    public ModelAndView sigUp(HttpSession session, @ModelAttribute("user") UserProfile user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("regform", "user", user);
        }
        user.setConfirmPassword(null);
        passwordEncryptor.encryptPassword(user);
        accountService.addAccount(user);
        accountService.addSessionForUser(user, session.getId());
        return new ModelAndView("chat", "user", user);
    }
}