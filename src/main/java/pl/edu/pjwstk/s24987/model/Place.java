package pl.edu.pjwstk.s24987.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "places")
public class Place extends WorldElement {
    @Enumerated
    private PlaceType type;
    @OneToMany
    private List<Place> primaryPlaces = new ArrayList<>();
    @OneToMany
    private List<Place> secondaryPlaces = new ArrayList<>();

    public Place() {
        super();
    }

    public Place(String name, World world) {
        super(name, world);
    }

    public PlaceType getType() {
        return type;
    }

    public void setType(PlaceType type) {
        this.type = type;
    }

    /**
     * @return places that contain this place
     */
    public List<Place> getPrimaryPlaces() {
        return primaryPlaces;
    }

    public void setPrimaryPlaces(List<Place> primaryPlaces) {
        this.primaryPlaces = primaryPlaces;
    }

    /**
     * @return places that this place contains
     */
    public List<Place> getSecondaryPlaces() {
        return secondaryPlaces;
    }

    public void setSecondaryPlaces(List<Place> secondaryPlaces) {
        this.secondaryPlaces = secondaryPlaces;
    }

    /**
     * Links this place to a primary place & vice versa, then reevaluates types of both places
     * @param primaryPlace a Place object
     */
    public void linkPrimaryPlace(Place primaryPlace) {
        primaryPlaces.add(primaryPlace);
        primaryPlace.linkSecondaryPlace(this);
        reevaluatePlaceType();
    }

    /**
     * Unlinks this place from a primary place & vice versa, then reevaluates types of both places
     * @param primaryPlace a Place object
     */
    public void unlinkPrimaryPlace(Place primaryPlace) {
        primaryPlaces.remove(primaryPlace);
        primaryPlace.unlinkSecondaryPlace(this);
        reevaluatePlaceType();
    }

    /**
     * Links this place to a secondary place & vice versa, then reevaluates types of both places
     * @param secondaryPlace a Place object
     */
    public void linkSecondaryPlace(Place secondaryPlace) {
        secondaryPlaces.add(secondaryPlace);
        secondaryPlace.linkPrimaryPlace(this);
        reevaluatePlaceType();
    }

    /**
     * Unlinks this place from a secondary place & vice versa, then reevaluates types of both places
     * @param secondaryPlace a Place object
     */
    public void unlinkSecondaryPlace(Place secondaryPlace) {
        secondaryPlaces.remove(secondaryPlace);
        secondaryPlace.unlinkPrimaryPlace(this);
        reevaluatePlaceType();
    }

    private void reevaluatePlaceType() {
        int primaryPlacesNum = primaryPlaces.size();
        int secondaryPlacesNum = secondaryPlaces.size();
        if (primaryPlacesNum == 0 && secondaryPlacesNum > 0) {
            type = PlaceType.PRIMARY;
        } else if (primaryPlacesNum > 0 && secondaryPlacesNum == 0) {
            type = PlaceType.SECONDARY;
        } else {
            type = PlaceType.MIDDLE;
        }
    }

    public enum PlaceType {
        PRIMARY, MIDDLE, SECONDARY
    }
}
