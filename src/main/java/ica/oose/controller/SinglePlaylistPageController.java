package ica.oose.controller;

import ica.oose.datasource.dao.IPlaylistDAO;
import ica.oose.domain.Playlist;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kevin on 3-4-2017.
 */
@WebServlet("/playlist")
public class SinglePlaylistPageController extends HttpServlet {
    @Inject
    private IPlaylistDAO playlistDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playlistID = Integer.parseInt(request.getParameter("id"));

        Playlist playlist = playlistDAO.getPlayList(playlistID);

        request.setAttribute("playlist", playlist);
        request.getRequestDispatcher("WEB-INF/SinglePlaylistView.jsp").forward(request, response);
    }
}
