package OfficeBuildings;

import java.util.Arrays;



public class OfficeBuilding {
    private int numFloors;
    private static class Node { //узел списка
        Node next;
        Node prev;
        OfficeFloor oneOfficeFloor;
    }
    private Node head;

    public OfficeBuilding() {  //конструктор
        head = new Node();
        head.next = head;
        head.prev = head;
    }
    public OfficeBuilding(int numFloors, int[] numOffices ) {  // Конструктор с количеством этажей и массивом количества офисов по этажам
        this();
        this.numFloors=numFloors;
        Node temp = head;
        for (int i = 0; i < numFloors; i++) {
            Node x = new Node();
            x.oneOfficeFloor = new OfficeFloor(numOffices[i]);
            temp.next = x;
            temp.next.prev = temp;
            temp = temp.next;
        }
        temp.next = head.next;
        head.next.prev = temp;
    }
    public OfficeBuilding(OfficeFloor[] Floors) {  //Конструктор массива этажей офисного здания
        this();
        numFloors=Floors.length;
        Node temp = head;
        for (OfficeFloor Floor : Floors) {
            Node x = new Node();
            temp.next = x;
            x.prev = temp;
            x.oneOfficeFloor = Floor;
        }
        temp.next = head.next;
        head.next.prev = temp;
    }

    private Node getNode(int index) { //приватный метод получения узла по его номеру
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private void addNode(int index, Node newNode) {  //приватный метод добавления узла в список по номеру
        Node a = head;
        for (int i = 0; i < index; i++) {
            a = a.next;
        }
        newNode.next.prev = newNode;
        newNode.next=a.next;
        a.next = newNode;
        newNode.prev = a;
    }


    private void deleteNode(int index) {  //приватный метод удаления узла из списка по его номеру
        Node a = head;
        for (int i = 0; i < index; i++) {
            a = a.next;
        }
        a.next = a.next.next;
        a.next.prev = a;
    }

    public int getAmountFloors(){ //метод получения общего количества этажей дома
        int res=0;
        Node temp=head;
        do
        {
            temp = temp.next;
            res++;
        } while(temp.next != head.next);
        return res;
    }

    public int getAmountFloorstoo(){ //метод получения общего количества этажей дома
        return numFloors;
    }  //количество этажей

    public int getAmountOffices(){  //метод получения общего количества офисов
        int res=0;
        Node temp=head;
        do
        {
            temp = temp.next;
            res+=temp.oneOfficeFloor.getAmountOffices();
        } while(temp.next != head.next);
        return res;
    }

    public double getTotalSize(){  //метод получения общей площади
        double size=0;
        Node temp=head;
        do
        {
            temp = temp.next;
            size+=temp.oneOfficeFloor.getTotalSize();
        } while(temp.next != head.next);
        return size;
    }

    public int getTotalRooms(){  //метод получения общего количества комнат
        int rooms=0;
        Node temp=head;
        do
        {
            temp = temp.next;
            rooms+=temp.oneOfficeFloor.getTotalRooms();
        } while(temp.next != head.next);
        return rooms;
    }

    public OfficeFloor[] getMassFloors(){  //метод получения массива этажей
        int i=0;
        OfficeFloor[] floors = new OfficeFloor[getAmountOffices()];
        Node temp=head;
        do
        {
            temp = temp.next;
            floors[i]= temp.oneOfficeFloor;
            i++;
        } while(temp.next != head.next);
        return floors;
    }

    public OfficeFloor getOneFloor(int number){  //метод получения объекта этажа, по его номеру
        return getNode(number).oneOfficeFloor;
    }

    public void changeFloor(int number, OfficeFloor newFloor){  //метод изменения этажа по его номеру в доме и ссылке на обновленный этаж
        /*Node temp=head;
        for (int i=0;i<number;i++)
            temp = temp.next;
        temp.next.oneOfficeFloor= newFloor;*/
        getNode(number).oneOfficeFloor=newFloor;
    }

    public Office getOffice(int number){  //метод получения объекта офиса по ее номеру
        Node temp=head;
        for (int i=0;i<getAmountFloors();++i)
        {
            temp=temp.next;
            if(number<getOneFloor(i).getAmountOffices())
                return getOneFloor(i).getOneOffice(number);
            number-=getOneFloor(i).getAmountOffices();
        }
        return null;
    }

    public int getNumberOffice(int number){  //(не нужно)метод получения НОМЕРА офиса по ее номеру в доме
        Node temp=head;
        for (int i=0;i<getAmountFloors();++i)
        {
            temp=temp.next;
            if(number<getOneFloor(i).getAmountOffices())
                return number;
            number-=getOneFloor(i).getAmountOffices();
        }
        return 0;
    }

    public void changeOffice(int number, Office newOffice){  //изменения объекта офиса по ее номеру в доме и ссылке на объект
        Node temp=head;
        for (int i=0;i<getAmountFloors();++i)
        {
            temp=temp.next;
            if(number<getOneFloor(i).getAmountOffices()) {
                getOneFloor(i).changeOffice(number, newOffice);
                break;
            }
            number-=getOneFloor(i).getAmountOffices();
        }
    }

    public void addOffice(int number, Office newOffice){  //метод добавления офиса по будущему номеру и ссылке на объект
        Node temp=head;
        for (int i=0;i<getAmountFloors();++i)
        {
            temp=temp.next;
            if(number<getOneFloor(i).getAmountOffices()) {
                getOneFloor(i).addOffice(number, newOffice);
                break;
            }
            number-=getOneFloor(i).getAmountOffices();
        }
    }

    public void deleteOffice(int number ){  //метод удаления офиса по ее номеру
        Node temp=head;
        for (int i=0;i<getAmountFloors();++i)
        {
            temp=temp.next;
            if(number<getOneFloor(i).getAmountOffices()) {
                getOneFloor(i).deleteOffice(number);
                break;
            }
            number-=getOneFloor(i).getAmountOffices();
        }
    }

    public Office getBestSpace(){ //метод получения самого большого оффиса
        Office answer=new Office(0,0);
        Node temp=head;
        for (int i=0;i<getAmountFloors();++i)
        {
            temp=temp.next;
            temp.oneOfficeFloor.getBestSpace();
            if(answer.getSize()< temp.oneOfficeFloor.getBestSpace().getSize())
                answer=temp.oneOfficeFloor.getBestSpace();

        }
        return answer;
    }

    public Office[] getMassSpace(){  //метод получения отсортированного по убыванию площадей массива
        Node temp=head;
        int office=-1;
        Office[] sortm= new Office[getAmountOffices()];
            for (int i = 0; i < getAmountFloors(); i++){
                temp=temp.next;
                for (int j = 0; j < temp.oneOfficeFloor.getAmountOffices(); j++){
                    office++;
                    sortm[office]=temp.oneOfficeFloor.getOneOffice(j);}}
        Arrays.sort(sortm, new sortOffice());

        for (int i = 0; i < getAmountOffices(); i++){
            System.out.println(sortm[i].getSize()+"   "+ sortm[i].getRooms());
            }

        return sortm;
        }
}
