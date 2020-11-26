package ica.oose.service;

import ica.oose.datasource.dao.LoginDAOMySQL;
import ica.oose.domain.Playlist;
import ica.oose.domain.User;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Kevin on 30-3-2017.
 */
@Path("/")
public class LoginService {
    @Inject
    LoginDAOMySQL loginDAO;

    @POST
    @Consumes("application/json")
    public User login(String owner) {
        return loginDAO.login(owner);
    }
}
