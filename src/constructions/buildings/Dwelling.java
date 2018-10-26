package constructions.buildings;

import interfaces.*;

import java.io.Serializable;
import java.util.Arrays;
import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;

public class Dwelling implements Building,Serializable,Cloneable {
    private int numFloors;
    private Floor[] floors;
    public  Dwelling( int numFloors,int numApartments ) {
        this.numFloors = numFloors;
        this.floors = new Floor[numFloors];
        for (int i = 0; i < numFloors; i++)
            floors[i] = new DwellingFloor(numApartments);
    }
    public  Dwelling(Floor[] Floors) {
        this.floors =Floors;
    }

    public int getAmountFloors(){ //метод получения общего количества этажей дома
        return numFloors;
    }
    public int getAmountSpace(){  //метод получения общего количества квартир дома
        int amount=0;
        for (int i = 0; i < numFloors; i++)
            amount+= floors[i].getAmountSpaces();
        return amount;
    }

    public double getTotalSize(){  //метод получения общей площади квартир дома
        double size=0;
        for (int i = 0; i < numFloors; i++)
            size+= floors[i].getTotalSize();
        return size;
    }

    public int getTotalRooms(){  //метод получения общего количества комнат дома
        int rooms=0;
        for (int i = 0; i < numFloors; i++)
            rooms+= floors[i].getTotalRooms();
        return rooms;
    }

    public Floor[] getMassFloors(){  //метод получения массива этажей жилого дома
        return floors;
    }

    public Floor getOneFloor(int number){  //метод получения объекта этажа, по его номеру в доме
        if ((number >= getAmountFloors())||(number < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        return floors[number];
    }

    public void changeFloor(int number, Floor newFloor){  //метод изменения этажа по его номеру в доме и ссылке на обновленный этаж
        if ((number >= getAmountFloors())||(number < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        floors[number]=(DwellingFloor)newFloor;
    }

    public Space getSpace(int number){  //метод получения объекта квартиры по ее номеру в доме
        if ((number >= getAmountSpace())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int i,flat=0;
        for(i = 0; i < numFloors; i++)
            if(number> floors[i].getAmountSpaces())
                number-= floors[i].getAmountSpaces();
            else flat=number;
        return  (Flat) floors[i-1].getOneSpace(flat-1);
    }

    public int getNumberSpace(int number){  //метод получения НОМЕРА квартиры по ее номеру в доме
        if ((number >= getAmountSpace())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int i,flat=0;
        for(i = 0; i < numFloors; i++)
            if(number> floors[i].getAmountSpaces())
                number-= floors[i].getAmountSpaces();
            else flat=number;
        return   flat-1;
    }

    public void changeSpace(int number, Space newFlat){  //изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры
        if ((number >= getAmountSpace())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int flat= getNumberSpace(number);
        floors[flat].changeSpace(flat,newFlat);
    }

    public void addSpace(int number, Space newFlat){  //метод добавления квартиры в дом по будущему номеру квартиры в доме и ссылке на объект квартиры
        if ((number >= getAmountSpace())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int flat= getNumberSpace(number);
        floors[flat].addSpace(flat, newFlat);
    }

    public void removeSpace(int number ){  //метод удаления квартиры по ее номеру в доме
        if ((number >= getAmountSpace())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int flat= getNumberSpace(number);
        floors[flat].removeSpace(flat);
    }

    public Space getBestSpace(){ //метод получения самой большой по площади квартиры дома.
        double size=0,sizeOnFl;
        Space temp,answer=new Flat();
        for(int i = 0; i < numFloors; i++)
        {
            temp= floors[i].getBestSpace();
            sizeOnFl=temp.getSize();
            if (sizeOnFl>size) {
                size = sizeOnFl;
                answer=temp;
            }
        }
        return answer;
    }

    public Space[] getMassSpace(){  //метод получения отсортированного по убыванию площадей массива квартир
        int flat=-1;
        Space[] sortm= new Space[getAmountSpace()];
            for (int i = 0; i < numFloors; i++)
                for (int j = 0; j < floors[i].getAmountSpaces(); j++){
                    flat++;
                    sortm[flat]= floors[i].getOneSpace(j);}
        Arrays.sort((Space[])sortm, new sortSpace());

        for (int i = 0; i < getAmountSpace(); i++){
            System.out.println(sortm[i].getSize()+"   "+ sortm[i].getRooms());
            }

        return sortm;
        }
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Dwelling ");
        str.append(numFloors);
        for(Floor sp : floors) {
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
        if (!(obj instanceof Dwelling))
            return false;
        Dwelling other = (Dwelling) obj;
        if (!Arrays.equals(floors, other.floors))
            return false;
        return true;
    }

    public int hashCode() {
        int hashf = 31;
        int res = 1;
        for(Floor sp : floors)
            res += hashf * res * sp.hashCode();
        return res;
    }
    public Object clone() {
        Building result = null;
        try {
            result = (Building) super.clone();
            // clone of attay
            for(int i = 0; i < result.getAmountFloors(); i++) {
                result.changeFloor(i, (Floor)result.getOneFloor(i).clone());
                for(int j = 0; j < result.getOneFloor(i).getAmountSpaces(); i++) {
                    result.getOneFloor(i).changeSpace(j, (Space)result.getSpace(j).clone());
                }
            }
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return result;
    }
}
