package dungeon.engine;

public class Wall extends GameObject{

    public Wall(){
        this.blocksMovement = true;
    }

    public String getSymbol() {return "#";}
}
