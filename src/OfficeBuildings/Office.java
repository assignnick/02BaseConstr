package OfficeBuildings;

import java.io.Serializable;
import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;
import interfaces.Space;

public class Office implements Serializable,Space,Cloneable {
private int rooms=1;
private double size=250;
    public Office() {
    }
    public Office(int rooms) {
        if (rooms <= 0)
            throw new InvalidRoomsCountException();
        this.rooms =rooms;
        this.size=size;
    }
    public Office(double size) {
        if (size <= 0)
            throw new InvalidSpaceAreaException();
        this.rooms =rooms;
        this.size=size;
    }
    public Office(int rooms, double size) {
        if (rooms <= 0)
            throw new InvalidRoomsCountException();
        if (size <= 0)
            throw new InvalidSpaceAreaException();
        this.rooms=rooms;
        this.size=size;
    }
    public int getRooms() {
        return rooms;
    } //метод получения количества комнат в квартире
    public void setRooms(int rooms) {
        if (rooms <= 0)
            throw new InvalidRoomsCountException();
        this.rooms=rooms;
    }  //метод изменения количества комнат в квартире
    public double getSize() {
        return size;
    }  //метод получения площади квартиры
    public void setSize(double size) {
        if (size <= 0)
            throw new InvalidSpaceAreaException();
        this.size=size;
    }  //метод изменения площади квартиры
}
