package pl.edu.pjwstk.s24987.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edu.pjwstk.s24987.data.StoryWorldDao;
import pl.edu.pjwstk.s24987.data.StoryWorldDaoImpl;

import java.io.IOException;

public class LoginViewController {
    private final StoryWorldDao storyWorldDao = new StoryWorldDaoImpl();

    @FXML
    private Label appTitleLabel;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signInButton;

    @FXML
    void onSignIn(ActionEvent event) throws IOException {
        String loginEntered = loginTextField.getText();
        String passwordEntered = passwordTextField.getText();

        boolean signInSuccessful = storyWorldDao.signIn(loginEntered, passwordEntered);
        if (signInSuccessful) {
            System.out.println("Signed in!");
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/home_view.fxml"));
            Scene homeViewScene = new Scene(fxmlLoader.load());
            stage.setMaximized(true);
            stage.setScene(homeViewScene);
        }
    }

}
