package view;

import javafx.stage.Stage;

public abstract class View {
    protected Stage stage;

    public View() {
        stage = new Stage();
    }

    public View(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.show();
    }
}
