package dao;

import entities.Account;
import exceptions.AccountFileDoesntExistException;
import utilities.AccountDataGenerator;

import java.util.List;
import java.util.Optional;

public interface DAO {

    List<Account> loadAllAccounts();

    void saveAllAccounts(List<Account> accounts);

    Optional<Account> findAccountById(String id) throws AccountFileDoesntExistException;

    Account createAccount(String id, String firstName, String lastName, Long balance);

    Account createAccount(AccountDataGenerator accountDataGenerator);

    boolean deleteAccountFile(String id);

    Boolean existFileAccount(String id) throws AccountFileDoesntExistException;

    void deleteAllAccountFiles();
}
