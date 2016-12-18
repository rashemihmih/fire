import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

@SuppressWarnings("MagicNumber")
public class Main extends Application {
    final Stage settings = new Stage();
    final Group root = new Group();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
    Label particles = new Label();
    Label fps = new Label();

    private void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);
        camera.setNearClip(Settings.CAMERA_NEAR_CLIP);
        camera.setFarClip(Settings.CAMERA_FAR_CLIP);
        camera.setTranslateZ(Settings.CAMERA_INITIAL_DISTANCE);
        camera.setTranslateY(Settings.CAMERA_INITIAL_TRANSLATE_Y);
        cameraXform.setRotateY(Settings.CAMERA_INITIAL_Y_ANGLE);
        cameraXform.setRotateX(Settings.CAMERA_INITIAL_X_ANGLE);
    }

    private void buildSettings() {
        settings.setScene(new Scene(createToolbar()));
        settings.setTitle("Настройки");
        settings.setResizable(false);
        settings.setAlwaysOnTop(true);
    }

    private void handleMouse(Scene scene) {
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
                cameraXform.setRotateY(cameraXform.getRotateY() - mouseDeltaX * Settings.MOUSE_SPEED * Settings.ROTATION_SPEED);
                cameraXform.setRotateX(cameraXform.getRotateX() + mouseDeltaY * Settings.MOUSE_SPEED * Settings.ROTATION_SPEED);
            }
        });
    }

    @SuppressWarnings("all")
    private void handleKeyboard(Scene scene) {
        scene.setOnKeyPressed(keyEventHandler -> {
            switch (keyEventHandler.getCode()) {
                case C:
                    cameraXform2.setTy(0);
                    cameraXform2.setTx(0);
                    camera.setTranslateZ(Settings.CAMERA_INITIAL_DISTANCE);
                    cameraXform.setRotateY(Settings.CAMERA_INITIAL_Y_ANGLE);
                    cameraXform.setRotateX(Settings.CAMERA_INITIAL_X_ANGLE);
                    break;
                case O:
                    settings.show();
                    break;
                case R:
                    Settings.reset();
                    break;
                case S:
                    Settings.save();
                case L:
                    Settings.load();
            }
        });
    }

    private Node createSeparator(String text) {
        final VBox box = new VBox();
        final Label label = new Label(text);
        label.setFont(Font.font(null, FontWeight.BOLD, 14));
        final Separator separator = new Separator();
        box.getChildren().addAll(separator, label);
        box.setFillWidth(true);
        GridPane.setColumnSpan(box, 2);
        GridPane.setFillWidth(box, true);
        GridPane.setHgrow(box, Priority.ALWAYS);
        return box;
    }

    private Slider createNumberSlider(Property<Number> observable, double min, double max) {
        final Slider slider = new Slider(min, max, observable.getValue().doubleValue());
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.valueProperty().bindBidirectional(observable);
        return slider;
    }

    public Parent createToolbar() {
        final GridPane gp = new GridPane();
        gp.setPrefWidth(450);
        gp.setHgap(1);
        gp.setVgap(1);
        gp.setPadding(new Insets(8));
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(30);
        gp.getColumnConstraints().add(column);
        column = new ColumnConstraints();
        column.setPercentWidth(70);
        gp.getColumnConstraints().add(column);
        int rowIndex = 0;
        gp.addRow(rowIndex++, createSeparator("Управление"));
        gp.addRow(rowIndex++, new Label("Камера"), new Label("вращается мышью"));
        gp.addRow(rowIndex++, new Label("Сбросить камеру"), new Label("C"));
        gp.addRow(rowIndex++, new Label("Сбросить настройки"), new Label("R"));
        gp.addRow(rowIndex++, new Label("Сохранить настройки"), new Label("S"));
        gp.addRow(rowIndex++, new Label("Загрузить настройки"), new Label("L"));
        gp.addRow(rowIndex++, new Label("Открыть это окно"), new Label("O"));
        gp.addRow(rowIndex++, createSeparator("Информация"));
        gp.addRow(rowIndex++, new Label("Количество частиц"), particles);
        gp.addRow(rowIndex++, new Label("FPS"), fps);
        gp.addRow(rowIndex++, createSeparator("Система частиц"));
        gp.addRow(rowIndex++, new Label("Интенсивность"), createNumberSlider(Settings.emitterPowerProperty(), 100, 300));
        gp.addRow(rowIndex++, createSeparator("Цвет"));
        gp.addRow(rowIndex++, new Label("Красный"), createNumberSlider(Settings.initialRedProperty(), 0, 255));
        gp.addRow(rowIndex++, new Label("Изменение красного"), createNumberSlider(Settings.diffRedProperty(), -255, 255));
        gp.addRow(rowIndex++, new Label("Зеленый"), createNumberSlider(Settings.initialGreenProperty(), 0, 255));
        gp.addRow(rowIndex++, new Label("Изменение зеленого"), createNumberSlider(Settings.diffGreenProperty(), -255, 255));
        gp.addRow(rowIndex++, new Label("Синий"), createNumberSlider(Settings.initialBlueProperty(), 0, 255));
        gp.addRow(rowIndex, new Label("Изменение синего"), createNumberSlider(Settings.diffBlueProperty(), -255, 255));
        return gp;
    }

    @Override
    public void start(Stage primaryStage) {
        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);
        buildCamera();
        buildSettings();
        final Scene scene = new Scene(root, 1024, 768, true);
        scene.setFill(Color.GREY);
        handleKeyboard(scene);
        handleMouse(scene);
        primaryStage.setTitle("Огонь");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setCamera(camera);
        final ParticleSystem particleSystem = new ParticleSystem();
        world.getChildren().add(particleSystem);
        final AnimationTimer animationTimer = new AnimationTimer() {
            FpsCounter fpsCounter = new FpsCounter();
            @Override
            public void handle(long now) {
                particleSystem.loop();
                fpsCounter.update(now);
                particles.setText(String.valueOf(particleSystem.getSize()));
                fps.setText(String.valueOf(fpsCounter.getFrameRate()));
            }
        };
        animationTimer.start();
        settings.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
