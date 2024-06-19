package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Entity;

@Entity(name = "whole_num_attributes")
public class WholeNumAttribute extends ElemAttribute<Integer> {
    public WholeNumAttribute() {
    }

    public WholeNumAttribute(String name, Integer value, WorldElement worldElement) {
        this.setName(name);
        this.setValue(value);
        this.setWorldElement(worldElement);
    }
}
