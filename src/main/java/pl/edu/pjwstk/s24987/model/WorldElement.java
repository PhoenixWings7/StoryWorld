package pl.edu.pjwstk.s24987.model;

import java.util.ArrayList;
import java.util.List;

public abstract class WorldElement {
    private String name;
    private String description;
    private List<ElemAttribute<?>> attributes = new ArrayList<>();
}
