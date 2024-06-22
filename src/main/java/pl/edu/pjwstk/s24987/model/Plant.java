package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Entity;

@Entity(name = "plants")
public class Plant extends WorldElement {
    public Plant() {
        super();
    }

    public Plant(String name, World world) {
        super(name, world);
    }
}
