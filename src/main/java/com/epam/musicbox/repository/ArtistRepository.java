package com.epam.musicbox.repository;

import com.epam.musicbox.exception.HttpException;
import com.epam.musicbox.entity.Artist;
import com.epam.musicbox.entity.Track;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends Repository<Artist> {
    List<Artist> findByName(String regex, int offset, int limit) throws HttpException;

    List<Track> getTracks(Long artistId, int offset, int limit) throws HttpException;

    void addTrack(Long artistId, Long trackId) throws HttpException;

    void removeTrack(Long artistId, Long trackId) throws HttpException;
}
