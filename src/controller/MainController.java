package controller;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Automobile;
import model.Car;
import model.Engine;
import model.Motorcycle;
import model.Truck;
import view.AddView;
import view.DetailsView;
import view.HomeView;
import view.MainView;

public class MainController {

    private HomeView homeView;
    private Stage stage;

    private List<Engine> engines = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private List<Truck> trucks = new ArrayList<>();
    private List<Motorcycle> motorcycles = new ArrayList<>();
    private ObservableList<Automobile> allVehicles = FXCollections.observableArrayList();

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
        setupActions(mainView);
        Scene scene = new Scene( mainView, 1000, 600);

        stage.setScene(scene);
        stage.centerOnScreen();
    }

    private void setupActions(MainView mainView) {

        mainView.getAddButton().setOnAction( event -> openAddView());

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
        Scene scene = new Scene( addView, 1000, 600);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    private void openDetailsView(Automobile vehicle) {
        DetailsView detailsView = new DetailsView();
        Stage detailsStage = new Stage();
        DetailsController controller = new DetailsController(detailsView, detailsStage, vehicle, this);
        detailsStage.setTitle("Vehicle Details");
        Scene scene = new Scene( detailsView, 1000, 600);
        detailsStage.setScene(scene);
        detailsStage.showAndWait();
    }

    public void refreshTable() {
        updateAllVehiclesList();
    }

    public void addCar(Car car) {
        cars.add(car);
        updateAllVehiclesList();
    }

    public void addTruck(Truck truck) {
        trucks.add(truck);
        updateAllVehiclesList();
    }

    public void addMotorcycle(Motorcycle motorcycle) {
        motorcycles.add(motorcycle);
        updateAllVehiclesList();
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

    }

    public void saveDataToFiles() {

    }

}