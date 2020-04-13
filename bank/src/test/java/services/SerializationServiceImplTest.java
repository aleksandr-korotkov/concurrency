package services;

import entities.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SerializationServiceImplTest {
    private static SerializationService serializationService;
    private static final String DIRECTORY = "src/main/resources/accounts/";
    private static final String EXPANSION = ".txt";

    void delTestData(String id){
        new File(DIRECTORY + id + EXPANSION).delete();
    }

    @BeforeAll
    static void setUp(){
        serializationService = new SerializationServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("services.Providers#testSaveAccount")
    @DisplayName("Тест метода saveAccount() класса SerializationServiceImpl")
    void saveAccountTest(Account account) {
        serializationService.saveAccount(account, account.getId());
        assertTrue(new File(DIRECTORY + account.getId() + EXPANSION).exists());
        delTestData(account.getId());
    }

    @ParameterizedTest
    @MethodSource("services.Providers#testLoadAccount")
    @DisplayName("Тест метода loadAccount() класса SerializationServiceImpl")
    void loadAccountTest(Account account) {
        serializationService.saveAccount(account, account.getId());
        assertTrue(serializationService.loadAccount(account.getId() + EXPANSION).get().equals(account));
        delTestData(account.getId());
    }

    @Test
    @DisplayName("Тест метода readFilesInDirectory() класса SerializationServiceImpl")
    void readFilesInDirectoryTest() {
        assertEquals(serializationService.readFilesInDirectory().size(),new File(DIRECTORY).listFiles().length);
    }
}