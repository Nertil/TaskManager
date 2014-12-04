package demo.nertil.service;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Nertil
 * @param <T> the entity class
 */
public abstract class AbstractFacade<T> {
    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T create(T entity) {        
        EntityManager entity_manager = ApplicationConfig.getEntityManagerFactory().createEntityManager();
        try {
            entity_manager.getTransaction().begin();
            entity_manager.persist(entity);
            entity_manager.getTransaction().commit();
            //clear jpa cache
            ApplicationConfig.getEntityManagerFactory().getCache().evictAll();
        } catch (Exception e) {
            System.out.println("JPA create " +  e.getMessage());
            System.out.println("stack trace " +  Arrays.toString(e.getStackTrace()));
        } finally {
            entity_manager.close();
        }
        return entity;
    }

    public T edit(T entity) {       
        EntityManager entity_manager = ApplicationConfig.getEntityManagerFactory().createEntityManager();
        try {
            entity_manager.getTransaction().begin();
            entity_manager.merge(entity);
            entity_manager.getTransaction().commit();
            //clear jpa cache
            ApplicationConfig.getEntityManagerFactory().getCache().evictAll();
        } catch (Exception e) {
            System.out.println("JPA edit " +  e.getMessage());
            System.out.println("stack trace " +  Arrays.toString(e.getStackTrace()));
        } finally {
            entity_manager.close();
        }
        return entity;
    }

    public void remove(T entity) {       
        EntityManager entity_manager = ApplicationConfig.getEntityManagerFactory().createEntityManager();
        try {
            entity_manager.getTransaction().begin();
            entity_manager.remove(entity_manager.merge(entity));
            entity_manager.getTransaction().commit();
            //clear jpa cache
            ApplicationConfig.getEntityManagerFactory().getCache().evictAll();
        } catch (Exception e) {
            System.out.println("JPA remove " +  e.getMessage());
            System.out.println("stack trace " +  Arrays.toString(e.getStackTrace()));
        } finally {
            entity_manager.close();
        }
    }

    public T find(Object id) {
        T retval = null;
        EntityManager entity_manager = ApplicationConfig.getEntityManagerFactory().createEntityManager();
        try {
            retval = entity_manager.find(entityClass, id);
        } catch (Exception e) {
            System.out.println("JPA find " + e.getMessage());
            System.out.println("stack trace " + Arrays.toString(e.getStackTrace()));
        } finally {
            entity_manager.close();
        }
        return retval;
    }

    public List<T> findAll() {
        List<T> retval = null;
        EntityManager entity_manager = ApplicationConfig.getEntityManagerFactory().createEntityManager();        
        try {
            javax.persistence.criteria.CriteriaQuery cq = entity_manager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            retval = entity_manager.createQuery(cq).getResultList();
        } catch (Exception e) {
            System.out.println("JPA findAll " + e.getMessage());
            System.out.println("stack trace " + Arrays.toString(e.getStackTrace()));
        } finally {
            entity_manager.close();
        }
        return retval;
    }

    public List<T> findRange(int[] range) {        
        List<T> retval = null;
        EntityManager entity_manager = ApplicationConfig.getEntityManagerFactory().createEntityManager();        
        try {
            javax.persistence.criteria.CriteriaQuery cq = entity_manager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            javax.persistence.Query q = entity_manager.createQuery(cq);
            q.setMaxResults(range[1] - range[0] + 1);
            q.setFirstResult(range[0]);
            retval = q.getResultList();
        } catch (Exception e) {
            System.out.println("JPA findRange " + e.getMessage());
            System.out.println("stack trace " + Arrays.toString(e.getStackTrace()));
        } finally {
            entity_manager.close();
        }
        return retval;
    }

    public int count() {               
        int retval = 0;
        EntityManager entity_manager = ApplicationConfig.getEntityManagerFactory().createEntityManager();        
        try {
            javax.persistence.criteria.CriteriaQuery cq = entity_manager.getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(entity_manager.getCriteriaBuilder().count(rt));
            javax.persistence.Query q = entity_manager.createQuery(cq);
            retval = ((Long) q.getSingleResult()).intValue();
        } catch (Exception e) {
            System.out.println("JPA count " + e.getMessage());
            System.out.println("stack trace " + Arrays.toString(e.getStackTrace()));
        } finally {
            entity_manager.close();
        }
        return retval;
    }

}
