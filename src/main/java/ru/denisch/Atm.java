package ru.denisch;

import java.util.List;

public interface Atm {

    // положить деньги в банкомат
    long addMoney(List<BillImpl> money) throws AtmException;

    // взять деньги
    List<BillImpl> getMoney(long value) throws AtmException;

}
