package dungeon.engine;




public class Entry extends GameObject{


    //Constructor
    public Entry(){
        this.blocksMovement = false;
    }

    public String getSymbol() {return "E";}

    public String getImagePath() {return "/Door.png";}
}
