package interfaces;

public interface Floor {
    public int getAmountSpaces();//получения количества помещений на этаже
    public double getTotalSize();//получения общей площади помещений на этаже
    public int getTotalRooms();//получения общего количества комнат в помещениях этажа
    public Space[] getMassSpace();//получения массива всех помещений этажа
    public Space getOneSpace(int index);//получения помещения по его номеру
    public void changeSpace(int index, Space space);//изменения помещения по его номеру и ссылке на новое помещение
    public void addSpace(int index, Space space);//вставки помещения по его номеру и ссылке на новое помещение
    public void removeSpace(int index);//удаления помещения по его номеру
    public Space getBestSpace();//получения лучшего помещения на этаже
}