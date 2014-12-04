package demo.nertil.service;

import java.util.Set;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Application;

/**
 *
 * @author Nertil
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {
    private static final EntityManagerFactory entity_manager_factory = Persistence.createEntityManagerFactory("Task_managerPU");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entity_manager_factory;
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(demo.nertil.service.TaskFacadeREST.class);
        resources.add(demo.nertil.service.UserFacadeREST.class);
    }

}
