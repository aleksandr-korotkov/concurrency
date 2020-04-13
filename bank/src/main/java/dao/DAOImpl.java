package dao;

import entities.Account;
import exceptions.AccountFileDoesntExistException;
import org.apache.log4j.Logger;
import services.SerializationServiceImpl;
import utilities.AccountDataGenerator;
import utilities.AccountFactory;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DAOImpl implements DAO{
    SerializationServiceImpl serializationService;
    final static Logger logger = Logger.getLogger(DAOImpl.class);
    private final String DIRECTORY = "src/main/resources/accounts/";
    private final String EXPANSION = ".txt";
    private static DAOImpl dao;

    private DAOImpl() {
        this.serializationService = new SerializationServiceImpl();
    }

    public static DAOImpl getDAO(){
        if(dao == null){
            dao = new DAOImpl();
        }
        return dao;
    }

    @Override
    public List<Account> loadAllAccounts() {
        return serializationService.readFilesInDirectory().stream()
                .map(file -> serializationService.loadAccount(file.getName()).get())
                .collect(Collectors.toList());
    }

    @Override
    public void saveAllAccounts(List<Account> accounts) {
        accounts.stream().forEach(account -> serializationService.saveAccount(account, account.getId()));
    }

    @Override
    public Optional<Account> findAccountById(String id) throws AccountFileDoesntExistException {
        File file = new File(DIRECTORY + id + EXPANSION);
        if (serializationService.readFilesInDirectory().contains(file)) {
            return Optional.of(serializationService.loadAccount(id + EXPANSION).get());
        } else {
            throw new AccountFileDoesntExistException();
        }
    }

    @Override
    public Account createAccount(String id, String firstName, String lastName, Long balance) {
        Account account = AccountFactory.getAccount(id, firstName, lastName, balance);
        serializationService.saveAccount(account, account.getId());
        return account;
    }

    @Override
    public Account createAccount(AccountDataGenerator accountDataGenerator) {
        Account account = AccountFactory.getAccount(accountDataGenerator.getId(),
                accountDataGenerator.getFirstName(),
                accountDataGenerator.getLastName(),
                accountDataGenerator.getBalance());
        serializationService.saveAccount(account, account.getId());
        return account;
    }

    @Override
    public boolean deleteAccountFile(String id) {
        File file = new File(DIRECTORY + id + EXPANSION);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllAccountFiles() {
        serializationService.readFilesInDirectory().stream().forEach(file -> file.delete());
    }

    @Override
    public Boolean existFileAccount(String id) throws AccountFileDoesntExistException {
        File file = new File(DIRECTORY + id + EXPANSION);
        if (serializationService.readFilesInDirectory().contains(file)) {
            return true;
        } else {
            throw new AccountFileDoesntExistException();
        }
    }
}