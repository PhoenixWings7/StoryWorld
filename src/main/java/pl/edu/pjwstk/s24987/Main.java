package pl.edu.pjwstk.s24987;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.edu.pjwstk.s24987.model.World;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        World myWorld = new World("First World");
        System.out.println(myWorld);

        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;

        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            // Do something within the session, e.g. create/retrieve objects, etc.
            //session.save(myWorld);
            session.persist(myWorld);

            session.getTransaction().commit();
            List<World> worlds = session.createQuery("from worlds").list();
            System.out.println(worlds);

            session.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
        finally {
            if (sessionFactory != null) {

                sessionFactory.close();
                sessionFactory = null;
            }
        }
    }
}