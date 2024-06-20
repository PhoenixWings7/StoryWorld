package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "worlds")
public class World {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(nullable = false)
    @Basic(optional = false)
    private String name;
    private String summary;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Story> stories = new ArrayList<>(1);
    @OneToMany(cascade = CascadeType.ALL)
    private List<WorldElement> worldElements = new ArrayList<>();
    @ManyToOne
    private User owner;

    public World() {
        this.name = "My World";
    }

    public World(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic(optional = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public void addNewStory() {
        addNewStory("");
    }

    public void addNewStory(String title) {
        Story story = new Story(title, this);
        stories.add(story);
    }

    public List<WorldElement> getWorldElements() {
        return worldElements;
    }

    public void setWorldElements(List<WorldElement> worldElements) {
        this.worldElements = worldElements;
    }

    public void addElement(WorldElement worldElement) {
        worldElements.add(worldElement);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "World{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", stories=" + stories +
                ", worldElements=" + worldElements +
                '}';
    }
}
