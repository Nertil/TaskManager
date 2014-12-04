package demo.nertil.service;

import demo.nertil.entities.User;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Nertil
 */
@Path("user")
public class UserFacadeREST extends AbstractFacade<User> {

    public UserFacadeREST() {
        super(User.class);
    }
    @GET
    @Path("/login")
    @Produces("application/json")
    public User login(@QueryParam("username") String username, @QueryParam("password") String password) {
        User retval = null;
        EntityManager entity_manager = ApplicationConfig.getEntityManagerFactory().createEntityManager();
        try {
            CriteriaBuilder cb = entity_manager.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> e = query.from(User.class);
            query.where(cb.and(cb.equal(e.get("username"), username), cb.equal(e.get("password"), password)));
            List<User> result = entity_manager.createQuery(query).getResultList();
            if (result != null && !result.isEmpty()) {
                retval = result.get(0);
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " " + e.getMessage());
            System.out.println("stack trace " + Arrays.toString(e.getStackTrace()));
        } finally {
            entity_manager.close();
        }
        return retval;
    }
    
    @POST
    @Override
    @Produces("application/json")
    @Consumes("application/json")
    public User create(User entity) {
        boolean userExist = false;
        EntityManager entity_manager = ApplicationConfig.getEntityManagerFactory().createEntityManager();
        try {
            CriteriaBuilder cb = entity_manager.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> e = query.from(User.class);
            query.where(cb.equal(e.get("username"), entity.getUsername()));
            List<User> result = entity_manager.createQuery(query).getResultList();
            if (result != null && !result.isEmpty()) {
                userExist = true;
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " " + e.getMessage());
            System.out.println("stack trace " + Arrays.toString(e.getStackTrace()));
        } finally {
            entity_manager.close();
        }
        if (userExist) {
            return null;
        } else {
            return super.create(entity);
        }
    }
}
