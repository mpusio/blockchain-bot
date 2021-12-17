package com.coinspy.useful;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void hexToDec() {
        String exampleInput = "00000000000000000000000000000000000013b8b5b5056e16b3be0400000000";
        String result = Parser.hexToDec(exampleInput);
        assertEquals("400000000000000000000000000000000", result );
    }

    @Test
    void hexToDec2() {
        String exampleInput = "0xc4abee";
        String result = Parser.hexToDec(exampleInput);
        assertEquals("12889070", result );
    }


    @Test
    void divideDecimal2() {
        String exampleInput2 = "5089980051452155263";
        String result = Parser.divideByDecimals(exampleInput2, "18");
        assertEquals("5.089980051452155263", result );
    }

    @Test
    void divideDecimal3() {
        String exampleInput2 = "500000000000000000000000";
        String result = Parser.divideByDecimals(exampleInput2, "18");
        assertEquals("500000", result );
    }

    @Test
    void divideDecimal4() {
        String exampleInput2 = "50000000000000";
        String result = Parser.divideByDecimals(exampleInput2, "18");
        assertEquals("0.00005", result );
    }
}