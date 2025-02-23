package bt6.mot;

public class Square extends Rectangle {
    public Square() {
        super();
    }
    public Square(double side) {
        super(side,side);
    }
    public Square(double side,String color,boolean filled) {
        super(color,filled,side,side);
    }
    public void setLength(double side) {
        super.setLength(side);
        super.setWidth(side);
    }
    public void setWidth(double side) {
        super.setLength(side);
        super.setWidth(side);
    }
    public String toString() {
        return "Square[" + super.toString() + ",width = " + super.getWidth() + ",length = " + super.getLength() + "]";
    }
}
