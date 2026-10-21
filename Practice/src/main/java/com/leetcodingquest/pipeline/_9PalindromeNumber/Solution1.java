package com.leetcodingquest.pipeline._9PalindromeNumber;

public class Solution1 {
    // время O(log10(x)), память O(1)
    // Проверяем, является ли число палиндромом
    public boolean isPalindrome(int x){
        // Отрициательные числа и числа с последним нулём (кроме 0) не подходят
        if (x < 0 || (x % 10) == 0 && x != 0) return false;
        // Создаём переменную для перевёрнутой половины числа
        int reversedHalf = 0;
        while (x > reversedHalf){   // Продолжаем, пока исходная половина длиннее перевёрнутой
            int lastDigit = x % 10; // Получаем последнюю цифру исходного числа
            reversedHalf = reversedHalf * 10 + lastDigit;   // Добавляем последнюю цифру в перевёрнутую половину
            x /= 10;    // Удаляем последнюю цифру из исходного числа
        }   // завершаем цикл, когда обработана примерно половина цифр
        return x == reversedHalf || x == reversedHalf / 10; // Сравниваем половины с учетом возможно центральной цифры
    }

    public static void main(String[] args) {
        int x = 121;
        System.out.println(new Solution1().isPalindrome(x));    // true
        x = -121;
        System.out.println(new Solution1().isPalindrome(x));    // false
        x = 10;
        System.out.println(new Solution1().isPalindrome(x));    // false
    }
}
