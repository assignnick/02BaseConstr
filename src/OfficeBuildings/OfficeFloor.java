package OfficeBuildings;

public class OfficeFloor {
    //private Office[] offices;
   private int numOffices;
    private static class Node { //узел списка
        Node next;
        Office oneOffice;
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
    public OfficeFloor(Office[] newOfFlat) {//конструктор для массива
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

    void addFront(Office newOf, int number) { //(не нужно)
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
    }


    private void deleteNode(int index) {  //приватный метод удаления узла из списка по его номеру
        Node a = head;
        for (int i = 0; i < index; i++) {
            a = a.next;
        }
        a.next = a.next.next;
    }


    public int getAmountOffices(){  // метод получения количества офисов на этаже
        int res=0;
        Node temp=head;
        do
        {
            temp = temp.next;
            res++;
        } while(temp.next != head.next);
        return res;
    }

    public int getAmountOfficestoo(){  //количество офисов
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

    public Office[] getMassOf(){ //метод получения массива офисов
        int i=0;
        Office[] offices = new Office[getAmountOffices()];
        Node temp=head;
        do
        {
            temp = temp.next;
            offices[i]= temp.oneOffice;
            i++;
        } while(temp.next != head.next);
        return offices;
    }

    public Office getOneOffice(int number){  //метод получения объекта офиса по ее номеру
        Node temp=head;
        for (int i=0;i<number;i++)
            temp = temp.next;
        return temp.next.oneOffice;
    }

    public void changeOffice(int number, Office newOffice){  //метод изменения офиса по ее номеру на этаже и ссылке на новый офис
       /* Node temp=head;
        for (int i=0;i<number;i++)
            temp = temp.next;
        temp.next.oneOffice= newOffice;*/
       getNode(number).oneOffice=newOffice;
    }

    public void addOffice(int number, Office newOffice){  //метод добавления нового офиса на этаже по будущему номеру офиса
        Node temp = new Node();
        temp.oneOffice = newOffice;
        addNode(number,temp);
    }

    public void deleteOffice(int number){  //метод удаления офиса по его номеру на этаже
        deleteNode(number);
    }

    public Office getBestSpace(){ //получения самого большого по площади офиса этажа
        double maxSize = 0;
        Office maxOffice = null;
        Node temp = head;
        for (int i = 0; i <= getAmountOffices(); i++) {
            temp = temp.next;
            if(temp.oneOffice.getSize() > maxSize) {
                maxSize = temp.oneOffice.getSize();
                maxOffice = temp.oneOffice;
            }
        }
        return maxOffice;
    }

}
