package pl.edu.pjwstk.s24987.data;

import pl.edu.pjwstk.s24987.model.Chapter;
import pl.edu.pjwstk.s24987.model.Story;
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
     * @return currently logged-in User's ID
     */
    public Long getCurrentUserId();

    /**
     * Fetches all worlds created by the currently logged-in user
     * @return list of worlds
     */
    public List<World> getAllWorlds();

    public World getWorldData(Long worldId);

    public void setSelectedWorldId(Long worldId);

    public Long getSelectedWorldId();

    public World getSelectedWorld();

    public void setSelectedWorld(World selectedWorld);

    public void setSelectedStoryId(Long selectedStoryId);

    public Story getSelectedStory();

    /**
     * Saves the chapter object and refreshes it with data from the database
     * @param chapter chapter to update
     */
    public void updateChapter(Chapter chapter);
}
