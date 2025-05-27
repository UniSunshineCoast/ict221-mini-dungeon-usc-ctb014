package dungeon.engine;

public abstract class GameObject {
    protected int x;
    protected int y;

    public GameObject() {
        // Default constructor
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public abstract String getSymbol();
}

