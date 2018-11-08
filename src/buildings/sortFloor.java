package buildings;

import interfaces.Floor;

import java.util.Comparator;

public class sortFloor implements Comparator<Floor> {
    public int compare(Floor o1, Floor o2) {
        return -Integer.compare(o1.getAmountSpaces(), o2.getAmountSpaces());
    }

    public int compareTo(Floor o) {
        return 0;
    }
}

