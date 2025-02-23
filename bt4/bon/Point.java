package bt4.bon;

public class Point {private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Point() {
        x = 1.0f;
        y = 1.0f;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    public float[] getXY() {
        return new float[]{x,y};
    }
    public void setXY(float x,float y) {
        this.x=x;
        this.y = y;
    }
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
