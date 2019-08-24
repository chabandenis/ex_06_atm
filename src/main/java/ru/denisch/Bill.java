package ru.denisch;

// купюра
public class Bill {

    private String serNumber; // serial number of money

    public String getSerNumber() {
        return serNumber;
    }

    public Bill(String serNumber) {
        this.serNumber = serNumber;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "serNumber='" + serNumber + '\'' +
                '}';
    }
}
