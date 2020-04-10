package entities;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private volatile Long balance;
    private Lock lock = new ReentrantLock();

    public Account(String id, String firstName, String lastName, Long balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getBalance() {
        return balance;
    }

    public void subtractBalance(Long sum)  {
        try {
            lock.lock();
            this.balance = this.balance - sum;
        }
        finally {
            lock.unlock();
        }
    }

    public void addBalance(Long sum)  {
        try {
            lock.lock();
            this.balance = this.balance + sum;
        }
        finally {
            lock.unlock();
        }
    }
}
