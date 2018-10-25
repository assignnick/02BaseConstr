package com.company;

import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;

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
    public static Building inputBuilding(InputStream in) throws IOException {
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
//                Space space=new s
            }
        }
        byteIn.close();
        return null;
//
//
//
//        for (int i = 0, sizeFloors = floors.length; i < sizeFloors; i++) {
//
//            for (int j = 0, sizeFlats = flats.length; j < sizeFlats; j++) {
//                flats[j] = buildingFactory.createSpace(dis.readInt(), dis.readDouble());
//            }
//            floors[i] = buildingFactory.createFloor(flats);
//        }
//        dis.close();
//        return buildingFactory.createBuilding(floors);

    }
 	//записи здания в символьный поток
    public static void writeBuilding (Building building, Writer out){
     OutputStreamWriter writer = new OutputStreamWriter(System.out);

    }
	//чтения здания из символьного потока
    public static Building readBuilding (Reader in){
     InputStreamReader reader = new InputStreamReader(System.in);
    return null;
    }

    //сериализации здания в байтовый поток
    public static void serializeBuilding (Building building, ObjectOutputStream out) throws IOException{
     out = new ObjectOutputStream(new FileOutputStream("out.bin"));
     out.writeObject(building);
     out.close();

    }
    //десериализации здания из байтового потока
    public static Building deserializeBuilding (ObjectInputStream in){
     try {
      in = new ObjectInputStream(new FileInputStream("out.bin"));
      in.readObject();
     in.close();
     }
    catch(IOException e) {
      System.out.println("Some error occurred!");
     }
     catch(ClassNotFoundException e) {
      System.out.println("Wrong object type");
     }
        return null;
    }
}
