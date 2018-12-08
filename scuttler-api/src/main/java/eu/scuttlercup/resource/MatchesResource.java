package eu.scuttlercup.resource;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import eu.scuttlercup.dao.Matches;
import eu.scuttlercup.dao.Tournaments;
import eu.scuttlercup.dto.Tournament;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/matches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatchesResource 
{

    @GET
    @UnitOfWork
    public Response matches()
    {
        Optional<Tournament> tournament = Tournaments.latest();
        
        if(!tournament.isPresent()) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        return Response.ok(Matches.get(tournament.get().getId())).build();
    }
}
