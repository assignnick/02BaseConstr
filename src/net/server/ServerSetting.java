package net.server;

import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import com.company.Buildings;
import exceptions.BuildingUnderArrestException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServerSetting {



    private static boolean isArrested() {
        Random random = new Random();
        return random.nextInt(9) == 0;
    }

    private static double costValue(Building theBuilding) throws BuildingUnderArrestException {
        if (isArrested())
            throw new BuildingUnderArrestException();
        return theBuilding.getTotalSize() * theBuilding.getCostMultiplier();
    }

    public static Building[] readBuildingsWithTypes(Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<Building> buildings = new ArrayList<>();
        Building b;
        String input = null;

        try {
            while ((input = bufferedReader.readLine()) != null) {
                if (input.equals("POG")) break;
                String buildingType = input;
                Buildings.setBuildingFactory(Buildings.getFactoryFromName(buildingType));
                b = Buildings.readBuilding(bufferedReader);
                buildings.add(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buildings.toArray(new Building[0]);
    }

    public static void writeCosts(Building[] buildings, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        double cost;
        for (Building b : buildings) {
            try {
                cost = costValue(b);
                writer.println(cost);
            } catch (BuildingUnderArrestException e) {
                writer.println("arrested");
            }
        }
        writer.println("POG");
        writer.flush();
    }


    public static Object[] generateCosts(Building[] buildings) {
        Object[] result = new Object[buildings.length];

        for (int i = 0; i < result.length; i++) {
            try {
                result[i] = ServerSetting.costValue(buildings[i]);
            } catch (BuildingUnderArrestException e) {
                result[i] = new BuildingUnderArrestException();
            }
        }
        return result;
    }


//
//    protected static Building readBuilding(BufferedReader reader) {
//        Building building = null;
//        Floor[] floors;
//        Space[] spaces;
//
//        try {
//
//            int floorsAmount = Integer.parseInt(reader.readLine());
//
//            floors = new Floor[floorsAmount];
//            for (int i = 0; i < floorsAmount; i++) {
//
//                int spacesOnFloor = Integer.parseInt(reader.readLine());
//                spaces = new Space[spacesOnFloor];
//                for (int j = 0; j < spacesOnFloor; j++) {
//
//                    double area = Double.parseDouble(reader.readLine());
//                    int rooms = Integer.parseInt(reader.readLine());
//
//                    spaces[j] = Buildings.createSpace(area, rooms);
//                }
//
//                floors[i] = Buildings.createFloor(spaces);
//
//            }
//            building = Buildings.createBuilding(floors);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return building;
//    }
}
