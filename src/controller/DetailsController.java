package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Automobile;
import model.Car;
import model.Motorcycle;
import model.Truck;
import view.AddView;
import view.DetailsView;

public class DetailsController {

    private final DetailsView view;
    private final Stage stage;
    private final Automobile vehicle; 
    private final MainController mainController;
    
    public DetailsController(DetailsView view, Stage stage, Automobile vehicle, MainController mainController) {
        this.view = view;
        this.stage = stage;
        this.vehicle = vehicle;
        this.mainController = mainController;

        displayVehicleDetails();
        setupActions();
    }

    private void displayVehicleDetails() {

        view.getPlateNumberValue().setText(vehicle.plateNum);
        view.getModelValue().setText(vehicle.model);

        if (vehicle.manufactureDate != null) {
            view.getManufactureDateValue().setText(vehicle.manufactureDate.toString());
        }

        view.getBodySerialValue().setText(vehicle.getBodySerialNum());

        if (vehicle.engine != null) {
            view.getEngineModelValue().setText(vehicle.engine.model);
            view.getCylindersValue().setText(String.valueOf(vehicle.engine.cylinders) );
            view.getFuelTypeValue().setText(vehicle.engine.fuelType != null ? vehicle.engine.fuelType.toString() : "" );
            view.getCapacityValue().setText(String.valueOf(vehicle.engine.capacity));
        }

        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            view.getTypeValue().setText("Car");
            view.getLengthValue().setText(String.valueOf(car.length));
            view.getWidthValue().setText(String.valueOf(car.width));
            view.getColorValue().setText(formatColorName(car.color));
        } else if (vehicle instanceof Truck) {
            Truck truck = (Truck) vehicle;
            view.getTypeValue().setText("Truck");
            view.getLengthValue().setText(String.valueOf(truck.length));
            view.getWidthValue().setText(String.valueOf(truck.width));
            view.getColorValue().setText(formatColorName(truck.color));
        } else if (vehicle instanceof Motorcycle) {
            Motorcycle motorcycle = (Motorcycle) vehicle;
            view.getTypeValue().setText("Motorcycle");
            view.getLengthValue().setText(String.valueOf(motorcycle.length));
            view.getColorValue().setText(formatColorName(motorcycle.color));
        }
    }

    private String formatColorName(java.awt.Color color) {
        if (color == null) return "";
        if (color.equals(java.awt.Color.RED)) return "Red";
        if (color.equals(java.awt.Color.BLUE)) return "Blue";
        if (color.equals(java.awt.Color.GREEN)) return "Green";
        if (color.equals(java.awt.Color.BLACK)) return "Black";
        if (color.equals(java.awt.Color.WHITE)) return "White";
        if (color.equals(java.awt.Color.YELLOW)) return "Yellow";
        return String.format("RGB(%d, %d, %d)", color.getRed(), color.getGreen(), color.getBlue());
    }

    private void setupActions() {
        view.getUpdateButton().setOnAction(event -> handleUpdate());
        view.getPrintButton().setOnAction(event -> handlePrint());
        view.getDeleteButton().setOnAction(event -> handleDelete());
    }

    private void handleUpdate() {
        stage.close();

        AddView addView = new AddView();
        Stage addStage = new Stage();
        AddController addController = new AddController(addView, addStage, mainController);
        
        addController.setVehicleToEdit(vehicle);

        Scene scene = new Scene(addView, 800, 600);
        addStage.setTitle("Edit Vehicle");
        addStage.setScene(scene);
        addStage.centerOnScreen();
        addStage.show();
    }

    private void handlePrint() {

    }

    private void handleDelete() {

        if (vehicle instanceof Car) {
            mainController.getCars().remove(vehicle);
        } 
        else if (vehicle instanceof Truck) {
            mainController.getTrucks().remove(vehicle);
        }  
        else if (vehicle instanceof Motorcycle) {
            mainController.getMotorcycles().remove(vehicle);
        }

        mainController.refreshTable();
        mainController.saveDataToFiles();
        stage.close();
    }

}
