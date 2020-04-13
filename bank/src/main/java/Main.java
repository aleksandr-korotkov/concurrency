import entities.Account;
import entities.DaemonBackgroundThread;
import exceptions.AccountFileDoesntExistException;
import utilities.AccountManager;
import utilities.RandomTransferGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, AccountFileDoesntExistException {
        AccountManager accountManager = AccountManager.getAccountManager();
        //accountManager.createNewRandomAccounts(10);
        accountManager.initApp(20);

        DaemonBackgroundThread thread = new DaemonBackgroundThread();
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 10; i++) {
            accountManager.transfer(RandomTransferGenerator.getRandomAccount(), RandomTransferGenerator.getRandomAccount(),RandomTransferGenerator.getRandomPositiveSum());
        }

        Thread.sleep(3000);
        accountManager.saveDataAndShowAccountsStatus();
    }
}

