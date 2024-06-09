package pl.edu.pjwstk.s24987;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.edu.pjwstk.s24987.model.Animal;
import pl.edu.pjwstk.s24987.model.Character;
import pl.edu.pjwstk.s24987.model.World;
import pl.edu.pjwstk.s24987.model.WorldElement;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // utworzenie klasy World, który zostanie zapisany do bazy danych
        World myWorld = new World("First World");
        System.out.println(myWorld);

        // utworzenie elementu świata, który zostanie dodany do świata - asocjacja 1 do wielu
        WorldElement myCharacter = new Character("Klaudia");
        System.out.println(myCharacter);

        // utworzenie elementu, który nie zostanie dodany do świata
        Animal myAnimal = new Animal("Squirrel");
        System.out.println(myAnimal);

        // dodanie elementu do świata
        myWorld.addElement(myCharacter);

        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;

        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            /**
             zapisywanie elementów - elementy typu WorldElement będą zapisane w wielu oddzielnych tabelach
             ze względu na dziedziczenie, patrz: klasa WorldElement
             * @see WorldElement
            */
            session.persist(myCharacter);
            session.persist(myAnimal);
            session.persist(myWorld);
            session.getTransaction().commit();

            // odzyskiwanie zapisanych elementów
            List<World> worlds = session.createQuery("from worlds").list();
            System.out.println(STR."Worlds saved: \{worlds}");
            List<WorldElement> worldElements = worlds.getFirst().getWorldElements();
            System.out.println(STR."First World's elements: \{worldElements}");
            List<WorldElement> createdElements = session.createQuery("from world_elems").list();
            System.out.println(STR."All saved WorldElement objects: \{createdElements}");

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