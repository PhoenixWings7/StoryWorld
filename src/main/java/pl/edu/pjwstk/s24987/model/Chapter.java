package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Chapter {
    private int id;
    private String title;
    //private List<ChapterScene> scenes = new ArrayList<>();

    public Chapter() {
        this.title = "New chapter";
    }

    public Chapter(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue
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

/*    public List<ChapterScene> getScenes() {
        return scenes;
    }

    public void setScenes(List<ChapterScene> scenes) {
        this.scenes = scenes;
    }*/
}
