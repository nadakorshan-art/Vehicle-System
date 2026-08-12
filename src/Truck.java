import java.awt.Color;
import java.util.Date;

public class Truck extends Vehicle {

    public double freeWight;
    public double fullWight;
    
    public Truck(){

    }

    public Truck(int length, int width, Color color, double freeWight, double fullWight, String manufactureCompany, Date manufactureDate, String model, Engine engine, int plateNum, GearType gearType ){
        this.manufactureCompany = manufactureCompany;
        this.manufactureDate = manufactureDate;
        this.model = model;
        this.engine = engine;
        this.plateNum = plateNum;
        this.gearType = gearType;
        this.length = length;
        this.width = width;
        this.color = color;
        this.freeWight = freeWight;
        this.fullWight = fullWight;
    }
}