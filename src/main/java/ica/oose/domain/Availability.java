package ica.oose.domain;

/**
 * Created by Kevin on 23-3-2017.
 */
public class Availability {
    private int id;
    private boolean offlineAvailability;

    public Availability(boolean offlineAvailability) {
        this.offlineAvailability = offlineAvailability;
    }
    public void toggle(){
        this.offlineAvailability = !offlineAvailability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOfflineAvailability() {
        return offlineAvailability;
    }
}
