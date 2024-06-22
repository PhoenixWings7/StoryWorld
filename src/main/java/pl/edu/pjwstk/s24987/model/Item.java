package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Entity;

@Entity(name = "items")
public class Item extends WorldElement {
    public Item() {
        super();
    }

    public Item(String name, World world) {
        super(name, world);
    }
}
