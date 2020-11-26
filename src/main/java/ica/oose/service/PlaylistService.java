package ica.oose.service;

import ica.oose.datasource.dao.IPlaylistDAO;
import ica.oose.datasource.dao.ITrackDAO;
import ica.oose.domain.Playlist;
import ica.oose.domain.Track;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 23-3-2017.
 */
@Path("/playlists")
@Default
public class PlaylistService { //NFN04 NFN09
    @Inject
    private ITrackDAO trackDAO;

    @Inject
    IPlaylistDAO playlistDAO;

    @GET
    @Produces("application/json")
    public List<Playlist> getPlaylists(String owner) {
        return playlistDAO.findByOwner(owner);
    }

    /* @GET
     @Path("byowner/{owner}")
     @Produces("application/json")
     public List<Playlist> getAllPlaylists(@PathParam("owner")String owner) {
         return playlistDAO.findByOwner(owner);
     }
 */
    @GET
    @Path("/{playlistID}")
    @Produces("application/json")
    public Playlist getPlaylist(@PathParam("playlistID") int id) {
        return playlistDAO.getPlayList(id);
    }

    /* @POST
     @Consumes("application/json")
     public void addPlaylist(String owner, String name, int[] trackIDs) {
         ArrayList<Track> tracks = new ArrayList<>();
         for (int trackID : trackIDs) {
             tracks.add(trackDAO.getTrack(trackID));
         }
         playlistDAO.save(new Playlist(owner, name, tracks));
     }*/
    @POST
    @Consumes("application/json")
    public void deletePlaylist(int playlistID) {
        playlistDAO.delete(playlistID);
    }

    @POST
    @Path("/createplaylist")
    @Consumes("application/json")
    public void createPlaylist(Playlist playlist) {
        playlistDAO.create(playlist);
    }

    @POST
    @Path("/changenames")
    @Consumes("application/json")
    public void update(Playlist playlist) {
        playlistDAO.changeName(playlist);
    }
}
