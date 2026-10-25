package com.leetcodingquest.pipeline.TwoPointers._28FindTheIndexOfTheFirstOccurrenceInAString;

public class Solution3 {

    /**
     * Метод для поиска первого вхождения подстроки needle в строку haystack.
     *
     * @param haystack Строка, в которой производится поиск.
     * @param needle   Подстрока, которую нужно найти.
     * @return Индекс первого вхождения needle в haystack, или -1, если не найдено.
     */
    public int strStr(String haystack, String needle) {
        // Проверяем, не пуста ли needle.
        // Согласно математическому определению, пустая строка содержится в любой строке на позиции 0.
        // Хотя в ограничениях задачи указано needle.length >= 1, эта проверка делает код надежным.
        if (needle.isEmpty()) {return 0;}

        // Используем встроенный метод indexOf класса String.
        // Этот метод ищет первое вхождение needle в haystack.
        // Внутри JDK реализация оптимизирована (использует проверки символов и эффективные циклы),
        // что делает её быстрой на практике для заданных ограничений.
        int index = haystack.indexOf(needle);

        // indexOf возвращает -1, если подстрока не найдена,
        // что полностью соответствует требованиям задачи.
        return index;
    }

    public static void main(String[] args) {
        // Создаем экземпляр класса Solution1 для вызова метода
        Solution3 solution = new Solution3();

        // --- Пример 1 ---
        String haystack1 = "sadbutsad";
        String needle1 = "sad";
        // Вызываем метод и выводим результат
        // Ожидаемый вывод: 0
        System.out.println("Пример 1: " + solution.strStr(haystack1, needle1));

        // --- Пример 2 ---
        String haystack2 = "leetcode";
        String needle2 = "leeto";
        // Вызываем метод и выводим результат
        // Ожидаемый вывод: -1
        System.out.println("Пример 2: " + solution.strStr(haystack2, needle2));

        // --- Пример 3 (Дополнительный для проверки) ---
        String haystack3 = "hello";
        String needle3 = "ll";
        // Вызываем метод и выводим результат
        // Ожидаемый вывод: 2
        System.out.println("Пример 3: " + solution.strStr(haystack3, needle3));
    }
}
