package services;

import entities.Account;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface SerializationService {

    void saveAccount(Account account, String filename);

    Optional<Account> loadAccount(String filename);

    List<File> readFilesInDirectory();
}
