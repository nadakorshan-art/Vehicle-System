package model;
import java.io.Serializable;
import java.time.LocalDate;

public class Engine implements Serializable {
    private static final long serialVersionUID = 1L;
    public String manufacture;
    public LocalDate manufactureDate;
    public String model;
    public double  capacity;
    public int cylinders;
    public FuelType fuelType;

    public Engine(String manufacture, LocalDate manufactureDate, String model, double  capacity, int cylinders,FuelType fuelType) {
        this.manufacture = manufacture;
        this.manufactureDate = manufactureDate;
        this.model = model;
        this.capacity = capacity;
        this.cylinders = cylinders;
        this.fuelType = fuelType;
    }  

    @Override
    public String toString() {
        return manufacture + " - " + model + " " + capacity + "L";
    }

}