package ru.denisch;

import java.util.List;

public interface AtmBehavior {

    // положить деньги в банкомат
    long addMoney(List<Bill> money) throws AtmException;

    // взять деньги
    List<Bill> getMoney(long value) throws AtmException;

}
