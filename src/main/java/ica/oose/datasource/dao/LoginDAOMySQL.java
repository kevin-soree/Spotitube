package ica.oose.datasource.dao;

import ica.oose.datasource.DBConnectionFactory;
import ica.oose.datasource.DBServer;
import ica.oose.domain.Playlist;
import ica.oose.domain.Track;
import ica.oose.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Kevin on 30-3-2017.
 */
public class LoginDAOMySQL {
    private DBConnectionFactory databaseProperties = new DBConnectionFactory();

    public User login(String owner){
        User user = null;
        try (
                Connection connection = databaseProperties.getConnection(DBServer.MYSQL);
                PreparedStatement statement = connection.prepareStatement("SELECT * from user where username = ?");
        ) {
            statement.setString(1, owner);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user = new User(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
