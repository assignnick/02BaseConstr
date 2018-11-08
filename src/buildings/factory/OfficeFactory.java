
package buildings.factory;

import buildings.office.*;
import interfaces.*;


public class OfficeFactory implements BuildingFactory {
	public Space createSpace(double area) {
		return new Office(area);
	}

	public Space createSpace(int roomsCount, double area) {
		return new Office(roomsCount, area);
	}

	public Floor createFloor(int spacesCount) {
		return new OfficeFloor(spacesCount);
	}

	public Floor createFloor(Space[] spaces) {
		return new OfficeFloor(spaces);
	}

	public Building createBuilding(int floorsCount, int[] spacesCounts) {
		return new OfficeBuilding(floorsCount, spacesCounts);
	}

	public Building createBuilding(Floor[] floors) {
		return new OfficeBuilding(floors);
	}
}
