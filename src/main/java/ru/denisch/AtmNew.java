package ru.denisch;

import java.util.*;

public class AtmNew {
    // в банкомате есть фиксированное количество касет
    // банкомат знает, где купюра большего номинала
    private List<CassetteNew> cassettes = new ArrayList<>();

    // банкомат должен знать в какой касете сколько купюр
    // операции зачисления и списания должны актуализировать остаток
    // это не дублирование кода, дак как касета механическая штука
    private Map<CassetteNew, Long> cntInCassete = new HashMap<>();




    // функция загрузки касет.
    // сначала загружаются касеты с банкнотами минимального достоинства
    // в функции за один раз должны быть передаваться номиналы одного достоинства
    // действия выполняет сотрудник банка и не автоматизированы
    public AtmNew loadCassetes(List<Bill> bills) {
        cassettes.add(new CassetteNew(bills));
        return this;
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

    public long getMoney(long value) throws AtmException {

        System.out.println("before operation");

        long retMoney = value;  //

        System.out.println("you want to take " + value);

        for (CassetteNew cassetteNew : cassettes) {
            int price = MoneyUnits.getPrice(curType);
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

    // user add money
    public long addMoney(CurType[] money) {
        long retValue = 0;

        System.out.println("before operation");
        cassettes.status();

        for (CurType curType : money) {
            cassettes.putMoney(curType, 1);
            retValue += MoneyUnits.getPrice(curType);
        }

        System.out.println("after operation");
        cassettes.status();

        return retValue;
    }
}
