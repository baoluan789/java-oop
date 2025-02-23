package bt4.ba;

public class Point2d {
    private float x;
    private float y;

    public Point2d(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Point2d() {
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
