package buildings.hotel;

import buildings.dwelling.Dwelling;
import interfaces.Floor;
import interfaces.Space;

import java.util.Arrays;

public class Hotel extends Dwelling{
    private int numFloors;
    private Floor[] floors;
    public  Hotel( int numFloors,int numApartments ) {
        super(numFloors,numApartments);

    }
    public  Hotel( int numFloors,int[] spacesCounts ) {
        super(numFloors,spacesCounts);
    }
    public  Hotel(Floor[] Floors) {
        super(Floors);

    }


    public int NumberOfStars(){
        int max=0;
        for(Object floor: floors){
            if (floor instanceof HotelFloor) {
                if(max < ((HotelFloor) floor).getStar())
                    max=((HotelFloor) floor).getStar();
            }
        }
        return max;
    }



    //метод получения самой большой по площади квартиры дома.
    public Space getBestSpace(){
            double coeff;
            int div,rem;
            Space bestSpace = null;
            double result = 0;
            for(Floor floor : floors) {
                if (floor instanceof HotelFloor) {
                    for(Space flat: floor.getMassSpace()) {
                        div=(((HotelFloor) floor).getStar())/3;
                        rem=(((HotelFloor) floor).getStar())%3;
                        coeff=div+rem*0.25;
                        double score = flat.getSize()*coeff;
                        if (result < score) {
                            result = score;
                            bestSpace = flat;
                        }
                    }
                }
            }
            return bestSpace;
        }


    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Hotel ");
        str.append(numFloors);
        str.append(" flors  ");
        str.append(NumberOfStars());
        str.append(" stars  ");
        for(Floor sp : floors) {
            str.append(", ");
            str.append(sp.toString());
        }
        return str.toString();
    }

    public String name(){ //toString???
        return "Здание отеля";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Hotel))
            return false;
        Hotel other = (Hotel) obj;
        if (!Arrays.equals(floors, other.floors))
            return false;
        return true;
    }

    public int hashCode() {
        int hashf = 31;
        int res = 1;
        for(Floor sp : floors)
            res += hashf ^ res ^ sp.hashCode() ^ floors.length;
        return res;
    }
    public Object clone() {
        Hotel result = null;
        result = (Hotel) super.clone();
        result.floors=this.floors.clone();

        // clone of attay
        for(int i = 0; i < result.getAmountFloors(); i++) {

            result.changeFloor(i, (Floor)result.getOneFloor(i).clone());
        }
        return result;
    }
}
