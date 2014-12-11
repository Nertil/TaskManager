package demo.nertil.entities;

import demo.nertil.entities.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-11T13:02:00")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, Integer> id;
    public static volatile SingularAttribute<Task, String> description;
    public static volatile SingularAttribute<Task, Integer> priority;
    public static volatile SingularAttribute<Task, User> userId;
    public static volatile SingularAttribute<Task, Date> dueDate;
    public static volatile SingularAttribute<Task, Boolean> completed;

}