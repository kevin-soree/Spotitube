package ica.oose.domain;

import java.util.ArrayList;

/**
 * Created by Kevin on 23-3-2017.
 */
public class Playlist {
    public Playlist(int id, String owner, String name, long totalLength, ArrayList<Track> tracks) {
        this.ID = id;
        this.owner = owner;
        this.name = name;
        this.tracks = tracks;
        this.totalLength = totalLength;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public int getID() {
        return ID;
    }

    private int ID;
    private String owner;
    private String name;
    private ArrayList<Track> tracks;
    private long totalLength;

    public Playlist(String owner, String name, ArrayList<Track> tracks) {
        this.owner = owner;
        this.name = name;
        this.tracks = tracks;
        calcTotalLength();
    }
    public void addTrack(Track track) {
        tracks.add(track);
        this.totalLength += track.getDuration();
    }
    public void changeName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    private void calcTotalLength() {
        for (Track track : tracks) {
            this.totalLength += track.getDuration();
        }

    }

    public long getTotalLength() {
        return totalLength;
    }
}
