public class Vector3D {
    double x;
    double y;
    double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Vector3D vector3D) {
        x += vector3D.x;
        y += vector3D.y;
        z += vector3D.z;
    }
}
