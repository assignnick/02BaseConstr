package buildings.threads;

import interfaces.Floor;
import interfaces.Space;

public class Repairer extends Thread {
    protected Floor floor;

    public Repairer(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        int spacesAmount = floor.getAmountSpaces();
        Space[] spaces = floor.getMassSpace();
        for(int i = 0; i < spacesAmount; i++) {
            System.out.println("Repairing space number " + i + " with total area " + spaces[i].getSize() + " square meters");
        }

    }
}

