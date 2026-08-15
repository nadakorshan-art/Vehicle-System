import java.awt.Color;
import java.time.LocalDate;

public class Motorcycle extends Automobile{
    
    public double tireDiameter;
    public double length;

    public Motorcycle(double tireDiameter, double length, String manufactureCompany, LocalDate manufactureDate, String model, Engine engine, int plateNum, GearType gearType, Color color){

        this.tireDiameter = tireDiameter;
        this.length = length;
        this.manufactureCompany = manufactureCompany;
        this.manufactureDate = manufactureDate;
        this.model = model;
        this.engine = engine;
        this.plateNum = plateNum;
        this.gearType = gearType;
        this.color = color;
    }
    
}
