import org.junit.jupiter.api.Test;
import ru.denisch.ATM;
import ru.denisch.CurType;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {

    @Test
    public void testGetMoney() {
        ATM atm = new ATM();

        assertEquals(atm.getMoney(1000), 1000);
        assertEquals(atm.getAllPrice(), 1150);

    }

    @Test
    void addMoney() {
        ATM atm = new ATM();
        CurType[] curTypes = {CurType.RUR500, CurType.RUR500};

        assertEquals(atm.getAllPrice(), 2150);
        assertEquals(atm.addMoney(curTypes), 1000);
        assertEquals(atm.getAllPrice(), 3150);
    }
}