package ica.oose.controller;

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
 * Created by Kevin on 5-4-2017.
 */
@WebServlet("/playlists/changename")
public class ChangeNamePageController extends HttpServlet {
    @Inject
    private PlaylistService playlistService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playlistID = Integer.parseInt(request.getParameter("id"));
        Playlist playlist = playlistService.getPlaylist(playlistID);
        request.setAttribute("playlist", playlist);
        request.getRequestDispatcher("/WEB-INF/ChangeNameView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String owner = ((User)request.getSession().getAttribute("user")).getUsername();
        int playlistID = Integer.parseInt(request.getParameter("playlistID"));

        playlistService.update(new Playlist(playlistID, owner, name,0, new ArrayList<Track>()));
        response.sendRedirect("/playlists");
    }
}
