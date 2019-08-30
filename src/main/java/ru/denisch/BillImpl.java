package ru.denisch;

// купюра
public class BillImpl implements Bill {

    private String serNumber; // serial number of money
    private CurTypeImpl curTypeImpl;

    public String getSerNumber() {
        return serNumber;
    }

    public BillImpl(String serNumber, CurTypeImpl curTypeImpl) {
        this.serNumber = serNumber;
        this.curTypeImpl = curTypeImpl;
    }

    public CurTypeImpl getCurTypeImpl() {
        return curTypeImpl;
    }

    @Override
    public String toString() {
        return "sr=" + serNumber + "; " + "nom=" + curTypeImpl.getNominal();
    }
}
