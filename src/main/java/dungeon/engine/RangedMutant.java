package dungeon.engine;

import java.util.Random;

public class RangedMutant extends GameObject {
    private static final Random rand = new Random();


    private final int damage; //How much damage the mutant will do to the player
    private final int value; //How much the score will increase if the mutant is defeated

    public RangedMutant(int damage, int value) {
        this.damage = damage;
        this.value = value;
        this.blocksMovement = false;  // or false if you want the player to walk over it
    }

    public String getSymbol() {
        return "R";
    }

    public boolean tryAttack() {
        // 50% chance
        return rand.nextBoolean();
    }

    public int getDamage() {
        return damage;
    }

    public int getValue() {
        return value;
    }
}
