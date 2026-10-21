package com.leetcodingquest.pipeline.Lesson2_3;

import com.leetcodingquest.pipeline.Lesson2_3.my.favourite.code.B;

public class Main {
    public static void main(String[] args) {
        // printMult(11, 54);  // 11 * 54 = 594
        int p1 = A.calcCredit(1000000, 12, 9.9);
        int p2 = A.calcCredit(10000, 12, 29);
        System.out.println("Общая сумма: " + (p1 + p2));

        B.printMe();
    }
}
