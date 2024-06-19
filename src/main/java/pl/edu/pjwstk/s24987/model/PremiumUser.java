package pl.edu.pjwstk.s24987.model;

public class PremiumUser extends User {
    public PremiumUser() {
    }

    public PremiumUser(String username, String email, String passwordHash) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPasswordHash(passwordHash);
        this.setPremium(true);
    }
}
