package eu.scuttlercup.resource;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import eu.scuttlercup.dao.Tournaments;
import eu.scuttlercup.dto.Tournament;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/tournaments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TournamentsResource 
{

    @GET
    @UnitOfWork
    public Response latest()
    {
        Optional<Tournament> tournament = Tournaments.latest();
        
        if(!tournament.isPresent()) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        return Response.ok(tournament.get().toString()).build();
    }
    
}
