package pl.edu.pjwstk.s24987.data;

import org.hibernate.Session;
import pl.edu.pjwstk.s24987.model.Chapter;
import pl.edu.pjwstk.s24987.model.Story;
import pl.edu.pjwstk.s24987.model.User;
import pl.edu.pjwstk.s24987.model.World;

import java.util.ArrayList;
import java.util.List;

public class StoryWorldDaoImpl implements StoryWorldDao {
    private static Long currentUserId = null;
    private static Long lastWorldId = null;
    private static World lastWorld = null;
    private static Long lastStoryId = null;

    @Override
    public boolean signIn(String username, String password) {
        Session currentSession = LocalDbHandler.getCurrentSession();
        List<User> users = currentSession
                .createQuery("from users where username = :username", User.class)
                .setParameter("username", username)
                .list();
        if (users.isEmpty()) {
            LocalDbHandler.closeCurrentSession();
            return false;
        }
        User user = users.getFirst();
        boolean signInSuccessful = LocalDbHandler.comparePasswords(user.getPasswordHash(), password);
        if (signInSuccessful) {
            currentUserId = user.getId();
        }
        LocalDbHandler.closeCurrentSession();
        return signInSuccessful;
    }

    @Override
    public Long getCurrentUserId() {
        return currentUserId;
    }

    @Override
    public List<String> getWorldNames() {
        List<String> worldNames = new ArrayList<>();
        Session session = LocalDbHandler.getCurrentSession();
        worldNames = session
                .createQuery("select name from worlds where owner.id = :userId", String.class)
                .setParameter("userId", currentUserId)
                .list();
        LocalDbHandler.closeCurrentSession();
        return worldNames;
    }


    @Override
    public List<World> getAllWorlds() {
        List<World> worlds = new ArrayList<>();
        Session session = LocalDbHandler.getCurrentSession();
        worlds = session
                .createQuery("from worlds where owner.id = :userId", World.class)
                .setParameter("userId", currentUserId)
                .list();
        LocalDbHandler.closeCurrentSession();
        return worlds;
    }

    @Override
    public World getWorldData(Long worldId) {
        World world;
        Session session = LocalDbHandler.getCurrentSession();
        List<World> worlds = session
                .createQuery("from worlds where id = :worldId", World.class)
                .setParameter("worldId", worldId)
                .list();
        if (worlds.isEmpty()) {
            LocalDbHandler.closeCurrentSession();
            return null;
        }

        world = worlds.getFirst();
        world.getStories().size();
        world.getWorldElements().size();
        LocalDbHandler.closeCurrentSession();
        return world;
    }

    @Override
    public void setSelectedWorldId(Long worldId) {
        lastWorldId = worldId;
    }

    @Override
    public Long getSelectedWorldId() {
        return lastWorldId;
    }

    @Override
    public World getSelectedWorld() {
        return lastWorld;
    }

    @Override
    public void setSelectedWorld(World selectedWorld) {
        lastWorld = selectedWorld;
    }

    @Override
    public void setSelectedStoryId(Long selectedStoryId) {
        lastStoryId = selectedStoryId;
    }

    @Override
    public Story getSelectedStory() {
        return lastWorld.getStory(lastStoryId);
    }

    @Override
    public void updateChapter(Chapter chapter) {
        Session session = LocalDbHandler.getCurrentSession();
        session.merge(chapter);
        session.refresh(chapter);
        LocalDbHandler.closeCurrentSession();
    }
}
