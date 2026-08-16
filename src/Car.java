import java.awt.Color;
import java.time.LocalDate;

public class Car extends Vehicle {
    
    public int chairNum;
    public boolean isFurniter;

    public Car(int length, int width, Color color, int chairNum, boolean isFurniter, String manufactureCompany, LocalDate manufactureDate, String model, Engine engine, String plateNum, GearType gearType, String bodySerialNum ){
        this.manufactureCompany = manufactureCompany;
        this.manufactureDate = manufactureDate;
        this.model = model;
        this.engine = engine;
        this.plateNum = plateNum;
        this.gearType = gearType;
        this.length = length;
        this.width = width;
        this.color = color;
        this.chairNum = chairNum;
        this.isFurniter = isFurniter;
        this.setBodySerialNum(bodySerialNum);

    }
 
}
