package pl.edu.pjwstk.s24987.data;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.mindrot.jbcrypt.BCrypt;
import pl.edu.pjwstk.s24987.model.Character;
import pl.edu.pjwstk.s24987.model.*;

public class LocalDbHandler {
    private static SessionFactory sessionFactory = null;
    private static Session currentSession = null;

    public static void connectToDb() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .loadProperties("hibernate.properties")
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void initializeExampleDb() {
        User myUser = new User("PhoenixWings7", "dummy@email.com", hashPassword("password"));
        World myWorld = new World("Magical World", myUser);
        myUser.addWorld(myWorld);
        WorldElement myCharacter = new Character("Cassie", myWorld);
        Animal myAnimal = new Animal("Cloud Squirrel", myWorld);
        myWorld.addNewStory("Silly Little Story");
        myWorld.getStories().getFirst().addNewChapter();
        myWorld.getStories().getFirst().addNewChapter();
        myWorld.getStories().getFirst().getChapters().getFirst().addNewScene();
        myWorld.getStories().getFirst().getChapters().getFirst().addNewScene();
        myWorld.getStories().getFirst().getChapters().getFirst().getScenes().getFirst().linkWorldElement(myCharacter);
        myWorld.getStories().getFirst().getChapters().getFirst().getScenes().getFirst().linkWorldElement(myAnimal);
        myWorld.getStories().getFirst().getChapters().getFirst().getScenes().get(1).linkWorldElement(myAnimal);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(myUser);
        session.getTransaction().commit();
        session.close();
    }

    public static Session getCurrentSession() {
        if (currentSession == null) {
            currentSession = sessionFactory.openSession();
            currentSession.beginTransaction();
        }
        return currentSession;
    }

    public static void closeCurrentSession() {
        if (currentSession != null) {
            currentSession.getTransaction().commit();
            currentSession.close();
            currentSession = null;
        }
    }

    public static void closeDbConnection() {
        try {
            if (sessionFactory != null) {
                if (currentSession != null) {
                    currentSession.flush();
                    currentSession.close();
                    currentSession = null;
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
                sessionFactory = null;
            }
        }
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean comparePasswords(String hashFromDb, String passwordEntered) {
        return BCrypt.checkpw(passwordEntered, hashFromDb);
    }
}
