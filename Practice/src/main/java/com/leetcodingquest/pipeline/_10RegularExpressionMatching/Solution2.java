package com.leetcodingquest.pipeline._10RegularExpressionMatching;

public class Solution2 {
    // время O(m * n), память O(m * n)
    // Динамическое программирование (Dynamic Programming)

    public boolean isMatch(String s, String p) {                  // объявляем метод: принимает строку и шаблон, возвращает boolean
        int n = s.length();                                       // n — длина входной строки s
        int m = p.length();                                       // m — длина шаблона p

        boolean[][] dp = new boolean[n + 1][m + 1];               // создаём таблицу dp размера (n+1) x (m+1), по умолчанию все false

        dp[0][0] = true;                                          // база: пустая строка соответствует пустому шаблону

        for (int j = 1; j <= m; j++) {                            // проходим по всем столбцам первой строки таблицы (i = 0)
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {         // если текущий символ шаблона — '*' и до пары "x*" пустая строка уже матчилась
                dp[0][j] = true;                                  // то "x*" может съесть ноль вхождений — пустая строка матчится
            }                                                     // иначе dp[0][j] остаётся false
        }

        for (int i = 1; i <= n; i++) {                            // внешний цикл по символам строки s (i = 1..n)
            for (int j = 1; j <= m; j++) {                        // внутренний цикл по символам шаблона p (j = 1..m)
                char pc = p.charAt(j - 1);                        // текущий символ шаблона p[j-1]

                if (pc == '*') {                                  // случай 1: текущий символ шаблона — звёздочка
                    boolean zero = dp[i][j - 2];                  // вариант A: '*' означает ноль вхождений предыдущего элемента — «откатываемся» на 2 позиции в шаблоне

                    char prev = p.charAt(j - 2);                  // символ, к которому применяется '*'
                    boolean oneOrMore = dp[i - 1][j] &&           // вариант B: '*'+prev сработал хотя бы раз — берём dp[i-1][j] (строка без последнего символа уже матчится с этим же шаблоном)
                            (s.charAt(i - 1) == prev || prev == '.'); // и последний символ строки совпадает с prev (или prev — точка)

                    dp[i][j] = zero || oneOrMore;                 // dp[i][j] истинно, если сработал любой из вариантов
                } else {                                          // случай 2: текущий символ шаблона — обычный символ или '.'
                    boolean matchChar = (s.charAt(i - 1) == pc || pc == '.'); // проверяем: последний символ строки совпадает с p[j-1] (или p[j-1] — точка)
                    dp[i][j] = dp[i - 1][j - 1] && matchChar;     // dp[i][j] истинно, если предыдущие префиксы матчились И текущие символы совпали
                }
            }
        }

        return dp[n][m];                                          // ответ — соответствует ли вся строка всему шаблону
    }

    public static void main(String[] args) {
        String s = "aa", p = "a";
        System.out.println(new Solution2().isMatch(s, p));   // false
        String s1 = "aa", p1 = "a*";
        System.out.println(new Solution2().isMatch(s1, p1)); // true
        String s2 = "ab", p2 = ".*";
        System.out.println(new Solution2().isMatch(s2, p2)); // true
    }
}
