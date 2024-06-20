package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "world_elems")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // zapisywanie klas pochodnych - podejście 1 tabeli na typ
public abstract class WorldElement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String description;
    @ManyToOne
    private World world;
    @ManyToMany
    private List<ChapterScene> scenes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<ElemAttribute<?>> attributes = new ArrayList<>();


    public WorldElement() {
        this.name = "Element";
    }

    public WorldElement(String name, World world) {
        this.name = name;
        this.world = world;
        world.addElement(this);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public List<ChapterScene> getScenes() {
        return scenes;
    }

    public void setScenes(List<ChapterScene> scenes) {
        this.scenes = scenes;
    }

    public List<ElemAttribute<?>> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ElemAttribute<?>> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(TextAttribute attribute) {
        attributes.add(attribute);
    }

    public void addAttribute(WholeNumAttribute attribute) {
        attributes.add(attribute);
    }

    public void addAttribute(FractalNumAttribute attribute) {
        attributes.add(attribute);
    }

    public void deleteAttribute(ElemAttribute<?> attribute) {
        attributes.remove(attribute);
    }

    protected void linkScene(ChapterScene scene) {
        scenes.add(scene);
    }

    protected void unlinkScene(ChapterScene scene) {
        scenes.remove(scene);
    }

    @Override
    public String toString() {
        return "WorldElement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", world=" + world +
                '}';
    }
}
