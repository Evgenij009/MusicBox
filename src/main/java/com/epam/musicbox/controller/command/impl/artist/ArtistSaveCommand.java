package com.epam.musicbox.controller.command.impl.artist;

import com.epam.musicbox.constant.Parameter;
import com.epam.musicbox.controller.command.Command;
import com.epam.musicbox.entity.Artist;
import com.epam.musicbox.exception.HttpException;
import com.epam.musicbox.service.ArtistService;
import com.epam.musicbox.util.Parameters;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtistSaveCommand implements Command {
    @Inject
    private ArtistService artistService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws HttpException {
        Long artistId = Parameters.getNullableLong(req, Parameter.ARTIST_ID);
        String name = req.getParameter(Parameter.NAME);
        String picture = req.getParameter(Parameter.PICTURE);
        Artist artist = new Artist(artistId, name, picture);
        artistService.save(artist);
    }
}
