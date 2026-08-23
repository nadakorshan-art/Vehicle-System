package controller;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.AddView;
import view.DetailsView;
import view.HomeView;
import view.MainView;

public class MainController {

    private HomeView homeView;
    private Stage stage;

    public MainController(HomeView homeView, Stage stage) {

        this.homeView = homeView;
        this.stage = stage;

        startHomeScreen();
    }

    private void startHomeScreen() {

        PauseTransition delay =new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> openMainView());
        delay.play();
    }

    public void openMainView() {

        MainView mainView = new MainView();
        setupActions(mainView);
        Scene scene = new Scene( mainView, 1000, 600);

        stage.setScene(scene);
        stage.centerOnScreen();
    }

    private void setupActions(MainView mainView) {

        mainView.getAddButton().setOnAction( event -> openAddView());

        mainView.getVehicleTable().setRowFactory(tv -> {
            javafx.scene.control.TableRow<String> row = new javafx.scene.control.TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    String rowData = row.getItem();
                    openDetailsView();
                }    
            });
            return row;
        });
    }

    private void openAddView() {

        AddView addView = new AddView();

        AddController controller = new AddController( addView, stage, this);

        Scene scene = new Scene( addView, 1000, 600);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    private void openDetailsView() {
        DetailsView detailsView = new DetailsView();

        Stage detailsStage = new Stage();
        detailsStage.setTitle("Vehicle Details");
        Scene scene = new Scene( detailsView, 1000, 600);
        detailsStage.setScene(scene);
        detailsStage.showAndWait();
        
    }

}