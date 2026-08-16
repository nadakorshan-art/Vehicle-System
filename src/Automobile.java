import java.awt.Color;
import java.time.LocalDate;

public class Automobile {

    public String manufactureCompany;
    public LocalDate manufactureDate;
    public String model;
    public Engine engine;
    public int plateNum;
    public GearType gearType;
    private int bodySerialNum;

    public int getBodySerialNum() {
        return bodySerialNum;
    }

    public void setBodySerialNum(int bodySerialNum) {
        this.bodySerialNum = bodySerialNum;
    }
    public Color color;

    
}