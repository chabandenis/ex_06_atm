package ru.denisch;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CassetteTest {

    @Test
    void TestEnum() {

        CurType curType = CurType.RUR100;

        System.out.println("_" + curType.getNominal());


    }

    @Test
    void putGet() {
        Cassette cassette = new Cassette();
        List<Bill> bills = new ArrayList<>();

        bills.add(new Bill("001", CurType.RUR50));
        bills.add(new Bill("002", CurType.RUR50));
        bills.add(new Bill("003", CurType.RUR50));

        try {
            cassette.put(bills);

            System.out.println(cassette.toString());


            HashSet d = new HashSet();
/*
        Bill b1 = cassette.get(1).get(0);
        Bill b2 = (Bill)cassette.get(1).get(0);
        Bill b3 = (Bill)cassette.get(1).get(0);
*/
            assertEquals(cassette.get(1).get(0).getSerNumber(), "003");
            assertEquals(cassette.get(1).get(0).getSerNumber(), "002");
            assertEquals(cassette.get(1).get(0).getSerNumber(), "001");

        } catch (AtmException e) {
            assertEquals(1, 2);
        }

    }

}