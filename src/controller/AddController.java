package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Engine;
import view.AddEngineView;
import view.AddView;

public class AddController {

    private AddView view;
    private Stage stage;
    private MainController mainController;

    private Engine createdEngine;

    public AddController( AddView view, Stage stage, MainController mainController) {

        this.view = view;
        this.stage = stage;
        this.mainController = mainController;

        setupActions();
    }

    private void setupActions() {

        view.getCreateEngineButton().setOnAction( event -> openAddEngineView());

        view.getCancelButton().setOnAction( event -> goBack());

        view.getSaveButton().setOnAction( event -> saveVehicle());
    }

    public void loadExistingEngines() {    
        view.getEngineComboBox().getItems().clear();
        view.getEngineComboBox().getItems().addAll(mainController.getEngines());
    }

    private void openAddEngineView() {

        AddEngineView addEngineView = new AddEngineView();
        Stage engineStage = new Stage();

        AddEngineController controller = new AddEngineController(addEngineView, engineStage, this);

        Scene scene = new Scene( addEngineView, 850,650);
        engineStage.setTitle("Create New Engine");
        engineStage.setScene(scene);
        engineStage.centerOnScreen();
        engineStage.showAndWait();
    }

    public void onEngineCreated(Engine newEngine) {
        mainController.addEngine(newEngine); 
        view.getEngineComboBox().getItems().add(newEngine); 
        view.getEngineComboBox().setValue(newEngine);
    }
    
    public void setCreatedEngine(Engine engine) {
        this.createdEngine = engine;
    }

    private void goBack() {
        mainController.openMainView();
    }

    private void saveVehicle() {
       
    }

}