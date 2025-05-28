package dungeon.engine;

public class MeleeMutant extends GameObject{

    private final int damage; //How much damage the mutant will do to the player
    private final int value; //How much the score will increase if the mutant is defeated

    public MeleeMutant(int damage, int value) {
        this.damage = damage;
        this.value = value;
        this.blocksMovement = false;
    }

    public int getDamage() {
        return damage;
    }

    public int getValue() {
        return value;
    }

    public String getSymbol() {return("M");}


}
