public class Vector3D {
    double x;
    double y;
    double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public void add(Vector3D vector3D) {
        x += vector3D.x;
        y += vector3D.y;
        z += vector3D.z;
    }

    public void add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void subtract(Vector3D vector3D) {
        x -= vector3D.x;
        y -= vector3D.y;
        z -= vector3D.z;
    }

    public void subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
    }

    public void multiply(double factor) {
        x *= factor;
        y *= factor;
        z *= factor;
    }

    public void divide(double factor) {
        x /= factor;
        y /= factor;
        z /= factor;
    }

    public void normalize() {
        final double magnitude = magnitude();
        if (magnitude != 0 && magnitude != 1) {
            divide(magnitude);
        }
    }

    public void limit(double max) {
        if (magnitude() > max) {
            normalize();
            multiply(max);
        }
    }

    public static Vector3D zero() {
        return new Vector3D(0, 0, 0);
    }
}
