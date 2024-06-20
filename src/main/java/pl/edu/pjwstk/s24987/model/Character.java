package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Entity;

@Entity(name = "characters")
public class Character extends WorldElement {
    private String background;

    public Character() { super(); }

    public Character(String name, World world) {
        super(name, world);
        WholeNumAttribute age = new WholeNumAttribute("Age", null, this);
        TextAttribute personality = new TextAttribute("Personality", null, this);
        TextAttribute physicalAppearance = new TextAttribute("Physical appearance", null, this);
        this.addAttribute(age);
        this.addAttribute(personality);
        this.addAttribute(physicalAppearance);
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
