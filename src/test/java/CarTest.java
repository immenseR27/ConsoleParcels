import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testEqualsCar() {
        Car car1 = new Car();
        car1.getBody()[6][1] = "1";
        Car car2 = new Car();
        car2.getBody()[6][1] = "1";
        Car car3 = new Car();
        car3.getBody()[6][1] = "2";
        car3.getBody()[6][2] = "2";
        assertAll(
                () -> assertEquals(car1, car2),
                () -> assertNotEquals(car1, car3)
        );
    }

    @Test
    public void testPutParcel() {
        Parcel parcel = new Parcel(7, List.of(3, 4));
        Car car1 = new Car();
        String[][] body = {
                {"+", "+", "+", "+", "+", "+", "+", "+"},
                {"+", " ", " ", " ", " ", " ", " ", "+"},
                {"+", " ", " ", " ", " ", " ", " ", "+"},
                {"+", " ", " ", " ", " ", " ", " ", "+"},
                {"+", " ", " ", " ", " ", " ", " ", "+"},
                {"+", "7", "7", "7", " ", " ", " ", "+"},
                {"+", "7", "7", "7", "7", " ", " ", "+"},
                {"+", "+", "+", "+", "+", "+", "+", "+"}
        };
        car1.setBody(body);
        Car car2 = new Car();
        car2.putParcel(6, 1, parcel);
        assertEquals(car1, car2);
    }

    @Test
    public void testWidthSuitability() {
        Parcel parcel1 = new Parcel(1, List.of(1));
        Parcel parcel2 = new Parcel(2, List.of(2));
        Car car = new Car();
        car.putParcel(6, 1, parcel1);
        assertAll(
                () -> assertTrue(car.isBottomSuitable(5, 1, parcel1)),
                () -> assertFalse(car.isBottomSuitable(5, 1, parcel2))
        );
    }

    @Test
    public void testBottomSuitability() {
        Car car = new Car();
        car.getBody()[6][1] = "1";
        Parcel parcel1 = new Parcel(1, List.of(1));
        Parcel parcel2 = new Parcel(2, List.of(2));
        assertAll(
                () -> assertTrue(car.isBottomSuitable(5, 1, parcel1)),
                () -> assertFalse(car.isBottomSuitable(5, 1, parcel2))
        );
    }
}
