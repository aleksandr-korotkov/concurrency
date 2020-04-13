package services;

import java.util.List;

public interface IOService {

    String readFromFile();

    void writeInFile(List<String> numbers);
}
