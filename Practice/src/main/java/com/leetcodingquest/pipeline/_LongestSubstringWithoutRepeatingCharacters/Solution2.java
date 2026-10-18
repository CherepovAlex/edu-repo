package com.leetcodingquest.pipeline._LongestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;

public class Solution2 {
    // время O(n), пространство O(n)
    // Метод возвращает длину самой длинной подстроки без повторов
    public int lengthOfLongestSubstring(String s) {
        // Если строка пустая, длина равна 0
        if (s == null || s.isEmpty()) return 0;

        // Карта для хранения последнего индекса встреченного символа
        // Ключ: символ, Значение: индекс этого символа в строке
        HashMap<Character, Integer> charIndexMap = new HashMap<>();

        // Переменная для хранения максимальной длины найденной подстроки
        int maxLength = 0;

        // Левая граница скользящего окна
        // Подстрока без повторов всегда находится в диапазоне [left, right]
        int left = 0;

        // Правая граница скользящего окна
        // Мы проходим по всей строке этим указателем
        for (int right = 0; right < s.length(); right++) {
            // Текущий символ, который мы рассматриваем
            char currentChar = s.charAt(right);

            // Если символ уже встречался и его предыдущая позиция находится внутри текущего окна (т.е. >= left),
            // значит мы нашли повтор
            // Нам нужно сдвинуть левую границу окна сразу после этого повторяющегося символа
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= left){
                // Сдвигаем левую границу на 1 позицию вправо от предыдущего вхождения
                left = charIndexMap.get(currentChar) + 1;
            }

            // Обновляем последнюю известную позицию текущего символа
            charIndexMap.put(currentChar, right);

            // Вычисляем текущую длину окна: (правая граница - левая граница + 1)
            int currentLength = right - left + 1;

            // Обновляем максимальную длину, если текущее окна длиннее
            if (currentLength > maxLength) maxLength = currentLength;
        }
        // Возвращаем результат
        return maxLength;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();

        String s1 = "abcabcbb";
        System.out.println(solution2.lengthOfLongestSubstring(s1)); // 3

        String s2 = "bbbbb";
        System.out.println(solution2.lengthOfLongestSubstring(s2)); // 1

        String s3 = "pwwkew";
        System.out.println(solution2.lengthOfLongestSubstring(s3)); // 3
    }
}
