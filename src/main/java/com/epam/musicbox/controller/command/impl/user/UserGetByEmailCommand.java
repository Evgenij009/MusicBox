package com.epam.musicbox.controller.command.impl.user;

import com.epam.musicbox.constant.PagePath;
import com.epam.musicbox.constant.Parameter;
import com.epam.musicbox.controller.command.Command;
import com.epam.musicbox.entity.User;
import com.epam.musicbox.exception.HttpException;
import com.epam.musicbox.service.UserService;
import com.epam.musicbox.util.Pages;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class UserGetByEmailCommand implements Command {
    @Inject
    protected UserService service;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws HttpException {
        String email = req.getParameter(Parameter.EMAIL);
        Optional<User> optionalUser = service.findByEmail(email);
        User user = optionalUser.orElse(null);
        req.setAttribute(Parameter.OBJECT, user);
        Pages.forward(req, resp, PagePath.USER);
    }
}
