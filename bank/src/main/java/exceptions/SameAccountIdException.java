package exceptions;

public class SameAccountIdException extends Exception {
    @Override
    public String getMessage() {
        return "Попытка перевода с одного и того же счета.";
    }
}