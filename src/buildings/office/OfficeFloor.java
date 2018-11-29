package buildings.office;


import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeFloor implements Floor,Serializable,Cloneable,Iterable<Space>{
   private int numOffices;


    private static class Node { //узел списка
        Node next;
        Space oneOffice;
    }
    private Node head;

    private OfficeFloor() {  //конструктор
        head = new Node();
        head.next = head;
    }

    public OfficeFloor(int numOffices) {  //конструктор с количеством офисов
        this();
        Node theHead = head;
        this.numOffices = numOffices;
        for (int i = 0; i < numOffices; i++){
            Node x = new Node();
            x.oneOffice = new Office();
            theHead.next = x;
            theHead = x;
        }
        theHead.next = head.next;
    }
    public OfficeFloor(Space[] newOfFlat) {//конструктор для массива
        this();
        numOffices=newOfFlat.length;
        Node theHead = head;
        for (int i = 0; i < newOfFlat.length; i++){
            Node x = new Node();
            x.oneOffice = newOfFlat[i];
            theHead.next = x;
            theHead = x;
        }
        theHead.next = head.next;
    }

    void addFront(Space newOf, int number) { //(не нужно)
        Node a = new Node();
        a.oneOffice = newOf;
        for (int i = 0; i < number; i++) {
            a = a.next;
        }
        if(head == null)
        {
            head = a;
        }
        else {
            a.next = head;
            head = a;
        }
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
        newNode.next = a.next;
        a.next = newNode;
        numOffices++;
    }


    private void deleteNode(int index) {  //приватный метод удаления узла из списка по его номеру
        Node a = head;
        for (int i = 0; i < index; i++) {
            a = a.next;
        }
        a.next = a.next.next;
        numOffices--;
    }


    public int getAmountOfficestoo(){  // метод получения количества офисов на этаже
        int res=0;
        Node temp=head;
        do
        {
            temp = temp.next;
            res++;
        } while(temp.next != head.next);
        return res;
    }

    public int getAmountSpaces(){  //метод получения количества офисов на этаже
        return numOffices;
    }

    public double getTotalSize(){  //метод получения общей площади помещений этажа
        double sizer=0;
        Node temp=head;
        do
        {
            temp = temp.next;
            sizer+=temp.oneOffice.getSize();
        } while(temp.next != head.next);
        return sizer;
    }
    public int getTotalRooms(){  //метод получения общего количества комнат
        int rooms=0;
        Node temp=head;
        do
        {
            temp = temp.next;
            rooms+=temp.oneOffice.getRooms();
        } while(temp.next != head.next);
        return rooms;
    }

    public Space[] getMassSpace(){ //метод получения массива офисов
        int i=0;
        Space[] offices = new Space[getAmountSpaces()];
        Node temp=head;
        do
        {
            temp = temp.next;
            offices[i]= temp.oneOffice;
            i++;
        } while(temp.next != head.next);
        return offices;
    }

    public Space getOneSpace(int number){  //метод получения объекта офиса по ее номеру
        if ((number >= getAmountSpaces())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node temp=head;
        for (int i=0;i<number;i++)
            temp = temp.next;
        return temp.next.oneOffice;
    }

    public void setSpace(int number, Space newOffice){  //метод изменения офиса по ее номеру на этаже и ссылке на новый офис
        if ((number >= getAmountSpaces())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        /* Node temp=head;
        for (int i=0;i<number;i++)
            temp = temp.next;
        temp.next.oneOffice= newOffice;*/
       getNode(number).oneOffice=(Office) newOffice;
    }

    public void addSpace(int number, Space newOffice){  //метод добавления нового офиса на этаже по будущему номеру офиса
        if ((number >= getAmountSpaces())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        } Node temp = new Node();
        temp.oneOffice =(Office) newOffice;
        addNode(number,temp);
    }

    public void removeSpace(int number){  //метод удаления офиса по его номеру на этаже
        if ((number >= getAmountSpaces())||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        deleteNode(number);
    }

    public Space getBestSpace(){ //получения самого большого по площади офиса этажа
        double maxSize = 0;
        Space maxOffice = null;
        Node temp = head;
        for (int i = 0; i <= getAmountSpaces(); i++) {
            temp = temp.next;
            if(temp.oneOffice.getSize() > maxSize) {
                maxSize = temp.oneOffice.getSize();
                maxOffice = temp.oneOffice;
            }
        }
        return maxOffice;
    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("OfficeFloor ");
        str.append(numOffices);
        Node temp=head;
        for (int i=0;i<numOffices;i++) {
            temp = temp.next;

            str.append(", ");
            str.append(temp.oneOffice.toString());
        }
        return str.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof OfficeFloor))
            return false;
        OfficeFloor other = (OfficeFloor) obj;
        if (!head.equals(other.head))
            return false;
        return true;
    }

    public int hashCode() {
        int hashf = 31;
        int res = 1;
        Node temp=head;
        for (int i=0;i<numOffices;i++) {
            temp = temp.next;
            res += hashf * res * temp.oneOffice.hashCode();
        }
        return res;
    }
    public Object clone() {
        OfficeFloor result = null;
        try {
            result = (OfficeFloor) super.clone();
            result.head = head;
            for(int i = 0; i < result.getAmountSpaces(); i++) {
                result.addSpace(i, (Space)result.getOneSpace(i).clone());
            }
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return result;
    }

    public Iterator<Space> iterator() {

        Iterator<Space> it = new Iterator<>() {
            private int currentIndex = 0;
            Node head;

            @Override
            public boolean hasNext() {
                return currentIndex < numOffices &&  head.oneOffice!= null;
            }

            @Override
            public Space next() {

                return head.next.oneOffice;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    @Override
    public int compareTo(Floor obj) {

        if (this.getAmountSpaces() < obj.getAmountSpaces())
            return -1;
        else if (this.getAmountSpaces() > obj.getAmountSpaces())
            return 1;
        return 0;
    }
}

