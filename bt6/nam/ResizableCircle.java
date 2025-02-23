package bt6.nam;

public class ResizableCircle extends Circle implements Resizable {
    public ResizableCircle(double radius) {
        super(radius);
    }
    public String toString() {
        return "ResizableCircle" + super.toString() +"]";
    }
    public double resize(int percent) {
        return radius *= percent/100.0;
    }
}
