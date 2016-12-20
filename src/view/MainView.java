package view;

import controller.Controller;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@SuppressWarnings("MagicNumber")
public class MainView extends View {
    private Xform world = new Xform();
    private Group root = new Group();
    private Scene scene = new Scene(root, 1024, 768, true);
    private Camera camera = new Camera(scene);

    public MainView(Stage stage) {
        super(stage);
        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);
        scene.setFill(Color.BLACK);
        stage.setTitle("Огонь");
        stage.setScene(scene);
    }

    public void add(Node node) {
        world.getChildren().add(node);
    }

    public void rotateCamera(double dx, double dy) {
        camera.rotate(dx, dy);
    }

    public void resetCamera() {
        camera.reset();
    }

    public void applyController(Controller controller) {
        controller.handleMouse(scene);
        controller.handleKeyboard(scene);
    }
}
