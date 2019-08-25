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
    private Map<Long, CassetteNew> casseteForMoney = new HashMap<>();

    // функция загрузки касет.
    // в функции за один раз должны быть передаваться номиналы одного достоинства
    public AtmNew loadCassetes(List<Bill> bills) {
        // сформировали касету
        CassetteNew cassetteNew = new CassetteNew(bills);
        // запомнили соответствие купюра- касета
        casseteForMoney.put(bills.get(0).getCurType().cost(), cassetteNew);
        // запонили сколько в касете денег
        cntInCassete.put(cassetteNew, (long)bills.size());

        cassettes.add(cassetteNew);
        return this;
    }

    // user add money
    public long addMoney(Set<Bill> money) {
        long retValue = 0;

        System.out.println("before operation");
        cassettes.toString();

        for (Bill bill : money) {
            CassetteNew cassetteNew = casseteForMoney.get(bill.getCurType().cost());
            List<Bill> tmpList = new ArrayList<>();
            tmpList.add(bill);
            cassetteNew.put(tmpList);

            // запонили сколько в касете денег
            cntInCassete.put(cassetteNew, cntInCassete.get(cassetteNew) + 1);


            retValue += bill.getCurType().cost();
        }

        System.out.println("after operation");
        cassettes.toString();

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
        String tmpBills = "";

        for (Bill bill : status()) {
            tmpBills += bill;
        }

        return "AtmNew{" +
                "cassettes=" + tmpBills +
                ", cntInCassete=" + cntInCassete +
                '}';
    }

    /*
        public long getMoney(long value) throws AtmException {

            System.out.println("before operation");

            long retMoney = value;  //

            System.out.println("you want to take " + value);

            for (CassetteNew cassetteNew : cassettes) {
                long price = cassetteNew.getCurType().cost();
                int cnt = 0;
                if (price <= value) {
                    while (price * cassettes.getCount(curType) >= retMoney && retMoney != 0) {
                        retMoney -= cassettes.getMoney(curType, 1);
                        cnt++;
                    }
                }
                if (cnt > 0) {
                    System.out.println("    your money is " + price + " * " + cnt);
                }
            }

            if (retMoney > 0) {
                throw new AtmException("atm doesn't have money for you. go to another atm");
            }

            System.out.println("");
            System.out.println("after operation");
            cassettes.status();

            return value;

        }
    */

}
