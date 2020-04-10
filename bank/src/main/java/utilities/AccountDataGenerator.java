package utilities;

import dao.DAO;
import dao.DAOImpl;
import entities.Account;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import services.SerializationService;
import services.SerializationServiceImpl;

import java.util.List;

public class AccountDataGenerator {
    DAO dao;
    SerializationService service;
    private static String id;
    private String firstName;
    private String lastName;
    private Long balance;

    public AccountDataGenerator() {
        service = new SerializationServiceImpl();
        dao = DAOImpl.getDAO();
        setId();
        setFirstName();
        setLastName();
        setBalance();
    }

    private void setId(){
        if(service.readFilesInDirectory().size()==0){
            id = "a110";
        }
        else {
            List<Account> accounts = dao.loadAllAccounts();
            String id = accounts.get(accounts.size() - 1).getId();
            this.id = incrementId(id);
        }
    }

    private String incrementId(String id){
        int numbers = Integer.parseInt(id.substring(1));
        numbers++;
        return id.substring(0,1) + numbers;
    }

    private String generateString(){
        int length = 5;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }

    private void setFirstName(){
        this.firstName = generateString();
    }

    private void setLastName(){
        this.lastName = generateString();
    }

    private void setBalance(){
        long startInclusive = 0l;
        long endInclusive = 100000l;
        this.balance = RandomUtils.nextLong(startInclusive, endInclusive);
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
}