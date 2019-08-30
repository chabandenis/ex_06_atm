package ru.denisch;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CassetteImplTest {

    @Test
    void TestEnum() {

        CurTypeImpl curTypeImpl = CurTypeImpl.RUR100;

        System.out.println("_" + curTypeImpl.getNominal());


    }

    @Test
    void putGet() {
        CassetteImpl cassetteImpl = new CassetteImpl();
        List<BillImpl> billImpls = new ArrayList<>();

        billImpls.add(new BillImpl("001", CurTypeImpl.RUR50));
        billImpls.add(new BillImpl("002", CurTypeImpl.RUR50));
        billImpls.add(new BillImpl("003", CurTypeImpl.RUR50));

        try {
            cassetteImpl.put(billImpls);

            System.out.println(cassetteImpl.toString());


            HashSet d = new HashSet();
/*
        Bill b1 = cassette.get(1).get(0);
        Bill b2 = (Bill)cassette.get(1).get(0);
        Bill b3 = (Bill)cassette.get(1).get(0);
*/
            assertEquals(cassetteImpl.get(1).get(0).getSerNumber(), "003");
            assertEquals(cassetteImpl.get(1).get(0).getSerNumber(), "002");
            assertEquals(cassetteImpl.get(1).get(0).getSerNumber(), "001");

        } catch (AtmException e) {
            assertEquals(1, 2);
        }

    }

}