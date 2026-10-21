package com.leetcodingquest.pipeline._10RegularExpressionMatching;

public class Solution {
    // время O(m * n), память O(n)
    // Динамическое программирование (Dynamic Programming)
    public boolean isMatch(String s, String p) { // Проверяем, соответствует ли вся строка шаблону.
        int m = s.length(); // Сохраняем длину входной строки.
        int n = p.length(); // Сохраняем длину шаблона.

        boolean[] dp = new boolean[n + 1]; // dp[j] хранит результат для текущего префикса строки и первых j символов шаблона.
        dp[0] = true; // Пустая строка соответствует пустому шаблону.

        for (int j = 2; j <= n; j++) { // Проверяем шаблоны, которые могут соответствовать пустой строке.
            if (p.charAt(j - 1) == '*') { // Если текущий символ шаблона — звёздочка.
                dp[j] = dp[j - 2]; // Учитываем случай, когда элемент перед звёздочкой повторяется ноль раз.
            }
        }

        for (int i = 1; i <= m; i++) { // Последовательно добавляем символы входной строки.
            boolean diagonal = dp[0]; // Сохраняем старое значение для пустого шаблона и предыдущего префикса строки.
            dp[0] = false; // Непустая строка не может соответствовать пустому шаблону.

            for (int j = 1; j <= n; j++) { // Перебираем все непустые префиксы шаблона.
                boolean up = dp[j]; // Сохраняем значение предыдущей строки DP до его перезаписи.

                char currentPattern = p.charAt(j - 1); // Получаем текущий символ шаблона.
                char currentString = s.charAt(i - 1); // Получаем текущий символ входной строки.

                if (currentPattern == '*') { // Если шаблон содержит звёздочку.
                    char previousPattern = p.charAt(j - 2); // Получаем элемент, который может повторяться.

                    boolean matches = previousPattern == '.' // Проверяем, подходит ли точка к любому символу.
                            || previousPattern == currentString; // Либо проверяем совпадение обычных символов.

                    dp[j] = dp[j - 2] // Вариант: использовать предыдущий элемент ноль раз.
                            || (matches && up); // Вариант: использовать его ещё раз, если символы совпадают.
                } else { // Если текущий символ шаблона не является звёздочкой.
                    boolean matches = currentPattern == '.' // Точка подходит к любому одному символу.
                            || currentPattern == currentString; // Обычные символы должны совпадать.

                    dp[j] = matches && diagonal; // Учитываем совпадение символов и предыдущих префиксов.
                }

                diagonal = up; // Подготавливаем диагональное значение для следующей итерации.
            }
        }
        return dp[n]; // Возвращаем результат сопоставления всей строки со всем шаблоном.
    }

    public static void main(String[] args) {
        String s = "aa", p = "a";
        System.out.println(new Solution().isMatch(s, p));   // false
        String s1 = "aa", p1 = "a*";
        System.out.println(new Solution().isMatch(s1, p1)); // true
        String s2 = "ab", p2 = ".*";
        System.out.println(new Solution().isMatch(s2, p2)); // true
    }
}
