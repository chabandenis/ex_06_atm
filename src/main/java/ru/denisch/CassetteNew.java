package ru.denisch;

import java.util.*;

public class CassetteNew {

    // в касете хранятся банкноты, не более 10
    private Deque<Bill> q = new ArrayDeque(10);

    // добавить кюпюру в касету
    public void put(List<Bill> bills) {
        for (Bill b : bills) {
            q.add(b);
        }
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
    public CassetteNew( List<Bill> bills) {
        this.put(bills);
    }

    // пустая касета
    public CassetteNew() {
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for(Bill bill : q){
            str.append(bill.toString());
        }
        return str.toString();
    }
}
