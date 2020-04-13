package utilities;

import dao.DAOImpl;
import entities.Account;
import entities.TransferThread;
import exceptions.AccountFileDoesntExistException;
import services.ConsoleServiceImpl;
import services.ExecutorThreadServiceImpl;

import java.util.List;

public class AccountManager {
    private List<Account> accounts;
    private DAOImpl dao;
    private ConsoleServiceImpl consoleService;
    private ExecutorThreadServiceImpl executorService;
    private static AccountManager accountManager;

    private AccountManager() {
        this.dao = DAOImpl.getDAO();
        this.consoleService = new ConsoleServiceImpl();
    }

    public static AccountManager getAccountManager(){
        if(accountManager == null){
            accountManager = new AccountManager();
        }
        return accountManager;
    }

    public void initApp(int quantityThreadInPool){
        executorService = new ExecutorThreadServiceImpl(quantityThreadInPool);
        accounts = dao.loadAllAccounts();
        consoleService.printAccountsStatus(accounts);
        consoleService.printAccountBalanceSum(accounts);
    }

    public void saveDataAndShowAccountsStatus(){
        dao.saveAllAccounts(accounts);
        consoleService.printAccountsStatus(accounts);
        consoleService.printAccountBalanceSum(accounts);
        executorService.shutdown();
    }

    public boolean transfer(Account sourceAccount, Account targetAccount,Long sum){
        executorService.executeThread(new TransferThread(sourceAccount, targetAccount, sum));
        return true;
    }

    public List<Account> getAccounts(){
        return accounts;
    }

    public Account findAccountByIdFromArray(String id) throws AccountFileDoesntExistException {
        Account foundAccount = accounts.stream().filter(account -> account.getId().equals(id)).findFirst().get();
        if(foundAccount == null) {
            throw new AccountFileDoesntExistException();
        }
        return foundAccount;
    }

    public Account findAccountByIdFromFiles(String id) throws AccountFileDoesntExistException {
        return dao.findAccountById(id).get();
    }


    public void createNewRandomAccounts(int quantityAccounts){
        for(int i = 0;i < quantityAccounts; i++){
            AccountDataGenerator accountData = new AccountDataGenerator();
            dao.createAccount(accountData.getId(),accountData.getFirstName(),
                    accountData.getLastName(),accountData.getBalance());
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllAccountFiles(){
        dao.deleteAllAccountFiles();
    }

    public void submitThread(Runnable thread){
        executorService.executeThread(thread);
    }

    public void refresh() {
        dao.saveAllAccounts(accounts);
        accounts = dao.loadAllAccounts();
    }
}