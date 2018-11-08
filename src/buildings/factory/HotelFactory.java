
package buildings.factory;

import buildings.dwelling.Flat;
import buildings.hotel.*;
import interfaces.*;


public class HotelFactory implements BuildingFactory {
	public Space createSpace(double area) {
		return new Flat(area);
	}

	public Space createSpace(int roomsCount, double area) {
		return new Flat(roomsCount, area);
	}

	public Floor createFloor(int spacesCount) {
		return new HotelFloor(spacesCount);
	}

	public Floor createFloor(Space[] spaces) {
		return new HotelFloor(spaces);
	}

	public Building createBuilding(int floorsCount, int[] spacesCounts) {
		return new Hotel(floorsCount, spacesCounts);
	}

	public Building createBuilding(Floor[] floors) {
		return new Hotel(floors);
	}
}
