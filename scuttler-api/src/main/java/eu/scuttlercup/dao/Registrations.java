package eu.scuttlercup.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import eu.scuttlercup.dto.Registration;

public class Registrations 
{

    private static SessionFactory factory;
    
    public static void setSessionFactory(SessionFactory factory) {
        Registrations.factory = factory;
    }
    
    public static boolean create(long tournamentId, String accountId) 
    {
        Session session = null;
        
        try
        {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(new Registration(tournamentId, accountId));
            transaction.commit();
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally 
        {
            if(session != null) 
            {
                session.close();
            }
        }
    }
    
    public static boolean exists(long tournamentId, String accountId) 
    {
        Session session = null;
        
        try
        {
            session = factory.openSession();
            
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Registration> cq = cb.createQuery(Registration.class);
            Root<Registration> root = cq.from(Registration.class);
            
            cq.where(cb.equal(root.get("tournamentId"), tournamentId),
                     cb.equal(root.get("accountId"), accountId));
            
            List<Registration> list = session.createQuery(cq).getResultList();
            return (list != null && !list.isEmpty());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
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
