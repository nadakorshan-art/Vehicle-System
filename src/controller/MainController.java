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
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import view.AddView;
import view.DetailsView;
import view.HomeView;
import view.MainView;

public class MainController {

    private HomeView homeView;
    private Stage stage;
    private MainView mainView;

    private List<Engine> engines = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private List<Truck> trucks = new ArrayList<>();
    private List<Motorcycle> motorcycles = new ArrayList<>();
    private ObservableList<Automobile> allVehicles = FXCollections.observableArrayList();
    private static final String DATA_FILE = "vehicles.data";

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
        loadDataFromFiles();
        updateAllVehiclesList();

        MainView mainView = new MainView();
        mainView.getVehicleTable().setItems(allVehicles);
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
        AddController controller = new AddController( addView, stage, this);
        Stage addStage = new Stage();
        Scene scene = new Scene( addView, 750, 550);
        addStage.setScene(scene);
        addStage.show();
    }

    private void openDetailsView(Automobile vehicle) {
        DetailsView detailsView = new DetailsView();
        Stage detailsStage = new Stage();
        DetailsController controller = new DetailsController(detailsView, detailsStage, vehicle, this);
        detailsStage.setTitle("Vehicle Details");
        Scene scene = new Scene( detailsView, 800, 600);
        detailsStage.setScene(scene);
        detailsStage.showAndWait();
    }

    public void refreshTable() {
        updateAllVehiclesList();

        if (mainView != null && mainView.getVehicleTable() != null) {
            TableView<Automobile> table = mainView.getVehicleTable();
        
            table.setItems(null); 
            table.layout();
            table.setItems(javafx.collections.FXCollections.observableArrayList(allVehicles));
        
            table.refresh();
        }
    }

    public void searchVehicles(String searchText) {

        String text = searchText.trim().toLowerCase();

        if (text.isEmpty()) {
            updateAllVehiclesList();
            return;
        }

        ObservableList<Automobile> results = FXCollections.observableArrayList();
        for (Automobile vehicle : allVehicles) {

            boolean matchesPlate = vehicle.plateNum != null && vehicle.plateNum.toLowerCase().contains(text);
            boolean matchesManufacture = vehicle.manufactureCompany != null && vehicle.manufactureCompany.toLowerCase().contains(text);
            boolean matchesDate = vehicle.manufactureDate != null && vehicle.manufactureDate.toString().contains(text);

            if (matchesPlate || matchesManufacture || matchesDate) {
                results.add(vehicle);
            }
        }
        allVehicles.setAll(results);
    }

    public void addCar(Car car) {
        cars.add(car);
        updateAllVehiclesList();
        saveDataToFiles();
    }

    public void addTruck(Truck truck) {
        trucks.add(truck);
        updateAllVehiclesList();
        saveDataToFiles();
    }

    public void addMotorcycle(Motorcycle motorcycle) {
        motorcycles.add(motorcycle);
        updateAllVehiclesList();
        saveDataToFiles();
    }

    public void addEngine(Engine engine) {
        engines.add(engine);
    }

    public List<Engine> getEngines() {
        return engines;
    }
    public List<Car> getCars() {
        return cars; 
    }
    public List<Truck> getTrucks() { 
        return trucks; 
    }
    public List<Motorcycle> getMotorcycles() { 
        return motorcycles; 
    }
    public ObservableList<Automobile> getAllVehicles() { 
        return allVehicles; 
    }

    private void updateAllVehiclesList() {
        allVehicles.clear();
        allVehicles.addAll(cars);
        allVehicles.addAll(trucks);
        allVehicles.addAll(motorcycles);
    }

    private void loadDataFromFiles() {

        File file = new File(DATA_FILE);

        if (!file.exists()) {
            System.out.println("No saved data found.");
            return;
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(DATA_FILE))) {

            engines = (List<Engine>) input.readObject();
            cars = (List<Car>) input.readObject();
            trucks = (List<Truck>) input.readObject();
            motorcycles = (List<Motorcycle>) input.readObject();
            System.out.println("Data loaded successfully.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public void saveDataToFiles() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
                output.writeObject(engines);
                output.writeObject(cars);
                output.writeObject(trucks);
                output.writeObject(motorcycles);

            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

}