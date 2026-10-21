package com.leetcodingquest.pipeline.Lesson2_3;

public class A {

    public static void printDelim(String title) {
        System.out.println();
        System.out.println();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^");
        System.out.println(title);
        System.out.println("_____________________");
    }

    public static void printMult(int a, int b) {
        int result = a * b;
        System.out.println(a + " x " + b + " = " + result);
    }

    public static int calcCredit(int amount, int months, double rate) {
        int monthlyPayment = amount / months;
        // System.out.println("Платёж: " + monthlyPayment);
        return monthlyPayment;
    }
}
