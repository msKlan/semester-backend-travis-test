package facades;

import entities.RenameMe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    
    public List<String> getUserInformationByID(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            List<String> informationByID = em.createNamedQuery("User.allInformationByID").setParameter("id", id).getResultList();
            em.getTransaction().commit();
            return informationByID;
        } finally {
            em.close();
        }
    }
    
    public List<String> getUserInformationByUserName(String userName) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            List<String> informationByUserName = em.createNamedQuery("User.allInformationByUserName").setParameter("userName", userName).getResultList();
            em.getTransaction().commit();
            return informationByUserName;
        } finally {
            em.close();
        }
    }
    
    public List<String> getUserInformationByEmail(String userEmail) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            List<String> informationByUserEmail = em.createNamedQuery("User.allInformationByEmail").setParameter("email", userEmail).getResultList();
            em.getTransaction().commit();
            return informationByUserEmail;
        } finally {
            em.close();
        }
    }

}
