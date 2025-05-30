package dungeon.gui;

import dungeon.engine.GameEngine2;

/**
 * This class is just to start up the GUI without requiring any VM options.
 */
public class RunGame {
    public static void main(String[] args) {
        GameEngine2 engine = new GameEngine2();
        GameGUI.setGameEngine(engine);
        GameGUI.main(args);
    }
}
