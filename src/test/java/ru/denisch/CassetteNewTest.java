package ru.denisch;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CassetteNewTest {

    @Test
    void putGet() {
        CassetteNew cassette = new CassetteNew();
        Set<Bill> bills = new HashSet<>();

        bills.add(new Bill("001"));
        bills.add(new Bill("002"));
        bills.add(new Bill("003"));

        cassette.put(bills);

        HashSet d=new HashSet();

        Bill b1 = (Bill)cassette.get(1).toArray()[0];
        Bill b2 = (Bill)cassette.get(1).toArray()[0];
        Bill b3 = (Bill)cassette.get(1).toArray()[0];

        assertEquals(b1.getSerNumber(), "003");
        assertEquals(b2.getSerNumber(), "002");
        assertEquals(b3.getSerNumber(), "001");

    }

}