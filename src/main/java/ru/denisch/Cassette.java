package ru.denisch;

import java.util.*;

public class Cassette {


    private Map<CurType, Integer> allMoney = new TreeMap<>(Collections.reverseOrder());

    public Cassette() {
        initFirst();
    }

    public int getCount(CurType curType) {
        return allMoney.get(curType);
    }

    public long getAllPrice() {
        long tmpPrice = 0;

        for (CurType curType : allMoney.keySet()) {
            tmpPrice += this.getCount(curType) * MoneyUnits.getPrice(curType);
        }

        return tmpPrice;
    }

    public Set<CurType> getKeySet() {
        return allMoney.keySet();
    }

    public long getMoney(CurType curType, int count) {

        if (allMoney.get(curType) < count) {
            throw new RuntimeException("you whant to take " + count + " but ther is only " + allMoney.get(curType) + " + price " + MoneyUnits.getPrice(curType));
        }

        allMoney.put(curType, allMoney.get(curType) - count);

        return MoneyUnits.getPrice(curType) * count;
    }

    public long putMoney(CurType curType, int count) {

        allMoney.put(curType, allMoney.get(curType) + count);

        return MoneyUnits.getPrice(curType) * count;
    }

    // status atm
    public void status() {
        double res = 0;
        System.out.println("");
        System.out.println("state atm. I have money. types of money=" + allMoney.size());

        for (CurType curType : allMoney.keySet()) {
            int price = MoneyUnits.getPrice(curType);
            System.out.println("    price=" + price + "; count=" + allMoney.get(curType));
            res += price * allMoney.get(curType);
        }

        System.out.println("Total=" + res);
        System.out.println("");
    }

    private void initFirst() {
        allMoney.put(CurType.RUR50, 5);
        allMoney.put(CurType.RUR100, 4);
        allMoney.put(CurType.RUR500, 3);
    }
}
