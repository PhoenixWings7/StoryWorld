package pl.edu.pjwstk.s24987.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pl.edu.pjwstk.s24987.data.StoryWorldDao;
import pl.edu.pjwstk.s24987.data.StoryWorldDaoImpl;
import pl.edu.pjwstk.s24987.model.Chapter;
import pl.edu.pjwstk.s24987.model.ChapterScene;
import pl.edu.pjwstk.s24987.model.Story;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SingleStoryViewController implements Initializable {
    private Story story;
    private Chapter displayedChapter;
    private StoryWorldDao storyWorldDao = new StoryWorldDaoImpl();
    private ObservableList<Chapter> chapters;

    @FXML
    private TextField chapterTitleTextField;

    @FXML
    private ListView<Chapter> chaptersListView;

    @FXML
    private Accordion sceneObjectsAccordion;

    @FXML
    private TextField storyTitleLabel;

    @FXML
    private Button nextChapterButton;

    @FXML
    private Button previousChapterButton;

    @FXML
    private VBox scenesVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        story = storyWorldDao.getSelectedStory();
        storyTitleLabel.setText(story.getTitle());
        chapters = FXCollections.observableArrayList(story.getChapters());
        chaptersListView.setItems(chapters);
    }

    public void onChaptersListViewClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getTarget().getClass().getSimpleName().equals("LabeledText")) {
            // item chosen
            int selectedChapterIndex = chaptersListView.getSelectionModel().getSelectedIndex();
            changeChapterDisplayed(selectedChapterIndex);
        }
    }

    private void changeChapterDisplayed(int selectedChapterIndex) {
        Chapter selectedChapter = story.getChapters().get(selectedChapterIndex);
        displayedChapter = selectedChapter;
        chapterTitleTextField.setText(selectedChapter.getTitle());

        adjustButtons(selectedChapterIndex);
        displayScenes(selectedChapter.getScenes());
        // todo: get scene to object associations and display them
    }

    private void adjustButtons(int selectedChapterIndex) {
        // disable arrows if necessary
        int firstChapterIndex = 0;
        int lastChapterIndex = story.getChapters().size() - 1;
        if (selectedChapterIndex == firstChapterIndex && selectedChapterIndex == lastChapterIndex) {
            previousChapterButton.setDisable(true);
            nextChapterButton.setDisable(true);
        } else if (selectedChapterIndex == firstChapterIndex && selectedChapterIndex < lastChapterIndex) {
            previousChapterButton.setDisable(true);
            nextChapterButton.setDisable(false);
        } else if (selectedChapterIndex > firstChapterIndex && selectedChapterIndex == lastChapterIndex) {
            previousChapterButton.setDisable(false);
            nextChapterButton.setDisable(true);
        } else if (selectedChapterIndex > firstChapterIndex && selectedChapterIndex < lastChapterIndex) {
            previousChapterButton.setDisable(false);
            nextChapterButton.setDisable(false);
        }
        // hide image button if disabled
        previousChapterButton.setVisible(!previousChapterButton.isDisabled());
        nextChapterButton.setVisible(!nextChapterButton.isDisabled());
    }

    private void displayScenes(List<ChapterScene> scenes) {
        // add scenes and their content
        ArrayList<TextArea> sceneNodes = new ArrayList<>(scenes.size());

        for (ChapterScene scene : scenes) {
            TextArea sceneTextArea = new TextArea();
            sceneTextArea.setPromptText("Type text here...");
            sceneTextArea.setText(scene.getContent());
            sceneTextArea.getStyleClass().add("scene-text-area");
            sceneTextArea.setWrapText(true);
            sceneTextArea.setMinSize(200, 100);
            sceneTextArea.setPrefSize(TextArea.USE_COMPUTED_SIZE, TextArea.USE_COMPUTED_SIZE);
            sceneNodes.add(sceneTextArea);
        }

        scenesVBox.getChildren().setAll(sceneNodes);
    }
}
