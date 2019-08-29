package ru.denisch;

import java.util.*;

public class Cassette {

    // номинал
    private CurType nominal;

    // максимальное количество купюр в кассете
    private static int maxCount = 10;

    // в касете хранятся банкноты, не более 10
    private Deque<Bill> q = new ArrayDeque(maxCount);

    // добавить кпюру в касету
    public void put(List<Bill> bills) throws AtmException {

        if ((bills.size() + q.size())> maxCount){
            throw new AtmException("Касета переполнена");
        }

        for (Bill b : bills) {
            q.add(b);
        }
        nominal = bills.get(0).getCurType();
    }

    // взять купюру из касеты
    public List<Bill> get(int cnt) {
        List tmpBill= new ArrayList();
        for(int i=0;i<cnt;i++){
            tmpBill.add(q.getLast());
            q.removeLast();
        }
        return tmpBill;
    }

    // функция служебная. Аналог окрыть банкомат и посмотреть, а что в касете лежит, в какой последовательности и с какими серийными номерами
    public List<Bill> getStatus(){
        List<Bill> tmp = new ArrayList<>();
        for(Bill bill : q){
            tmp.add(bill);
        }

        return tmp;
    }

    // касету создать и передать набор купюр
    public Cassette(List<Bill> bills) throws AtmException {
        this.put(bills);
        nominal = bills.get(0).getCurType();
    }

    // пустая касета
    public Cassette() {
    }

    // купюры в касете
    public CurType getCurType(){
        return nominal;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for(Bill bill : q){
            str.append(bill.toString() + "\n") ;
        }
        return str.toString();
    }
}
