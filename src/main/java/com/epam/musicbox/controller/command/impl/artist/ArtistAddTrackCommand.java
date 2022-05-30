package com.epam.musicbox.controller.command.impl.artist;

import com.epam.musicbox.constant.Parameter;
import com.epam.musicbox.controller.command.Command;
import com.epam.musicbox.exception.HttpException;
import com.epam.musicbox.service.ArtistService;
import com.epam.musicbox.util.Parameters;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtistAddTrackCommand implements Command {
    @Inject
    private ArtistService artistService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws HttpException {
        long userId = Parameters.getLong(req, Parameter.USER_ID);
        long trackId = Parameters.getLong(req, Parameter.TRACK_ID);
        artistService.addTrack(userId, trackId);
    }
}
