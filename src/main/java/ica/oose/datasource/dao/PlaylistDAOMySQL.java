package ica.oose.datasource.dao;

import ica.oose.datasource.DBConnectionFactory;
import ica.oose.datasource.DBServer;
import ica.oose.domain.*;

import javax.enterprise.inject.Default;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 23-3-2017.
 */

@Default
public class PlaylistDAOMySQL implements IPlaylistDAO { //NFN10
    private DBConnectionFactory databaseProperties = new DBConnectionFactory();
    private static final DBServer SERVER = DBServer.MYSQL;

 /*   @Override
    public List list() {
        List<Playlist> playlists = new ArrayList<>();
        try (
                Connection connection = databaseProperties.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * from playlist");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                playlists.add(new Playlist(resultSet.getString("owner"), resultSet.getString("name"), new ArrayList<Track>()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }*/
    @Override
    public List findByOwner(String owner) { //FN01
        List<Playlist> playlists = new ArrayList<>();
        try (
                Connection connection = databaseProperties.getConnection(SERVER);
                PreparedStatement playlistStatement = connection.prepareStatement("SELECT * from playlist where owner = ?");
                ) {
            playlistStatement.setString(1, owner);
            ResultSet resultSet = playlistStatement.executeQuery();
            while(resultSet.next()) {
                playlists.add(new Playlist(resultSet.getInt("ID"), resultSet.getString("owner"), resultSet.getString("name"),resultSet.getInt("totalLength"), new ArrayList<Track>()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }
    @Override
    public Playlist getPlayList(int playlistID) { //FN01
        Playlist playlist = null;
        try (
                Connection connection = databaseProperties.getConnection(SERVER);
                PreparedStatement playlistStatement = connection.prepareStatement("SELECT * from playlist p join playlist_track pt on p.id = pt.playlistID join track t on t.id = pt.trackID join availability a on pt.availabilityID = a.id where p.id = ?");
        ) {
            playlistStatement.setInt(1, playlistID);
            ResultSet resultSet = playlistStatement.executeQuery();
            ArrayList<Track> tracks = new ArrayList<>();
            while(resultSet.next()) {
                if(resultSet.getString("album").length() > 0) {
                    tracks.add(new Song(resultSet.getInt("trackID"), resultSet.getString("performer"), resultSet.getString("title"), resultSet.getString("url"), resultSet.getLong("duration"), resultSet.getString("album"), new Availability(resultSet.getBoolean("offlineAvailability"))));
                }
                else {
                    tracks.add(new Video(resultSet.getInt("trackID"), resultSet.getString("performer"), resultSet.getString("title"), resultSet.getString("url"), resultSet.getLong("duration"), resultSet.getInt("playcount"), resultSet.getDate("publicationDate"), resultSet.getString("description"), new Availability(resultSet.getBoolean("offlineAvailability"))));
                }
                if(resultSet.isLast()){
                    playlist = new Playlist(resultSet.getInt("playlistID"),resultSet.getString("owner"), resultSet.getString("name"),resultSet.getInt("totalLength"), tracks);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlist;
    }
   /* @Override
    public void save(Playlist playlist) {
        try (
                Connection connection = databaseProperties.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO playlist (name, owner) VALUES (?, ?)");
                PreparedStatement getPlaylistID = connection.prepareStatement("SELECT MAX(id) FROM playlist");
                PreparedStatement availabilityStatement = connection.prepareStatement("INSERT INTO availability (offlineAvailability) VALUES (?)");
                PreparedStatement getavailabilityID = connection.prepareStatement("SELECT MAX(id) FROM availability");
                PreparedStatement trackStatement = connection.prepareStatement("INSERT INTO playlist_track (playlistID, trackID, availabilityID) VALUES (?, ?, ?)");

        ) {
            statement.setString(1, playlist.getName());
            statement.setString(1, playlist.getOwner());
            statement.execute();
            availabilityStatement.setInt(1, 0);
            availabilityStatement.execute();
            ResultSet availabilityResult = getavailabilityID.executeQuery();
            int availabilityID = 0;
            while (availabilityResult.next()) {
                availabilityID = availabilityResult.getInt("ID");
            }
            ResultSet playlistResult = getavailabilityID.executeQuery();
            int playlistID = 0;
            while (playlistResult.next()) {
                playlistID = playlistResult.getInt("ID");
            }
            for (Track track :
                    playlist.getTracks()) {
                trackStatement.setInt(1, playlistID);
                trackStatement.setInt(2, track.getID());
                trackStatement.setInt(3, availabilityID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    @Override //FN01
    public void delete(int PlaylistID) {
        try (
                Connection connection = databaseProperties.getConnection(SERVER);
                PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist where id = ?");

        ) {
            statement.setInt(1, PlaylistID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override //FN01
    public void create(Playlist playlist) {
        try (
                Connection connection = databaseProperties.getConnection(SERVER);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO playlist (name, owner) values (?, ?)");

        ) {
            statement.setString(1, playlist.getName());
            statement.setString(2, playlist.getOwner());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override// FN01
    public void changeName(Playlist playlist) {
        try (
                Connection connection = databaseProperties.getConnection(SERVER);
                PreparedStatement statement = connection.prepareStatement("UPDATE playlist SET name=? WHERE id=?");
        ) {
            statement.setString(1, playlist.getName());
            statement.setInt(2, playlist.getID());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
