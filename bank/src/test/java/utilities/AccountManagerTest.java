package utilities;

import dao.DAO;
import dao.DAOImpl;
import entities.Account;
import exceptions.AccountFileDoesntExistException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagerTest {
    private final String DIRECTORY = "src/main/resources/accounts/";
    private static final String TEST_ACCOUNT_SOURCE_ID = "a222";
    private static final String TEST_ACCOUNT_TARGET_ID = "a223";
    static AccountManager accountManager;
    private static DAO dao;

    @BeforeAll
    static void setUp() {
        accountManager = AccountManager.getAccountManager();
        dao = DAOImpl.getDAO();
        dao.createAccount(TEST_ACCOUNT_SOURCE_ID,"a", "b",100l);
        dao.createAccount(TEST_ACCOUNT_TARGET_ID,"va","zee",20l);
        accountManager.initApp(2);
    }

    @AfterAll
    static void delTestData(){
        dao.deleteAccountFile(TEST_ACCOUNT_SOURCE_ID);
        dao.deleteAccountFile(TEST_ACCOUNT_TARGET_ID);
    }

    @ParameterizedTest
    @MethodSource("utilities.Providers#testTransfer")
    @DisplayName("Тест метода transfer() класса AccountManager")
    void transferTest(Account source, Account target, Long sum, Long expected) throws AccountFileDoesntExistException, InterruptedException {
        accountManager.transfer(source, target, sum);
        Thread.sleep(300);
        assertEquals(accountManager.findAccountById(TEST_ACCOUNT_SOURCE_ID).getBalance(),expected);
    }

    @ParameterizedTest
    @MethodSource("utilities.Providers#testCreateNewRandomAccounts")
    @DisplayName("Тест метода createNewRandomAccounts() класса AccountManager")
    void createNewRandomAccountsTest(int quantityAccounts, int expected){
        int countFilesBefore = new File(DIRECTORY).listFiles().length;
        accountManager.createNewRandomAccounts(quantityAccounts);
        int countFilesAfter = new File(DIRECTORY).listFiles().length;
        assertEquals(countFilesAfter-countFilesBefore, expected);
    }

    @Ignore
    @Test
    void deleteAllAccountFilesTest(){
        accountManager.deleteAllAccountFiles();
        assertTrue(new File(DIRECTORY).listFiles().length==0);
    }
}