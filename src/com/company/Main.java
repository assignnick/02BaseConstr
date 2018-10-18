package com.company;
import OfficeBuildings.Office;
import OfficeBuildings.OfficeBuilding;
import OfficeBuildings.OfficeFloor;
import buildings.*;

import java.util.Random;

class Main {
    public static void main(String[] s) {
        System.out.println(Double.compare(1.,0.));
        Random random =new Random();
        Dwelling Dom = new Dwelling(5,4);
        System.out.println(Dom.getMassSpace());
        System.out.println("");

        OfficeBuilding newOff = new OfficeBuilding(3,new int[]{3,4,5});

        int[] a ={3, 4, 5};
        OfficeBuilding newOffice = new OfficeBuilding(a.length, a);

        OfficeFloor newfloor = new OfficeFloor(1);
        System.out.println("newfloor "+newfloor.getAmountSpaces());


        /*for (int i=0;i<3;i++)
            for (int j=0;j<newOffice.getOneFloor(i).getAmountSpaces();j++)
                newOffice.getOneFloor(i).getOneSpace(j).setSize(random.nextInt(400)+1);
                System.out.println("newOffice 0 "+newOffice.getOneFloor(0).getAmountSpaces());
                */
        System.out.println(newOffice.getMassSpace());


    }
}

