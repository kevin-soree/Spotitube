package ica.oose.service;

import ica.oose.datasource.dao.IPlaylistDAO;
import ica.oose.domain.Playlist;
import ica.oose.domain.Track;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 6-4-2017.
 */
@Alternative
public class localPlaylistDAO implements IPlaylistDAO { // NFN05
    private List<Playlist> playlists = new ArrayList<>();

    public localPlaylistDAO() {
        playlists.add(new Playlist(1, "kevin", "myplaylist", 342, new ArrayList<Track>()));
        playlists.add(new Playlist(2, "kevin", "andereplaylist",32, new ArrayList<Track>()));
        playlists.add(new Playlist(3, "kevin", "blabla",324, new ArrayList<Track>()));
        playlists.add(new Playlist(4,"kevin", "appelplaylist",34, new ArrayList<Track>()));
    }

    @Override
    public List findByOwner(String owner) {
        return playlists;
    }

    @Override
    public Playlist getPlayList(int playlistID) {
        return playlists.get(playlistID-1);
    }

    @Override
    public void delete(int PlaylistID) {
        playlists.remove(playlists);
    }

    @Override
    public void create(Playlist playlist) {
        playlists.add(playlist);
    }

    @Override
    public void changeName(Playlist playlist) {
        playlists.get(playlist.getID()-1).changeName(playlist.getName());
    }
}
