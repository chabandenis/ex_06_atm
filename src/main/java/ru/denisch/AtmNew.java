package ru.denisch;

import java.util.*;

public class AtmNew {
    // в банкомате есть фиксированное количество касет
    private List<CassetteNew> cassettes = new ArrayList<>();

    // банкомат должен знать в какой касете сколько купюр
    // операции зачисления и списания должны актуализировать остаток
    // это не дублирование кода, дак как касета механическая штука
    private Map<CassetteNew, Long> cntInCassete = new HashMap<>();


    // по купюре определим нужную касету
    private Map<Integer, CassetteNew> casseteForMoney = new HashMap<>();

    // функция загрузки касет.
    // в функции за один раз должны быть передаваться номиналы одного достоинства
    // инициализация в обратном порядке
    public AtmNew loadCassetes(List<Bill> bills) {
        // сформировали касету
        CassetteNew cassetteNew = new CassetteNew(bills);
        // запомнили соответствие купюра- касета
        casseteForMoney.put(bills.get(0).getCurType().getNominal(), cassetteNew);
        // запонили сколько в касете денег
        cntInCassete.put(cassetteNew, (long) bills.size());

        cassettes.add(cassetteNew);
        return this;
    }

    // user add money
    public long addMoney(List<Bill> money) {
        long retValue = 0;

        for (Bill bill : money) {
            CassetteNew cassetteNew = casseteForMoney.get(bill.getCurType().getNominal());
            List<Bill> tmpList = new ArrayList<>();
            tmpList.add(bill);
            cassetteNew.put(tmpList);

            // запонили сколько в касете денег
            cntInCassete.put(cassetteNew, cntInCassete.get(cassetteNew) + 1);


            retValue += bill.getCurType().getNominal();
        }

        return retValue;
    }

    // служебная функция, возвращает номиналы в касетах
    public List<Bill> status() {
        List<Bill> tmp = new ArrayList<>();

        for (CassetteNew cassetteNew : cassettes) {
            tmp.addAll(cassetteNew.getStatus());
        }

        return tmp;
    }

    @Override
    public String toString() {
        String tmpStatus = "";

        for (CassetteNew cassetteNew : cassettes) {
            tmpStatus += cassetteNew.toString() + "; " + cntInCassete.get(cassetteNew) + "\n" ;
        }

        return tmpStatus;
    }


    public List<Bill> getMoney(long value) throws AtmException {
        List<Bill> tmpBills = new ArrayList<>();

        long retMoney = value;

        for (CassetteNew cassetteNew : cassettes) {
//            System.out.println("касета " + cassetteNew.toString());

            long price = cassetteNew.getCurType().getNominal();


            if (price <= value) {
                // есть купюры
                // денег для списания больше, чем купюра в кассете
                // остаток весь списан
                while (cassetteNew.getCurType().getNominal() <= retMoney && retMoney != 0 && cntInCassete.get(cassetteNew) > 0) {
                    List oneBill = new ArrayList();
                    oneBill = cassetteNew.get(1);
                    tmpBills.addAll(oneBill);
                    // запонили сколько в касете денег
                    cntInCassete.put(cassetteNew, cntInCassete.get(cassetteNew) - 1);
                    retMoney -= cassetteNew.getCurType().getNominal();
                }
            }


        }

        if (retMoney > 0) {
            throw new AtmException("atm doesn't have money for you. go to another atm");
        }
        return tmpBills;
    }

}
