import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.AddEngineView;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {

        /*HomeView homeView = new HomeView();
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
        });*/
       // delay.play();

       /*AddView addView = new AddView();

        Scene scene = new Scene(addView, 850, 650);

        primaryStage.setTitle("Vehicle Management System");
        primaryStage.setScene(scene);
        primaryStage.show();*/

       AddEngineView addEngineView = new AddEngineView();

        Scene scene = new Scene(addEngineView, 850, 650);

        primaryStage.setTitle("Vehicle Management System");
        primaryStage.setScene(scene);
        primaryStage.show();


        
    }

    

    public static void main(String[] args) {
        launch(args);
    }
    
}
