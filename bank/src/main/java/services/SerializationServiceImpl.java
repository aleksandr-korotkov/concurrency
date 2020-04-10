package services;

import entities.Account;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SerializationServiceImpl implements  SerializationService{
    final static Logger logger = Logger.getLogger(SerializationServiceImpl.class);
    private final String DIRECTORY = "src/main/resources/accounts/";
    private final String EXPANSION = ".txt";

    @Override
    public void saveAccount(Account account, String filename) {
        try (ObjectOutputStream objstream = new ObjectOutputStream( new FileOutputStream(DIRECTORY + filename + EXPANSION))){
            objstream.writeObject(account);
        }
        catch (FileNotFoundException e){
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Optional<Account> loadAccount(String filename)  {
        try (ObjectInputStream objstream = new ObjectInputStream(new FileInputStream(DIRECTORY + filename))){
            return Optional.of((Account) objstream.readObject());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<File> readFilesInDirectory(){
        return Arrays.asList(new File(DIRECTORY).listFiles());
    }
}
