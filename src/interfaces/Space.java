package interfaces;

public interface Space extends Comparable<Space> {
    public int getRooms();

    public void setRooms(int rooms);

    public double getSize();

    public void setSize(double size);

    public Object clone();
}