package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Entity;

@Entity(name = "characters")
public class Character extends WorldElement {
    private String background;

    public Character() {
        this("New character");
    }

    public Character(String name) {
        super(name);
        WholeNumAttribute age = new WholeNumAttribute("Age", null, this);
        TextAttribute personality = new TextAttribute("Personality", null, this);
        TextAttribute physicalAppearance = new TextAttribute("Physical appearance", null, this);
        this.addAttribute(age);
        this.addAttribute(personality);
        this.addAttribute(physicalAppearance);
    }
}
