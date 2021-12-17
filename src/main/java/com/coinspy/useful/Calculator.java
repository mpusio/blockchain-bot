package com.coinspy.useful;

import java.math.BigDecimal;
import java.math.MathContext;

public class Calculator {
    private Calculator() { }

    public static String percent(String total, String pieceOf){
        var totalBigInt = new BigDecimal(total);
        var pieceOfBigInt = new BigDecimal(pieceOf);
        var roundTo = new MathContext(4);

        return pieceOfBigInt.divide(totalBigInt, roundTo).floatValue() * 100 + "%";
    }
}
