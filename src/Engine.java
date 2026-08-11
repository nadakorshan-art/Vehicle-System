
import java.util.Date;

public class Engine {
    public String manufacture;
    public Date manufactureDate;
    public String model;
    public int capacity;
    public int cylinders;
    public FuelType fuelType;

    public Engine() {

    }

    public Engine(String manufacture, Date manufactureDate,
            String model, int capacity, int cylinders,FuelType fuelType) {

        this.manufacture = manufacture;
        this.manufactureDate = manufactureDate;
        this.model = model;
        this.capacity = capacity;
        this.cylinders = cylinders;
        this.fuelType = fuelType;
    }  
    
}
