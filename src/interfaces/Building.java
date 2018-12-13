package interfaces;

public interface  Building extends Iterable<Floor>{
    public int getAmountFloors();//получения количества этажей в здании
    public int getAmountSpace();//получения количества помещений в здании
    public double getTotalSize();//получения общей площади всех помещений здания
    public int getTotalRooms();//получения общего количества комнат в помещениях здания
    public Floor[] getMassFloors();//получения массива этажей здания
    public Floor getFloor(int index);//получения этажа здания по его номеру
    public void setFloor(int index, Floor oneFloor);//изменения этажа по его номеру и ссылке на новый этаж
    public Space getSpace(int index);//получения помещения по его номеру в здании
    public void setSpace(int index, Space oneSpace);//изменения помещения в здании по номеру и ссылке на новое помещение
    public void addSpace(int index, Space oneSpace);//вставке помещения в здании по будущему номеру и ссылке на новое помещение
    public void removeSpace(int index);//удаления помещения из здания
    public Space getBestSpace();//получения лучшего помещения в здании
    public double getCostMultiplier();
    public Space[] getMassSpace();//получения отсортированного массива всех помещений
    public String name();
    public Object clone();

}
