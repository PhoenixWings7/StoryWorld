package pl.edu.pjwstk.s24987.data;

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
}
