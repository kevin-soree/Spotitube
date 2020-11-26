package ica.oose.datasource.dao;

import ica.oose.domain.Playlist;

import java.util.List;

/**
 * Created by Kevin on 28-3-2017.
 */
public interface IPlaylistDAO {
    //List list();

    List findByOwner(String owner);

    Playlist getPlayList(int playlistID);

   // void save(Playlist playlist);

    void delete(int PlaylistID);

    void create(Playlist playlist);

    void changeName(Playlist playlist);
}
