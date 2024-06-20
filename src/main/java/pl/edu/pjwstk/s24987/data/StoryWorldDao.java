package pl.edu.pjwstk.s24987.data;

import pl.edu.pjwstk.s24987.model.World;

import java.util.List;

public interface StoryWorldDao {
    /**
     * @param username user's username
     * @param password user's password
     * @return true if sign-in was successful, false otherwise
     */
    public boolean signIn(String username, String password);

    /**
     * @return currently logged-in User
     */
    public Long getCurrentUserId();

    public List<String> getWorldNames();
    public List<World> getAllWorlds();
    public World getWorldData(Long worldId);

    public void setSelectedWorldId(Long worldId);

    public Long getSelectedWorldId();

    public World getSelectedWorld();

    public void setSelectedWorld(World selectedWorld);
}
