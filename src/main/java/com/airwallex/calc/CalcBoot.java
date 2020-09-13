package com.airwallex.calc;

import java.util.Scanner;

/**
 * 启动类
 */
public class CalcBoot {
    public static void main(String[] args) {
        Calc calc = new ReversePolishNotationCalculator();
        String prefixIdentification = "input:  ";
        Scanner scan = new Scanner(System.in);
        System.out.print(prefixIdentification);
        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            calc.input(s);
            System.out.print(prefixIdentification);
        }
    }
}
