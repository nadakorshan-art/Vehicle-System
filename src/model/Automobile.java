package model;
import java.awt.Color;
import java.io.Serializable;
import java.time.LocalDate;

public class Automobile implements Serializable {

    private static final long serialVersionUID = 1L;
    public String manufactureCompany;
    public LocalDate manufactureDate;
    public String model;
    public Engine engine;
    public String plateNum;
    public GearType gearType;
    private String bodySerialNum;
    public Color color;

    public String getBodySerialNum() {
        return bodySerialNum;
    }

    public void setBodySerialNum(String bodySerialNum) {
        this.bodySerialNum = bodySerialNum;
    }

}