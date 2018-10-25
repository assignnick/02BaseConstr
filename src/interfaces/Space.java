package interfaces;

public interface Space {
    public int getRooms();
    public void setRooms(int rooms);
    public double getSize();
    public void setSize(double size);
    public String toString();
    public int hashCode();
    public Object clone();
}