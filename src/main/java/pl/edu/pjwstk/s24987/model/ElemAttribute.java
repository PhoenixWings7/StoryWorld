package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.*;

@Entity(name = "attributes")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ElemAttribute<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Basic(optional = false)
    @Column(nullable = false)
    private String name;
    @Transient
    private T value;

    @ManyToOne
    private WorldElement worldElement;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public WorldElement getWorldElement() {
        return worldElement;
    }

    public void setWorldElement(WorldElement world) {
        this.worldElement = world;
    }
}
