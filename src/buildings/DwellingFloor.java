package buildings;

import interfaces.Floor;
import interfaces.Space;

public class DwellingFloor implements Floor {
   private int numApartments;
   private Space[] flat;
    public  DwellingFloor( int numApartments) {  //конструктор
        this.numApartments = numApartments;
        this.flat = new Space[numApartments];
        for (int i = 0; i < numApartments; i++)
            flat[i] = new Flat();
    }
    public  DwellingFloor( Space[] newFlat) {
            this.flat=newFlat;
    }  //конструктор для массива
    public int getAmountSpaces(){  //метод получения количества квартир на этаже
        return numApartments;
    }
    public double getTotalSize(){  //метод получения общей площади квартир этажа
        double sizer=0;
        for (int i = 0; i < numApartments; i++)
            sizer+=flat[i].getSize();
        return sizer;
    }
    public int getTotalRooms(){  //метод получения общего количества комнат этажа
        int rooms=0;
        for (int i = 0; i < numApartments; i++)
            rooms+=flat[i].getRooms();
        return rooms;
    }
    public Space[] getMassSpace(){
        return flat;
    }  //метод получения массива квартир этажа

    public Space getOneSpace(int number){  //метод получения объекта квартиры по ее номеру на этаже

        return flat[number];
    }

    public void changeSpace(int number, Space newFlat){  //метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру
        flat[number]=(Flat) newFlat;
    }

    public void addSpace(int number, Space newFlat){  //метод добавления новой квартиры на этаже по будущему номеру квартиры
        numApartments++;    //(т.е. в параметрах указывается номер, который должны иметь квартира после вставки) и ссылке на объект квартиры
        Space[] flatTwo = new Space[numApartments];
        for(int i = 0; i < number+1; i++)
            flatTwo[i]=flat[i];
        flatTwo[number]=(Flat) newFlat;
        for(int i = number+1; i < numApartments-1; i++)
            flatTwo[i]=flat[i];
        flat=flatTwo;
    }

    public void removeSpace(int number){  //метод удаления квартиры по ее номеру на этаже
        Space[] flatTwo = new Space[numApartments-1];
        for(int i = 0; i < number-1; i++)
            flatTwo[i]=flat[i];
        for(int i = number+1; i < numApartments; i++)
            flatTwo[i]=flat[i];
        flat=flatTwo;
        numApartments--;
    }

    public Space getBestSpace(){ //метод получения самой большой по площади квартиры этажа.
        double size=0,tmpSize;
        int number=0;
        for(int i = 0; i < numApartments; i++)
        {
            tmpSize=flat[i].getSize();
            if (tmpSize>size) {
                size = flat[i].getSize();
                number=i;
            }
        }
        return flat[number];
    }
}
