package buildings;

import interfaces.Space;

import java.util.Comparator;

public class sortFlat implements Comparator<Space>
        {
    public int compare(Space o1, Space o2)
    {
        return -Double.compare(o1.getSize(),o2.getSize());
    }
}