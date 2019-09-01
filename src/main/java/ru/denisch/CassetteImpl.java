package ru.denisch;

import java.util.*;

public class CassetteImpl implements Cassete {

    // номинал
    private CurTypeImpl nominal;

    // максимальное количество купюр в кассете
    private static int maxCount = 10;

    // в касете хранятся банкноты, не более 10
    private Deque<BillImpl> q = new ArrayDeque(maxCount);

    // добавить кпюру в касету
    public void put(List<BillImpl> billImpls) throws AtmException {

        if ((billImpls.size() + q.size())> maxCount){
            throw new AtmException("Касета переполнена");
        }

        for (BillImpl b : billImpls) {
            q.add(b);
        }
        nominal = billImpls.get(0).getCurTypeImpl();
    }

    // взять купюру из касеты
    public List<BillImpl> get(int cnt) {
        List tmpBill= new ArrayList();
        for(int i=0;i<cnt;i++){
            tmpBill.add(q.getLast());
            q.removeLast();
        }
        return tmpBill;
    }

    // функция служебная. Аналог окрыть банкомат и посмотреть, а что в касете лежит, в какой последовательности и с какими серийными номерами
    public List<BillImpl> getStatus(){
        List<BillImpl> tmp = new ArrayList<>();
        for(BillImpl billImpl : q){
            tmp.add(billImpl);
        }

        return tmp;
    }

    // касету создать и передать набор купюр
    public CassetteImpl(List<BillImpl> billImpls) throws AtmException {
        this.put(billImpls);
        nominal = billImpls.get(0).getCurTypeImpl();
    }

    // пустая касета
    public CassetteImpl() {
    }

    // купюры в касете
    public CurTypeImpl getCurType(){
        return nominal;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for(BillImpl billImpl : q){
            str.append(billImpl.toString() + "\n") ;
        }
        return str.toString();
    }
}
