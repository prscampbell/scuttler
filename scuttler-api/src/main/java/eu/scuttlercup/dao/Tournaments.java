package eu.scuttlercup.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import eu.scuttlercup.dto.Tournament;

public class Tournaments 
{

    private static SessionFactory factory;
    
    public static void setSessionFactory(SessionFactory factory) {
        Tournaments.factory = factory;
    }
    
    public static Optional<Tournament> latest() 
    {
        Session session = null;
        
        try
        {
            session = factory.openSession();
            
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Tournament> cq = cb.createQuery(Tournament.class);
            Root<Tournament> root = cq.from(Tournament.class);
            
            cq
            .where(cb.greaterThan(root.get("registration"), new Date()))
            .orderBy(cb.desc(root.get("start")));
            
            List<Tournament> list = session.createQuery(cq).getResultList();
            return (list == null || list.isEmpty() ? Optional.empty() : Optional.of(list.get(0)));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return Optional.empty();
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
