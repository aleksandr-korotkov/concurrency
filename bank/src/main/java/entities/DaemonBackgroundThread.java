package entities;

import org.apache.log4j.Logger;
import services.SerializationService;
import services.SerializationServiceImpl;
import utilities.AccountManager;

public class DaemonBackgroundThread extends Thread{
    final static Logger logger = Logger.getLogger(DaemonBackgroundThread.class);

    SerializationService service;
    AccountManager accountManager;

    public DaemonBackgroundThread() {
        service = new SerializationServiceImpl();
        this.accountManager = AccountManager.getAccountManager();
    }

    @Override
    public void run() {
        while (true) {
            logger.info("Сканирую директорию с файлами аккаунтов");
            if (service.readFilesInDirectory().size() > accountManager.getAccounts().size()) {
                logger.warn("Обнаружен новый файл клиента");
            }
            if (service.readFilesInDirectory().size() < accountManager.getAccounts().size()) {
                logger.warn("Обнаружен новый не сериализованный экземпляр класса Account");
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}