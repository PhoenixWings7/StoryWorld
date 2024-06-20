package pl.edu.pjwstk.s24987.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pl.edu.pjwstk.s24987.data.StoryWorldDao;
import pl.edu.pjwstk.s24987.data.StoryWorldDaoImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {
    private final StoryWorldDao storyWorldDao = new StoryWorldDaoImpl();
    ObservableList<String> worldNames = FXCollections.observableArrayList();
    @FXML
    private Button accountBtn;

    @FXML
    private Label appTitleLabel;

    @FXML
    private Button homeBtn;

    @FXML
    private Button newWorldBtn;

    @FXML
    private ListView<String> worldsListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> names = storyWorldDao.getWorldNames();
        worldNames.setAll(names);
        worldsListView.setItems(worldNames);
    }
}

