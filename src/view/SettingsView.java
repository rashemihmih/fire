package view;

import javafx.beans.property.Property;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Settings;

public class SettingsView extends View {
    private Label lblParticles = new Label();
    private Label lblFps = new Label();

    public SettingsView() {
        stage.setScene(new Scene(buildSettingsPane()));
        stage.setTitle("���������");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
    }

    public void updateParticles(int particles) {
        lblParticles.setText(String.valueOf(particles));
    }

    public void updateFps(double fps) {
        lblFps.setText(String.valueOf(fps));
    }

    @SuppressWarnings("MagicNumber")
    private VBox buildSeparator(String text) {
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

    private Slider buildSlider(Property<Number> observable, double min, double max) {
        final Slider slider = new Slider(min, max, observable.getValue().doubleValue());
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.valueProperty().bindBidirectional(observable);
        return slider;
    }

    @SuppressWarnings("MagicNumber")
    private GridPane buildSettingsPane() {
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
        gp.addRow(rowIndex++, buildSeparator("����������"));
        gp.addRow(rowIndex++, new Label("������"), new Label("��������� �����"));
        gp.addRow(rowIndex++, new Label("�������� ������"), new Label("C"));
        gp.addRow(rowIndex++, new Label("�������� ���������"), new Label("R"));
        gp.addRow(rowIndex++, new Label("��������� ���������"), new Label("S"));
        gp.addRow(rowIndex++, new Label("��������� ���������"), new Label("L"));
        gp.addRow(rowIndex++, new Label("������� ��� ����"), new Label("O"));
        gp.addRow(rowIndex++, buildSeparator("����������"));
        gp.addRow(rowIndex++, new Label("���������� ������"), lblParticles);
        gp.addRow(rowIndex++, new Label("FPS"), lblFps);
        gp.addRow(rowIndex++, buildSeparator("������� ������"));
        gp.addRow(rowIndex++, new Label("�������������"), buildSlider(Settings.emitterPowerProperty(), 0, 300));
        gp.addRow(rowIndex++, buildSeparator("����"));
        gp.addRow(rowIndex++, new Label("�������"), buildSlider(Settings.initialRedProperty(), 0, 255));
        gp.addRow(rowIndex++, new Label("��������� ��������"), buildSlider(Settings.diffRedProperty(), -255, 255));
        gp.addRow(rowIndex++, new Label("�������"), buildSlider(Settings.initialGreenProperty(), 0, 255));
        gp.addRow(rowIndex++, new Label("��������� ��������"), buildSlider(Settings.diffGreenProperty(), -255, 255));
        gp.addRow(rowIndex++, new Label("�����"), buildSlider(Settings.initialBlueProperty(), 0, 255));
        gp.addRow(rowIndex, new Label("��������� ������"), buildSlider(Settings.diffBlueProperty(), -255, 255));
        return gp;
    }
}
