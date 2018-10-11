package buildings;

public class DwellingFloor {
   private int numApartments;
   private Flat[] flat;
    public  DwellingFloor( int numApartments) {  //конструктор
        this.numApartments = numApartments;
        this.flat = new Flat[numApartments];
        for (int i = 0; i < numApartments; i++)
            flat[i] = new Flat();
    }
    public  DwellingFloor( Flat[] newFlat) {
            this.flat=newFlat;
    }  //конструктор для массива
    public int getAmountApart(){  //метод получения количества квартир на этаже
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
    public Flat[] getMassFlat(){
        return flat;
    }  //метод получения массива квартир этажа

    public Flat getOneFlat(int number){  //метод получения объекта квартиры по ее номеру на этаже

        return flat[number];
    }

    public void changeFlat(int number,Flat newFlat){  //метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру
        flat[number]=newFlat;
    }

    public void addFlat(int number,Flat newFlat){  //метод добавления новой квартиры на этаже по будущему номеру квартиры
        numApartments++;    //(т.е. в параметрах указывается номер, который должны иметь квартира после вставки) и ссылке на объект квартиры
        Flat[] flatTwo = new Flat[numApartments];
        for(int i = 0; i < number+1; i++)
            flatTwo[i]=flat[i];
        flatTwo[number]=newFlat;
        for(int i = number+1; i < numApartments-1; i++)
            flatTwo[i]=flat[i];
        flat=flatTwo;
    }

    public void deleteFlat(int number){  //метод удаления квартиры по ее номеру на этаже
        Flat[] flatTwo = new Flat[numApartments-1];
        for(int i = 0; i < number-1; i++)
            flatTwo[i]=flat[i];
        for(int i = number+1; i < numApartments; i++)
            flatTwo[i]=flat[i];
        flat=flatTwo;
        numApartments--;
    }

    public Flat getBestSpace(){ //метод получения самой большой по площади квартиры этажа.
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
