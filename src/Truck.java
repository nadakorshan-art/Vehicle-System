import java.awt.Color;

public class Truck extends Vehicle {

    public double freeWight;
    public double fullWight;
    
    public Truck(){

    }

    public Truck(int length, int width, Color color, double freeWight, double fullWight){
        this.length = length;
        this.width = width;
        this.color = color;
        this.freeWight = freeWight;
        this.fullWight = fullWight;
    }
}