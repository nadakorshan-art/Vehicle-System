import controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.HomeView;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {

        HomeView homeView = new HomeView();
        Scene homeScene = new Scene(homeView, 1000, 600);

        MainController controller = new MainController(homeView, primaryStage);

        primaryStage.setTitle("Vehicle Management System");
        primaryStage.setScene(homeScene);
        primaryStage.show(); 

    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
