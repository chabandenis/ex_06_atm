package ru.denisch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtmNewTest {

    @Test
    void loadBills() {
        AtmNew atmNew = new AtmNew();


        List<Bill> bills50  = new ArrayList<>();
        bills50.add(new Bill("r50 n001", CurType.RUR50));
        bills50.add(new Bill("r50 n002", CurType.RUR50));
        bills50.add(new Bill("r50 n003", CurType.RUR50));

        List<Bill> bills100  = new ArrayList<>();
        bills50.add(new Bill("r100 n001", CurType.RUR100));
        bills50.add(new Bill("r100 n002", CurType.RUR100));
        bills50.add(new Bill("r100 n003", CurType.RUR100));

        List<Bill> bills500  = new ArrayList<>();
        bills50.add(new Bill("r500 n001", CurType.RUR500));
        bills50.add(new Bill("r500 n002", CurType.RUR500));
        bills50.add(new Bill("r500 n003", CurType.RUR500));

        atmNew.loadCassetes(bills50).loadCassetes(bills100).loadCassetes(bills500);

        System.out.println("1 " + atmNew.status().size());

        for (Bill bill : atmNew.status()){
            System.out.println(bill.toString());
        }

        System.out.println("2 " + atmNew.status().size());

        assertEquals(atmNew.status().get(0).getSerNumber(), "r50 n001");
        assertEquals(atmNew.status().get(1).getSerNumber(), "r50 n002");
        assertEquals(atmNew.status().get(2).getSerNumber(), "r50 n003");
        assertEquals(atmNew.status().get(3).getSerNumber(), "r100 n001");
        assertEquals(atmNew.status().get(4).getSerNumber(), "r100 n002");
        assertEquals(atmNew.status().get(5).getSerNumber(), "r100 n003");
        assertEquals(atmNew.status().get(6).getSerNumber(), "r500 n001");
        assertEquals(atmNew.status().get(7).getSerNumber(), "r500 n002");
        assertEquals(atmNew.status().get(8).getSerNumber(), "r500 n003");

//        System.out.println("status " + atmNew.status());
//        System.out.println("string status " + atmNew.toString());

    }
}