package bt2.tam;

import bt2.sau.MyPoint;

public class MyCircle {
    private MyPoint center;
    private int radius;

    public MyCircle() {
        center = new MyPoint();
        radius = 1;
    }
    public MyCircle(int x,int y,int radius) {
        center = new MyPoint(x,y);
        this.radius = radius;
    }
    public MyCircle(MyPoint center,int radius) {
        this.center = center;
        this.radius = radius;
    }

    public MyPoint getCenter() {
        return center;
    }

    public void setCenter(MyPoint center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    public int getCenterX() {
        return center.getX();
    }
    public int getCenterY() {
        return center.getY();
    }
    public void setCenterX(int x) {
        center.setX(x);
    }
    public void setCenterY(int y) {
        center.setY(y);
    }
    public int[] getCenterXY() {
        return new int[]{center.getX(),center.getY()};
    }
    public void setCenterXY(int x,int y) {
        center.setXY(center.getX(),center.getY());
    }
    public String toString() {
        return "MyCircle[radius = " + radius + ",center = " +center.toString() + "]";
    }
    public double getArea() {
        return Math.pow(radius,2) * Math.PI;
    }
    public double getCircumference() {
        return 2*radius*Math.PI;
    }
    public  double distance(MyCircle another) {
        return center.distance(another.center);
    }
}
