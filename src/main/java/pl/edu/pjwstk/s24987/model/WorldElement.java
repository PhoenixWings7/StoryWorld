package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class WorldElement {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    // TODO: implement later
    //private List<ElemAttribute<?>> attributes = new ArrayList<>();


    public WorldElement() {
        this.name = "Element";
    }

    public WorldElement(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
