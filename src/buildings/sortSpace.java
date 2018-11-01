package buildings;

import interfaces.Space;

import java.util.Comparator;

public class sortSpace implements Comparator<Space> {
    public int compare(Space o1, Space o2) {
        return -Double.compare(o1.getSize(), o2.getSize());
    }




    public int compareTo(Space o) {
        double d;
        int i;
        String s;
        char c;
        int result;
        result = Double.compare(d, o.d);
        if(result != 0) return result;
        result = Integer.compare(i, o.i);
        if(result != 0) return result;
        result = s.compareTo(o.s);
        if(result != 0) return result;
        result = Character.compare(c, o.c);
        return result;
    }
}