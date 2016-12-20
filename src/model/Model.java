package model;

import javafx.animation.AnimationTimer;
import view.FpsCounter;
import view.MainView;
import view.SettingsView;

public class Model {
    private MainView mainView;
    private SettingsView settingsView;
    private ParticleSystem particleSystem = new ParticleSystem();

    public Model(MainView mainView, SettingsView settingsView) {
        this.mainView = mainView;
        this.settingsView = settingsView;
        mainView.add(particleSystem);
    }

    public void start() {
        new AnimationTimer() {
            FpsCounter fpsCounter = new FpsCounter();
            @Override
            public void handle(long now) {
                particleSystem.loop();
                fpsCounter.update(now);
                settingsView.updateParticles(particleSystem.getSize());
                settingsView.updateFps(fpsCounter.getFrameRate());
            }
        }.start();
    }

    public void rotateCameraOnMainView(double dx, double dy) {
        mainView.rotateCamera(dx, dy);
    }

    public void resetCameraOnMainView() {
        mainView.resetCamera();
    }

    public void showSettings() {
        settingsView.show();
    }
}
