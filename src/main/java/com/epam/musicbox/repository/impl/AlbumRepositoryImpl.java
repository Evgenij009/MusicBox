package com.epam.musicbox.repository.impl;

import com.epam.musicbox.entity.Album;
import com.epam.musicbox.exception.HttpException;
import com.epam.musicbox.repository.AlbumRepository;
import com.epam.musicbox.service.impl.AlbumServiceImpl;
import com.epam.musicbox.util.QueryHelper;

import java.util.List;
import java.util.Optional;

public class AlbumRepositoryImpl implements AlbumRepository {
    private static final String SQL_FIND_ALL = """
            SELECT *
            FROM albums
            ORDER BY name
            LIMIT ?,?""";

    private static final String SQL_FIND_BY_ID = """
            SELECT *
            FROM albums
            WHERE album_id=?""";

    private static final String SQL_INSERT_ONE = """
            INSERT INTO albums (name, picture)
            VALUES (?,?)""";

    private static final String SQL_UPDATE_ONE = """
            UPDATE albums (name, picture)
            SET name=? picture=?
            WHERE album_id=?""";

    private static final String SQL_DELETE_BY_ID = """
            DELETE FROM albums
            WHERE album_id=?""";

    private static final String SQL_FIND_BY_NAME = """
            SELECT *
            FROM albums
            WHERE name REGEXP (?)
            ORDER BY name
            LIMIT ?,?""";

    public static final AlbumRepositoryImpl instance = new AlbumRepositoryImpl();

    private final Album.Builder albumEntityBuilder = Album.Builder.getInstance();

    public static AlbumRepository getInstance() {
        return instance;
    }

    @Override
    public List<Album> findAll(int offset, int limit) throws HttpException {
        return QueryHelper.queryAll(SQL_FIND_ALL, albumEntityBuilder, offset, limit);
    }

    @Override
    public Optional<Album> findById(Long id) {
        return QueryHelper.queryOne(SQL_FIND_BY_ID, albumEntityBuilder, id);
    }

    @Override
    public long save(Album artist) throws HttpException {
        Long artistId = artist.getId();
        if (artistId == null) {
            return QueryHelper.insert(SQL_INSERT_ONE,
                    artist.getName(),
                    artist.getPicture());
        } else {
            QueryHelper.update(SQL_UPDATE_ONE,
                    artist.getName(),
                    artist.getPicture(),
                    artistId);
            return artistId;
        }
    }

    @Override
    public void deleteById(Long id) throws HttpException {
        QueryHelper.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public List<Album> findByName(String regex, int offset, int limit) throws HttpException {
        return QueryHelper.queryAll(SQL_FIND_BY_NAME, albumEntityBuilder, regex, offset, limit);
    }
}
