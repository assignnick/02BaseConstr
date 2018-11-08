package interfaces;

public interface Space extends Comparable<Space>{
    public int getRooms();
    public void setRooms(int rooms);
    public double getSize();
    public void setSize(double size);
    public String toString();
    public int hashCode();
    public Object clone();
    public boolean equals(Object obj);
}