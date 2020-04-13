package services;

import org.junit.jupiter.params.provider.Arguments;
import utilities.AccountDataGenerator;
import utilities.AccountFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Providers {

    private Providers(){
    }

    public static Stream<Arguments> testSaveAccount() {
        return Stream.of(
                arguments(AccountFactory.getAccount(new AccountDataGenerator())),
                arguments(AccountFactory.getAccount(new AccountDataGenerator()))
        );
    }

    public static Stream<Arguments> testLoadAccount() {
        return Stream.of(
                arguments(AccountFactory.getAccount(new AccountDataGenerator())),
                arguments(AccountFactory.getAccount(new AccountDataGenerator()))
        );
    }
}

