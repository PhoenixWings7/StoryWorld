package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(nullable = false)
    @Basic(optional = false)
    private String title;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Chapter> chapters = new ArrayList<>();
    @ManyToOne
    World world;

    public Story() {
    }

    public Story(String title, World world) {
        this.title = title;
        this.world = world;
        addNewChapter();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic(optional = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Adds a new chapter to the story with a default title
     */
    public void addNewChapter() {
        Chapter newChapter = new Chapter("Chapter", this);
        chapters.add(newChapter);
    }

    @Override
    public String toString() {
        return title;
    }
}
