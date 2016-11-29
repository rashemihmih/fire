import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class Main extends Application {
    final Group root = new Group();
    final Xform axisGroup = new Xform();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private static final double CAMERA_INITIAL_DISTANCE = -300;
    private static final double CAMERA_INITIAL_X_ANGLE = 90;
    private static final double CAMERA_INITIAL_Y_ANGLE = 0;
    private static final double CAMERA_INITIAL_TRANSLATE_Y = -40;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

    private void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);
        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        camera.setTranslateY(CAMERA_INITIAL_TRANSLATE_Y);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }

    private void buildAxes() {
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.RED);
        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.GREEN);
        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.BLUE);
        final Box xAxis = new Box(50, 1, 1);
        final Box yAxis = new Box(1, 50, 1);
        final Box zAxis = new Box(1, 1, 50);
        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);
        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(false);
        world.getChildren().addAll(axisGroup);
    }

    private void handleMouse(Scene scene, final Node root) {
        scene.setOnMousePressed(mouseEventEventHandler -> {
            mousePosX = mouseEventEventHandler.getSceneX();
            mousePosY = mouseEventEventHandler.getSceneY();
            mouseOldX = mouseEventEventHandler.getSceneX();
            mouseOldY = mouseEventEventHandler.getSceneY();
        });
        scene.setOnMouseDragged(mouseEventEventHandler -> {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = mouseEventEventHandler.getSceneX();
            mousePosY = mouseEventEventHandler.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);
            if (mouseEventEventHandler.isPrimaryButtonDown()) {
                cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX*MOUSE_SPEED*ROTATION_SPEED);
                cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY*MOUSE_SPEED*ROTATION_SPEED);
            }
            else if (mouseEventEventHandler.isSecondaryButtonDown()) {
                final double z = camera.getTranslateZ();
                final double newZ = z + mouseDeltaX*MOUSE_SPEED;
                camera.setTranslateZ(newZ);
            }
            else if (mouseEventEventHandler.isMiddleButtonDown()) {
                cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX*MOUSE_SPEED*TRACK_SPEED);
                cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY*MOUSE_SPEED*TRACK_SPEED);
            }
        });
    }

    private void handleKeyboard(Scene scene, final Node root) {
        scene.setOnKeyPressed(keyEventHandler -> {
            switch (keyEventHandler.getCode()) {
                case Z:
                    cameraXform2.t.setX(0.0);
                    cameraXform2.t.setY(0.0);
                    camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                    cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                    cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                    break;
                case X:
                    axisGroup.setVisible(!axisGroup.isVisible());
                    break;
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {
        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);
        buildCamera();
        buildAxes();
        final Scene scene = new Scene(root, 1024, 768, true);
        scene.setFill(Color.GREY);
        handleKeyboard(scene, world);
        handleMouse(scene, world);
        primaryStage.setTitle("Fire");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setCamera(camera);
        final ParticleSystem particleSystem = new ParticleSystem();
        world.getChildren().add(particleSystem);
        final AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                particleSystem.loop();
            }
        };
        animationTimer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
