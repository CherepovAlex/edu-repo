package com.leetcodingquest.pipeline.String._6ZigzagConversion;

public class Solution {
    // время O(n), память O(n+r)
    public String convert(String s, int numRows) {
        // Если строка одна или строк меньше, чем символов в строке, зигзаг не нужен: порядок сиволов не изменится
        if (numRows == 1 || numRows >= s.length()) return s;
        // Создаём массив для хранения символов каждой строки зигзага
        StringBuilder[] rows = new StringBuilder[numRows];
        // Создаём массив для хранения символов каждой строки зигзага
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        // Начинаем размещать символы с первой строки (индекс 0)
        int currentRow = 0;
        // Первоначально движемся вниз по строкам
        int direction = 1;
        // Последовательно обрабатываем каждый символ исходной строки
        for (int i = 0; i < s.length(); i++) {
            // Добавляем текущий символ в выбранную строку зигзага
            rows[currentRow].append(s.charAt(i));
            // Если достигли верхней или нижней границы, меняем направление
            if (currentRow == 0) {
                direction = 1;
            } else if (currentRow == numRows - 1) { // нижняя строка
                direction = -1;
            }
            // Переходим к следующей строке с учетом направления движения
            currentRow += direction;
        }
        // Создаём объект для формирования итоговой строки
        StringBuilder result = new StringBuilder();
        // Обходим объект для формирования итоговой строки
        for (int i = 0; i < numRows; i++) {
            // Приписываем содержимое текущей строки к результату
            result.append(rows[i]);
        }
        // Возвращаем полученную строку без разделителей
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING"; int n = 3;
        System.out.println(new Solution().convert(s, n)); // PAHNAPLSIIGYIR
               s = "PAYPALISHIRING"; n = 4;
        System.out.println(new Solution().convert(s, n)); // PINALSIGYAHRPI
               s = "A"; n = 1;
        System.out.println(new Solution().convert(s, n)); // A
    }
}
