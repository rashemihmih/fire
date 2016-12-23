import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import view.MainView;
import view.SettingsView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        final MainView mainView = new MainView(primaryStage);
        final SettingsView settingsView = new SettingsView();
        final Model model = new Model(mainView, settingsView);
        final Controller controller = new Controller(model);
        mainView.applyController(controller);
        model.start();
        mainView.show();
        settingsView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
