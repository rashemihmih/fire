import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.*;
import java.util.Properties;

public class Settings {
    public static final double CAMERA_INITIAL_DISTANCE = -300;
    public static final double CAMERA_INITIAL_X_ANGLE = 90;
    public static final double CAMERA_INITIAL_Y_ANGLE = 0;
    public static final double CAMERA_INITIAL_TRANSLATE_Y = -40;
    public static final double CAMERA_NEAR_CLIP = 0.1;
    public static final double CAMERA_FAR_CLIP = 10000.0;
    public static final double MOUSE_SPEED = 0.1;
    public static final double ROTATION_SPEED = 2.0;
    private static DoubleProperty emitterPower = new SimpleDoubleProperty();
    private static IntegerProperty initialRed = new SimpleIntegerProperty();
    private static IntegerProperty diffRed = new SimpleIntegerProperty();
    private static IntegerProperty initialGreen = new SimpleIntegerProperty();
    private static IntegerProperty diffGreen = new SimpleIntegerProperty();
    private static IntegerProperty initialBlue = new SimpleIntegerProperty();
    private static IntegerProperty diffBlue = new SimpleIntegerProperty();

    static {
        reset();
    }

    public static double getEmitterPower() {
        return emitterPower.get();
    }

    public static DoubleProperty emitterPowerProperty() {
        return emitterPower;
    }

    public static void setEmitterPower(double emitterPower) {
        Settings.emitterPower.set(emitterPower);
    }

    public static int getInitialRed() {
        return initialRed.get();
    }

    public static IntegerProperty initialRedProperty() {
        return initialRed;
    }

    public static void setInitialRed(int initialRed) {
        Settings.initialRed.set(initialRed);
    }

    public static int getDiffRed() {
        return diffRed.get();
    }

    public static IntegerProperty diffRedProperty() {
        return diffRed;
    }

    public static void setDiffRed(int diffRed) {
        Settings.diffRed.set(diffRed);
    }

    public static int getInitialGreen() {
        return initialGreen.get();
    }

    public static IntegerProperty initialGreenProperty() {
        return initialGreen;
    }

    public static void setInitialGreen(int initialGreen) {
        Settings.initialGreen.set(initialGreen);
    }

    public static int getDiffGreen() {
        return diffGreen.get();
    }

    public static IntegerProperty diffGreenProperty() {
        return diffGreen;
    }

    public static void setDiffGreen(int diffGreen) {
        Settings.diffGreen.set(diffGreen);
    }

    public static int getInitialBlue() {
        return initialBlue.get();
    }

    public static IntegerProperty initialBlueProperty() {
        return initialBlue;
    }

    public static void setInitialBlue(int initialBlue) {
        Settings.initialBlue.set(initialBlue);
    }

    public static int getDiffBlue() {
        return diffBlue.get();
    }

    public static IntegerProperty diffBlueProperty() {
        return diffBlue;
    }

    public static void setDiffBlue(int diffBlue) {
        Settings.diffBlue.set(diffBlue);
    }

    @SuppressWarnings("MagicNumber")
    public static void reset() {
        emitterPower.set(200);
        initialRed.set(255);
        diffRed.set(-40);
        initialGreen.set(225);
        diffGreen.set(-220);
        initialBlue.set(75);
        diffBlue.set(-255);
    }

    @SuppressWarnings("OverlyBroadCatchBlock")
    public static void save() {
        final Properties properties = new Properties();
        properties.setProperty("emitterPower", String.valueOf(emitterPower.get()));
        properties.setProperty("initialRed", String.valueOf(initialRed.get()));
        properties.setProperty("diffRed", String.valueOf(diffRed.get()));
        properties.setProperty("initialGreen", String.valueOf(initialGreen.get()));
        properties.setProperty("diffGreen", String.valueOf(diffGreen.get()));
        properties.setProperty("initialBlue", String.valueOf(initialBlue.get()));
        properties.setProperty("diffBlue", String.valueOf(diffBlue.get()));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("settings.txt"))) {
            properties.store(writer, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("OverlyBroadCatchBlock")
    public static void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader("settings.txt"))) {
            final Properties properties = new Properties();
            properties.load(reader);
            emitterPower.set(Double.parseDouble(properties.getProperty("emitterPower")));
            initialRed.set(Integer.parseInt(properties.getProperty("initialRed")));
            diffRed.set(Integer.parseInt(properties.getProperty("diffRed")));
            initialGreen.set(Integer.parseInt(properties.getProperty("initialGreen")));
            diffGreen.set(Integer.parseInt(properties.getProperty("diffGreen")));
            initialBlue.set(Integer.parseInt(properties.getProperty("initialBlue")));
            diffBlue.set(Integer.parseInt(properties.getProperty("diffBlue")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
