package com.leetcodingquest.pipeline.String._6ZigzagConversion;

public class Solution1{
    // время O(n), память O(n+r)
    public String convert(String s, int numRows) {
        // Если количество строк равно 1, зигзаг не формируется, возвращаем исходную строку.
        // Это базовый случай, который ускоряет выполнение.
        if (numRows == 1) return s;
        // Создаем массив StringBuilder для каждой строки зигзага.
        // Каждый элемент массива будет хранить символы, принадлежащие одной строке.
        StringBuilder[] rows = new StringBuilder[numRows];
        // Инициализируем каждый StringBuilder в массиве.
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        // Текущий индекс строки, в которую мы записываем символ.
        int currentRow = 0;
        // Флаг направления движения: true - движемся вниз, false - вверх.
        boolean goingDown = false;
        // Проходим по каждому символу входной строки s.
        for (char c : s.toCharArray()){
            // Добавляем текущий символ в соответствующую строку зигзага.
            rows[currentRow].append(c);
            // Если мы находимся в первой строке (0) или в последней строке (numRows - 1),
            // нужно сменить направление движения.
            if (currentRow ==0 || currentRow == numRows - 1) {
                goingDown = !goingDown; // Меняем направление на противоположное
            }
            // Обновляем текущую строку в зависимости от направления.
            // Если идем вниз, увеличиваем индекс строки. Если идем вверх, уменьшаем индекс строки.
            currentRow += goingDown ? 1 : -1;
        }
        // Создаем итоговый StringBuilder для объединения всех строк.
        StringBuilder result = new StringBuilder();
        // Проходим по всем строкам зигзага и добавляем их содержимое к результату.
        for (StringBuilder row : rows) {
            result.append(row);
        }
        // Преобразуем итоговый StringBuilder в строку и возвращаем.
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
