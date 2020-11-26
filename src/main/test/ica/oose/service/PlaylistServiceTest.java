package ica.oose.service;

import ica.oose.domain.Playlist;
import ica.oose.domain.Track;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Kevin on 5-4-2017.
 */
public class PlaylistServiceTest { //NFN05

    @Inject
    private PlaylistService playlistService;

    @Before
    public void setUp() {
        playlistService = new PlaylistService();
        playlistService.playlistDAO = new localPlaylistDAO();
    }

    @Test
    public void createPlaylist() throws Exception {
        String expected = "testplaylist";
        String owner = "kevin";
        playlistService.createPlaylist(new Playlist(owner,expected,new ArrayList<Track>()));
        List<Playlist> playlists = playlistService.getPlaylists(owner);
        Playlist playlist = playlists.get(playlists.size()-1);
        assertEquals(expected, playlist.getName());
    }

    @Test
    public void update() throws Exception {
        String expected = "othertestplaylistname";
        int playlistID = 1;
        playlistService.update(new Playlist(playlistID, "kevin", expected, 0, new ArrayList<Track>()));
        Playlist playlist = playlistService.getPlaylist(playlistID);
        assertEquals(expected, playlist.getName());
    }
}