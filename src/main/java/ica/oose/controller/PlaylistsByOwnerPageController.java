package ica.oose.controller;

import ica.oose.domain.Playlist;
import ica.oose.service.PlaylistService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kevin on 30-3-2017.
 */
//@WebServlet("/playlist/")
public class PlaylistsByOwnerPageController extends HttpServlet {
    @Inject
    PlaylistService playlistService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Playlist> playlists = playlistService.getPlaylists(request.getParameter("owner"));
        request.setAttribute("allPlaylists", playlists);
        request.getRequestDispatcher("WEB-INF/PlaylistByOwnerView.jsp").forward(request, response);
    }
}
