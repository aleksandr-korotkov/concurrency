package utilities;

import entities.Account;

public class AccountFactory {
    public static Account getAccount(String id, String firstName, String lastName, Long balance){
        return new Account(id, firstName, lastName, balance);
    }
    public static Account getAccount(AccountDataGenerator accountDataGenerator){
        return new Account(accountDataGenerator.getId(), accountDataGenerator.getFirstName(), accountDataGenerator.getLastName(), accountDataGenerator.getBalance());
    }
}
