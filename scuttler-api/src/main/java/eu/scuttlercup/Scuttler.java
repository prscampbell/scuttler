package eu.scuttlercup;

import eu.scuttlercup.dao.Matches;
import eu.scuttlercup.dao.Registrations;
import eu.scuttlercup.dao.Tournaments;
import eu.scuttlercup.dto.Match;
import eu.scuttlercup.dto.Registration;
import eu.scuttlercup.dto.Tournament;
import eu.scuttlercup.resource.MatchesResource;
import eu.scuttlercup.resource.RegistrationsResource;
import eu.scuttlercup.resource.TournamentsResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Scuttler extends Application<ScuttlerConfiguration>
{
    
    public static void main(String[] args) throws Exception
    {
        new Scuttler().run(args);
    }
    
    private final HibernateBundle<ScuttlerConfiguration> hibernate = new HibernateBundle<ScuttlerConfiguration>(Registration.class, Tournament.class, Match.class) 
    {
        public DataSourceFactory getDataSourceFactory(ScuttlerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<ScuttlerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    
    @Override
    public void run(ScuttlerConfiguration configuration, Environment environment) throws Exception 
    {
        Registrations.setSessionFactory(hibernate.getSessionFactory());
        environment.jersey().register(RegistrationsResource.class);

        Tournaments.setSessionFactory(hibernate.getSessionFactory());
        environment.jersey().register(TournamentsResource.class);

        Matches.setSessionFactory(hibernate.getSessionFactory());
        environment.jersey().register(MatchesResource.class);
    }

}
