package exceptions;

public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public FloorIndexOutOfBoundsException() {
        super("выход за границы номеров этажей");
    }
}
