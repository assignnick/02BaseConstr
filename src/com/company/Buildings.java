package com.company;

import constructions.OfficeBuildings.Office;
import constructions.OfficeBuildings.OfficeBuilding;
import constructions.OfficeBuildings.OfficeFloor;
import constructions.buildings.Dwelling;
import constructions.buildings.DwellingFloor;
import constructions.buildings.Flat;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;
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
       byteOut.close();
   }

    //чтения данных о здании из байтового потока
    public static Building inputBuilding(String str,InputStream in) throws IOException {
        DataInputStream byteIn = new DataInputStream(in);
        Floor floor;
        Space space;
        int maxF = byteIn.readInt();
        Floor[] floors = new Floor[maxF];
        int rooms; double size;
        for (int i = 0; i < maxF; i++) {
            int maxS = byteIn.readInt();
            Space[] spaces= new Space[maxS];
            for (int j = 0; j < maxS; j++) {
                rooms=byteIn.readInt();
                size=byteIn.readDouble();
                if(str.equals("Office"))
                space=new Office(rooms,size);
                else
                space=new Flat(rooms,size);
                spaces[j]=space;
            }
            if (str.equals("Office"))
                floor = new OfficeFloor(spaces);
            else
                floor = new DwellingFloor(spaces);
            floors[i]=floor;
        }
        Building bild;
        if (str.equals("Office"))
            bild = new Dwelling(floors);
        else
            bild = new OfficeBuilding(floors);
        byteIn.close();
        return bild;
    }

 	//записи здания в символьный поток
    public static void writeBuilding (Building building, Writer out){
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
        print.close();

    }
	//чтения здания из символьного потока
    public static Building readBuilding (String str,Reader in) throws IOException{
        StreamTokenizer st = new StreamTokenizer(in);
        Floor floor;
        Space space;

        int maxF = st.nextToken();
        Floor[] floors = new Floor[maxF];
        int rooms; double size;
        for (int i = 0; i < maxF; i++) {
            int maxS = st.nextToken();
            Space[] spaces= new Space[maxS];
            for (int j = 0; j < maxS; j++) {
                rooms=st.nextToken();
                size=(double) st.nextToken();
                if(str.equals("Office"))
                    space=new Office(rooms,size);
                else
                    space=new Flat(rooms,size);
                spaces[j]=space;
            }
            if (str.equals("Office"))
                floor = new OfficeFloor(spaces);
            else
                floor = new DwellingFloor(spaces);
            floors[i]=floor;
        }
        Building build;
        if (str.equals("Office"))
            build = new Dwelling(floors);
        else
            build = new OfficeBuilding(floors);
        return build;
    }



    //сериализации здания в байтовый поток
    public static void serializeBuilding (Building building, ObjectOutputStream out) throws IOException{
     out = new ObjectOutputStream(new FileOutputStream("out.bin"));
     out.writeObject(building);
     out.close();
    }
    //десериализации здания из байтового потока
    public static Building deserializeBuilding(String str, ObjectInputStream in) {
        try {
            Building build;
            in = new ObjectInputStream(new FileInputStream("out.bin"));
            if (str.equals("Office"))
                build = (OfficeBuilding) in.readObject();
            else
                build = (Dwelling) in.readObject();
            in.close();
            return build;
        } catch (IOException e) {
            System.out.println("Some error occurred!");
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong object type");
        }
        return null;
    }

    public static void writeBuildingFormat(String str,Building building, Writer out) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        if (str.equals("Office"))
            sb.append(formatter.format("Офисное здание"));
        else
            sb.append(formatter.format("Жилое здание"));
        Floor floor;
        Space space;
        int max = building.getAmountFloors();
        sb.append(formatter.format("Количество этажей %s\n",max));
        for (int i = 0; i < max; i++) {
            floor = building.getOneFloor(i);
            int maxS=floor.getAmountSpaces();
            sb.append(formatter.format("Этаж %s(%s). Квартир на этаже %s\n",i+1,i,maxS));
            int number=0;
            for (int j = 0; j < maxS; j++) {
                number++;
                sb.append(formatter.format("Квартира №%s (%s) ",number,j));
                space = floor.getOneSpace(j);
                sb.append(formatter.format("Комнат: %s, Площадь: %f\n",space.getRooms(),space.getSize()));
            }
        }

 /*       PrintWriter print = new PrintWriter(out);

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
        print.close();*/


    }
}

//        Добавьте в класс Buildings следующие методы:
//        •	сериализации здания в байтовый поток
//public static void serializeBuilding (Building building, OutputStream out);
//        •	десериализации здания из байтового потока
//public static Building deserializeBuilding (InputStream in);
//        Продемонстрировать возможности сериализации в методе main(), записав в файл объект, затем считав и сравнив его с исходным.
////
////        Задание 5
////        Добавьте метод текстовой записи
////public static void writeBuildingFormat (Building building, Writer out)
////        для зданий класса Buildings, использующий возможности форматированного вывода.
////        Перегрузите метод текстового чтения зданий класса Buildings таким образом,
// чтобы он использовал возможности форматированного ввода и вывода и имел аргумент типа Scanner.
////
////
////        Примечание: При проверке работы методов в методе main() используйте блок try с ресурсом.