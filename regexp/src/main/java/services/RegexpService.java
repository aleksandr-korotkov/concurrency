package services;

import java.util.List;

public interface RegexpService {

    List<String> findNumbersAndReturnList(String text);

    List<String> filterSymbols(List<String> matchers);
}
