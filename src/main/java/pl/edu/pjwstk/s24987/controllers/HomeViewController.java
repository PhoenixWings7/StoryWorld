package pl.edu.pjwstk.s24987.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.edu.pjwstk.s24987.data.StoryWorldDao;
import pl.edu.pjwstk.s24987.data.StoryWorldDaoImpl;
import pl.edu.pjwstk.s24987.model.World;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {
    private final StoryWorldDao storyWorldDao = new StoryWorldDaoImpl();
    ObservableList<World> userWorlds = FXCollections.observableArrayList();
    @FXML
    private Button accountBtn;

    @FXML
    private Label appTitleLabel;

    @FXML
    private Button homeBtn;

    @FXML
    private Button newWorldBtn;

    @FXML
    private ListView<World> worldsListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        worldsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        List<World> names = storyWorldDao.getAllWorlds();
        userWorlds.setAll(names);
        worldsListView.setItems(userWorlds);
    }

    public void onWorldsListClicked(MouseEvent mouseEvent) throws IOException {
        boolean isDoubleClick = (mouseEvent.getClickCount() == 2);
        if (isDoubleClick && (mouseEvent.getTarget().getClass().getSimpleName().equals("LabeledText"))) {
            // item chosen
            int selectedWorldIndex = worldsListView.getSelectionModel().getSelectedIndex();
            Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            goToWorldView(primaryStage, userWorlds.get(selectedWorldIndex).getId());
        }
    }

    private void goToWorldView(Stage primaryStage, Long worldId) throws IOException {
        primaryStage.setUserData(worldId);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/world_view.fxml"));
        Scene worldViewScene = new Scene(fxmlLoader.load());
        primaryStage.setScene(worldViewScene);
    }
}

