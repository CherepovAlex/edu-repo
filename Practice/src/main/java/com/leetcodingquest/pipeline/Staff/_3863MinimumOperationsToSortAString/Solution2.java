package com.leetcodingquest.pipeline.Staff._3863MinimumOperationsToSortAString;

public class Solution2 {
    // время O(n), память O(1)
    // возвращаем минимальное количество операций
    public int minOperations(String s){
        char[] cs = s.toCharArray();    // Преобразуем строку в массив символов для посимвольного доступа
        int n = cs.length;  // Получаем длину строки

        boolean sorted = true;  // Флаг: отсортирована ли строка
        for (int i = 1; i < n; i++) {   // Проходим со второго символа
            if (cs[i] < cs[i - 1]){     // Если текущий символ меньше предыдущего
                sorted = false;         // Значит, строка не отсортирована по неубыванию
                break;                  // Прерываем цикл досрочно
            }
        }
        if (sorted) return 0;           // Если строка отсортирована, то опрации не нужны
        if (n == 2) return -1;          // Длина 2 и строка не отсортирована. Невозможно поменять символы места: -1
        char mn = cs[0], mx = cs[0];    // Инициализируем минимум и максимум первым символом
        for (int i = 1; i < n; i++) {   // Проходим по всему массиву в поисках глобальных экстремумов
            mn = (char) Math.min(mn, cs[i]);    // Обновляем минимум
            mx = (char) Math.max(mx, cs[i]);    // Обновляем максимум
        }
        if (cs[0] == mn || cs[n-1] == mx)   // Если первый символ - минимум, или последний - максимум
            return 1;       // Достаточно один раз отсортировать оставшуюся часть

        for (int i = 1; i < n - 1; i++) {   // Проверяем среднюю часть (без первого и последнего)
            if (cs[i] == mn || cs[i] == mx) // Минимум или максимум встречаются в середине
                return 2;       // Одно действие - перенести к краю, вторе - отсортировать остаток
        }
        return 3;   // Худший случай: минимум в конце, максимум - в начале - нужно 3 операции
    }

    public static void main(String[] args) {
        String s = "dog";
        System.out.println(new Solution2().minOperations(s)); // 1
        s = "card";
        System.out.println(new Solution2().minOperations(s)); // 2
        s = "gf";
        System.out.println(new Solution2().minOperations(s)); // -1
        s = "edc";
        System.out.println(new Solution2().minOperations(s)); // 3
    }
}
