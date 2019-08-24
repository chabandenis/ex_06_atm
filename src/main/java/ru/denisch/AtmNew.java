package ru.denisch;

import java.util.*;

public class AtmNew {
    // в банкомате есть фиксированное количество касет
    // банкомат знает, где купюра большего номинала
    private Set<CassetteNew> cassettes = new HashSet<>();

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
}
