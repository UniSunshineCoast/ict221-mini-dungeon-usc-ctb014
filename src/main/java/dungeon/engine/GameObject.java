package dungeon.engine;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected boolean blocksMovement;

    public GameObject() {
        // Default constructor
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }


    public boolean isBlocksMovement(){
        return blocksMovement;
    }


    public abstract String getSymbol();

}

