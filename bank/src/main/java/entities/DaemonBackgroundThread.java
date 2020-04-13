package entities;

import org.apache.log4j.Logger;
import services.SerializationService;
import services.SerializationServiceImpl;
import utilities.AccountManager;

import java.io.File;
import java.util.List;

public class DaemonBackgroundThread extends Thread{
    final static Logger logger = Logger.getLogger(DaemonBackgroundThread.class);
    private final String EXPANSION = ".txt";

    SerializationService service;
    AccountManager accountManager;

    public DaemonBackgroundThread() {
        service = new SerializationServiceImpl();
        this.accountManager = AccountManager.getAccountManager();
    }

    @Override
    public void run() {
        while (true) {
            List<File> filesInDirectory = service.readFilesInDirectory();
            List<Account> accounts = accountManager.getAccounts();
            logger.info("Сканирую директорию с файлами аккаунтов");
            if (filesInDirectory.size() > accounts.size()) {
                logger.warn("Обнаружен новый файл клиента");
                File newFile = null;
                for (File file : filesInDirectory) {
                    newFile = file;
                    for (Account account : accounts){
                        if(file.getName().equals((account.getId() + EXPANSION))){
                            newFile = null;
                            break;
                        }
                    }
                    if(!(newFile == null)){
                        break;
                    }
                }
                logger.warn(Thread.currentThread().getName() + " Новый файл клиента имеет ID " + newFile.getName().substring(0, newFile.getName().length()-4));
                logger.info(Thread.currentThread().getName() + " Загружаю аккаунт " + newFile.getName().substring(0, newFile.getName().length()-4));
                accountManager.refresh();
            }
            if (filesInDirectory.size() < accounts.size()) {
                logger.warn(Thread.currentThread().getName() + " Обнаружен новый, не сериализованный экземпляр класса Account");
                Account newAccount = null;
                for (Account account : accounts) {
                    newAccount = account;
                    for (File file : filesInDirectory){
                        if((account.getId() + EXPANSION).equals(file.getName())){
                            newAccount = null;
                            break;
                        }
                    }
                    if(!(newAccount == null)){
                        break;
                    }
                }
                logger.warn(Thread.currentThread().getName() + " Новый экземпляр класса Account имеет ID " + newAccount.getId());
                logger.info(Thread.currentThread().getName() + " Сохраняю аккаунт " + newAccount.getId());
                service.saveAccount(newAccount, newAccount.getId());
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}