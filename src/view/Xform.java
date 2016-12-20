package view;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Xform extends Group {
    private Translate translate = new Translate();
    private Rotate rotateX = new Rotate();
    private Rotate rotateY = new Rotate();
    private Rotate rotateZ = new Rotate();

    public Xform() {
        super();
        rotateX.setAxis(Rotate.X_AXIS);
        rotateY.setAxis(Rotate.Y_AXIS);
        rotateZ.setAxis(Rotate.Z_AXIS);
        getTransforms().addAll(translate, rotateZ, rotateY, rotateX);
    }

    public double getTx() {
        return translate.getX();
    }

    public void setTx(double x) {
        translate.setX(x);
    }

    public double getTy() {
        return translate.getY();
    }

    public void setTy(double y) {
        translate.setY(y);
    }

    public double getTz() {
        return translate.getZ();
    }

    public void setTz(double z) {
        translate.setZ(z);
    }

    public double getRotateX() {
        return rotateX.getAngle();
    }

    public void setRotateX(double x) {
        rotateX.setAngle(x);
    }

    public double getRotateY() {
        return rotateY.getAngle();
    }

    public void setRotateY(double y) {
        rotateY.setAngle(y);
    }

    public double getRotateZ() {
        return rotateZ.getAngle();
    }

    public void setRotateZ(double z) {
        rotateZ.setAngle(z);
    }
}
