package services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexpServiceImpl implements RegexpService{
    private String REGEX_PATTERN_NUMBER = "\\+\\d\\s*\\(\\d{3}\\)\\s*\\d{3}\\s*\\d{2}\\s*\\d{2}";
    private String REGEX_PATTERN_SYMBOLS = "\\+|\\s|\\(|\\)";

    @Override
    public List<String> findNumbersAndReturnList(String text){
        List<String> matchers = new ArrayList<>();
        Matcher matcher = Pattern.compile(REGEX_PATTERN_NUMBER).matcher(text);
        while (matcher.find()){
            matchers.add(matcher.group());
        }
        return matchers;
    }

    @Override
    public List<String> filterSymbols(List<String> matchers){
        return matchers.stream().map(str-> str = str.replaceAll(REGEX_PATTERN_SYMBOLS,"")).collect(Collectors.toList());
    }
}
