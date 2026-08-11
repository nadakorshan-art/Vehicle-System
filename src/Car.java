import java.awt.Color;

public class Car extends Vehicle {
    
    public int chairNum;
    public boolean isFurniter;

    public Car(){

    }

    public Car(int length, int width, Color color, int chairNum, boolean isFurniter){
        this.length = length;
        this.width = width;
        this.color = color;
        this.chairNum = chairNum;
        this.isFurniter = isFurniter;
    }

    
}
