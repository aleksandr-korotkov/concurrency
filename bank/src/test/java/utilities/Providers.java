package utilities;

import dao.DAO;
import dao.DAOImpl;
import exceptions.AccountFileDoesntExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Providers {
    private static AccountManager accountManager = AccountManager.getAccountManager();
    private static final String TEST_ACCOUNT_SOURCE_ID = "a222";
    private static final String TEST_ACCOUNT_TARGET_ID = "a223";

    private Providers(){
    }

    public static Stream<Arguments> testTransfer() throws AccountFileDoesntExistException {
        return Stream.of(
                arguments(accountManager.findAccountById(TEST_ACCOUNT_SOURCE_ID),
                        accountManager.findAccountById(TEST_ACCOUNT_TARGET_ID), 50l, 50l),
                arguments(accountManager.findAccountById(TEST_ACCOUNT_SOURCE_ID),
                        accountManager.findAccountById(TEST_ACCOUNT_TARGET_ID), 10l, 40l)

        );
    }

    public static Stream<Arguments> testCreateNewRandomAccounts() {
        return Stream.of(
                arguments(10,10),
                arguments(2,2)
        );
    }
}