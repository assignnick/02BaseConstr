package OfficeBuildings;

public class OfficeFloor {
    private Office[] offices;
   private int numOffices;
   private static class Unit { //узел списка
        Unit next;
        Office anOffice;
    }
    private Unit head;
    private Unit tail;

    private OfficeFloor() {
        head = new Unit();
        head.next = head;
    }

    public OfficeFloor(int numOffices) {  //конструктор
        this();
        Unit theHead = head;
        this.numOffices = numOffices;
        for (int i = 0; i < numOffices; i++){
            Unit x = new Unit();
            x.anOffice = new Office();
            theHead.next = x;
            theHead = x;
        }
        theHead.next = head.next;
    }
    public OfficeFloor(Office[] newOfFlat) {//конструктор для массива
        this();
        Unit theHead = head;
        for (int i = 0; i < numOffices; i++){
            Unit x = new Unit();
            x.anOffice = newOfFlat[i];
            theHead.next = x;
            theHead = x;
        }
        theHead.next = head.next;
    }

    void addFront(Office newOf, int number) { //добавить спереди
        Unit a = new Unit();
        a.anOffice = newOf;
        for (int i = 0; i < number; i++) {
            a = a.next;
        }

        if(head == null)
        {
            head = a;
            tail = a;
        }
        else {
            a.next = head;
            head = a;
        }
    }

    void addBack(Office newOf) {  //добавление в конец списка
        Unit a = new Unit();
        a.anOffice = newOf;
        if (tail == null)
        {
            head = a;
            tail = a;
        } else {
            tail.next = a;
            tail = a;
        }
    }

    void delEl(Office delOf)          //удаление элемента
    {
        if(head == null)        //если список пуст -
            return;             //ничего не делаем

        if (head == tail) {     //если список состоит из одного элемента
            head = null;        //очищаем указатели начала и конца
            tail = null;
            return;             //и выходим
        }

        if (head.anOffice == delOf) {    //если первый элемент - тот, что нам нужен
            head = head.next;       //переключаем указатель начала на второй элемент
            return;                 //и выходим
        }

        Unit t = head;       //иначе начинаем искать
        while (t.next != null) {    //пока следующий элемент существует
            if (t.next.anOffice == delOf) {  //проверяем следующий элемент
                if(tail == t.next)      //если он последний
                {
                    tail = t;           //то переключаем указатель на последний элемент на текущий
                }
                t.next = t.next.next;   //найденный элемент выкидываем
                return;                 //и выходим
            }
            t = t.next;                //иначе ищем дальше
        }
    }

    public int getAmountOffices(){  //количество офисов
        return numOffices;
    }

    public double getTotalSize(){  //метод получения общей площади
        double sizer=0;
        for (int i = 0; i < numOffices; i++)
            sizer+= offices[i].getSize();
        return sizer;
    }
    public int getTotalRooms(){  //метод получения общего количества комнат
        int rooms=0;
        for (int i = 0; i < numOffices; i++)
            rooms+= offices[i].getRooms();
        return rooms;
    }
    public Office[] getMassOf(){
        return offices;
    }  //метод получения массива офисов

    public Office getOneOffice(int number){  //метод получения объекта офиса по ее номеру
        return offices[number];
    }

    public void changeOffice(int number, Office newFlat){  //метод изменения офиса по ее номеру на этаже и ссылке на новый офис
        offices[number]=newFlat;
    }

    public void addOffice(int number, Office newOffice){  //метод добавления нового офиса на этаже по будущему номеру офиса
        numOffices++;    //(т.е. в параметрах указывается номер, который должны иметь офис после вставки) и ссылке на объект офиса
        Office[] officeTwo = new Office[numOffices];
        for(int i = 0; i < number+1; i++)
            officeTwo[i]= offices[i];
        officeTwo[number]=newOffice;
        for(int i = number+1; i < numOffices -1; i++)
            officeTwo[i]= offices[i];
        offices =officeTwo;
    }

    public void deleteOffice(int number){  //метод удаления квартиры по ее номеру на этаже
        Office[] flatTwo = new Office[numOffices -1];
        for(int i = 0; i < number-1; i++)
            flatTwo[i]= offices[i];
        for(int i = number+1; i < numOffices; i++)
            flatTwo[i]= offices[i];
        offices =flatTwo;
        numOffices--;
    }

    public Office getBestSpace(){ //метод получения самой большой по площади квартиры этажа.
        double size=0,tmpSize;
        int number=0;
        for(int i = 0; i < numOffices; i++)
        {
            tmpSize= offices[i].getSize();
            if (tmpSize>size) {
                size = offices[i].getSize();
                number=i;
            }
        }
        return offices[number];
    }

}