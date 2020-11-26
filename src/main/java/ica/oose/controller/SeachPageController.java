package ica.oose.controller;

import ica.oose.datasource.dao.IPlaylistDAO;
import ica.oose.domain.*;
import ica.oose.service.PlaylistService;
import ica.oose.service.TrackService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 3-4-2017.
 */
@WebServlet("/search")
public class SeachPageController extends HttpServlet {
    @Inject
    private TrackService trackService;
    @Inject
    private PlaylistService playlistService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playlistID = Integer.parseInt(request.getParameter("playlist"));
        int trackID = Integer.parseInt(request.getParameter("trackID"));
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Song(trackID, "r", "f", "f", 0,"", new Availability(false)));

        trackService.addTrackToPlaylist(new Playlist(playlistID, "ef","ef",0, tracks));
        response.sendRedirect("/search");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTitle = request.getParameter("title");

        List tracks = trackService.searchTrack(searchTitle);
        List playlists = playlistService.getPlaylists(((User)request.getSession().getAttribute("user")).getUsername());

        request.setAttribute("tracks", tracks);
        request.setAttribute("playlists", playlists);
        request.getRequestDispatcher("WEB-INF/SearchView.jsp").forward(request, response);
    }
}
