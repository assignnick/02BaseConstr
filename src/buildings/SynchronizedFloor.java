package buildings;


import interfaces.Floor;
import interfaces.Space;

import java.util.Iterator;

public class SynchronizedFloor implements Floor {
    private Floor floor;

    public SynchronizedFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public synchronized int getAmountSpaces() {
        return floor.getAmountSpaces();
    }

    @Override
    public synchronized double getTotalSize() {
        return floor.getTotalSize();
    }

    @Override
    public synchronized int getTotalRooms() {
        return floor.getTotalRooms();
    }

    @Override
    public synchronized Space[] getMassSpace() {
        return floor.getMassSpace();
    }

    @Override
    public synchronized Space getOneSpace(int index) {
        return floor.getOneSpace(index);
    }

    @Override
    public synchronized void setSpace(int index, Space space) {
        floor.setSpace(index, space);
    }

    @Override
    public synchronized void addSpace(int index, Space space) {
        floor.addSpace(index, space);
    }

    @Override
    public synchronized void removeSpace(int index) {
        floor.removeSpace(index);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public synchronized String toString() {
        return floor.toString();
    }

    @Override
    public synchronized int hashCode() {
        return floor.hashCode();
    }

    @Override
    public synchronized boolean equals(Object obj) {
        return floor.equals(obj);
    }

    @Override
    public synchronized Object clone() {
        Floor result = null;
        try {
            result = (Floor) super.clone();
            for(int i = 0; i < result.getAmountSpaces(); i++) {
                result.setSpace(i, (Space)result.getOneSpace(i).clone());
            }
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return result;
    }

    @Override
    public int compareTo(Floor obj) {
        return floor.compareTo(obj);
    }

    @Override
    public Iterator<Space> iterator() {
        return floor.iterator();
    }
}
