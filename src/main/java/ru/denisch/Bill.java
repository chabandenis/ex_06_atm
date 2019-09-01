package ru.denisch;

public interface Bill {

    // получить серийный номер
    String getSerNumber();

    // номинал
    CurTypeImpl getCurTypeImpl();


}
