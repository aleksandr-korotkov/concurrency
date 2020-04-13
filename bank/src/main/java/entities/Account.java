package entities;

import java.io.Serializable;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(firstName, account.firstName) &&
                Objects.equals(lastName, account.lastName) &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, balance);
    }
}
