package controller;

import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Engine;
import model.FuelType;
import view.AddEngineView;

public class AddEngineController {

    private AddEngineView view;
    private Stage stage;
    private AddController addController;

    public AddEngineController(AddEngineView view, Stage stage, AddController addController) {
        this.view = view;
        this.stage = stage;
        this.addController = addController;
        setupActions();
    }

    private void setupActions() {
        view.getCancelButton().setOnAction(event -> closeWindow());
        
        view.getSaveButton().setOnAction(event -> saveEngine());
    }

    private void closeWindow() {
        stage.close();
    }

    private void saveEngine() {

        try {
            String manufacture = view.getManufactureField().getText();
            LocalDate manufactureDate = view.getManufactureDatePicker().getValue();
            String model = view.getModelField().getText();
            double capacity = Double.parseDouble(view.getCapacityField().getText());
            int cylinders = Integer.parseInt(view.getCylindersField().getText());
            FuelType fuelType = view.getFuleComboBox().getValue();
            Engine engine = new Engine( manufacture, manufactureDate, model, capacity, cylinders, fuelType);

            addController.onEngineCreated(engine);

            stage.close();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Data");
            alert.setHeaderText("Could not save engine");
            alert.setContentText("Please check the entered data.");
            alert.showAndWait();
        }
    }
    
}
