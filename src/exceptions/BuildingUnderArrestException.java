package exceptions;

public class BuildingUnderArrestException extends Throwable {
    public BuildingUnderArrestException(String string) {
        System.out.println(string);
    }

}
