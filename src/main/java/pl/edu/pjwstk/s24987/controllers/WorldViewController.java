package pl.edu.pjwstk.s24987.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import pl.edu.pjwstk.s24987.data.StoryWorldDao;
import pl.edu.pjwstk.s24987.data.StoryWorldDaoImpl;
import pl.edu.pjwstk.s24987.model.World;

public class WorldViewController {
    private final StoryWorldDao storyWorldDao = new StoryWorldDaoImpl();
    private World world;

    @FXML
    private Tab worldTab;

    public void onWorldTabSelected(Event event) {
        Long worldId = storyWorldDao.getSelectedWorldId();
        world = storyWorldDao.getWorldData(worldId);
        worldTab.setText(STR."World: \{world.getName()}");
    }

}
