package eu.scuttlercup.resource;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import eu.scuttlercup.dao.Registrations;
import eu.scuttlercup.dao.Tournaments;
import eu.scuttlercup.dto.Tournament;
import eu.scuttlercup.riot.dao.Summoners;
import eu.scuttlercup.riot.dto.Summoner;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/registrations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationsResource 
{

    @POST
    @UnitOfWork
    @Path("/{name}")
    public Response register(@PathParam("name") String name)
    {
        Optional<Summoner> summoner = Summoners.byName(name);
        
        if(!summoner.isPresent()) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        Optional<Tournament> tournament = Tournaments.latest();
        
        if(!tournament.isPresent()) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        long tournamentId = tournament.get().getId();
        boolean registrationDeadlinePassed = tournament.get().hasRegistrationDeadlinePassed();
        String accountId = summoner.get().getAccountId();
        
        if(registrationDeadlinePassed) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        
        if(Registrations.exists(tournamentId, accountId)) {
            return Response.status(Status.CONFLICT).build();
        }
        
        if(!Registrations.create(tournamentId, accountId)) {
            return Response.serverError().build();
        }
        
        return Response.ok().build();
    }
    
}
