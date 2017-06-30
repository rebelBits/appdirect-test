package com.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OpenIdLoginController {

    @RequestMapping("/login")
    public String subscriptions(final Model model) {
        return "login";
    }
}
