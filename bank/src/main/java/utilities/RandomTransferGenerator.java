package utilities;

import entities.Account;

import java.util.List;
import java.util.Random;

public class RandomTransferGenerator {
    private static List<Account> accounts;

    public static Account getRandomAccount(){
        accounts = AccountManager.getAccountManager().getAccounts();
        int min = 0;
        int max = accounts.size()-1;
        Random random = new Random();
        return accounts.get(random.nextInt((max - min) + 1) + min);
    }

    public static Long getRandomPositiveSum(){
        long min = 1L;
        long max = 1000l;
        return min + (long) (Math.random() * (max - min));
    }
}
