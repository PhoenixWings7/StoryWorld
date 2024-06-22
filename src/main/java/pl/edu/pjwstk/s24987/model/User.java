package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
@Inheritance
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @Basic(optional = false)
    private String username;
    @Column(nullable = false)
    @Basic(optional = false)
    private String email;
    @Column(nullable = false)
    @Basic(optional = false)
    private String passwordHash;
    @Column(nullable = false)
    @Basic(optional = false)
    private boolean isPremium = false;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<World> worlds = new ArrayList<>();

    public User() {}

    public User(String username, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public User(String username, String email, String passwordHash, boolean isPremium) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.isPremium = isPremium;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public List<World> getWorlds() {
        return worlds;
    }

    public void setWorlds(List<World> worlds) {
        this.worlds = worlds;
    }

    public void addWorld(World world) {
        worlds.add(world);
    }
}
