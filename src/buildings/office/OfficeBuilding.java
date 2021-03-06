package buildings.office;

import buildings.sortSpace;
import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;


public class OfficeBuilding implements Building,Serializable,Cloneable,Iterable<Floor> {
    private int numFloors;
    private static class Node { //узел списка
        Node next;
        Node prev;
        Floor oneOfficeFloor;
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
        temp.oneOfficeFloor= new OfficeFloor(numOffices[0]);
        for (int i = 1; i < numFloors; i++) {
            Node x = new Node();
            x.oneOfficeFloor = new OfficeFloor(numOffices[i]);
            temp.next = x;
            temp.next.prev = temp;
            temp = temp.next;
        }
        temp.next = head.next;
        head.next.prev = temp;
    }
    public OfficeBuilding(Floor[] Floors) {  //Конструктор массива этажей офисного здания
        this();
        numFloors=Floors.length;
        Node temp = head;
        for (Floor Floor : Floors) {
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
        numFloors++;
    }


    private void deleteNode(int index) {  //приватный метод удаления узла из списка по его номеру
        Node a = head;
        for (int i = 0; i < index; i++) {
            a = a.next;
        }
        a.next = a.next.next;
        a.next.prev = a;
        numFloors--;
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

    public int getAmountSpace(){  //метод получения общего количества офисов
        int res=0;
        Node temp=head;
        do
        {
            temp = temp.next;
            res+=temp.oneOfficeFloor.getAmountSpaces();
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

    public Floor[] getMassFloors(){  //метод получения массива этажей
        int i=0;
        Floor[] floors = new Floor[getAmountSpace()];
        Node temp=head;
        do
        {
            temp = temp.next;
            floors[i]= temp.oneOfficeFloor;
            i++;
        } while(temp.next != head.next);
        return floors;
    }

    public Floor getFloor(int number){  //метод получения объекта этажа, по его номеру
        if ((number > getAmountFloors())||(number < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        return getNode(number).oneOfficeFloor;
    }

    public void setFloor(int number, Floor newFloor){  //метод изменения этажа по его номеру в доме и ссылке на обновленный этаж
        if ((number > getAmountFloors())||(number < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        /*Node temp=head;
        for (int i=0;i<number;i++)
            temp = temp.next;
        temp.next.oneOfficeFloor= newFloor;*/
        getNode(number).oneOfficeFloor=newFloor;
    }

    public Space getSpace(int number){  //метод получения объекта офиса по его номеру
        if ((number > getAmountSpace())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node temp=head;
        for (int i=0;i<getAmountFloors();++i)
        {
            temp=temp.next;
            if(number< getFloor(i).getAmountSpaces())
                return getFloor(i).getOneSpace(number);
            number-= getFloor(i).getAmountSpaces();
        }
        return null;
    }

    public int getNumberSpace(int number){  //(не нужно)метод получения НОМЕРА офиса по его номеру в доме
        if ((number > getAmountSpace())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node temp=head;
        for (int i=0;i<getAmountFloors();++i)
        {
            temp=temp.next;
            if(number< getFloor(i).getAmountSpaces())
                return number;
            number-= getFloor(i).getAmountSpaces();
        }
        return 0;
    }

    public void setSpace(int number, Space newOffice){  //изменения объекта офиса по ее номеру в доме и ссылке на объект
        if ((number > getAmountSpace())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node temp=head;
        for (int i=0;i<getAmountFloors();++i)
        {
            temp=temp.next;
            if(number< getFloor(i).getAmountSpaces()) {
                getFloor(i).setSpace(number, newOffice);
                break;
            }
            number-= getFloor(i).getAmountSpaces();
        }
    }

    public void addSpace(int number, Space newOffice){  //метод добавления офиса по будущему номеру и ссылке на объект
        if ((number > getAmountSpace())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node temp=head;
        for (int i=0;i<getAmountFloors();++i)
        {
            temp=temp.next;
            if(number< getFloor(i).getAmountSpaces()) {
                getFloor(i).addSpace(number, newOffice);
                break;
            }
            number-= getFloor(i).getAmountSpaces();
        }
    }

    public void removeSpace(int number ){  //метод удаления офиса по ее номеру
        if ((number > getAmountSpace())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node temp=head;
        for (int i=0;i<getAmountFloors();++i)
        {
            temp=temp.next;
            if(number< getFloor(i).getAmountSpaces()) {
                getFloor(i).removeSpace(number);
                break;
            }
            number-= getFloor(i).getAmountSpaces();
        }
    }

    public Space getBestSpace(){ //метод получения самого большого оффиса
        Space answer=new Office(0,0);
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

    public Space[] getMassSpace(){  //метод получения отсортированного по убыванию площадей массива
        Node temp=head;
        int office=-1;
        Space[] sortm= new Office[getAmountSpace()];
            for (int i = 0; i < getAmountFloors(); i++){
                temp=temp.next;
                for (int j = 0; j < temp.oneOfficeFloor.getAmountSpaces(); j++){
                    office++;
                    sortm[office]=temp.oneOfficeFloor.getOneSpace(j);}}
        Arrays.sort( sortm, new sortSpace());

        for (int i = 0; i < getAmountSpace(); i++){
            System.out.println(sortm[i].getSize()+"   "+ sortm[i].getRooms());
            }

        return sortm;
        }

    public double getCostMultiplier(){
        return 1500;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("OfficeBuilding ");
        str.append(numFloors);
        Node temp = head;
        for (int i = 0; i < numFloors; i++) {
            temp = temp.next;
            str.append(", ");
            str.append(temp.oneOfficeFloor.toString());
        }
        return str.toString();
    }
    public String name(){ //toString???
        return "Офисное здание";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof OfficeBuilding))
            return false;
        OfficeBuilding other = (OfficeBuilding) obj;
        if (!head.equals(other.head))
            return false;
        return true;
    }

    public int hashCode() {
        int hashf = 31;
        int res = 1;
        Node temp = head;
        for (int i = 0; i < numFloors; i++) {
            temp = temp.next;
            res += hashf * res * temp.oneOfficeFloor.hashCode();
        }
        return res;
    }
    public Object clone() {
        Building result = null;
        try {
            result = (Building) super.clone();
            for (int i = 0; i < result.getAmountFloors(); i++) {
                result.setFloor(i, (Floor) result.getFloor(i).clone());
                for (int j = 0; j < result.getFloor(i).getAmountSpaces(); i++) {
                    result.getFloor(i).setSpace(j, (Space) result.getSpace(j).clone());
                }
            }
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return result;
    }
    public Iterator<Floor> iterator() {

        Iterator<Floor> it = new Iterator<>() {
            private int currentIndex = 0;
            Node head;

            @Override
            public boolean hasNext() {
                return currentIndex < numFloors &&  head.oneOfficeFloor!= null;
            }

            @Override
            public Floor next() {

                return head.next.oneOfficeFloor;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

}
