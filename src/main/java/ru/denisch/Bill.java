package ru.denisch;

// купюра
public class Bill {

    private String serNumber; // serial number of money
    private CurType curType;

    public String getSerNumber() {
        return serNumber;
    }

    public Bill(String serNumber, CurType curType) {
        this.serNumber = serNumber;
        this.curType = curType;
    }

    public CurType getCurType() {
        return curType;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "serNumber='" + serNumber + '\'' +
                "curType='" + curType.cost() + '\'' +
                '}';
    }
}
