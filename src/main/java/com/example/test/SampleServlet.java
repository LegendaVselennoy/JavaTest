package com.example.test;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SampleServlet extends HttpServlet {
    public boolean isAuthenticated(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        if (session==null){
            return false;
        }
        String authenticationAttribute=
                (String) session.getAttribute("authenticated");
        return Boolean.parseBoolean(authenticationAttribute);
    }
}
