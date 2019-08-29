package ru.denisch;

import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtmTest {

    @Test
    void loadBills() {
        Atm atm = new Atm();


        List<Bill> bills50 = new ArrayList<>();
        bills50.add(new Bill("r50 n001", CurType.RUR50));
        bills50.add(new Bill("r50 n002", CurType.RUR50));
        bills50.add(new Bill("r50 n003", CurType.RUR50));

        List<Bill> bills100 = new ArrayList<>();
        bills100.add(new Bill("r100 n001", CurType.RUR100));
        bills100.add(new Bill("r100 n002", CurType.RUR100));
        bills100.add(new Bill("r100 n003", CurType.RUR100));

        List<Bill> bills500 = new ArrayList<>();
        bills500.add(new Bill("r500 n001", CurType.RUR500));
        bills500.add(new Bill("r500 n002", CurType.RUR500));
        bills500.add(new Bill("r500 n003", CurType.RUR500));

        try {
            atm.loadCassetes(bills50).loadCassetes(bills100).loadCassetes(bills500);
        } catch (AtmException e) {
            assertEquals(1, 2);
        }

        System.out.println("1 " + atm.status().size());

        for (Bill bill : atm.status()) {
            System.out.println(bill.toString());
        }

        System.out.println("2 " + atm.status().size());

        assertEquals(atm.status().get(0).getSerNumber(), "r50 n001");
        assertEquals(atm.status().get(1).getSerNumber(), "r50 n002");
        assertEquals(atm.status().get(2).getSerNumber(), "r50 n003");
        assertEquals(atm.status().get(3).getSerNumber(), "r100 n001");
        assertEquals(atm.status().get(4).getSerNumber(), "r100 n002");
        assertEquals(atm.status().get(5).getSerNumber(), "r100 n003");
        assertEquals(atm.status().get(6).getSerNumber(), "r500 n001");
        assertEquals(atm.status().get(7).getSerNumber(), "r500 n002");
        assertEquals(atm.status().get(8).getSerNumber(), "r500 n003");


        // пробуем передать больше чем есть
        System.out.println("загружаем больше чем есть");
        List<Bill> billsCnt10 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            billsCnt10.add(new Bill("over0" + i, CurType.RUR50));
        }


        try {
            atm.addMoney(billsCnt10);
            assertEquals(1, 2);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Касета переполнена");
        }
    }

    @Test
    void addMoney() throws AtmException {

        Atm atm = new Atm();

        List<Bill> bills50 = new ArrayList<>();
        bills50.add(new Bill("r50 n001", CurType.RUR50));

        List<Bill> bills100 = new ArrayList<>();
        bills100.add(new Bill("r100 n001", CurType.RUR100));

        List<Bill> bills500 = new ArrayList<>();
        bills500.add(new Bill("r500 n001", CurType.RUR500));

        System.out.println("Состояние пустого банкомата");
        System.out.println(atm.toString());
        System.out.println("");

        // первоначальная загрузка банкомата
        atm.loadCassetes(bills500).loadCassetes(bills100).loadCassetes(bills50);

        System.out.println("Состояние после первоначальной загрузки банкомата");
        System.out.println(atm.toString());
        System.out.println("");

        List<Bill> tmp = new ArrayList<>();
        tmp.add(new Bill("my 50 001", CurType.RUR50));
        tmp.add(new Bill("my 50 002", CurType.RUR50));
        tmp.add(new Bill("my 500 003", CurType.RUR500));

        // загружаю в банкомат курюры
        atm.addMoney(tmp);


        System.out.println("Состояние после загрузки купюры");
        System.out.println(atm.toString());
        System.out.println("");


        // снять пытаюсь те же купюры в обратном порядке
        try {
            List<Bill> tmpList;
            tmpList = atm.getMoney(50);

            assertEquals(tmpList.get(0).getSerNumber(), "my 50 002");

            System.out.println("Состояние после списания 50р");
            System.out.println(atm.toString());
            System.out.println("");

            tmpList = atm.getMoney(1000);

            System.out.println("Состояние после списания 1000р");
            System.out.println(atm.toString());
            System.out.println("");

            assertEquals(tmpList.get(0).getSerNumber(), "my 500 003");
            assertEquals(tmpList.get(1).getSerNumber(), "r500 n001");

            tmpList = atm.getMoney(150);

            System.out.println("Состояние после списания 150р");
            System.out.println(atm.toString());
            System.out.println("");

            assertEquals(tmpList.get(0).getSerNumber(), "r100 n001");
            assertEquals(tmpList.get(1).getSerNumber(), "my 50 001");
        } catch (AtmException e) {
            // в данном алгоритме ошбки должны отсутствовать
            assertEquals(1, 2);
        }

        // снимем меньше чем нужно
        // остаток 50 рублей
        // пробуем списать 40
        System.out.println("Пробуем списать 40р");
        try {
            List<Bill> tmpList;
            tmpList = atm.getMoney(40);

            System.out.println("Состояние после списания 40р");
            System.out.println(atm.toString());
            System.out.println("");

            // должно возникнуть исключение о нехватке денег
            assertEquals(1, 2);
        } catch (AtmException e) {
            System.out.println(e.getMessage());
            // исключение, все верно
            assertEquals(e.getMessage(), "Подходящие купюры отсутствуют в банкомате");
        }

        // снимем больше чем нужно
        // остаток 50 рублей
        // пробуем списать 60
        System.out.println("Пробуем списать 60р");
        try {
            List<Bill> tmpList;
            tmpList = atm.getMoney(60);

            System.out.println("Состояние после списания 60р");
            System.out.println(atm.toString());
            System.out.println("");

            // должно возникнуть исключение о нехватке денег
            assertEquals(1, 2);
        } catch (AtmException e) {
            System.out.println(e.getMessage());
            // исключение, все верно
            assertEquals(e.getMessage(), "Запрашиваемая сумма больше остатка в банкомате");
        }


    }
}