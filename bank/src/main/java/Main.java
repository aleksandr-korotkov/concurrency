import entities.DaemonBackgroundThread;
import exceptions.AccountFileDoesntExistException;
import utilities.AccountManager;

public class Main {
    public static void main(String[] args) throws InterruptedException, AccountFileDoesntExistException {
        AccountManager accountManager = AccountManager.getAccountManager();
        accountManager.createNewRandomAccounts(10);
        accountManager.initApp(20);

        DaemonBackgroundThread thread = new DaemonBackgroundThread();
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 50; i++) {
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a111"),1l);
            accountManager.transfer(accountManager.findAccountById("a111"), accountManager.findAccountById("a112"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a111"),1l);
            accountManager.transfer(accountManager.findAccountById("a113"), accountManager.findAccountById("a112"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a113"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a116"),1l);
            accountManager.transfer(accountManager.findAccountById("a116"), accountManager.findAccountById("a112"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a115"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a116"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a117"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a118"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a119"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a114"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a113"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a111"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a111"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a111"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a111"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a111"),1l);
            accountManager.transfer(accountManager.findAccountById("a112"), accountManager.findAccountById("a111"),1l);
        }

        Thread.sleep(3000);
        accountManager.saveDataAndShowAccountsStatus();
    }
}

