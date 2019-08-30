package ru.denisch;

import java.util.List;

public interface Cassete {

    // добавить кпюру в касету
    void put(List<BillImpl> billImpls) throws AtmException;

    // взять купюру из касеты
    List<BillImpl> get(int cnt);

}
