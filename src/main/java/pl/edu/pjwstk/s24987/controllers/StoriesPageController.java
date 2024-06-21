package pl.edu.pjwstk.s24987.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.edu.pjwstk.s24987.data.StoryWorldDao;
import pl.edu.pjwstk.s24987.data.StoryWorldDaoImpl;
import pl.edu.pjwstk.s24987.model.Story;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StoriesPageController implements Initializable {
    private StoryWorldDao storyWorldDao = new StoryWorldDaoImpl();

    @FXML
    private ListView<Story> storiesListView;

    ObservableList<Story> stories = FXCollections.observableArrayList(new Story[]{});

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Story> worldStories = storyWorldDao.getSelectedWorld().getStories();
        stories.setAll(worldStories);
        storiesListView.setItems(stories);
    }

    public void onStoriesListClicked(MouseEvent mouseEvent) throws IOException {
        boolean isDoubleClick = (mouseEvent.getClickCount() == 2);
        if (isDoubleClick && (mouseEvent.getTarget().getClass().getSimpleName().equals("LabeledText"))) {
            // item chosen
            int selectedStoryIndex = storiesListView.getSelectionModel().getSelectedIndex();
            Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            goToSingleStoryView(primaryStage, stories.get(selectedStoryIndex).getId());
        }
    }

    private void goToSingleStoryView(Stage primaryStage, long storyId) throws IOException {
        storyWorldDao.setSelectedStoryId(storyId);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/single_story_view.fxml"));
        Scene singleStoryView = new Scene(fxmlLoader.load());
        primaryStage.setScene(singleStoryView);
    }
}
