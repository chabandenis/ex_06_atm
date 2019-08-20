package ru.denisch;

public class ATM {
    private Cassette cassette = new Cassette();

    public long getMoney(long value) throws AtmException {

        System.out.println("before operation");
        cassette.status();

        long retMoney = value;  //

        System.out.println("you want to take " + value);

        for (CurType curType : cassette.getKeySet()) {
            int price = MoneyUnits.getPrice(curType);
            int cnt = 0;
            if (price <= value) {
                while (price * cassette.getCount(curType) >= retMoney && retMoney != 0) {
                    retMoney -= cassette.getMoney(curType, 1);
                    cnt++;
                }
            }
            if (cnt > 0) {
                System.out.println("    your money is " + price + " * " + cnt);
            }
        }

        if (retMoney > 0) {
            throw new AtmException("atm doesn't have money for you. go to another atm");
        }

        System.out.println("");
        System.out.println("after operation");
        cassette.status();

        return value;

    }

    // user add money
    public long addMoney(CurType[] money) {
        long retValue = 0;

        System.out.println("before operation");
        cassette.status();

        for (CurType curType : money) {
            cassette.putMoney(curType, 1);
            retValue += MoneyUnits.getPrice(curType);
        }

        System.out.println("after operation");
        cassette.status();

        return retValue;
    }

    public long getAllPrice() {
        return cassette.getAllPrice();
    }
}


