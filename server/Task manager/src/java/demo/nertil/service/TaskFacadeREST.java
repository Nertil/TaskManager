package demo.nertil.service;

import demo.nertil.entities.Task;
import demo.nertil.entities.User;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Nertil
 */
@Path("task")
public class TaskFacadeREST extends AbstractFacade<Task> {

    public TaskFacadeREST() {
        super(Task.class);
    }

    @POST
    @Override
    @Produces("application/json")
    @Consumes("application/json")
    public Task create(Task entity) {
        return super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Task edit(@PathParam("id") Integer id, Task entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Produces("application/json")
    public List<Task> findAllByUser(@QueryParam("user_id") Integer user) {
        List<Task> retval = null;
        EntityManager entity_manager = ApplicationConfig.getEntityManagerFactory().createEntityManager();
        try {
            CriteriaBuilder cb = entity_manager.getCriteriaBuilder();
            CriteriaQuery<Task> query = cb.createQuery(Task.class);
            Root<Task> e = query.from(Task.class);
            query.where(cb.equal(e.get("userId"), new User(user)));
            retval = entity_manager.createQuery(query).getResultList();
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " " + e.getMessage());
            System.out.println("stack trace " + Arrays.toString(e.getStackTrace()));
        } finally {
            entity_manager.close();
        }
        return retval;
    }
//    @GET
//    @Produces("application/json")
//    @Override
//    public List<Task> findAll() {
//        return super.findAll();
//    }
}
