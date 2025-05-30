package dungeon.gui;

import dungeon.engine.GameEngine2;
import dungeon.engine.GameObject;
import dungeon.engine.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Controller {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label playerStatsLabel;

    private GameEngine2 engine;

    private static final int maxLevel = 2;  // Same as in RunGameAsText

    // Called from GameGUI
    public void setGameEngine(GameEngine2 engine) {
        this.engine = engine;
        updateGui();
    }

    @FXML
    private void moveUp() {
        if (!engine.isGameOver() && engine.getCurrentLevel() <= maxLevel) {
            engine.movePlayer("u");
            updateGui();
        }
    }

    @FXML
    private void moveDown() {
        if (!engine.isGameOver() && engine.getCurrentLevel() <= maxLevel) {
            engine.movePlayer("d");
            updateGui();
        }
    }

    @FXML
    private void moveLeft() {
        if (!engine.isGameOver() && engine.getCurrentLevel() <= maxLevel) {
            engine.movePlayer("l");
            updateGui();
        }
    }

    @FXML
    private void moveRight() {
        if (!engine.isGameOver() && engine.getCurrentLevel() <= maxLevel) {
            engine.movePlayer("r");
            updateGui();
        }
    }

    private void updateGui() {
        gridPane.getChildren().clear();

        // Redraw the grid
        for (int y = 0; y < engine.getSizeY(); y++) {
            for (int x = 0; x < engine.getSizeX(); x++) {
                GameObject obj = null;

                if (engine.getPlayer().getX() == x && engine.getPlayer().getY() == y) {
                    obj = engine.getPlayer();
                } else {
                    obj = engine.getMap().getObjectInCell(x, y, GameObject.class);
                }

                if (obj != null && obj.getImagePath() != null) {
                    try {
                        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(obj.getImagePath())));
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(60);
                        imageView.setFitHeight(60);
                        gridPane.add(imageView, x, y);
                    } catch (Exception e) {
                        Label label = new Label(obj.getSymbol());
                        label.setMinSize(60, 60);
                        label.setStyle("-fx-border-color: black; -fx-alignment: center; -fx-font-size: 24px;");
                        gridPane.add(label, x, y);
                    }
                } else {
                    Label label = new Label(obj != null ? obj.getSymbol() : ".");
                    label.setMinSize(60, 60);
                    label.setStyle("-fx-border-color: black; -fx-alignment: center; -fx-font-size: 24px;");
                    gridPane.add(label, x, y);
                }
            }
        }

        // Update player stats or game over message
        String stats;
        if (engine.isGameOver() || engine.getCurrentLevel() > maxLevel) {
            if (engine.isLost()) {
                stats = "💀 YOU LOST! 💀";
            } else if (engine.getCurrentLevel() > maxLevel) {
                stats = "🎉 YOU WON! 🎉";
            } else {
                stats = "Game Over.";
            }
        } else {
            stats = "Health: " + engine.getPlayer().getPlayerHealth() +
                    " | Score: " + engine.getPlayer().getPlayerScore() +
                    " | Steps: " + engine.getPlayerSteps() +
                    " | Level: " + engine.getCurrentLevel() +
                    " | Difficulty: " + engine.getDifficulty();
        }


        playerStatsLabel.setText(stats);
    }

}
