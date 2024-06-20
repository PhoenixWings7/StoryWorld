package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Entity;

@Entity(name = "animals")
public class Animal extends WorldElement {
    public Animal() {
        super();
    }

    public Animal(String name, World world) {
        super(name, world);
    }
}
