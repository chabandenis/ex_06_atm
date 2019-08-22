package ru.denisch;

import java.util.*;

public class CassetteNew {

    // в касете хранятся банкноты, не более 10
    private Deque<Bill> q = new ArrayDeque(10);

    public void put(Set<Bill> bills) {
        for (Bill b : bills) {
            q.add(b);
        }
    }

    public Set<Bill> get(int cnt) {
        Set tmpBill= new HashSet();
        for(int i=0;i<cnt;i++){
            tmpBill.add(q.getLast());
            q.removeLast();
        }
        return tmpBill;
    }

}
