package ica.oose.datasource.dao;

import ica.oose.domain.Track;

import java.util.List;

/**
 * Created by Kevin on 28-3-2017.
 */
public interface ITrackDAO {
    List list();

    List findByTitle(String searchTerm);

    void addTrackToPlaylist(int playlistID, int trackID);

    Track getTrack(int id);
}
