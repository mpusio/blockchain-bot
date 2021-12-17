package com.coinspy.useful;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private Parser() {}

    public static String hexToDec(String hex){
        return new BigInteger(hex, 16).toString(10);
    }

    public static String divideByDecimals(String value, String decimals){
        var decimal = Integer.parseInt(decimals);
        var bigValue = new BigDecimal(value);
        var bigDecimal = new BigDecimal("10");
        var pow = bigDecimal.pow(decimal);

        return bigValue.divide(pow, new MathContext(78, RoundingMode.CEILING)).toString();
    }

    public static List<String> parseInputFromExplorer(String inputData, boolean containMethodId){
        return splitIntoEqualCharacterParts(inputData.replace("0x", ""), 64, containMethodId);
    }

    public static String cutAddress(String data){
        return "0x" + data.replace("0x", "").substring(24);
    }

    private static String removeMethodId(String inputData){
        return inputData.substring(8);
    }

    private static List<String> splitIntoEqualCharacterParts(String inputData, int substringSize, boolean containMethodId){
        if (containMethodId){
            inputData = removeMethodId(inputData);
        }

        if (inputData.length() % substringSize != 0){
            throw new RuntimeException("Wrong input data");
        }

        List<String> inputList = new ArrayList<>();

        for (int i = 0, j=64; i < inputData.length(); i+=substringSize, j+=substringSize) {
            inputList.add(inputData.substring(i, j));
        }

        return inputList;
    }
}
