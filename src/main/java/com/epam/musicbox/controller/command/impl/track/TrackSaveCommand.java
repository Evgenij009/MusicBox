package com.epam.musicbox.controller.command.impl.track;

import com.epam.musicbox.constant.Parameter;
import com.epam.musicbox.controller.command.Command;
import com.epam.musicbox.entity.Track;
import com.epam.musicbox.exception.HttpException;
import com.epam.musicbox.service.TrackService;
import com.epam.musicbox.util.Parameters;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TrackSaveCommand implements Command {
    @Inject
    private TrackService trackService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws HttpException {
        Long trackId = Parameters.getNullableLong(req, Parameter.TRACK_ID);
        String name = req.getParameter(Parameter.NAME);
        String path = req.getParameter(Parameter.PICTURE);
        Long albumId = Parameters.getLong(req, Parameter.ALBUM_ID);
        Track track = new Track(trackId, name, path, albumId);
        trackService.save(track);
    }
}
