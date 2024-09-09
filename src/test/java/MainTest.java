import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testWrongParcelForm() {
        try {
            Parcel parcel = new Parcel(3, List.of(1, 2));
            Main.addParcel(parcel);
        } catch (ParcelException e) {
            assertEquals(ParcelErrorCode.WRONG_PARCEL_FORM, e.getErrorCode());
        }
    }

    @Test
    public void testWrongParcelContent() {
        try {
            Parcel parcel = new Parcel(7, List.of(3, 4));
            String line = "7778";
            Main.checkLine(parcel, line);
        } catch (ParcelException e) {
            assertEquals(ParcelErrorCode.WRONG_PARCEL_CONTENT, e.getErrorCode());
        }
    }
}
