package pl.edu.pjwstk.s24987.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pl.edu.pjwstk.s24987.model.Story;

import java.net.URL;
import java.util.ResourceBundle;

public class StoriesPageController implements Initializable {

    @FXML
    private ListView<Story> storiesListView;

    @FXML
    private TextField storyTitleTextField;

    ObservableList<Story> stories = FXCollections.observableArrayList(new Story[]{new Story("Story 1"), new Story("Story 2")});

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storiesListView.setItems(stories);
    }
}
