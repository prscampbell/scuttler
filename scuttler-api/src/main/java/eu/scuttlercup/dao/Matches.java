package eu.scuttlercup.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import eu.scuttlercup.dto.Match;

public class Matches 
{
    
    private static SessionFactory factory;
    
    public static void setSessionFactory(SessionFactory factory) {
        Matches.factory = factory;
    }
    
    public static List<Match> get(long tournamentId) 
    {
        Session session = null;
        
        try
        {
            session = factory.openSession();
            
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Match> cq = cb.createQuery(Match.class);
            Root<Match> root = cq.from(Match.class);
            
            cq.where(cb.equal(root.get("tournamentId"), tournamentId));
            return session.createQuery(cq).getResultList();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return new ArrayList<>();
        }
        finally 
        {
            if(session != null) 
            {
                session.close();
            }
        }
    }
    
}
