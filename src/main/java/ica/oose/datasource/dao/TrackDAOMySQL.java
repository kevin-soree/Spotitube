package ica.oose.datasource.dao;

import ica.oose.datasource.DBConnectionFactory;
import ica.oose.datasource.DBServer;
import ica.oose.domain.Availability;
import ica.oose.domain.Song;
import ica.oose.domain.Track;
import ica.oose.domain.Video;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 23-3-2017.
 */
@Default
public class TrackDAOMySQL implements ITrackDAO {
    private DBConnectionFactory databaseProperties = new DBConnectionFactory();
    private static final DBServer SERVER = DBServer.MYSQL;
    @Override
    public List list() {
        List<Track> tracks = new ArrayList<>();
        try (
                Connection connection = databaseProperties.getConnection(SERVER);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM track");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                tracks.add(createTrack(resultSet, false));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }
    @Override
    public List findByTitle(String searchTerm) { //FN03
        List<Track> tracks = new ArrayList<>();
        try (
                Connection connection = databaseProperties.getConnection(SERVER);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM track WHERE LOWER(title) LIKE ?");
                //PreparedStatement availabilityStatement = connection.prepareStatement("SELECT * FROM availability WHERE id = ?");

        ) {
            statement.setString(1, "%"+searchTerm+"%");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
//                availabilityStatement.setInt(1, resultSet.getInt("availabilityID"));
//                ResultSet availabilityResultSet = availabilityStatement.executeQuery();
//                Availability availability = null;
//                while(availabilityResultSet.next()) {
//                    availability = new Availability(resultSet.getBoolean("offlineAvailability"));
//                }
                tracks.add(createTrack(resultSet, false));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }
    @Override
    public void addTrackToPlaylist(int playlistID, int trackID) { //FN02
        try (
                Connection connection = databaseProperties.getConnection(SERVER);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO playlist_track (playlistID, trackID, availabilityID) values (?, ?, ?)");
                PreparedStatement availabilityStatement = connection.prepareStatement("INSERT INTO availability (offlineAvailability) VALUES (?)");
                PreparedStatement getavailabilityID = connection.prepareStatement("SELECT MAX(id) as id FROM availability");
        ) {
            availabilityStatement.setInt(1, 0);
            availabilityStatement.execute();
            ResultSet availabilityResult = getavailabilityID.executeQuery();
            int availabilityID = 0;
            while (availabilityResult.next()) {
                availabilityID = availabilityResult.getInt("id");
            }
            statement.setInt(1, playlistID);
            statement.setInt(2, trackID);
            statement.setInt(3, availabilityID);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Track createTrack(ResultSet resultSet, boolean fromPlaylist) throws SQLException { //FN01
        Availability availability;
        if(fromPlaylist)
        {
            availability = new Availability(resultSet.getBoolean("offlineAvailability"));
        }
        else availability = new Availability(false);
        if(resultSet.getString("album").length() > 0) {
             return new Song(resultSet.getInt("id"), resultSet.getString("performer"), resultSet.getString("title"), resultSet.getString("url"), resultSet.getLong("duration"), resultSet.getString("album"), availability);
        }
        else {
             return new Video(resultSet.getInt("id"), resultSet.getString("performer"), resultSet.getString("title"), resultSet.getString("url"), resultSet.getLong("duration"), resultSet.getInt("playcount"), resultSet.getDate("publicationDate"), resultSet.getString("description"),availability);
        }
    }

    @Override
    public Track getTrack(int id) { //FN01
        Track track = null;
        try (
                Connection connection = databaseProperties.getConnection(SERVER);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM track t join availability a on a.trackID = t.id  WHERE t.id = ?");
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                track = createTrack(resultSet, true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return track;
    }
}
