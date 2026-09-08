package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import view.*;

public class MainController {

    private final Stage stage;
    private MainView mainView;

    private List<Engine> engines = new ArrayList<>();
    private List<Automobile> vehicles = new ArrayList<>();
    private final ObservableList<Automobile> vehicleTableData = FXCollections.observableArrayList();
    private static final String DATA_FILE = "vehicles.data";

    public MainController(Stage stage) {
        this.stage = stage;
        loadDataFromFiles();
        startHomeScreen();
    }

    private void startHomeScreen() {

        PauseTransition delay =new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> openMainView());
        delay.play();
    }

    public void openMainView() {
        refreshTable();

        mainView = new MainView();
        mainView.getVehicleTable().setItems(vehicleTableData);
        setupActions(mainView);
        Scene scene = new Scene( mainView, 1000, 600);

        stage.setScene(scene);
        stage.centerOnScreen();
    }

    private void setupActions(MainView mainView) {

        mainView.getAddButton().setOnAction( event -> openAddView());
        mainView.getSearchButton().setOnAction(event -> searchVehicles(mainView.getSearchField().getText()));

        mainView.setActionHandlers(vehicle -> openDetailsView(vehicle), vehicle -> openDetailsView(vehicle));
        
        mainView.getVehicleTable().setRowFactory(tv -> {
            javafx.scene.control.TableRow<Automobile> row = new javafx.scene.control.TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Automobile selectedVehicle = row.getItem();
                    openDetailsView(selectedVehicle);
                }    
            });
            return row;
        });
    }

    private void openAddView() {
        AddView addView = new AddView(); 
        Stage addStage = new Stage();
        new AddController( addView, addStage, this);
        Scene scene = new Scene( addView, 750, 550);
        addStage.setScene(scene);
        addStage.show();
    }

    private void openDetailsView(Automobile vehicle) {
        DetailsView detailsView = new DetailsView();
        Stage detailsStage = new Stage();
        new DetailsController(detailsView, detailsStage, vehicle, this);
        detailsStage.setTitle("Vehicle Details");
        Scene scene = new Scene( detailsView, 800, 600);
        detailsStage.setScene(scene);
        detailsStage.showAndWait();
    }

    public void refreshTable() {
        vehicleTableData.setAll(vehicles);

        if (mainView != null) {
            mainView.getVehicleTable().refresh();
        }
    }

    public void searchVehicles(String searchText) {

        String text = searchText.trim().toLowerCase();

        if (text.isEmpty()) {
            refreshTable();
            return;
        }

        ObservableList<Automobile> results = FXCollections.observableArrayList();
        for (Automobile vehicle : vehicles) {

            boolean matchesPlate = vehicle.plateNum != null && vehicle.plateNum.toLowerCase().contains(text);
            boolean matchesManufacture = vehicle.manufactureCompany != null && vehicle.manufactureCompany.toLowerCase().contains(text);
            boolean matchesDate = vehicle.manufactureDate != null && vehicle.manufactureDate.toString().contains(text);

            if (matchesPlate || matchesManufacture || matchesDate) {
                results.add(vehicle);
            }
        }
        vehicleTableData.setAll(results);
    }

    public void addVehicle(Automobile vehicle) {
        vehicles.add(vehicle);
        saveDataToFiles();
        refreshTable();
    }

    public void addEngine(Engine engine) {
        engines.add(engine);
    }

    public List<Engine> getEngines() {
        return engines;
    }

    public List<Automobile> getVehicles() {
        return vehicles;
    }

    @SuppressWarnings("unchecked")
    private void loadDataFromFiles() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No saved data found.");
            return;
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            engines = (List<Engine>) input.readObject();
            vehicles = (List<Automobile>) input.readObject();
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public void saveDataToFiles() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
                output.writeObject(engines);
                output.writeObject(vehicles);

            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void deleteVehicle(Automobile vehicle){
        vehicles.remove(vehicle);
        saveDataToFiles();
        refreshTable();
    }

}