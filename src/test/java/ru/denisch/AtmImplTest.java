package ru.denisch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtmImplTest {

    @Test
    void loadBills() {
        AtmImpl atmImpl = new AtmImpl();


        List<BillImpl> bills50 = new ArrayList<>();
        bills50.add(new BillImpl("r50 n001", CurTypeImpl.RUR50));
        bills50.add(new BillImpl("r50 n002", CurTypeImpl.RUR50));
        bills50.add(new BillImpl("r50 n003", CurTypeImpl.RUR50));

        List<BillImpl> bills100 = new ArrayList<>();
        bills100.add(new BillImpl("r100 n001", CurTypeImpl.RUR100));
        bills100.add(new BillImpl("r100 n002", CurTypeImpl.RUR100));
        bills100.add(new BillImpl("r100 n003", CurTypeImpl.RUR100));

        List<BillImpl> bills500 = new ArrayList<>();
        bills500.add(new BillImpl("r500 n001", CurTypeImpl.RUR500));
        bills500.add(new BillImpl("r500 n002", CurTypeImpl.RUR500));
        bills500.add(new BillImpl("r500 n003", CurTypeImpl.RUR500));

        try {
            atmImpl.loadCassetes(bills50).loadCassetes(bills100).loadCassetes(bills500);
        } catch (AtmException e) {
            assertEquals(1, 2);
        }

        System.out.println("1 " + atmImpl.status().size());

        for (BillImpl billImpl : atmImpl.status()) {
            System.out.println(billImpl.toString());
        }

        System.out.println("2 " + atmImpl.status().size());

        assertEquals(atmImpl.status().get(0).getSerNumber(), "r50 n001");
        assertEquals(atmImpl.status().get(1).getSerNumber(), "r50 n002");
        assertEquals(atmImpl.status().get(2).getSerNumber(), "r50 n003");
        assertEquals(atmImpl.status().get(3).getSerNumber(), "r100 n001");
        assertEquals(atmImpl.status().get(4).getSerNumber(), "r100 n002");
        assertEquals(atmImpl.status().get(5).getSerNumber(), "r100 n003");
        assertEquals(atmImpl.status().get(6).getSerNumber(), "r500 n001");
        assertEquals(atmImpl.status().get(7).getSerNumber(), "r500 n002");
        assertEquals(atmImpl.status().get(8).getSerNumber(), "r500 n003");


        // пробуем передать больше чем есть
        System.out.println("загружаем больше чем есть");
        List<BillImpl> billsCnt10 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            billsCnt10.add(new BillImpl("over0" + i, CurTypeImpl.RUR50));
        }


        try {
            atmImpl.addMoney(billsCnt10);
            assertEquals(1, 2);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Касета переполнена");
        }
    }

    @Test
    void addMoney() throws AtmException {

        AtmImpl atmImpl = new AtmImpl();

        List<BillImpl> bills50 = new ArrayList<>();
        bills50.add(new BillImpl("r50 n001", CurTypeImpl.RUR50));

        List<BillImpl> bills100 = new ArrayList<>();
        bills100.add(new BillImpl("r100 n001", CurTypeImpl.RUR100));

        List<BillImpl> bills500 = new ArrayList<>();
        bills500.add(new BillImpl("r500 n001", CurTypeImpl.RUR500));

        System.out.println("Состояние пустого банкомата");
        System.out.println(atmImpl.toString());
        System.out.println("");

        // первоначальная загрузка банкомата
        atmImpl.loadCassetes(bills500).loadCassetes(bills100).loadCassetes(bills50);

        System.out.println("Состояние после первоначальной загрузки банкомата");
        System.out.println(atmImpl.toString());
        System.out.println("");

        List<BillImpl> tmp = new ArrayList<>();
        tmp.add(new BillImpl("my 50 001", CurTypeImpl.RUR50));
        tmp.add(new BillImpl("my 50 002", CurTypeImpl.RUR50));
        tmp.add(new BillImpl("my 500 003", CurTypeImpl.RUR500));

        // загружаю в банкомат курюры
        atmImpl.addMoney(tmp);


        System.out.println("Состояние после загрузки купюры");
        System.out.println(atmImpl.toString());
        System.out.println("");


        // снять пытаюсь те же купюры в обратном порядке
        try {
            List<BillImpl> tmpList;
            tmpList = atmImpl.getMoney(50);

            assertEquals(tmpList.get(0).getSerNumber(), "my 50 002");

            System.out.println("Состояние после списания 50р");
            System.out.println(atmImpl.toString());
            System.out.println("");

            tmpList = atmImpl.getMoney(1000);

            System.out.println("Состояние после списания 1000р");
            System.out.println(atmImpl.toString());
            System.out.println("");

            assertEquals(tmpList.get(0).getSerNumber(), "my 500 003");
            assertEquals(tmpList.get(1).getSerNumber(), "r500 n001");

            tmpList = atmImpl.getMoney(150);

            System.out.println("Состояние после списания 150р");
            System.out.println(atmImpl.toString());
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
            List<BillImpl> tmpList;
            tmpList = atmImpl.getMoney(40);

            System.out.println("Состояние после списания 40р");
            System.out.println(atmImpl.toString());
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
            List<BillImpl> tmpList;
            tmpList = atmImpl.getMoney(60);

            System.out.println("Состояние после списания 60р");
            System.out.println(atmImpl.toString());
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