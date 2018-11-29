package buildings.hotel;

import buildings.dwelling.DwellingFloor;
import interfaces.Space;

import java.util.Arrays;

public class HotelFloor extends DwellingFloor{


    //Количество звезд этажа при создании объекта должно браться из константы в классе, равной 1.
    private static final int CONSTSTAR  = 1;
        private int numApartments;
        private int star;
        private Space[] flats;

        public  HotelFloor( int numApartments) {//конструктор
            super(numApartments);
            this.numApartments = numApartments;
            this.star=CONSTSTAR;
        }
        public  HotelFloor( Space[] newFlat) {
            super(newFlat);
            this.star=CONSTSTAR;

        }  //конструктор для массива


   // методы получения и изменения его количества звезд.
    public int  getStar(){
            return star;
    }

    public void  setStar(int star){
            this.star=star;
    }



        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append("HotelFloor ");
            str.append(numApartments);
            str.append(" apps  ");
            str.append(star);
            str.append(" stars  ");
            for(Space sp : flats) {
                str.append(", ");
                str.append(sp.toString());
            }
            return str.toString();
        }

        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof HotelFloor))
                return false;
            HotelFloor other = (HotelFloor) obj;
            if (!Arrays.equals(flats, other.flats))
                return false;
            return true;
        }
        public int hashCode() {
            int hashf = 31;
            int res = 1;
            for(Space sp : flats)
                res += hashf ^ res ^ sp.hashCode() ^ flats.length ^ getStar();
            return res;
        }

        public Object clone() {
            HotelFloor result = null;
            result = (HotelFloor) super.clone();
            result.flats = this.flats.clone();

            for(int i = 0; i < result.getAmountSpaces(); i++) {
                result.setSpace(i, (Space)result.getOneSpace(i).clone());
            }
            return result;
        }
}
