import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void getName() {
        Animal newObject = new Animal("Oliver","Swim","Seal");
        assertEquals("Oliver", newObject.getName());
    }

    @Test
    void getFavoriteActivity() {
        Animal newObject = new Animal("Oliver","Swim","Seal");
        assertEquals("Swim", newObject.getFavoriteActivity());
    }

    @Test
    void getFavoriteFood() {
        Animal newObject = new Animal("Oliver","Swim","Seal");
        assertEquals("Seal", newObject.getFavoriteFood());
    }
}