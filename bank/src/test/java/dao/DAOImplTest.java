package dao;

import exceptions.AccountFileDoesntExistException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utilities.AccountDataGenerator;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAOImplTest {
    private static final String DIRECTORY = "src/main/resources/accounts/";
    private static final String EXPANSION = ".txt";
    static DAO dao;

    @BeforeAll
    static void setUp() {
        dao = DAOImpl.getDAO();
    }

    List<File> filesInDirectory(){
        return Arrays.asList(new File(DIRECTORY).listFiles());
    }

    void delTestData(String id){
        new File(DIRECTORY + id + EXPANSION).delete();
    }

    @Test
    @DisplayName("Тест метода loadAllAccounts() класса DAOImpl")
    void loadAllAccountsTest() {
        assertTrue(dao.loadAllAccounts().size() == filesInDirectory().size());
    }


    @ParameterizedTest
    @MethodSource("dao.Providers#testFindAccountById")
    @DisplayName("Тест метода findAccountById() класса DAOImpl")
    void findAccountByIdTest(AccountDataGenerator accountDataGenerator) throws AccountFileDoesntExistException {
        dao.createAccount(accountDataGenerator);
        assertEquals(dao.findAccountById(accountDataGenerator.getId()).get().getId(),accountDataGenerator.getId());
    }

    @ParameterizedTest
    @MethodSource("dao.Providers#testCreateAccount")
    @DisplayName("Тест метода createAccount() класса DAOImpl")
    void createAccountTest(String id, String firstName, String lastName, Long balance, String expected) {
        dao.createAccount(id,firstName,lastName,balance);
        assertEquals(filesInDirectory().stream().filter(file -> file.getName().equals(expected)).findFirst().get().getName(),expected);
        delTestData(id);
    }

    @ParameterizedTest
    @MethodSource("dao.Providers#testCreateAccountRandomObject")
    @DisplayName("Тест метода createAccount() с объектом DataGenerator класса DAOImpl")
    void testCreateAccountTest(AccountDataGenerator dataGenerator) {
        dao.createAccount(dataGenerator);
        assertEquals(filesInDirectory().stream().filter(file -> file.getName().equals(dataGenerator.getId() + EXPANSION)).findFirst().get().getName(),dataGenerator.getId() + EXPANSION);
        delTestData(dataGenerator.getId());
    }

    @ParameterizedTest
    @MethodSource("dao.Providers#testDeleteAccountFile")
    @DisplayName("Тест метода deleteAccountFile() класса DAOImpl")
    void deleteAccountFileTest(AccountDataGenerator accountDataGenerator) {
        dao.createAccount(accountDataGenerator);
        dao.deleteAccountFile(accountDataGenerator.getId());
        assertFalse(new File(DIRECTORY + accountDataGenerator.getId() + EXPANSION).exists());
    }

    @Test
    @DisplayName("Тест метода deleteAllAccountFiles() класса DAOImpl")
    void deleteAllAccountFilesTest() {
        dao.deleteAllAccountFiles();
        assertTrue(filesInDirectory().size() == 0);
    }

    @ParameterizedTest
    @MethodSource("dao.Providers#testExistFileAccount")
    @DisplayName("Тест метода existFileAccount() класса DAOImpl")
    void existFileAccountTest(AccountDataGenerator accountDataGenerator) throws AccountFileDoesntExistException {
        dao.createAccount(accountDataGenerator);
        assertEquals(dao.existFileAccount(accountDataGenerator.getId()),new File(DIRECTORY + accountDataGenerator.getId() + EXPANSION).exists());
        delTestData(accountDataGenerator.getId());
    }
}