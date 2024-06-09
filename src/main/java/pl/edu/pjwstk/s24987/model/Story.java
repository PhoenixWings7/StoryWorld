package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Story {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    //private List<Chapter> chapters = new ArrayList<>();
    @ManyToOne
    World world;

    public Story() {
    }

    public Story(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic(optional = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

/*    public List<Chapter> getChapters() {
        return chapters;
    }*/

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
