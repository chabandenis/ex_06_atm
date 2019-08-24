package ru.denisch;

public enum CurType {
    RUR50 {
        public long cost() {
            return 50;
        }
    },
    RUR100 {
        public long cost() {
            return 100;
        }
    },
    RUR500 {
        public long cost() {
            return 500;
        }
    };

    public abstract long cost();
}