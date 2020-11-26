package ica.oose.domain;

/**
 * Created by Kevin on 23-3-2017.
 */
public abstract class Track {
    private int ID;
    private String performer;
    private String title;
    private String url;
    private long duration;
    private Availability availability;

    public Track(String performer, String title, String url, long duration) {
        this.performer = performer;
        this.title = title;
        this.url = url;
        this.duration = duration;
    }
    public Track(int id, String performer, String title, String url, long duration, Availability availability) {
        this.ID = id;
        this.performer = performer;
        this.title = title;
        this.url = url;
        this.duration = duration;
        this.availability = availability;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getID() {
        return ID;
    }

    public Availability getAvailability() {
        return availability;
    }
}
