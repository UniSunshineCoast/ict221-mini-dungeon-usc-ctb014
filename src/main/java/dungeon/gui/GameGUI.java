package dungeon.gui;

import dungeon.engine.GameEngine2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameGUI extends Application {

    // Static reference to GameEngine2
    private static GameEngine2 engine;

    public static void setGameEngine(GameEngine2 gameEngine) {
        engine = gameEngine;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game_gui.fxml"));
        BorderPane root = loader.load();

        // Get the Controller from the FXML
        Controller controller = loader.getController();

        // Pass the GameEngine2 instance to the Controller
        controller.setGameEngine(engine);

        // Create and show the scene
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.setTitle("MiniDungeon Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
