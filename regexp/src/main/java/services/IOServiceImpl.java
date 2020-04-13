package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IOServiceImpl implements IOService{
    private StringBuilder stringBuilder;
    private String SOURCE_PATH = "src/main/resources/";
    private String SOURCE_FILE = "source.txt";
    private String TARGET_FILE = "target.txt";

    @Override
    public String readFromFile(){
        stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(SOURCE_PATH + SOURCE_FILE)))) {
            String line;
            while ((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(stringBuilder);
    }

    @Override
    public void writeInFile(List<String> numbers){
        File targetFile = new File(SOURCE_PATH + TARGET_FILE);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetFile))) {
            if(!targetFile.exists()){
                targetFile.createNewFile();
            }
            numbers.stream().forEach(str-> {
                try {
                    bufferedWriter.write(str);
                    bufferedWriter.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
