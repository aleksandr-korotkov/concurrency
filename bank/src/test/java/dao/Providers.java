package dao;

import org.junit.jupiter.params.provider.Arguments;
import utilities.AccountDataGenerator;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Providers {

        private Providers(){
        }

        public static Stream<Arguments> testCreateAccount() {
            return Stream.of(
                    arguments("a544", "ab", "va", 100l, "a544.txt"),
                    arguments("asf444", "sd", "ls", 120l, "asf444.txt")
            );
        }

    public static Stream<Arguments> testCreateAccountRandomObject() {
        return Stream.of(
                arguments(new AccountDataGenerator()),
                arguments(new AccountDataGenerator())
        );
    }

    public static Stream<Arguments> testDeleteAccountFile() {
        return Stream.of(
                arguments(new AccountDataGenerator()),
                arguments(new AccountDataGenerator())
        );
    }

    public static Stream<Arguments> testExistFileAccount() {
        return Stream.of(
                arguments(new AccountDataGenerator()),
                arguments(new AccountDataGenerator())
        );
    }
    public static Stream<Arguments> testFindAccountById() {
        return Stream.of(
                arguments(new AccountDataGenerator()),
                arguments(new AccountDataGenerator())
        );
    }
}

