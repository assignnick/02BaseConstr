package OfficeBuildings;

import java.util.Arrays;

public class OfficeBuilding {
    private int numFloors;
    private OfficeFloor[] Floors;
    public OfficeBuilding(int numFloors, int numOffices ) {  //конструктор
        this.numFloors = numFloors;
        this.Floors = new OfficeFloor[numFloors];
        for (int i = 0; i < numFloors; i++)
            Floors[i] = new OfficeFloor(numOffices);
    }
    public OfficeBuilding(OfficeFloor[] Floors) {
        this.Floors=Floors;
    }  //конструктор для массива

    public int getAmountFloors(){ //метод получения общего количества этажей дома
        return numFloors;
    }  //количество этажей

    public int getAmountOffices(){  //метод получения общего количества офисов
        int amount=0;
        for (int i = 0; i < numFloors; i++)
            amount+=Floors[i].getAmountOffices();
        return amount;
    }

    public double getTotalSize(){  //метод получения общей площади
        double size=0;
        for (int i = 0; i < numFloors; i++)
            size+=Floors[i].getTotalSize();
        return size;
    }

    public int getTotalRooms(){  //метод получения общего количества комнат
        int rooms=0;
        for (int i = 0; i < numFloors; i++)
            rooms+=Floors[i].getTotalRooms();
        return rooms;
    }

    public OfficeFloor[] getMassFloors(){  //метод получения массива этажей
        return Floors;
    }

    public OfficeFloor getOneFloor(int number){  //метод получения объекта этажа, по его номеру
        return Floors[number];
    }

    public void changeFloor(int number, OfficeFloor newFloor){  //метод изменения этажа по его номеру в доме и ссылке на обновленный этаж
        Floors[number]=newFloor;
    }

    public Office getOffice(int number){  //метод получения объекта по ее номеру
        int i,office=0;
        for(i = 0; i < numFloors; i++)
            if(number>Floors[i].getAmountOffices())
                number-=Floors[i].getAmountOffices();
            else office=number;
        return   Floors[i-1].getOneOffice(office-1);
    }

    public int getNumberOffice(int number){  //метод получения НОМЕРА офиса по ее номеру в доме
        int i,office=0;
        for(i = 0; i < numFloors; i++)
            if(number>Floors[i].getAmountOffices())
                number-=Floors[i].getAmountOffices();
            else office=number;
        return   office-1;
    }

    public void changeOffice(int number, Office newOffice){  //изменения объекта офиса по ее номеру в доме и ссылке на объект
        int office= getNumberOffice(number);
        Floors[office].changeOffice(office,newOffice);
    }

    public void addOffice(int number, Office newOffice){  //метод добавления офиса по будущему номеру и ссылке на объект
        int office= getNumberOffice(number);
        Floors[office].addOffice(office, newOffice);
    }

    public void deleteOffice(int number ){  //метод удаления офиса по ее номеру
        int office= getNumberOffice(number);
        Floors[office].deleteOffice(office);
    }

    public Office getBestSpace(){ //метод получения самого большого оффиса
        double size=0,sizeOnFl;
        Office temp,answer=new Office();
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

    public Office[] getMassSpace(){  //метод получения отсортированного по убыванию площадей массива
        int office=-1;
        Office[] sortm= new Office[getAmountOffices()];
            for (int i = 0; i < numFloors; i++)
                for (int j = 0; j < Floors[i].getAmountOffices(); j++){
                    office++;
                    sortm[office]=Floors[i].getOneOffice(j);}
        Arrays.sort(sortm, new sortOffice());

        for (int i = 0; i < getAmountOffices(); i++){
            System.out.println(sortm[i].getSize()+"   "+ sortm[i].getRooms());
            }

        return sortm;
        }


}
