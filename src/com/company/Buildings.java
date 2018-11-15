package com.company;


import buildings.dwelling.Dwelling;
import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import buildings.hotel.Hotel;
import buildings.office.OfficeBuilding;
import buildings.sortFloor;
import buildings.sortSpace;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;

//Задание 3
//Создайте отдельный класс Buildings, работающий со ссылками типа Space, Floor, Building, содержащий статические методы ввода-вывода зданий:
//        •	записи данных о здании в байтовый поток
//public static void outputBuilding (Building building, OutputStream out);
//        •	чтения данных о здании из байтового потока
//public static Building inputBuilding (InputStream in);
//        •	записи здания в символьный поток
//public static void writeBuilding (Building building, Writer out);
//        •	чтения здания из символьного потока
//public static Building readBuilding (Reader in).
//        Записанные данные о здании представляет собой последовательность чисел, первым из которых является количество этажей, далее следует количество помещений текущего этажа и соответствующие значения количества комнат и площадей помещений текущего этажа.
//        Например, символьная запись для трехэтажного здания:
//        «3 2 3 150.0 2 100.0 1 3 250.0 3 2 140.0 1 60.0 1 50.0»
//        Для чтения этажа из символьного потока можно использовать StreamTokenizer.
//        Проверьте возможности всех реализованных методов, в качестве реальных потоков используя файловые потоки, а также потоки System.in и System.out.
//


public class Buildings {
    private static BuildingFactory BuildingFactory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory BuildingFactory) {
        Buildings.BuildingFactory = BuildingFactory;
    }

    public static Space createSpace(double area) {
        return BuildingFactory.createSpace(area);
    }

    public static Space createSpace(int roomsCount, double area) {
        return BuildingFactory.createSpace(roomsCount, area);
    }

    public static Floor createFloor(int spacesCount) {
        return BuildingFactory.createFloor(spacesCount);
    }

    public static Floor createFloor(Space[] spaces) {
        return BuildingFactory.createFloor(spaces);
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return BuildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    public static Building createBuilding(Floor[] floors) {
        return BuildingFactory.createBuilding(floors);
    }

    //записи данных о здании в байтовый поток
    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream byteOut = new DataOutputStream(out);
        Floor floor;
        Space space;
        byteOut.writeInt(building.getAmountFloors());
        for (int i = 0, max = building.getAmountFloors(); i < max; i++) {
            floor = building.getOneFloor(i);
            byteOut.writeInt(floor.getAmountSpaces());
            for (int j = 0, maxS = floor.getAmountSpaces(); j < maxS; j++) {
                space = floor.getOneSpace(j);
                byteOut.writeInt(space.getRooms());
                byteOut.writeDouble(space.getSize());
            }
        }
//       byteOut.close();
    }

    //чтения данных о здании из байтового потока
    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream byteIn = new DataInputStream(in);
        Floor floor;
        Space space;
        int maxF = byteIn.readInt();
        Floor[] floors = new Floor[maxF];
        int rooms;
        double size;
        for (int i = 0; i < maxF; i++) {
            int maxS = byteIn.readInt();
            Space[] spaces = new Space[maxS];
            for (int j = 0; j < maxS; j++) {
                rooms = byteIn.readInt();
                size = byteIn.readDouble();
                space = createSpace(rooms, size);//instead if
                spaces[j] = space;
            }
            floor = createFloor(spaces);//instead if
            floors[i] = floor;
        }
        Building bild;
        bild = createBuilding(floors);//instead if
