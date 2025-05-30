package dungeon.engine;

public class Health extends GameObject{

    private final int healthValue; //How much health effect the potion will have

    public Health(int healthValue) {
        this.healthValue = healthValue;
        this.blocksMovement = false;
    }

    public int getHealthValue() {
        return healthValue;
    }

    public String getSymbol() {return("H");}

    public String getImagePath() {return "/Health.png";}
}
