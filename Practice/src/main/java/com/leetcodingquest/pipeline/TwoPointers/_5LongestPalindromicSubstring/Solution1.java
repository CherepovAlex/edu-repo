package com.leetcodingquest.pipeline.TwoPointers._5LongestPalindromicSubstring;

public class Solution1 {
    // время O(n^2), память O(1)
    public String longestPalindrome(String s) {
        // Если строка пустая, пустой палиндром тоже пустой
        if (s == null || s.length() < 1) {return "";}
        // Переменные для хранения результата:
        int start = 0;      // Начальный индекс самого длинного палиндрома
        int maxLength = 1;  // Длина самого длинного палиндрома, найденного на данный момент
        // Перебираем каждый индекс как возможный центр
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);           // Ищем нечётный палиндром с центром в i
            int len2 = expandAroundCenter(s, i, i + 1); // Ищем четный палиндром между i и i + 1
            // Выбираем более длинный из двух найденных палиндромов
            int len = Math.max(len1, len2);
            // Обновляем результат, только если нашли палиндром длиннее
            if (len > maxLength) {
                maxLength = len;            // Запоминаем новую максимальную длину
                start = i - (len - 1) / 2;  // Вычисляем начальный индекс палиндрома
            }
        }
        return s.substring(start, start + maxLength);   // Возвращаем найденную подстроку
    }
        // Метод расширяется от заданного центра (left, right) наружу,пока символы совпадают.
        //  Возвращает длину найденного палиндрома.
    private int expandAroundCenter(String s, int left, int right) {
        // Расширяем границы, пока они внутри строки и символы совпадают
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;     // Сдвигаем левую границу на один символ влево
            right++;    // Сдвигаем правую границу на один символ вправо
        } // Длина палиндрома равна: (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;    // Возвращаем длину последнего корректного палиндрома
    }
    public static void main(String[] args) {
        String s = "babad";
        System.out.println(new Solution1().longestPalindrome(s)); // bab

        s = "cbbd";
        System.out.println(new Solution1().longestPalindrome(s)); // bb

        s = "abba";
        System.out.println(new Solution1().longestPalindrome(s)); // abba
    }
}
