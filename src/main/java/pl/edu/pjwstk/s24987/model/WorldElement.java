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
    @OneToMany
    private List<ElemAttribute<?>> attributes = new ArrayList<>();


    public WorldElement() {
        this.name = "Element";
    }

    public WorldElement(String name) {
        this.name = name;
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
