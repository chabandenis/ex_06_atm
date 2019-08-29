package ru.denisch;

import java.util.*;

public class Atm implements AtmBehavior {
    // в банкомате есть фиксированное количество касет
    private List<Cassette> cassettes = new ArrayList<>();

    // банкомат должен знать в какой касете сколько купюр
    // операции зачисления и списания должны актуализировать остаток
    // это не дублирование кода, дак как касета механическая штука
    private Map<Cassette, Long> cntInCassete = new HashMap<>();


    // по купюре определим нужную касету
    private Map<Integer, Cassette> casseteForMoney = new HashMap<>();

    // функция загрузки касет.
    // в функции за один раз должны быть передаваться номиналы одного достоинства
    // инициализация в обратном порядке
    public Atm loadCassetes(List<Bill> bills) throws AtmException {
        // сформировали касету
        Cassette cassette = new Cassette(bills);
        // запомнили соответствие купюра- касета
        casseteForMoney.put(bills.get(0).getCurType().getNominal(), cassette);
        // запонили сколько в касете денег
        cntInCassete.put(cassette, (long) bills.size());

        cassettes.add(cassette);
        return this;
    }

    // user add money
    public long addMoney(List<Bill> money) throws AtmException {
        long retValue = 0;

        for (Bill bill : money) {
            Cassette cassette = casseteForMoney.get(bill.getCurType().getNominal());
            List<Bill> tmpList = new ArrayList<>();
            tmpList.add(bill);
            cassette.put(tmpList);

            // запонили сколько в касете денег
            cntInCassete.put(cassette, cntInCassete.get(cassette) + 1);


            retValue += bill.getCurType().getNominal();
        }

        return retValue;
    }

    // служебная функция, возвращает номиналы в касетах
    public List<Bill> status() {
        List<Bill> tmp = new ArrayList<>();

        for (Cassette cassette : cassettes) {
            tmp.addAll(cassette.getStatus());
        }

        return tmp;
    }

    @Override
    public String toString() {
        String tmpStatus = "";

        for (Cassette cassette : cassettes) {
            tmpStatus += cassette.toString() + "; " + cntInCassete.get(cassette) + "\n";
        }

        return tmpStatus;
    }


    public List<Bill> getMoney(long value) throws AtmException {
        List<Bill> tmpBills = new ArrayList<>();

        long retMoney = value;

        for (Cassette cassette : cassettes) {
//            System.out.println("касета " + cassetteNew.toString());

            long price = cassette.getCurType().getNominal();


            if (price <= value) {
                // есть купюры
                // денег для списания больше, чем купюра в кассете
                // остаток весь списан
                while (cassette.getCurType().getNominal() <= retMoney && retMoney != 0 && cntInCassete.get(cassette) > 0) {
                    List oneBill = new ArrayList();
                    oneBill = cassette.get(1);
                    tmpBills.addAll(oneBill);
                    // запонили сколько в касете денег
                    cntInCassete.put(cassette, cntInCassete.get(cassette) - 1);
                    retMoney -= cassette.getCurType().getNominal();
                }
            }


        }

        int countMoney = 0;

        // посмотрим, есть ли в банкомате деньги
        for (Cassette cassette : cassettes) {
            countMoney += cntInCassete.get(cassette);
        }

        if (retMoney > 0 && countMoney > 0) {
            throw new AtmException("Подходящие купюры отсутствуют в банкомате");
        }

        if (retMoney > 0 && countMoney == 0) {
            throw new AtmException("Запрашиваемая сумма больше остатка в банкомате");
        }

        return tmpBills;
    }

}
