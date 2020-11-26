package ica.oose.domain;

import java.util.Date;

/**
 * Created by Kevin on 23-3-2017.
 */
public class Video extends Track {
    private int playCount;
    private Date publicationDate;
    private String description;

    public Video(int id, String performer, String title, String url, long duration, int playCount, Date publicationDate, String description, Availability availability) {
        super(id, performer, title, url, duration, availability);
        this.playCount = playCount;
        this.publicationDate = publicationDate;
        this.description = description;
    }
    public Video(String performer, String title, String url, long duration, int playCount, Date publicationDate, String description) {
        super(performer, title, url, duration);
        this.playCount = playCount;
        this.publicationDate = publicationDate;
        this.description = description;
    }

    public int getPlayCount() {
        return playCount;
    }


    public Date getPublicationDate() {
        return publicationDate;
    }


    public String getDescription() {
        return description;
    }

}
