package OfficeBuildings;

import java.util.Comparator;

public class sortOffice implements Comparator<Office>
{
    public int compare(Office o1, Office o2)
    {
        return -Double.compare(o1.getSize(),o2.getSize());
    }
}