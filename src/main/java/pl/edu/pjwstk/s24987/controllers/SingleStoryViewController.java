package pl.edu.pjwstk.s24987.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pl.edu.pjwstk.s24987.data.StoryWorldDao;
import pl.edu.pjwstk.s24987.data.StoryWorldDaoImpl;
import pl.edu.pjwstk.s24987.model.Chapter;
import pl.edu.pjwstk.s24987.model.ChapterScene;
import pl.edu.pjwstk.s24987.model.Story;
import pl.edu.pjwstk.s24987.model.WorldElement;

import java.net.URL;
import java.util.*;

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
        displayChapterWorldElements(selectedChapter.getScenes());
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

    private void displayChapterWorldElements(List<ChapterScene> scenes) {
        ArrayList<TitledPane> panes = new ArrayList<>(scenes.size());
        for (int i = 0; i < scenes.size(); i++) {
            ChapterScene scene = scenes.get(i);

            // create list of objects
            VBox objListBox = new VBox();
            for (WorldElement element : scene.getWorldElements()) {
                Label objNameLabel = new Label(element.getName());
                objNameLabel.getStyleClass().add("obj-list-label");
                objListBox.getChildren().add(objNameLabel);
            }

            // create and add the new TitledPane
            TitledPane pane = getTitledPane(scene, objListBox, i);
            panes.add(pane);
        }
        sceneObjectsAccordion.getPanes().setAll(panes);
    }

    private TitledPane getTitledPane(ChapterScene scene, VBox objListBox, int i) {
        Button linkObjBtn = new Button("Link object");
        linkObjBtn.setOnAction(actionEvent -> onLinkObjBtnClicked(actionEvent, scene));
        VBox contentVBox = new VBox(objListBox, linkObjBtn);
        return new TitledPane(STR."Scene \{i + 1}", contentVBox);
    }

    private void onLinkObjBtnClicked(ActionEvent actionEvent, ChapterScene scene) {
        Dialog<List<WorldElement>> dialog = new Dialog<>();
        dialog.setTitle("Choose objects to link");
        dialog.getDialogPane().getButtonTypes().setAll(ButtonType.APPLY, ButtonType.CANCEL);

        List<WorldElement> allWorldElements = scene.getChapter().getStory().getWorld().getWorldElements();
        ListView<WorldElement> choices = new ListView<>();
        choices.getItems().setAll(allWorldElements);
        List<WorldElement> selected = new ArrayList<>();

        // map world elements to a list of choices
        choices.setCellFactory(CheckBoxListCell.forListView(worldElement -> {
            boolean doSelect = scene.getWorldElements().contains(worldElement);
            if (doSelect && !selected.contains(worldElement))
                selected.add(worldElement);
            ObservableBooleanValue observableValue = new SimpleBooleanProperty(doSelect);
            // handle checkbox state change
            observableValue.addListener((observableVal, wasSelected, isSelected) -> {
                if (!wasSelected && isSelected)
                    selected.add(worldElement);
                else if (wasSelected && !isSelected)
                    selected.remove(worldElement);
            });
            return observableValue;
        }));

        dialog.getDialogPane().setContent(choices);
        dialog.setResultConverter(dialogBtn -> {
            if (!dialogBtn.getButtonData().isCancelButton())
                return selected;
            else return null;
        });
        Optional<List<WorldElement>> selectedElements = dialog.showAndWait();
        handleElemSelectionResult(selectedElements, scene);
    }

    private void handleElemSelectionResult(Optional<List<WorldElement>> selectedElements, ChapterScene scene) {
        if (selectedElements.isPresent() && !selectedElements.get().isEmpty()) {
            // save to database
            if (scene.getWorldElements().size() == selectedElements.get().size()
                    && new HashSet<>(scene.getWorldElements()).containsAll(selectedElements.get())
                    && new HashSet<>(selectedElements.get()).containsAll(scene.getWorldElements()))
                return;
            scene.setWorldElements(selectedElements.get());
            storyWorldDao.updateChapter(displayedChapter);
        }
        // refresh the view
        refreshLinkedObjView();
    }

    private void refreshLinkedObjView() {
        displayChapterWorldElements(displayedChapter.getScenes());
    }
}
