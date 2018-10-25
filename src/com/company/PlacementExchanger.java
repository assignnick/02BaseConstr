package com.company;

import exceptions.FloorIndexOutOfBoundsException;
import exceptions.InexchangeableFloorsException;
import exceptions.InexchangeableSpacesException;

import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.*;

//Задание 2
//Создайте отдельный класс PlacementExchanger, работающий со ссылками типа Space, Floor, Building и содержащий следующие статические методы.
//Метод проверки возможности обмена помещениями. Передаются две ссылки на объекты типа Space. Метод возвращает true, если общая площадь и количество комнат в помещениях равны, и false в других случаях.
//Метод проверки возможности обмена этажами. Методу передаются две ссылки на объекты типа Floor. Метод возвращает true, если общая площадь этажей и количество помещений равны, и false в других случаях.
//Метод обмена помещениями двух этажей public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2). Метод должен проверять возможность обмена помещениями и допустимость номеров помещений, выбрасывать при необходимости соответствующие исключения.
//Метод обмена этажами двух зданий public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2). Методу передаются две ссылки типа Building и номера соответствующих этажей. Метод должен проверять возможность обмена этажами и допустимость номеров этажей, выбрасывать при необходимости соответствующие исключения


public class PlacementExchanger {

    public static boolean replaceSpace(Space space1, Space space2) {  //Метод проверки возможности обмена помещениями
        if (space1.getRooms() == space2.getRooms() || space1.getSize() == space2.getSize())
            return true;
        else return false;
    }

    public static boolean replaceFloor(Floor floor1, Floor floor2) {  //Метод проверки возможности обмена этажами
        if (floor1.getAmountSpaces() == floor2.getAmountSpaces() || floor1.getTotalSize() == floor2.getTotalSize())
            return true;
        else return false;
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException {  //Метод обмена помещениями двух этажей
        if (!replaceSpace(floor1.getOneSpace(index1), floor2.getOneSpace(index2))) {
            throw new InexchangeableSpacesException();
        }
        if ((index1 >= floor1.getAmountSpaces() || index1 < 0) || (index2 >= floor2.getAmountSpaces() || index2 < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Space temp = floor1.getOneSpace(index1);
        floor1.changeSpace(index1, floor2.getOneSpace(index2));
        floor2.changeSpace(index2, temp);
    }// Метод должен проверять возможность обмена помещениями и допустимость номеров помещений, выбрасывать при необходимости соответствующие исключения.


    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException {  //Метод обмена этажами двух зданий Методу передаются две ссылки типа Building и номера соответствующих этажей. Метод должен проверять возможность обмена этажами и допустимость номеров этажей, выбрасывать при необходимости соответствующие исключения
        if (!replaceFloor(building1.getOneFloor(index1), building2.getOneFloor(index2))) {
            throw new InexchangeableFloorsException();
        }
        if ((index1 >= building1.getAmountFloors() || index1 < 0) || (index2 >= building2.getAmountFloors() || index2 < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        Floor temp = building1.getOneFloor(index1);
        building1.changeFloor(index1, building2.getOneFloor(index2));
        building2.changeFloor(index2, temp);
    }// Метод должен проверять возможность обмена помещениями и допустимость номеров помещений, выбрасывать при необходимости соответствующие исключения.
}

