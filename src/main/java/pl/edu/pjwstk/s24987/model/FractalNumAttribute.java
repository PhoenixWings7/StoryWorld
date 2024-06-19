package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Entity;

@Entity(name = "fractal_num_attributes")
public class FractalNumAttribute extends ElemAttribute<Float> {
    public FractalNumAttribute() {
    }

    public FractalNumAttribute(String name, Float value, WorldElement worldElement) {
        this.setName(name);
        this.setValue(value);
        setWorldElement(worldElement);
    }
}
