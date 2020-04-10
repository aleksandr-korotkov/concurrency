package services;

import entities.Account;
import org.apache.log4j.Logger;

import java.util.List;


public class ConsoleServiceImpl implements ConsoleService{
    final static Logger logger = Logger.getLogger(ConsoleServiceImpl.class);

    @Override
    public void printAccountsStatus(List<Account> accounts){
        accounts.stream().forEach(account -> logger.info("Id аккаунта:" + account.getId() + ". Имя: " + account.getFirstName()
                + ", фамилия: " + account.getLastName() + ". Баланс = " + account.getBalance()));
    }

    @Override
    public void printAccountBalanceSum(List<Account> accounts){
        logger.info("Сумма вкладов составляет: " + accounts.stream().mapToLong(account -> account.getBalance()).sum());
    }
}