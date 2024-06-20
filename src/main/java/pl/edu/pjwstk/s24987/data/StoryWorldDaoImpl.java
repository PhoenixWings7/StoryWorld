package pl.edu.pjwstk.s24987.data;

import org.hibernate.Session;
import pl.edu.pjwstk.s24987.model.User;

import java.util.ArrayList;
import java.util.List;

public class StoryWorldDaoImpl implements StoryWorldDao {
    private static Long currentUserId = null;

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
}
