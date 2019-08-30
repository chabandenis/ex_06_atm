package ru.denisch;

public enum CurTypeImpl implements CurType {
    RUR50(50),
    RUR100(100),
    RUR500(500)
    ;

    private int nominal;

    CurTypeImpl(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }
}