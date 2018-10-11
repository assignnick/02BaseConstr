package buildings;

import java.io.Serializable;
import java.util.Random;

public class Flat implements Serializable {
    Random random =new Random();
private int rooms=2;
private double size=random.nextInt(50);
    public Flat() {

    }
    public Flat(int rooms) {
        this.rooms =rooms;
        this.size=size;
    }
    public Flat(double size) {
        this.rooms =rooms;
        this.size=size;
    }
    public Flat(int rooms, double size) {
        this.rooms=rooms;
        this.size=size;
    }
    public int getRooms() {
        return rooms;
    } //метод получения количества комнат в квартире
    public void setRooms(int rooms) {
        this.rooms=rooms;
    }  //метод изменения количества комнат в квартире
    public double getSize() {
        return size;
    }  //метод получения площади квартиры
    public void setSize(double size) {
        this.size=size;
    }  //метод изменения площади квартиры
}
