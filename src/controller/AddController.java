package controller;

import java.time.LocalDate;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Automobile;
import model.Car;
import model.Engine;
import model.GearType;
import model.Motorcycle;
import model.Truck;
import view.AddEngineView;
import view.AddView;

public class AddController {

    private final AddView view;
    private final Stage stage;
    private final MainController mainController;

    private Automobile vehicleToEdit = null;

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
        new AddEngineController(addEngineView, engineStage, this);

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

    public void setVehicleToEdit(Automobile vehicle) {
        this.vehicleToEdit = vehicle;
        if (vehicle == null) return;

        view.getTitleLabel().setText("Edit Vehicle");
        view.getPlateNumField().setText(vehicle.plateNum != null ? vehicle.plateNum : "");
        view.getPlateNumField().setDisable(false); 

        view.getManufactureNameField().setText(vehicle.manufactureCompany != null ? vehicle.manufactureCompany : "");
        view.getModelField().setText(vehicle.model != null ? vehicle.model : "");
        view.getManufactureDatePicker().setValue(vehicle.manufactureDate);
        view.getBodySerialField().setText(vehicle.getBodySerialNum() != null ? vehicle.getBodySerialNum() : "");
        view.getGearTypeComboBox().setValue(vehicle.gearType);
        loadExistingEngines();
        if (vehicle.engine != null) {
            view.getEngineComboBox().setValue(vehicle.engine);
        }       

        if (vehicle.color != null) {
            java.awt.Color awtColor = vehicle.color;
            javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(
                awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue(), awtColor.getAlpha() / 255.0
            );
            view.getColorPicker().setValue(fxColor);
        }

        if (vehicle instanceof Car car) {
            view.getTypeComboBox().setValue("Car");
            view.updateFieldsForType("Car");
            view.getLengthField().setText(String.valueOf(car.length)); 
            view.getExtraField1().setText(String.valueOf(car.width));
            view.getExtraField2().setText(String.valueOf(car.chairNum));
            view.getLeatherCheckBox().setSelected(car.isFurniter);
        } else if (vehicle instanceof Truck truck) {
            view.getTypeComboBox().setValue("Truck");
            view.updateFieldsForType("Truck");
            view.getLengthField().setText(String.valueOf(truck.length)); 
            view.getExtraField1().setText(String.valueOf(truck.width));
            view.getExtraField2().setText(String.valueOf(truck.freeWight));
            view.getExtraField3().setText(String.valueOf(truck.fullWight));
        } else if (vehicle instanceof Motorcycle motorcycle) {
            view.getTypeComboBox().setValue("Motorcycle");
            view.updateFieldsForType("Motorcycle");
            view.getLengthField().setText(String.valueOf(motorcycle.length)); 
            view.getExtraField1().setText(String.valueOf(motorcycle.tireDiameter));
        }
    }

    private void goBack() {
        stage.close();
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
            javafx.scene.paint.Color fxColor = view.getColorPicker().getValue();
            java.awt.Color color = toAwtColor(fxColor);

            Automobile vehicle = null;

           if ("Car".equals(type)) {
                Car car = new Car( length, width, color, seats, leather, manufactureCompany, manufactureDate, model, engine, plateNum, gearType, bodySerialNum);
                
            }else if ("Truck".equalsIgnoreCase(type)) {
                double freeWeight = parseDoubleSafe(view.getExtraField2().getText());
                double fullWeight = parseDoubleSafe(view.getExtraField3().getText());

                Truck truck = new Truck(length, width, color, freeWeight, fullWeight, manufactureCompany, manufactureDate, model, engine, plateNum, gearType, bodySerialNum);
                

            } else if ("Motorcycle".equalsIgnoreCase(type)) {
                double tireDiameter = parseDoubleSafe(view.getExtraField1().getText());

                Motorcycle motorcycle = new Motorcycle(tireDiameter, length, manufactureCompany, manufactureDate, model, engine, plateNum, gearType, color, bodySerialNum);
               
            }

            if (vehicleToEdit == null) {
                mainController.addVehicle(vehicle);
            }else{
                int index = mainController.getVehicles().indexOf(vehicleToEdit);

                if (index != -1) {
                    mainController.getVehicles().set(index, vehicle);
                    mainController.saveDataToFiles();
                    mainController.refreshTable();
                }
            }
            stage.close();
            mainController.openMainView();

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

    private java.awt.Color toAwtColor(javafx.scene.paint.Color fxColor) {
        if (fxColor == null) return java.awt.Color.BLACK;
        return new java.awt.Color(
            (float) fxColor.getRed(),
            (float) fxColor.getGreen(),
            (float) fxColor.getBlue(),
            (float) fxColor.getOpacity()
        );
    }

}