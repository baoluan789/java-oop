package bt4.ba;
import java.util.Arrays;

public class Point3d extends Point2d{
    private float z;
    public Point3d() {
        super();
        z = 1.0f;
    }
    public Point3d(float x,float y,float z) {
        super(x,y);
        this.z = z;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
    public float[] getXYZ() {
        float[] xy = super.getXY(); // Giả sử getXY() trả về float[]
        float[] xyz = Arrays.copyOf(xy, xy.length + 1);
        xyz[xy.length] = z;
        return xyz;
    }
    public void setXYZ(float x,float y,float z) {
        super.setXY(x,y);
        this.z = z;
    }
    public String toString() {
        return super.toString() + "," + z + ")";
    }

}
