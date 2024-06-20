package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "scenes")
public class ChapterScene {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    private Chapter chapter;
    private String content;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<WorldElement> worldElements = new ArrayList<>();

    public ChapterScene() {}

    public ChapterScene(Chapter chapter) {
        this.chapter = chapter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<WorldElement> getWorldElements() {
        return worldElements;
    }

    public void setWorldElements(List<WorldElement> worldElements) {
        this.worldElements = worldElements;
    }

    public void linkWorldElement(WorldElement worldElement) {
        worldElements.add(worldElement);
        worldElement.linkScene(this);
    }

    public void unlinkWorldElement(WorldElement worldElement) {
        worldElements.remove(worldElement);
        worldElement.unlinkScene(this);
    }

    public boolean checkLinkToElement(WorldElement worldElement) {
        return worldElements.contains(worldElement);
    }
}
