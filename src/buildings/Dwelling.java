package buildings;

import java.util.Arrays;

public class Dwelling {
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
    public int getAmountFlats(){  //метод получения общего количества квартир дома
        int amount=0;
        for (int i = 0; i < numFloors; i++)
            amount+=Floors[i].getAmountApart();
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

    public void changeFloor(int number, DwellingFloor newFloor){  //метод изменения этажа по его номеру в доме и ссылке на обновленный этаж
        Floors[number]=newFloor;
    }

    public Flat getFlat(int number){  //метод получения объекта квартиры по ее номеру в доме
        int i,flat=0;
        for(i = 0; i < numFloors; i++)
            if(number>Floors[i].getAmountApart())
                number-=Floors[i].getAmountApart();
            else flat=number;
        return   Floors[i-1].getOneFlat(flat-1);
    }

    public int getNumberFlat(int number){  //метод получения НОМЕРА квартиры по ее номеру в доме
        int i,flat=0;
        for(i = 0; i < numFloors; i++)
            if(number>Floors[i].getAmountApart())
                number-=Floors[i].getAmountApart();
            else flat=number;
        return   flat-1;
    }

    public void changeFlat(int number, Flat newFlat){  //изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры
        int flat= getNumberFlat(number);
        Floors[flat].changeFlat(flat,newFlat);
    }

    public void addFlat(int number, Flat newFlat){  //метод добавления квартиры в дом по будущему номеру квартиры в доме и ссылке на объект квартиры
        int flat= getNumberFlat(number);
        Floors[flat].addFlat(flat, newFlat);
    }

    public void deleteFlat(int number ){  //метод удаления квартиры по ее номеру в доме
        int flat= getNumberFlat(number);
        Floors[flat].deleteFlat(flat);
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

    public Flat[] getMassSpace(){  //метод получения отсортированного по убыванию площадей массива квартир
        int flat=-1;
        Flat[] sortm= new Flat[getAmountFlats()];
            for (int i = 0; i < numFloors; i++)
                for (int j = 0; j < Floors[i].getAmountApart(); j++){
                    flat++;
                    sortm[flat]=Floors[i].getOneFlat(j);}
        Arrays.sort(sortm, new sortFlat());

        for (int i = 0; i < getAmountFlats(); i++){
            System.out.println(sortm[i].getSize()+"   "+ sortm[i].getRooms());
            }

        return sortm;
        }


}
