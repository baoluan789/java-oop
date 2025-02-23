package bt6.tam;

public class MovablePoint {
    int x,y,xSpeed,ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public String toString() {
        return "(" + x + "," + y + ")"+",Speed(" + xSpeed + "," + ySpeed + ")";
    }
    public void moveUp() {
        y -= ySpeed;
    }
    public void moveDown() {
        y +=ySpeed;
    }
    public void moveLeft() {
        x -= xSpeed;
    }
    public void moveRight() {
        x += xSpeed;
    }
}
