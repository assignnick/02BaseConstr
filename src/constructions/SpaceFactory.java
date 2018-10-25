package constructions;

import constructions.OfficeBuildings.Office;
import constructions.buildings.Flat;
import interfaces.Space;


/**
 * в процессе
*/
public class SpaceFactory {
        public void main(String[] args) {
            Creator[] creators = {
                    new FlatCreator(),
                    new OfficeCreator()};
            for (Creator creator : creators) {
                Space space = creator.factoryMethod();
                System.out.println("Created {%s}\n" + space.getClass());
            }
        }
    abstract class Creator {
        public abstract Space factoryMethod();
    }
    class FlatCreator extends Creator {
        public Space factoryMethod() {
            return new Flat(); }
    }
    class OfficeCreator extends Creator {
        public Space factoryMethod() {
            return new Office(); }
    }

}

