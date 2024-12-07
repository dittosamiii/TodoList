package com.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    @GetMapping("/")
    public String goToDefaultPage() {
        return "login";
    }

    @GetMapping("/login")
    public String goToLoginPage() {
        return "login";
    }

    @GetMapping("/welcome")
    public String goToWelcomePage(ModelMap model) {
        model.put("name", getLoggedInUser());
        return "welcome";
    }

    public String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
