package controller;

import javafx.stage.Stage;
import model.Automobile;
import view.DetailsView;

public class DetailsController {

    private final DetailsView view;
    private final Stage stage;
    private final Automobile vehicle; 
    private final MainController mainController;

    private boolean isEditMode = false;

    public DetailsController(DetailsView view, Stage stage, Automobile vehicle, MainController mainController) {
        this.view = view;
        this.stage = stage;
        this.vehicle = vehicle;
        this.mainController = mainController;

        displayVehicleDetails();
        setupActions();
    }

    private void displayVehicleDetails(){

    }

    private void setupActions() {
        view.getUpdateButton().setOnAction(event -> handleUpdate());
        
        view.getPrintButton().setOnAction(event -> handlePrint());
        
        view.getDeleteButton().setOnAction(event -> handleDelete());
    }

    private void handleUpdate() {
         
    }

    private void handlePrint() {

    }

    private void handleDelete() {

    }

}
