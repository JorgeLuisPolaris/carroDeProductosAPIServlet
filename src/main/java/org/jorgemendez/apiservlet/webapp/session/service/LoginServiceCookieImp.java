package org.jorgemendez.apiservlet.webapp.session.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

//@ApplicationScoped
//@Alternative
public class LoginServiceCookieImp implements LoginService{
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies()!= null? req.getCookies():new Cookie[0];
        return  Arrays.stream(cookies)
                .filter(c->c.getName().equals("username"))
                .map(Cookie::getValue).findFirst();
    }
}
