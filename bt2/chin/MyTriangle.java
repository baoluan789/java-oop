package bt2.chin;

import bt2.sau.MyPoint;

public class MyTriangle {
    private MyPoint v1;
    private MyPoint v2;
    private MyPoint v3;

    public MyTriangle(MyPoint v1, MyPoint v2, MyPoint v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }
    public MyTriangle(int x1,int y1,int x2,int y2,int x3,int y3) {
        v1 = new MyPoint(x1,y1);
        v2 = new MyPoint(x2,y2);
        v3 = new MyPoint(x3,y3);
    }

    public String toString() {
        return "MyTriangle[v1 = " + v1.toString() + ",v2 = " + v2.toString() + ",v3 = " + v3.toString() + "]";
    }
    public double getPerimeter() {
        double AB,AC,BC;
        AB = v2.distance(v1);
        AC = v3.distance(v1);
        BC = v3.distance(v2);
        return  AB + AC + BC;
    }
    public String getType() {
        String a;
        double AB,AC,BC;
        AB = v2.distance(v1);
        AC = v3.distance(v1);
        BC = v3.distance(v2);
        if((AB == AC) && (AC == BC)) {
            a = "equilateral";
        } else if((AB == AC) || (AB == BC) || (AC == BC)) {
            a = "isosceles";
        } else {
            a = "scalene";
        }
        return a;
    }
}
