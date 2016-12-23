package model;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import view.Xform;

@SuppressWarnings("MagicNumber")
public class Particle extends Xform {
    public static final int MAX_LIFE = 70;
    private Vector3D location;
    private Vector3D velocity;
    private int initialLife;
    private int life;
    private Sphere sphere = new Sphere(0.3);

    public Particle(Vector3D location, Vector3D velocity, int initialLife) {
        this.location = location;
        this.velocity = velocity;
        getChildren().add(sphere);
        setTx(location.x);
        setTy(location.y);
        setTz(location.z);
        this.initialLife = initialLife;
        life = initialLife;
    }

    public void move() {
        location.add(velocity);
        life--;
        repaint();
        setTx(location.x);
        setTy(location.y);
        setTz(location.z);
    }

    private void repaint() {
        final PhongMaterial material = new PhongMaterial();
        int red = (int) (Settings.getInitialRed() + (double) Settings.getDiffRed() * (initialLife - life) / MAX_LIFE);
        int green = (int) (Settings.getInitialGreen() + (double) Settings.getDiffGreen() * (initialLife - life) / MAX_LIFE);
        int blue = (int) (Settings.getInitialBlue() + (double) Settings.getDiffBlue() * (initialLife - life) / MAX_LIFE);
        if (red < 0) {
            red = 0;
        } else if (red > 255) {
            red = 255;
        }
        if (green < 0) {
            green = 0;
        } else if (green > 255) {
            green = 255;
        }
        if (blue < 0) {
            blue = 0;
        } else if (blue > 255) {
            blue = 255;
        }
        material.setDiffuseColor(Color.rgb(red, green, blue, 0.7));
        sphere.setMaterial(material);
    }

    public boolean isAlive() {
        return life > 0;
    }
}
