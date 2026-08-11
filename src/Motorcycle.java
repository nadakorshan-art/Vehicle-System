import java.util.Date;

public class Motorcycle extends Automobile{
    
    public double tireDiameter;
    public double length;

    public Motorcycle() {

    }

    public Motorcycle(double tireDiameter, double length, String manufactureCompany, Date manufactureDate,
                  String model, Engine engine, int plateNum, GearType gearType ){

        this.tireDiameter = tireDiameter;
        this.length = length;
        this.manufactureCompany = manufactureCompany;
        this.manufactureDate = manufactureDate;
        this.model = model;
        this.engine = engine;
        this.plateNum = plateNum;
        this.gearType = gearType;
    }
    
}
