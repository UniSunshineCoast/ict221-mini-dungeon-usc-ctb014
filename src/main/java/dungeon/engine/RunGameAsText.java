package dungeon.engine;

import javafx.scene.text.Text;

import java.util.List;
import java.util.Scanner;

public class RunGameAsText {

    private static final int maxLevel = 2;

    public static void main(String[] args) {
        GameEngine2 engine = new GameEngine2();
        Scanner scanner = new Scanner(System.in);

        //Runs Game In Text Mode
        while (!engine.isGameOver()) {
            engine.printMapToConsole();
            System.out.println("Health: " + engine.getPlayer().getPlayerHealth() + " | Score: " + engine.getPlayer().getPlayerScore());
            System.out.print("Move " + engine.getPlayerSteps() + "/100 (u/d/l/r): ");
            String input = scanner.nextLine();
            engine.movePlayer(input);

            //Ends Game after level has reached maxLevel
            if (engine.getCurrentLevel() > maxLevel){
                break;
            }
        }

        //Loose
        if (engine.isLost()){
            System.out.println("!!YOU LOST!!");
        }


        //Win
        else{
            System.out.println("!!YOU WON!!");
        }


    }
}