//        byteIn.close();
        return bild;
    }

    //записи здания в символьный поток
    public static void writeBuilding(Building building, Writer out) {
        PrintWriter print = new PrintWriter(out);
        Floor floor;
        Space space;
        print.print(building.getAmountFloors());
        print.print(" ");
        for (int i = 0, max = building.getAmountFloors(); i < max; i++) {
            floor = building.getOneFloor(i);
            print.print(floor.getAmountSpaces());
            print.print(" ");
            for (int j = 0, maxS = floor.getAmountSpaces(); j < maxS; j++) {
                space = floor.getOneSpace(j);
                print.print(space.getRooms());
                print.print(" ");
                print.print(space.getSize());
                print.print(" ");
            }
        }
//        print.close();

    }

    //чтения здания из символьного потока
    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        Floor floor;
        Space space;

        int maxF = st.nextToken();
        Floor[] floors = new Floor[maxF];
        int rooms;
        double size;
        for (int i = 0; i < maxF; i++) {
            int maxS = st.nextToken();
            Space[] spaces = new Space[maxS];
            for (int j = 0; j < maxS; j++) {
                rooms = st.nextToken();
                size = (double) st.nextToken();
                space = createSpace(rooms, size);//instead if
                spaces[j] = space;
            }
            floor = createFloor(spaces);//instead if
            floors[i] = floor;
        }
        Building build;
        build = createBuilding(floors);//instead if
        return build;
    }

    //чтения здания из символьного потока
    //scanner
    public static Building readBuildingSc(Scanner sc) {
        Floor floor;
        Space space;
        int maxF = sc.nextInt();
        Floor[] floors = new Floor[maxF];
        int rooms;
        double size;
        for (int i = 0; i < maxF; i++) {
            int maxS = sc.nextInt();
            Space[] spaces = new Space[maxS];
            for (int j = 0; j < maxS; j++) {
                rooms = sc.nextInt();
                size = (double) sc.nextDouble();
                space = createSpace(rooms, size);//instead if
                spaces[j] = space;
            }
            floor = createFloor(spaces);//instead if
            floors[i] = floor;
        }
        Building build;
        build = createBuilding(floors);//instead if
        return build;
    }


    //сериализации здания в байтовый поток
    public static void serializeBuilding(Building building, ObjectOutputStream out) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream("out.bin"));
        out.writeObject(building);
//     out.close();
    }

    //десериализации здания из байтового потока
    public static Building deserializeBuilding(ObjectInputStream in) {
        try {
            Building build = null;
            in = new ObjectInputStream(new FileInputStream("out.bin"));
//            if (BuildingFactory instanceof DwellingFactory)
                build = (Dwelling) in.readObject();
//            else if (BuildingFactory instanceof OfficeFactory)
//                build = (OfficeBuilding) in.readObject();
//            else if (BuildingFactory instanceof HotelFactory)
//                build = (Hotel) in.readObject();
//            in.close();
            return build;
        } catch (IOException e) {
            System.out.println("Some error occurred!");
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong object type");
        }
        return null;
    }

    public static void writeBuildingFormat(Building building, Writer out) {
        Formatter formatter = new Formatter(out);
        formatter.format(building.name() + "\n");
        Floor floor;
        Space space;
        int max = building.getAmountFloors();
        int rooms;
        double size;
        formatter.format("Количество этажей %s\n", max);
        for (int i = 0; i < max; i++) {
            floor = building.getOneFloor(i);
            int maxS = floor.getAmountSpaces();
            formatter.format("Этаж %s(%s). Квартир на этаже %s\n", i + 1, i, maxS);
            int number = 0;
            for (int j = 0; j < maxS; j++) {
                number++;
                formatter.format("Квартира №%s(%s) ", number, j);
                space = floor.getOneSpace(j);
                rooms = space.getRooms();
                size = space.getSize();
                formatter.format("Комнат: %s, Площадь: %.2f\n", rooms, size);
            }
        }
    }

    public int Sort(Object o1, Object o2) {
        if (o1 instanceof Space && o2 instanceof Space)
            return new sortSpace().compare((Space)o1,(Space)o2);
        else if (o1 instanceof Floor && o2 instanceof Floor)
            return new sortFloor().compare((Floor)o1,(Floor)o2);
        return 0;
    }
}
