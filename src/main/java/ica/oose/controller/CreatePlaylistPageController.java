package ica.oose.controller;

import ica.oose.datasource.dao.IPlaylistDAO;
import ica.oose.domain.Playlist;
import ica.oose.domain.Track;
import ica.oose.domain.User;
import ica.oose.service.PlaylistService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kevin on 4-4-2017.
 */
@WebServlet("/playlists/createplaylist")
public class CreatePlaylistPageController extends HttpServlet {
    @Inject
    private PlaylistService playlistService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/CreatePlaylistView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String owner = ((User)request.getSession().getAttribute("user")).getUsername();
        playlistService.createPlaylist(new Playlist(owner, name, new ArrayList<Track>()));
        response.sendRedirect("/playlists");
    }
}
