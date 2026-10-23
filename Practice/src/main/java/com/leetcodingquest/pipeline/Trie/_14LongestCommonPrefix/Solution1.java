package com.leetcodingquest.pipeline.Trie._14LongestCommonPrefix;

public class Solution1 {
    // время  O(S), память O(1)
    // Главная идея алгоритма: сравнивать строки посимвольно, начиная с начала, и сразу возвращать результат при первом несовпадении.
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String first = strs[0]; // Берём первую строку как образец для сравнения
        for (int i = 0; i < first.length(); i++) {  // Перебираем символы первой строки слева направо
            for (int j = 1; j < strs.length; j++) { // Сравниваем текущий символ со всеми остальными строками
                if (i >= strs[j].length()){ //   Проверяем, не закончилась ли текущая строка
                    return first.substring(0, i);   // Возвращаем совпавшую часть первой строки
                }
                if (first.charAt(i) != strs[j].charAt(i)){ // Проверяем совпадают ли символы на позиции i
                    return first.substring(0, i);   // При несовпадении возвращаем общий префикс
                }
            }
        }
        return first;   // Если все символы совпали, возвращаем первую строку целиком
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(new Solution1().longestCommonPrefix(strs));
        String[] strs1 = {"dog","racecar","car"};
        System.out.println(new Solution1().longestCommonPrefix(strs1));
    }
}
