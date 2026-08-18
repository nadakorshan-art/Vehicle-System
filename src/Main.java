import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.HomeView;
import ui.MainView;

public class Main extends Application{

    public void start(Stage primaryStage) {

        HomeView homeView = new HomeView();
        Scene homeScene = new Scene(homeView, 1000, 600);

        primaryStage.setTitle("Vehicle Management System");
        primaryStage.setScene(homeScene);
        primaryStage.show(); 

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> {
            MainView mainView = new MainView();
            Scene scene = new Scene(mainView, 1000, 600);

            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        });

        delay.play();

    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
