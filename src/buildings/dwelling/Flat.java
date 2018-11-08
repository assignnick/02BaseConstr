package buildings.dwelling;

import buildings.sortSpace;
import interfaces.Space;
import java.io.Serializable;
import java.util.Random;

public class Flat extends sortSpace implements Space,Serializable,Cloneable {
    Random random = new Random();
    private int rooms = 2;
    private double size = random.nextInt(50);

    public Flat() {

    }

    public Flat(int rooms) {
        this.rooms = rooms;
        this.size = size;
    }

    public Flat(double size) {
        this.rooms = rooms;
        this.size = size;
    }

    public Flat(int rooms, double size) {
        this.rooms = rooms;
        this.size = size;
    }

    public int getRooms() {
        return rooms;
    } //метод получения количества комнат в квартире

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }  //метод изменения количества комнат в квартире

    public double getSize() {
        return size;
    }  //метод получения площади квартиры

    public void setSize(double size) {
        this.size = size;
    }  //метод изменения площади квартиры

    public String toString() {
        return "Flat (" + rooms + ", " + size + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Flat))
            return false;
//        if (getClass() != obj.getClass())
//            return false;
        Flat other = (Flat) obj;
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
// Значение хеш-функции помещения можно вычислить как
// значение последовательного побитового исключающего ИЛИ битового представления количества комнат помещения,
// и, например, первых 4 байтов и вторых 4-х байтов (для типа double) битовых представлений площадей помещений этажа
// (следует воспользоваться вспомогательными методами классов-оберток).

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
        Flat tmp = (Flat) obj;
        if (this.getSize() < tmp.getSize())
            return -1;
        else if (this.getSize() > tmp.getSize())
            return 1;
        return 0;
    }

}
