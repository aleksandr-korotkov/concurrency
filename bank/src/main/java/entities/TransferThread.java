package entities;

import dao.DAOImpl;
import exceptions.AccountFileDoesntExistException;
import exceptions.NotEnoughMoneyException;
import exceptions.SameAccountIdException;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransferThread extends Thread {
    final static Logger logger = Logger.getLogger(TransferThread.class);
    private DAOImpl dao = DAOImpl.getDAO();
    private Lock lock = new ReentrantLock();
    private Account source;
    private Account target;
    private Long sum;

    public TransferThread(Account source, Account target, Long sum) {
        this.source = source;
        this.target = target;
        this.sum = sum;
    }

    @Override
    public void run() {
        try {
            transfer(source, target, sum);
            Thread.sleep(1);
        } catch (AccountFileDoesntExistException e) {
            e.printStackTrace();
        } catch (SameAccountIdException e) {
            e.printStackTrace();
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean transfer(Account sourceAccount, Account targetAccount,Long sum) throws AccountFileDoesntExistException, SameAccountIdException, NotEnoughMoneyException {
        if(sourceAccount.getId().equals(targetAccount.getId())){
            throw new SameAccountIdException();
        }
        if(!dao.existFileAccount(sourceAccount.getId())||!dao.existFileAccount(targetAccount.getId())){
            throw new AccountFileDoesntExistException();
        }
        try {
            lock.lock();
            if(sourceAccount.getBalance()<sum){
                throw new NotEnoughMoneyException();
            }
            sourceAccount.subtractBalance(sum);
            targetAccount.addBalance(sum);
            logger.info("Со счета " + sourceAccount.getId() + " совершено списание на сумму "
                    + sum + ". Баланс остатка " + sourceAccount.getBalance() +
                    ". Средства поступили на счет " + targetAccount.getId() + ". Баланс " + targetAccount.getBalance());
        } finally {
            lock.unlock();
        }
        return true;
    }

}
