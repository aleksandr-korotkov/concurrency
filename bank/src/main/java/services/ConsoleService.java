package services;

import entities.Account;

import java.util.List;

public interface ConsoleService {

    void printAccountsStatus(List<Account> accounts);

    void printAccountBalanceSum(List<Account> accounts);
}
