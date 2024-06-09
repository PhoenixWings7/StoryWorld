package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ChapterScene {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    //private List<WorldElement> worldElements = new ArrayList<>();

    public ChapterScene() {
        this.title = "New chapter";
    }

    public ChapterScene(String title) {
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

/*    public List<WorldElement> getWorldElements() {
        return worldElements;
    }

    public void setWorldElements(List<WorldElement> worldElements) {
        this.worldElements = worldElements;
    }*/
}
