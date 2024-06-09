package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Entity;

@Entity(name = "characters")
public class Character extends WorldElement {
    private String background;

    public Character() {
        super("New character");
    }

    public Character(String name) {
        super(name);
    }
}
