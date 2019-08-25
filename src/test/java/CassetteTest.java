import org.junit.jupiter.api.Test;
import ru.denisch.CurType;

import static org.junit.jupiter.api.Assertions.*;

class CassetteTest {
    @Test
    void initCassette50() {
        Cassette cassette = new Cassette();
        assertEquals(cassette.getCount(CurType.RUR50), 5);
    }

    @Test
    void initCassette100() {
        Cassette cassette = new Cassette();
        assertEquals(cassette.getCount(CurType.RUR100), 4);
    }

    @Test
    void initCassette500() {
        Cassette cassette = new Cassette();
        assertEquals(cassette.getCount(CurType.RUR500), 3);
    }

    @Test
    void getAllPrice() {
        Cassette cassette = new Cassette();
        assertEquals(cassette.getAllPrice(), 250 + 400 + 1500);
    }

    @Test
    void getKeySet() {
        Cassette cassette = new Cassette();
        assertEquals(cassette.getKeySet().toArray()[0], CurType.RUR500);
        assertEquals(cassette.getKeySet().toArray()[1], CurType.RUR100);
        assertEquals(cassette.getKeySet().toArray()[2], CurType.RUR50);
    }

    @Test
        // try to get enough money
    void getMoneyEnough() {
        Cassette cassette = new Cassette();
        assertEquals(cassette.getMoney(CurType.RUR50, 5), 250);
        assertEquals(cassette.getCount(CurType.RUR50), 0);
        assertEquals(cassette.getCount(CurType.RUR100), 4);
        assertEquals(cassette.getCount(CurType.RUR500), 3);
    }

    @Test
        // try to get not enough money
    void getMoneyNotEnough() {
        Cassette cassette = new Cassette();
        assertThrows(RuntimeException.class, () -> cassette.getMoney(CurType.RUR50, 6));
        assertEquals(cassette.getCount(CurType.RUR50), 5);
        assertEquals(cassette.getCount(CurType.RUR100), 4);
        assertEquals(cassette.getCount(CurType.RUR500), 3);

    }

    @Test
    void putMoney() {
        Cassette cassette = new Cassette();
        cassette.putMoney(CurType.RUR50, 1);
        assertEquals(cassette.getCount(CurType.RUR50), 6);
        assertEquals(cassette.getCount(CurType.RUR100), 4);
        assertEquals(cassette.getCount(CurType.RUR500), 3);

        cassette.putMoney(CurType.RUR100, 1);
        assertEquals(cassette.getCount(CurType.RUR50), 6);
        assertEquals(cassette.getCount(CurType.RUR100), 5);
        assertEquals(cassette.getCount(CurType.RUR500), 3);

        cassette.putMoney(CurType.RUR500, 1);
        assertEquals(cassette.getCount(CurType.RUR50), 6);
        assertEquals(cassette.getCount(CurType.RUR100), 5);
        assertEquals(cassette.getCount(CurType.RUR500), 4);

    }
}