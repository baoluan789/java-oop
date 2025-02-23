package bt2.bay;

import bt2.sau.MyPoint;

public class MyLine {
    private MyPoint begin;
    private MyPoint end;

    public MyLine(int x1,int x2,int y1,int y2) {
        begin = new MyPoint(x1,y1);
        end = new MyPoint(x2,y2);
    }
    public MyLine(MyPoint begin,MyPoint end) {
        this.begin = begin;
        this.end = end;
    }

    public MyPoint getBegin() {
        return begin;
    }

    public void setBegin(MyPoint begin) {
        this.begin = begin;
    }

    public MyPoint getEnd() {
        return end;
    }

    public void setEnd(MyPoint end) {
        this.end = end;
    }
    public int getBeginX() {
        return begin.getX();
    }
    public int getBeginY() {
        return begin.getY();
    }
    public int getEndX() {
        return end.getX();
    }
    public int getEndY() {
        return end.getY();
    }
    public void setBeginX(int x1) {
        begin.setX(x1);
    }
    public void setBeginY(int y1) {
        begin.setY(y1);
    }
    public void setEndX(int x2) {
        end.setX(x2);
    }
    public void setEndY(int y2) {
        end.setY(y2);
    }
    public int[] getBeginXY() {
        return new int[]{begin.getX(),begin.getY()};
    }
    public int[] getEndXY() {
        return new int[]{end.getX(),end.getY()};
    }
    public void setBeginXY(int x1,int y1) {
        begin.setXY(x1,y1);
    }
    public void setEndXY(int x2,int y2) {
        end.setXY(x2,y2);
    }
    public double Length() {
        return begin.distance(end);
    }
    public double getGradient() {
        int x1,x2,y1,y2;
        x1 = begin.getX();
        x2 = end.getX();
        y1 = begin.getY();
        y2 = end.getY();
        return Math.atan2((y2-y1),(x2-x1));
    }
    public String toString() {
        return "MyLine[Begin = " + begin.toString() + ",End = " + end.toString();
    }
}
