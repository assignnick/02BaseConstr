package buildings;

import java.util.Comparator;

public class sortFlat implements Comparator<Flat>
{
    public int compare(Flat o1, Flat o2)
    {
        return -Double.compare(o1.getSize(),o2.getSize());
    }
}