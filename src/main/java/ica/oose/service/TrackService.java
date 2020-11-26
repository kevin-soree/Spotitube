package ica.oose.service;

import ica.oose.datasource.dao.ITrackDAO;
import ica.oose.domain.Playlist;
import ica.oose.domain.Track;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Kevin on 28-3-2017.
 */
@Path("/search")
public class TrackService {
    @Inject
    private ITrackDAO ITrackDAO;

    @GET
    @Path("/{titel}")
    @Produces("application/json")
    public List<Track> searchTrack(@PathParam("titel") String titel) {
        return ITrackDAO.findByTitle(titel);
    }

    @POST
    @Consumes("application/json") //@FormParam("playlistID") int playlistID, @FormParam("trackID") int trackID
    public void addTrackToPlaylist(Playlist playlist){
        ITrackDAO.addTrackToPlaylist(playlist.getID(), playlist.getTracks().get(playlist.getTracks().size()-1).getID());
    }
}
