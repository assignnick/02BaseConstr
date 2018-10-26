package com.company;
import constructions.OfficeBuildings.Office;
import constructions.OfficeBuildings.OfficeBuilding;
import constructions.OfficeBuildings.OfficeFloor;
import constructions.buildings.Dwelling;

import java.io.*;
import java.util.Random;

class Main {
    public static void main(String[] s) {
        System.out.println(Double.compare(1., 0.));
        Random random = new Random();
        Dwelling Dom = new Dwelling(5, 4);
        System.out.println(Dom.getMassSpace());
        System.out.println("");

        OfficeBuilding newOff = new OfficeBuilding(3, new int[]{3, 4, 5});

        int[] a = {3, 4, 5};
        OfficeBuilding newOffice = new OfficeBuilding(a.length, a);
        Dwelling newDDD = new Dwelling(a.length, 5);

        OfficeFloor newfloor = new OfficeFloor(1);
        System.out.println("newfloor " + newfloor.getAmountSpaces());

/*
        for (int i=0;i<3;i++)
            for (int j=0;j<newOffice.getOneFloor(i).getAmountSpaces();j++)
            newOffice.getOneFloor(i).getOneSpace(j).setSize(random.nextInt(400)+1);
        System.out.println("newOffice 0 "+newOffice.getOneFloor(0).getOneSpace(0).getSize());*/

        System.out.println(newDDD.getOneFloor(0));

        System.out.println(newDDD.getOneFloor(1));
        System.out.println(newDDD.getOneFloor(2));

        OfficeFloor newOfficeFl = new OfficeFloor(a.length);
        System.out.println("fg " + newOfficeFl.getOneSpace(0));
        System.out.println("fg " + newOfficeFl.getOneSpace(1));
        System.out.println("fg " + newOfficeFl.getOneSpace(2));

        System.out.println(newOffice.getOneFloor(0));

        System.out.println(newOffice.getOneFloor(1));
        System.out.println(newOffice.getOneFloor(2));


        Buildings.writeBuildingFormat("Office", newDDD);

    }
}


//    Задание на практическую работу
//    Ознакомьтесь с концепцией интерфейсов и механизмом систем ввода/вывода данных в Java.
//        Задание 1
//        Опишите классы ошибок:
//        •	несоответствия обменивающихся помещений InexchangeableSpacesException (объявляемое).
//        •	несоответствия обменивающихся этажей InexchangeableFloorsException (объявляемое).


//Задание 4
//        Модифицировать классы помещений, этажей и зданий таким образом, чтобы они были сериализуемыми.
//        Добавьте в класс Buildings следующие методы:
//        •	сериализации здания в байтовый поток
//public static void serializeBuilding (Building building, OutputStream out);
//        •	десериализации здания из байтового потока
//public static Building deserializeBuilding (InputStream in);
//        Продемонстрировать возможности сериализации в методе main(), записав в файл объект, затем считав и сравнив его с исходным.
//
//        Задание 5
//        Добавьте метод текстовой записи
//public static void writeBuildingFormat (Building building, Writer out)
//        для зданий класса Buildings, использующий возможности форматированного вывода.
//        Перегрузите метод текстового чтения зданий класса Buildings таким образом,
// чтобы он использовал возможности форматированного ввода и вывода и имел аргумент типа Scanner.
//
//
//        Примечание: При проверке работы методов в методе main() используйте блок try с ресурсом.

/*
*
*
* */