package com.leetcodingquest.pipeline.String._3863MinimumOperationsToSortAString;

public class Solution {
    // время O(n), память O(1)
    // возвращаем минимальное количество операций
    public int minOperations(String s){
        int n = s.length(); // Получаем длину строки
        char minChar = s.charAt(0); // Считаем первый символ минимальным
        char maxChar = s.charAt(0); // Считаем первый символ максимальным
        boolean isSorted = true;    // Предполагаем, что строка уже отсортирована

        for (int i = 1; i < n; i++) {   // Перебираем все символы со второго до последнего
            char current = s.charAt(i); // Получаем текущий символ

            if (current < minChar){   // Проверяем, меньше ли он текущего сивола
                minChar = current;  // Обновляем минимальный символ
            }

            if (current > maxChar){ // Проверяем, больше ли он текущего символа
                maxChar = current;  // Обновляем максимальный символ
            }

            if (current < s.charAt(i - 1)){ // Проверяем нарушение порядка соседних символов
                isSorted = false;   // Запоминаем, что строка не отсортирована
            }
        }

        if (isSorted) {  //  Проверяем, отсортирована ли вся строка
            return 0;   // Если да, операции не нужны
        }

        if (n == 2){    // Проверяем особый случай строки из двух символов
            return -1;  // Нельзя отсортировать всю строку одной операцией
        }

        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar){
            return 1;   // Правильный край позволяет отсортировать строку за одну операцию
        }

        for (int i = 1; i < n - 1; i++) {   // Проверяем только внутренние символы
            char current = s.charAt(i); // Получаем очередной внутренний символ

            if (current == minChar || current == maxChar){
                return 2;   // Внутренний минимум или позволяет обойтись двумя операциями
            }
        }
        return 3;   // В остальных случаях для сортировки нужны три операции
    }

    public static void main(String[] args) {
        String s = "dog";
        System.out.println(new Solution().minOperations(s)); // 1
        s = "card";
        System.out.println(new Solution().minOperations(s)); // 2
        s = "gf";
        System.out.println(new Solution().minOperations(s)); // -1
        s = "edc";
        System.out.println(new Solution().minOperations(s)); // 3
    }
}
