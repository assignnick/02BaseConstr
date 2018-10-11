package OfficeBuildings;

import java.io.Serializable;

public class Office implements Serializable {
private int rooms=1;
private double size=250;
    public Office() {

    }
    public Office(int rooms) {
        this.rooms =rooms;
        this.size=size;
    }
    public Office(double size) {
        this.rooms =rooms;
        this.size=size;
    }
    public Office(int rooms, double size) {
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
