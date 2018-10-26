package constructions.buildings;

import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.Arrays;

public class DwellingFloor implements Floor,Serializable,Cloneable {
   private int numApartments;
   private Space[] flats;
    public  DwellingFloor( int numApartments) {  //конструктор
        this.numApartments = numApartments;
        this.flats = new Space[numApartments];
        for (int i = 0; i < numApartments; i++)
            flats[i] = new Flat();
    }
    public  DwellingFloor( Space[] newFlat) {
            this.flats =newFlat;
    }  //конструктор для массива
    public int getAmountSpaces(){  //метод получения количества квартир на этаже
        return numApartments;
    }
    public double getTotalSize(){  //метод получения общей площади квартир этажа
        double sizer=0;
        for (int i = 0; i < numApartments; i++)
            sizer+= flats[i].getSize();
        return sizer;
    }
    public int getTotalRooms(){  //метод получения общего количества комнат этажа
        int rooms=0;
        for (int i = 0; i < numApartments; i++)
            rooms+= flats[i].getRooms();
        return rooms;
    }
    public Space[] getMassSpace(){
        return flats;
    }  //метод получения массива квартир этажа

    public Space getOneSpace(int number){  //метод получения объекта квартиры по ее номеру на этаже

        return flats[number];
    }

    public void changeSpace(int number, Space newFlat){  //метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру
        flats[number]=(Flat) newFlat;
    }

    public void addSpace(int number, Space newFlat){  //метод добавления новой квартиры на этаже по будущему номеру квартиры
        numApartments++;    //(т.е. в параметрах указывается номер, который должны иметь квартира после вставки) и ссылке на объект квартиры
        Space[] flatTwo = new Space[numApartments];
        for(int i = 0; i < number+1; i++)
            flatTwo[i]= flats[i];
        flatTwo[number]=(Flat) newFlat;
        for(int i = number+1; i < numApartments-1; i++)
            flatTwo[i]= flats[i];
        flats =flatTwo;
    }

    public void removeSpace(int number){  //метод удаления квартиры по ее номеру на этаже
        Space[] flatTwo = new Space[numApartments-1];
        for(int i = 0; i < number-1; i++)
            flatTwo[i]= flats[i];
        for(int i = number+1; i < numApartments; i++)
            flatTwo[i]= flats[i];
        flats =flatTwo;
        numApartments--;
    }

    public Space getBestSpace(){ //метод получения самой большой по площади квартиры этажа.
        double size=0,tmpSize;
        int number=0;
        for(int i = 0; i < numApartments; i++)
        {
            tmpSize= flats[i].getSize();
            if (tmpSize>size) {
                size = flats[i].getSize();
                number=i;
            }
        }
        return flats[number];
    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("DwellingFloor ");
        str.append(numApartments);
        for(Space sp : flats) {
            str.append(", ");
            str.append(sp.toString());
        }
        return str.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DwellingFloor))
            return false;
        DwellingFloor other = (DwellingFloor) obj;
        if (!Arrays.equals(flats, other.flats))
            return false;
        return true;
    }
    public int hashCode() {
        int hashf = 31;
        int res = 1;
        for(Space sp : flats)
        res += hashf * res * sp.hashCode();
        return res;
    }

    public Object clone() {
        DwellingFloor result = null;
        try {
            result = (DwellingFloor) super.clone();
            result.flats = this.flats.clone();
            for(int i = 0; i < result.getAmountSpaces(); i++) {
                result.changeSpace(i, (Space)result.getOneSpace(i).clone());
            }
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return result;
    }
}
