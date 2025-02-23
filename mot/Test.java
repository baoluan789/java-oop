package bt1.mot;

public class Test {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        System.out.println(c1);
        c1.setColor("blue");
        System.out.println(c1);
        Circle c2 = new Circle(2.0,"black");
        System.out.println(c2);
        System.out.println(c2.Area());
    }
}
