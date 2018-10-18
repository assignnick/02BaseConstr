package exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException {

    public InvalidRoomsCountException() {
        super("невозможное значение размера помещения");
    }
}
