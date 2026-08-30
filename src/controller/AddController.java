package controller;

import java.time.LocalDate;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Car;
import model.Engine;
import model.GearType;
import model.Motorcycle;
import model.Truck;
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
        loadExistingEngines();
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

        Scene scene = new Scene( addEngineView, 700,400);
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

        try {
            String type = view.getTypeComboBox().getValue();
            String plateNum = view.getPlateNumField().getText();
            String manufactureCompany = view.getManufactureNameField().getText();
            String model = view.getModelField().getText();
            LocalDate manufactureDate = view.getManufactureDatePicker().getValue();
            String bodySerialNum = view.getBodySerialField().getText();
            GearType gearType = view.getGearTypeComboBox().getValue();
            Engine engine = view.getEngineComboBox().getValue();
            double length = Double.parseDouble(view.getLengthField().getText());
            double width = parseDoubleSafe(view.getExtraField1().getText());
            int seats = parseIntSafe(view.getExtraField2().getText());
            boolean leather = view.getLeatherCheckBox().isSelected();
            //java.awt.Color color = java.awt.Color.decode(view.getColorField().getText());
            java.awt.Color color =parseColor(view.getColorField().getText());

           if ("Car".equals(type)) {
                Car car = new Car( length, width, color, seats, leather, manufactureCompany, manufactureDate, model, engine, plateNum, gearType, bodySerialNum);
                mainController.addCar(car);
            }else if ("Truck".equalsIgnoreCase(type)) {
                double freeWeight = parseDoubleSafe(view.getExtraField2().getText());
                double fullWeight = parseDoubleSafe(view.getExtraField3().getText());

                Truck truck = new Truck(length, width, color, freeWeight, fullWeight, manufactureCompany, manufactureDate, model, engine, plateNum, gearType, bodySerialNum);
                mainController.addTruck(truck);

                mainController.openMainView();
            } else if ("Motorcycle".equalsIgnoreCase(type)) {
                double tireDiameter = parseDoubleSafe(view.getExtraField1().getText());

                Motorcycle motorcycle = new Motorcycle(tireDiameter, length, manufactureCompany, manufactureDate, model, engine, plateNum, gearType, color, bodySerialNum);
                mainController.addMotorcycle(motorcycle);
             }

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Data");
            alert.setHeaderText("Could not save vehicle");
            alert.setContentText("Please check the entered data.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    private double parseDoubleSafe(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(text.trim());
    }
    private int parseIntSafe(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(text.trim());
    }

    private java.awt.Color parseColor(String colorName) {

    switch (colorName.trim().toLowerCase()) {
        case "red":
            return java.awt.Color.RED;

        case "blue":
            return java.awt.Color.BLUE;

        case "green":
            return java.awt.Color.GREEN;

        case "black":
            return java.awt.Color.BLACK;

        case "white":
            return java.awt.Color.WHITE;

        case "yellow":
            return java.awt.Color.YELLOW;

        case "orange":
            return java.awt.Color.ORANGE;

        case "pink":
            return java.awt.Color.PINK;

        case "gray":
            return java.awt.Color.GRAY;

        default:
            throw new IllegalArgumentException( "Unsupported color: " + colorName);
    }
}

}