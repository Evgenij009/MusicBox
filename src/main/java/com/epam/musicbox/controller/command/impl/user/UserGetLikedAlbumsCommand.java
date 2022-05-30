package com.epam.musicbox.controller.command.impl.user;

import com.epam.musicbox.constant.PagePath;
import com.epam.musicbox.constant.Parameter;
import com.epam.musicbox.controller.command.Command;
import com.epam.musicbox.entity.Album;
import com.epam.musicbox.exception.HttpException;
import com.epam.musicbox.service.UserService;
import com.epam.musicbox.util.Pages;
import com.epam.musicbox.util.Parameters;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class UserGetLikedAlbumsCommand implements Command {
    @Inject
    protected UserService service;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws HttpException {
        long userId = Parameters.getLong(req, Parameter.USER_ID);
        int page = Parameters.getInt(req, Parameter.PAGE);
        List<Album> list = service.getLikedAlbums(userId, page);
        req.setAttribute(Parameter.LIST, list);
        Pages.forward(req, resp, PagePath.ALBUM);
    }
}
