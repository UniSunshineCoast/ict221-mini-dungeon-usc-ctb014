package dungeon.engine;

public class Ladder extends GameObject{

    public Ladder(){
        this.blocksMovement = false;
    }


    public String getSymbol() {return("L");}

    public String getImagePath() {return "/Ladder.png";}
}
