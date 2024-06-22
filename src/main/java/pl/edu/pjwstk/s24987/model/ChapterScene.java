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

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<WorldElement> getWorldElements() {
        return worldElements;
    }

    public void setWorldElements(List<WorldElement> worldElements) {
        this.worldElements = worldElements;
    }

    /**
     * Links the world element to the scene and vice versa
     * @param worldElement world element to link
     */
    public void linkWorldElement(WorldElement worldElement) {
        worldElements.add(worldElement);
        worldElement.linkScene(this);
    }

    /**
     * Unlinks the world element from the scene and vice versa
     * @param worldElement world element to unlink
     */
    public void unlinkWorldElement(WorldElement worldElement) {
        worldElements.remove(worldElement);
        worldElement.unlinkScene(this);
    }

    /**
     * Checks if the scene is currently linked to the world element
     * @param worldElement world element to check
     * @return true if the scene is linked to the world element
     */
    public boolean checkLinkToElement(WorldElement worldElement) {
        return worldElements.contains(worldElement);
    }
}
