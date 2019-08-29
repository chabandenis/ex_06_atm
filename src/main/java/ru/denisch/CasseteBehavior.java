package ru.denisch;

import java.util.List;

public interface CasseteBehavior {

    // добавить кпюру в касету
    void put(List<Bill> bills) throws AtmException;

    // взять купюру из касеты
    List<Bill> get(int cnt);

}
