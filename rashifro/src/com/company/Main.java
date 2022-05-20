package com.company;

import java.io.*;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        StringBuilder readText = new StringBuilder();
        String line;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("text.txt")));
            while ((line = br.readLine()) != null) {
                readText.append(line).append(" ");
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        String lowerText = readText.toString().toLowerCase(Locale.ROOT);
        char[] characterText = lowerText.toCharArray();

        StringBuilder result = new StringBuilder();
        StringBuilder lineAndCharacterNumbers = new StringBuilder();
        try {
            BufferedReader br1 = new BufferedReader(new FileReader("numbers.txt"));
            while ((line = br1.readLine()) != null) {
                lineAndCharacterNumbers.append(line);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        /*
        первое извлекает цифры из скобок
        второе после ;
        */
        String regularForFindNumbers = "\\d+(\s*);(\s*)\\d+";
        String regularForNumberOfSymbol = ";\\d+";
        Pattern patternForNumbers = Pattern.compile(regularForFindNumbers);
        Pattern patternForSymbol = Pattern.compile(regularForNumberOfSymbol);
        Matcher matcherForFindNumbers = patternForNumbers.matcher(lineAndCharacterNumbers.toString());
        while(matcherForFindNumbers.find()){
            String t = matcherForFindNumbers.group().replaceAll(" ", "");
            Matcher matcherForSymbolNumber = patternForSymbol.matcher(t);
            while(matcherForSymbolNumber.find()){
                String numberOfSymbol = matcherForSymbolNumber.group().replaceAll(";", "");
                int index = Integer.parseInt(numberOfSymbol) - 1;
                result.append(characterText[index]);
            }
        }

        System.out.println(result);
    }
}
