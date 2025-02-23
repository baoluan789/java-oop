package bt1.ba;

public class test {
    public static void main(String[]args) {
        Rectangle r1 = new Rectangle();
        System.out.println(r1);
        Rectangle r2 = new Rectangle(2.0f,3.0f);
        System.out.println(r2);

        r1.setLength(5.0f);
        r1.setWidth(2.5f);
        System.out.println(r1);
        System.out.println("length = " + r2.getLength() + " width = " + r2.getWidth());

        System.out.printf("Area: %.2f%n", r2.getArea());
        System.out.printf("perimeter: %.1f%n",r1.getPerimeter());
    }
}
