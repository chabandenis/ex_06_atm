package ru.denisch;

import java.util.List;

public interface AtmBehavior {

    // Первоначальная инициализация касет
    Atm loadCassetes(List<Bill> bills) throws AtmException;

    // положить деньги в банкомат
    long addMoney(List<Bill> money) throws AtmException;

    // взять деньги
    List<Bill> getMoney(long value) throws AtmException;

}
