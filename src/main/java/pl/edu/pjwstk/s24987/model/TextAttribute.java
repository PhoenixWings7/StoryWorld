package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Entity;

@Entity(name = "text_attributes")
public class TextAttribute extends ElemAttribute<String> {
    public TextAttribute() {
    }

    public TextAttribute(String name, String value, WorldElement worldElement) {
        this.setName(name);
        this.setValue(value);
        setWorldElement(worldElement);
    }
}
