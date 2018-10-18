package exceptions;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public SpaceIndexOutOfBoundsException() {
        super("выход за границы номеров помещений ");
    }
}
