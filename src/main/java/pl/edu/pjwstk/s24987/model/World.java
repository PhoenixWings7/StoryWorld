package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "worlds")
public class World {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String summary;
    @OneToMany()
    private List<Story> stories = new ArrayList<>(1);
    //private List<WorldElement> worldElements = new ArrayList<>();

    public World() {
        this.name = "My World";
    }

    public World(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
/*
    public List<WorldElement> getWorldElements() {
        return worldElements;
    }

    public void setWorldElements(List<WorldElement> worldElements) {
        this.worldElements = worldElements;
    }*/

    @Override
    public String toString() {
        return "World{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", stories=" + stories +
                '}';
    }
}
