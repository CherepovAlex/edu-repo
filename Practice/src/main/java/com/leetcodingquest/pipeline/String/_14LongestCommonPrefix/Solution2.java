package com.leetcodingquest.pipeline.String._14LongestCommonPrefix;

public class Solution2 {
    // время  O(S), память O(1)
    public String longestCommonPrefix(String[] strs) {
        // Если массив пустой, префикса нет
        if (strs == null || strs.length == 0) return "";
        // Начинаем с первой строки как предполагаемого префикса
        String prefix = strs[0];
        // Проходим по всем остальным строкам в массиве
        for (int i = 1; i < strs.length; i++) {
            // Пока текущая строка (strs[i]) не начинается с текущего префикса,
            // мы укорачиваем префикс, убирая последний символ.
            // Это ключевой момент: мы не сравниваем посимвольно вручную,
            // а используем встроенный метод startsWith, который делает это эффективно.
            while (!strs[i].startsWith(prefix)) {
                // Укорачиваем префикс на 1 символ
                prefix = prefix.substring(0, prefix.length() - 1);

                // Если префикс стал пустым, значит общего префикса нет
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        // Если цикл завершился успешно, prefix содержит самый длинный общий префикс
        return prefix;
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(new Solution2().longestCommonPrefix(strs));
        String[] strs1 = {"dog", "racecar", "car"};
        System.out.println(new Solution2().longestCommonPrefix(strs1));
    }
}
