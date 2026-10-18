package com.leetcodingquest.pipeline._LongestSubstringWithoutRepeatingCharacters;

public class Solution1 {
    // время O(n), пространство O(n)
    // Метод возвращает длину самой длинной подстроки без повторов
    public int lengthOfLongestSubstring(String s) {
        // Для каждого ASCII-символа храним индекс последнего появления плюс один
        int[] lastSeen = new int[128];      // создаём массив
        // Левая граница текущего окна
        int left = 0;
        // Максимальная длина подстроки, найденная на данный момент
        int maxLength = 0;
        // Перебираем строку слева направо
        for (int right = 0; right < s.length(); right++) {
            // Получаем символ на текущей позиции right
            char ch = s.charAt(right);
            // Если символ повторился в окне, сдвигает left за его прошлое появление
            left = Math.max(left, lastSeen[ch]);
            // Запоминаем новую позицию символа в формате индекс плюс один
            lastSeen[ch] = right + 1;
            // Вычисляем длину текущего окна
            int currentLength = right - left + 1;
            // Обновляем максимальную длину, если текущее окно длиннее (сравниваем с лучшим старым)
            maxLength = Math.max(maxLength, currentLength);
        }
        // Возвращаем длину самой длинной подстроки без повторяющихся символов
        return maxLength;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();

        String s1 = "abcabcbb";
        System.out.println(solution1.lengthOfLongestSubstring(s1)); // 3

        String s2 = "bbbbb";
        System.out.println(solution1.lengthOfLongestSubstring(s2)); // 1

        String s3 = "pwwkew";
        System.out.println(solution1.lengthOfLongestSubstring(s3)); // 3
    }
}
