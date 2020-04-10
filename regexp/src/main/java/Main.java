import services.IOService;
import services.IOServiceImpl;
import services.RegexpService;
import services.RegexpServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IOService ioService = new IOServiceImpl();
        RegexpService regexpService = new RegexpServiceImpl();
        List<String> strings = regexpService.filterSymbols(regexpService.findNumbersAndReturnList(ioService.readFromFile()));
        ioService.writeInFile(strings);
    }
}
