package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(nullable = false)
    @Basic(optional = false)
    private String title;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ChapterScene> scenes = new ArrayList<>();
    @ManyToOne
    private Story story;

    public Chapter() {
        this.title = "New chapter";
    }

    public Chapter(String title, Story story) {
        this.title = title;
        this.story = story;
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

    public List<ChapterScene> getScenes() {
        return scenes;
    }

    public void setScenes(List<ChapterScene> scenes) {
        this.scenes = scenes;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
