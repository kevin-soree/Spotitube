package ica.oose.controller;

import ica.oose.domain.Playlist;
import ica.oose.domain.Track;
import ica.oose.domain.User;
import ica.oose.service.PlaylistService;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kevin on 23-3-2017.
 */
@WebServlet("/playlists")
public class PlaylistsViewPageController extends HttpServlet { //NFN08

    @Inject
    private PlaylistService playlistService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playlistID = Integer.parseInt(request.getParameter("playlistID"));
        playlistService.deletePlaylist(playlistID);
        response.sendRedirect("/playlists");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //playlistDAO.list();

        /*WebClient client = WebClient.create("http://localhost:8080/").path("/playlist").accept("application/json");
        Collection<? extends Playlist> playlists = client.getCollection(Playlist.class);*/

        String owner = ((User)request.getSession().getAttribute("user")).getUsername();
        List<Playlist> playlists = playlistService.getPlaylists(owner);
        request.setAttribute("allPlaylists", playlists);
        request.getRequestDispatcher("WEB-INF/PlaylistView.jsp").forward(request, response);
    }
}
