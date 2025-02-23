package bt2.sau;

public class MyPoint {
    private int x = 0;
    private int y = 0;

    public  MyPoint() {

    }

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int[] getXY() {
        return new int[]{x,y};
    }
    public void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return "(" + x + "," + y +")";
    }
    public double distance(int x,int y) {
        return (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y);
    }
    public double distance(MyPoint another) {
        return Math.sqrt(Math.pow(this.x - another.getX(), 2) + Math.pow(this.y - another.getY(), 2));
    }
    public double distance() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }
}
