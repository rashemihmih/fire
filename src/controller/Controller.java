package controller;

import javafx.scene.Scene;
import model.Model;
import model.Settings;

public class Controller {
    private Model model;
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;

    public Controller(Model model) {
        this.model = model;
    }

    public void handleMouse(Scene scene) {
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
                model.rotateCameraOnMainView(mouseDeltaX, mouseDeltaY);
            }
        });
    }

    @SuppressWarnings("EnumSwitchStatementWhichMissesCases")
    public void handleKeyboard(Scene scene) {
        scene.setOnKeyPressed(keyEventHandler -> {
            switch (keyEventHandler.getCode()) {
                case C:
                    model.resetCameraOnMainView();
                    break;
                case O:
                    model.showSettings();
                    break;
                case R:
                    Settings.reset();
                    break;
                case S:
                    Settings.save();
                case L:
                    Settings.load();
                default:
                    break;
            }
        });
    }
}
