package bt6.mot;

public abstract class  Shape {
    String color;
    boolean filled;

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }
    public Shape() {
        color = "red";
        filled = true;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    public  abstract  double   getArea();
    public abstract double getPerimeter();
    public String toString() {
        return "Shape[color = " + color + ",filled = " + filled;
    }
}
