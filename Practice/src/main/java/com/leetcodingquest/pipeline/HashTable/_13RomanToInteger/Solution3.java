package com.leetcodingquest.pipeline.HashTable._13RomanToInteger;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    // время O(n), память O(1)
    public int romanToInt(String s) {                                    // объявляем метод, принимающий строку s и возвращающий int
        Map<Character, Integer> map = new HashMap<>();                   // создаём HashMap для быстрого доступа к значению символа (O(1))
        map.put('I', 1);                                                 // I = 1
        map.put('V', 5);                                                 // V = 5
        map.put('X', 10);                                                // X = 10
        map.put('L', 50);                                                // L = 50
        map.put('C', 100);                                               // C = 100
        map.put('D', 500);                                               // D = 500
        map.put('M', 1000);                                              // M = 1000

        int result = 0;                                                  // аккумулятор для итогового числа
        int n = s.length();                                              // длина строки (нужна для проверки границы i+1)

        for (int i = 0; i < n; i++) {                                    // проходим по каждому символу строки
            int current = map.get(s.charAt(i));                          // значение текущего символа
            if (i + 1 < n && current < map.get(s.charAt(i + 1))) {       // если есть следующий символ и текущий меньше следующего
                result -= current;                                       // вычитаем (случай IV, IX, XL, XC, CD, CM)
            } else {                                                     // иначе
                result += current;                                       // прибавляем
            }
        }

        return result;                                                   // возвращаем итоговое число
    }
}
