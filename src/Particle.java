import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class Particle extends Xform {
    public static final int MAX_LIFE = 70;
    private Vector3D location;
    private Vector3D velocity;
    private Vector3D acceleration;
    int initialLife;
    int life;
    Sphere sphere = new Sphere(0.5);

    public Particle(Vector3D location, Vector3D velocity, Vector3D acceleration, int initialLife) {
        this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;
        getChildren().add(sphere);
        setTx(location.x);
        setTy(location.y);
        setTz(location.z);
        this.initialLife = initialLife;
        life = initialLife;
    }

    public void force(Vector3D force) {
        acceleration.add(force);
    }

    public void move() {
        velocity.add(acceleration);
        location.add(velocity);
        acceleration.set(0, 0, 0);
        life--;
        repaint();
        setTx(location.x);
        setTy(location.y);
        setTz(location.z);
    }

    private void repaint() {
        PhongMaterial material = new PhongMaterial();
        int startRed = 225;
        int startGreen = 40;
        int diffRed = -80;
        int diffGreen = 40;
        int red = (int) (startRed + (double) diffRed * (initialLife - life) / MAX_LIFE);
        int green = (int) (startGreen + (double) diffGreen * (initialLife - life) / MAX_LIFE);
        material.setDiffuseColor(Color.rgb(red, green, 0, 0.7));
        sphere.setMaterial(material);
    }

    public boolean isAlive() {
        return life > 0;
    }
}
