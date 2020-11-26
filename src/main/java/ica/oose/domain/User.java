package ica.oose.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 30-3-2017.
 */
public class User {
    public String getUsername() {
        return username;
    }
    private String username;

    public User(String username) {
        this.username = username;
    }

    /*public User(String username, List<Playlist> playlists) {
        this.username = username;
        this.playlists = playlists;
    }*/
}
