package bt6.bon;

public class MovableCircle {
    private int radius;
    private MovablePoint center;

    public MovableCircle(int x, int y, int xSpeed, int ySpeed,int radius) {
        this.radius = radius;
        MovablePoint center = new MovablePoint(x,y,xSpeed,ySpeed);
    }
    public void moveUp() {
        center.moveUp();
    }
    public void moveDown() {
        center.moveDown();
    }
    public void moveLeft() {
        center.moveLeft();
    }
    public void moveRight() {
        center.moveRight();
    }
    public String toString() {
        return center.toString() + ",radius = " + radius;
    }
}
