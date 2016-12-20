package view;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import model.Settings;

public class Camera {
    private Xform xform = new Xform();

    @SuppressWarnings("MagicNumber")
    public Camera(Scene scene) {
        final Xform xform1 = new Xform();
        xform.getChildren().add(xform1);
        final Xform xform2 = new Xform();
        xform1.getChildren().add(xform2);
        final PerspectiveCamera camera = new PerspectiveCamera(true);
        xform2.getChildren().add(camera);
        xform2.setRotateZ(180.0);
        camera.setNearClip(Settings.CAMERA_NEAR_CLIP);
        camera.setFarClip(Settings.CAMERA_FAR_CLIP);
        camera.setTranslateZ(Settings.CAMERA_INITIAL_DISTANCE);
        camera.setTranslateY(Settings.CAMERA_INITIAL_TRANSLATE_Y);
        xform.setRotateY(Settings.CAMERA_INITIAL_Y_ANGLE);
        xform.setRotateX(Settings.CAMERA_INITIAL_X_ANGLE);
        scene.setCamera(camera);
    }

    public void rotate(double dx, double dy) {
        xform.setRotateY(xform.getRotateY() - dx * Settings.MOUSE_SPEED * Settings.ROTATION_SPEED);
        xform.setRotateX(xform.getRotateX() + dy * Settings.MOUSE_SPEED * Settings.ROTATION_SPEED);
    }

    public void reset() {
        xform.setRotateY(Settings.CAMERA_INITIAL_Y_ANGLE);
        xform.setRotateX(Settings.CAMERA_INITIAL_X_ANGLE);
    }
}
