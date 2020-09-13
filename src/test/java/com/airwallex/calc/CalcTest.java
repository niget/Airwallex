package com.airwallex.calc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalcTest {

    @Test
    public void test() {
        Calc calc = new ReversePolishNotationCalculator();
        String example = "clear";
        System.out.println("---------------Example 1--------------");
        example = "5 2";
        calc.input(example);
        assertEquals("5 2",calc.curStatus().trim());

        calc.clear();
        System.out.println("---------------Example 2--------------");
        example = "2 sqrt";
        calc.input(example);
        assertEquals("1.4142135624",calc.curStatus().trim());

        calc.clear();
        System.out.println("---------------Example 3--------------");
        example = "5 2 -";
        calc.input(example);
        example = "3 -";
        calc.input(example);
        example = "clear";
        calc.input(example);
        assertEquals("",calc.curStatus().trim());

        calc.clear();
        System.out.println("---------------Example 4--------------");
        example = "5 4 3 2";
        calc.input(example);
        example = "undo undo *";
        calc.input(example);
        example = "5 *";
        calc.input(example);
        example = "undo";
        calc.input(example);
        assertEquals("",calc.curStatus().trim());


        calc.clear();
        System.out.println("---------------Example 5--------------");
        example = "7 12 2 /";
        calc.input(example);
        example = "*";
        calc.input(example);
        example = "4 /";
        calc.input(example);
        assertEquals("10.5",calc.curStatus().trim());


        calc.clear();
        System.out.println("---------------Example 6--------------");
        example = "1 2 3 4 5";
        calc.input(example);
        example = "*";
        calc.input(example);
        example = "clear 3 4 -";
        calc.input(example);
        assertEquals("-1",calc.curStatus().trim());


        calc.clear();
        System.out.println("---------------Example 7--------------");
        example = "1 2 3 4 5";
        calc.input(example);
        example = "* * * *";
        calc.input(example);
        assertEquals("120",calc.curStatus().trim());



        calc.clear();
        System.out.println("---------------Example 8--------------");
        example = "1 2 3 * 5 + * * 6 5";
        calc.input(example);
        assertEquals("11",calc.curStatus().trim());




    }
}
