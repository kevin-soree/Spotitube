package ica.oose.domain;

/**
 * Created by Kevin on 23-3-2017.
 */
public class Song extends Track {
    private String album;

    public Song(int id, String performer, String title, String url, long duration, String album, Availability availability) {
        super(id, performer, title, url, duration, availability);
        this.album = album;
    }
    public Song(String performer, String title, String url, long duration, String album) {
        super(performer, title, url, duration);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }
}
