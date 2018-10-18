package buildings;

import interfaces.*;

import java.io.File;
import java.util.Arrays;

public class Dwelling implements Building {
    private int numFloors;
    private DwellingFloor[] Floors;
    public  Dwelling( int numFloors,int numApartments ) {
        this.numFloors = numFloors;
        this.Floors = new DwellingFloor[numFloors];
        for (int i = 0; i < numFloors; i++)
            Floors[i] = new DwellingFloor(numApartments);
    }
    public  Dwelling( DwellingFloor[] Floors) {
        this.Floors=Floors;
    }

    public int getAmountFloors(){ //метод получения общего количества этажей дома
        return numFloors;
    }
    public int getAmountSpace(){  //метод получения общего количества квартир дома
        int amount=0;
        for (int i = 0; i < numFloors; i++)
            amount+=Floors[i].getAmountSpaces();
        return amount;
    }

    public double getTotalSize(){  //метод получения общей площади квартир дома
        double size=0;
        for (int i = 0; i < numFloors; i++)
            size+=Floors[i].getTotalSize();
        return size;
    }

    public int getTotalRooms(){  //метод получения общего количества комнат дома
        int rooms=0;
        for (int i = 0; i < numFloors; i++)
            rooms+=Floors[i].getTotalRooms();
        return rooms;
    }

    public DwellingFloor[] getMassFloors(){  //метод получения массива этажей жилого дома
        return Floors;
    }

    public DwellingFloor getOneFloor(int number){  //метод получения объекта этажа, по его номеру в доме
        return Floors[number];
    }

    public void changeFloor(int number, Floor newFloor){  //метод изменения этажа по его номеру в доме и ссылке на обновленный этаж
        Floors[number]=(DwellingFloor)newFloor;
    }

    public Flat getSpace(int number){  //метод получения объекта квартиры по ее номеру в доме
        int i,flat=0;
        for(i = 0; i < numFloors; i++)
            if(number>Floors[i].getAmountSpaces())
                number-=Floors[i].getAmountSpaces();
            else flat=number;
        return  (Flat) Floors[i-1].getOneSpace(flat-1);
    }

    public int getNumberSpace(int number){  //метод получения НОМЕРА квартиры по ее номеру в доме
        int i,flat=0;
        for(i = 0; i < numFloors; i++)
            if(number>Floors[i].getAmountSpaces())
                number-=Floors[i].getAmountSpaces();
            else flat=number;
        return   flat-1;
    }

    public void changeSpace(int number, Space newFlat){  //изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры
        int flat= getNumberSpace(number);
        Floors[flat].changeSpace(flat,newFlat);
    }

    public void addSpace(int number, Space newFlat){  //метод добавления квартиры в дом по будущему номеру квартиры в доме и ссылке на объект квартиры
        int flat= getNumberSpace(number);
        Floors[flat].addSpace(flat, newFlat);
    }

    public void removeSpace(int number ){  //метод удаления квартиры по ее номеру в доме
        int flat= getNumberSpace(number);
        Floors[flat].removeSpace(flat);
    }

    public Flat getBestSpace(){ //метод получения самой большой по площади квартиры дома.
        double size=0,sizeOnFl;
        Flat temp,answer=new Flat();
        for(int i = 0; i < numFloors; i++)
        {
            temp=Floors[i].getBestSpace();
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
        Space[] sortm= new Flat[getAmountSpace()];
            for (int i = 0; i < numFloors; i++)
                for (int j = 0; j < Floors[i].getAmountSpaces(); j++){
                    flat++;
                    sortm[flat]=Floors[i].getOneSpace(j);}
        Arrays.sort((Flat[])sortm, new sortFlat());

        for (int i = 0; i < getAmountSpace(); i++){
            System.out.println(sortm[i].getSize()+"   "+ sortm[i].getRooms());
            }

        return sortm;
        }


}
