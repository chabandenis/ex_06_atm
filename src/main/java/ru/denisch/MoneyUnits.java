package ru.denisch;

import java.util.HashMap;
import java.util.Map;

public class MoneyUnits {

    private static Map<CurType, Integer> price = new HashMap<>();

    static {
        price.put(CurType.RUR50, 50);
        price.put(CurType.RUR100, 100);
        price.put(CurType.RUR500, 500);
    }

    public static int getPrice(CurType curType) {
        return price.get(curType);
    }
}
