package ru.denisch;

public enum CurType implements  CurTypeBehavior{
    RUR50(50),
    RUR100(100),
    RUR500(500)
    ;

    private int nominal;

    CurType(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }
}