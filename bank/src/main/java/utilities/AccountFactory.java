package utilities;

import entities.Account;

public class AccountFactory {
    public static Account getAccount(String id, String firstName, String lastName, Long balance){
        return new Account(id, firstName, lastName, balance);
    }
}
