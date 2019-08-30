package ru.denisch;

import java.util.*;

public class AtmImpl implements Atm {
    // в банкомате есть фиксированное количество касет
    private List<CassetteImpl> cassetteImpls = new ArrayList<>();

    // банкомат должен знать в какой касете сколько купюр
    // операции зачисления и списания должны актуализировать остаток
    // это не дублирование кода, дак как касета механическая штука
    private Map<CassetteImpl, Long> cntInCassete = new HashMap<>();


    // по купюре определим нужную касету
    private Map<Integer, CassetteImpl> casseteForMoney = new HashMap<>();

    // функция загрузки касет.
    // в функции за один раз должны быть передаваться номиналы одного достоинства
    // инициализация в обратном порядке
    public AtmImpl loadCassetes(List<BillImpl> billImpls) throws AtmException {
        // сформировали касету
        CassetteImpl cassetteImpl = new CassetteImpl(billImpls);
        // запомнили соответствие купюра- касета
        casseteForMoney.put(billImpls.get(0).getCurTypeImpl().getNominal(), cassetteImpl);
        // запонили сколько в касете денег
        cntInCassete.put(cassetteImpl, (long) billImpls.size());

        cassetteImpls.add(cassetteImpl);
        return this;
    }

    // user add money
    public long addMoney(List<BillImpl> money) throws AtmException {
        long retValue = 0;

        for (BillImpl billImpl : money) {
            CassetteImpl cassetteImpl = casseteForMoney.get(billImpl.getCurTypeImpl().getNominal());
            List<BillImpl> tmpList = new ArrayList<>();
            tmpList.add(billImpl);
            cassetteImpl.put(tmpList);

            // запонили сколько в касете денег
            cntInCassete.put(cassetteImpl, cntInCassete.get(cassetteImpl) + 1);


            retValue += billImpl.getCurTypeImpl().getNominal();
        }

        return retValue;
    }

    // служебная функция, возвращает номиналы в касетах
    public List<BillImpl> status() {
        List<BillImpl> tmp = new ArrayList<>();

        for (CassetteImpl cassetteImpl : cassetteImpls) {
            tmp.addAll(cassetteImpl.getStatus());
        }

        return tmp;
    }

    @Override
    public String toString() {
        String tmpStatus = "";

        for (CassetteImpl cassetteImpl : cassetteImpls) {
            tmpStatus += cassetteImpl.toString() + "; " + cntInCassete.get(cassetteImpl) + "\n";
        }

        return tmpStatus;
    }


    public List<BillImpl> getMoney(long value) throws AtmException {
        List<BillImpl> tmpBillImpls = new ArrayList<>();

        long retMoney = value;

        for (CassetteImpl cassetteImpl : cassetteImpls) {
//            System.out.println("касета " + cassetteNew.toString());

            long price = cassetteImpl.getCurType().getNominal();


            if (price <= value) {
                // есть купюры
                // денег для списания больше, чем купюра в кассете
                // остаток весь списан
                while (cassetteImpl.getCurType().getNominal() <= retMoney && retMoney != 0 && cntInCassete.get(cassetteImpl) > 0) {
                    List oneBill = new ArrayList();
                    oneBill = cassetteImpl.get(1);
                    tmpBillImpls.addAll(oneBill);
                    // запонили сколько в касете денег
                    cntInCassete.put(cassetteImpl, cntInCassete.get(cassetteImpl) - 1);
                    retMoney -= cassetteImpl.getCurType().getNominal();
                }
            }


        }

        int countMoney = 0;

        // посмотрим, есть ли в банкомате деньги
        for (CassetteImpl cassetteImpl : cassetteImpls) {
            countMoney += cntInCassete.get(cassetteImpl);
        }

        if (retMoney > 0 && countMoney > 0) {
            throw new AtmException("Подходящие купюры отсутствуют в банкомате");
        }

        if (retMoney > 0 && countMoney == 0) {
            throw new AtmException("Запрашиваемая сумма больше остатка в банкомате");
        }

        return tmpBillImpls;
    }

}
