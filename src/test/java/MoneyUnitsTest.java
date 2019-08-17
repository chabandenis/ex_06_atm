import org.junit.jupiter.api.Test;
import ru.denisch.CurType;
import ru.denisch.MoneyUnits;

import static org.junit.jupiter.api.Assertions.*;

class MoneyUnitsTest {

    @Test
    void getPrice50() {
        assertEquals(MoneyUnits.getPrice(CurType.RUR50), 50);
    }

    @Test
    void getPrice100() {
        assertEquals(MoneyUnits.getPrice(CurType.RUR100), 100);
    }

    @Test
    void getPrice500() {
        assertEquals(MoneyUnits.getPrice(CurType.RUR500), 500);
    }

}