package pl.edu.pjwstk.s24987;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.pjwstk.s24987.data.LocalDbHandler;

import java.io.IOException;

public class StoryWorld extends Application {
    private static final String APP_NAME = "Story World";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        LocalDbHandler.connectToDb();
        LocalDbHandler.initializeExampleDb();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        primaryStage.setTitle(APP_NAME);
        primaryStage.setMaximized(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        LocalDbHandler.closeDbConnection();
        super.stop();
    }
}
