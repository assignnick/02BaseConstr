package buildings.office;

import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;
import interfaces.Space;

import java.io.Serializable;

public class Office implements Space, Serializable, Cloneable {
    private int rooms = 1;
    private double size = 250;

    public Office() {
        this.rooms = rooms;
        this.size = size;
    }

    public Office(int rooms) {

        if (rooms <= 0)
            throw new InvalidRoomsCountException();
        this.rooms = rooms;
        this.size = size;
    }

    public Office(double size) {
        if (size <= 0)
            throw new InvalidSpaceAreaException();
        this.rooms = rooms;
        this.size = size;
    }

    public Office(int rooms, double size) {
        if (rooms <= 0)
            throw new InvalidRoomsCountException();
        if (size <= 0)
            throw new InvalidSpaceAreaException();
        this.rooms = rooms;
        this.size = size;
    }

    public int getRooms() {
        return rooms;
    } //метод получения количества комнат в квартире

    public void setRooms(int rooms) {
        if (rooms <= 0)
            throw new InvalidRoomsCountException();
        this.rooms = rooms;
    }  //метод изменения количества комнат в квартире

    public double getSize() {
        return size;
    }  //метод получения площади квартиры

    public void setSize(double size) {
        if (size <= 0)
            throw new InvalidSpaceAreaException();
        this.size = size;
    }  //метод изменения площади квартиры

    public String toString() {
        return "Office (" + rooms + ", " + size + ")";
    }
    public String name(){ //toString???
        return "Офисное здание";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Office))
            return false;
//        if (getClass() != obj.getClass())
//            return false;
        Office other = (Office) obj;
        if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
            return false;
        if (rooms != other.rooms)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hashf = 31;
        int res = 1;
        long temp;
        temp = Double.doubleToLongBits(size);
        temp = temp >> 32;
        temp = temp >>> 32;
        res = (int) (hashf * res * (rooms ^ temp));
        return res;
    }

    public Object clone() {
        Object result = null;
        try {
            result = super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return result;
    }


    @Override
    public int compareTo(Space obj) {
        Office tmp = (Office) obj;
        if (this.getSize() < tmp.getSize())
            return -1;
        else if (this.getSize() > tmp.getSize())
            return 1;
        return 0;
    }
}
