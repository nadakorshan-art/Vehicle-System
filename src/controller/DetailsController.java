package controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Automobile;
import model.Car;
import model.GearType;
import model.Motorcycle;
import model.Truck;
import view.DetailsView;

public class DetailsController {

    private final DetailsView view;
    private final Stage stage;
    private final Automobile vehicle; 
    private final MainController mainController;
    //A map for managing showing and hiding rows based on vehicle type
    private final Map<String, HBox> rowContainers = new HashMap<>();

    private boolean isEditMode = false;

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

        view.getGearTypeValue().setText(vehicle.gearType != null ? vehicle.gearType.toString() : "");

        if (vehicle.engine != null) {

            view.getEngineModelValue().setText(vehicle.engine.model);

            view.getCylindersValue().setText(String.valueOf(vehicle.engine.cylinders) );

            view.getFuelTypeValue().setText(vehicle.engine.fuelType != null ? vehicle.engine.fuelType.toString() : "" );

            view.getCapacityValue().setText(String.valueOf(vehicle.engine.capacity));
        }

        setRowVisible("tireDiameter", vehicle instanceof Motorcycle);
        setRowVisible("freeWeight", vehicle instanceof Truck);
        setRowVisible("fullWeight", vehicle instanceof Truck);
        setRowVisible("seats", vehicle instanceof Car);
        setRowVisible("furniture", vehicle instanceof Car);

        if (vehicle instanceof Car) {

            Car car = (Car) vehicle;
            view.getTypeValue().setText("Car");
            view.getLengthValue().setText(String.valueOf(car.length));
            view.getWidthValue().setText(String.valueOf(car.width));
            view.getColorValue().setText(car.color != null ? car.color.toString() : "");
            view.getSeatsValue().setText(String.valueOf(car.chairNum));
            view.getFurnitureValue().setSelected(car.isFurniter);

        } else if (vehicle instanceof Truck) {

            Truck truck = (Truck) vehicle;
            view.getTypeValue().setText("Truck");
            view.getLengthValue().setText(String.valueOf(truck.length));
            view.getWidthValue().setText(String.valueOf(truck.width));
            view.getColorValue().setText(truck.color != null ? truck.color.toString() : "");
            view.getFreeWeightValue().setText(String.valueOf(truck.freeWight));
            view.getFullWeightValue().setText(String.valueOf(truck.fullWight));

        } else if (vehicle instanceof Motorcycle) {

            Motorcycle motorcycle = (Motorcycle) vehicle;
            view.getTypeValue().setText("Motorcycle");
            view.getLengthValue().setText(String.valueOf(motorcycle.length));
            view.getColorValue().setText(motorcycle.color != null ? motorcycle.color.toString() : "");
            view.getTireDiameterValue().setText(String.valueOf(motorcycle.tireDiameter));

        }
    }

    private void setRowVisible(String key, boolean visible) {
        HBox container = rowContainers.get(key);
        if (container != null) {
            container.setVisible(visible);
            container.setManaged(visible);
        }
    }

    private void setupActions() {
        view.getUpdateButton().setOnAction(event -> handleUpdate());
        
        view.getPrintButton().setOnAction(event -> handlePrint());
        
        view.getDeleteButton().setOnAction(event -> handleDelete());
    }

    private void handleUpdate() {
        vehicle.plateNum = view.getPlateNumberValue().getText().trim();
        vehicle.model = view.getModelValue().getText().trim();
        vehicle.manufactureDate = LocalDate.parse(view.getManufactureDateValue().getText().trim());
        vehicle.setBodySerialNum( view.getBodySerialValue().getText().trim());
        vehicle.gearType = GearType.valueOf(view.getGearTypeValue().getText().trim().toUpperCase());

        if (vehicle.engine != null) {
            vehicle.engine.model = view.getEngineModelValue().getText();
            vehicle.engine.cylinders = parseIntSafe(view.getCylindersValue().getText());
            vehicle.engine.capacity = parseDoubleSafe(view.getCapacityValue().getText());
        }
        if (vehicle instanceof Car car) {
            car.width = parseDoubleSafe(view.getWidthValue().getText());
            car.chairNum = parseIntSafe(view.getSeatsValue().getText());
            car.isFurniter = view.getFurnitureValue().isSelected();
        } else if (vehicle instanceof Truck truck) {
            truck.width = parseDoubleSafe(view.getWidthValue().getText());
            truck.freeWight = parseDoubleSafe(view.getFreeWeightValue().getText());
            truck.fullWight = parseDoubleSafe(view.getFullWeightValue().getText());
        } else if (vehicle instanceof Motorcycle motorcycle) {
            motorcycle.tireDiameter = parseDoubleSafe(view.getTireDiameterValue().getText());
        }

        mainController.saveDataToFiles();
        mainController.refreshTable();
        
        stage.close();
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
