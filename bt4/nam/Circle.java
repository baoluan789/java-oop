package bt4.nam;

public class Circle extends Shape{
    private double radius;
    public Circle() {
        super();
        radius = 1.0;
    }
    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    public Circle(String color, boolean filled, double radius) {
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getArea() {
        return Math.PI * radius * radius;
    }
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }
    public String toString() {
        return "Circle[" + super.toString() + ",radius = " + radius;
    }
}
