package com.leetcodingquest.pipeline.MidLevel._3963CreateGridWithExactlyOnePath;

import java.util.Arrays;

public class Solution1 {
    // время O(m*n), память O(n)
    // Метод создаём сетку с ровно одним допустимым путём
    public String[] createGrid(int m, int n) {
        // Создаём массив строк для хранения всей сетки
        String[] grid = new String[m];
        // Перебираем все строки сетки: от 0 до m - 1
        for (int i = 0; i < m; i++) {
            // Создаём массив символов для текущей строки
            char[] row = new char[n];
            // Заполняем текущу строки препятствиями '#'
            Arrays.fill(row, '#');
            // Если это первая строка, открываем её целиком
            if (i == 0) Arrays.fill(row, '.');
            // Открыавем ячейку каждой строки. Так образуется свободный последний столбец
            row[n - 1] = '.';
            // Преобразуем массив символов в строку и сохраняем строку в результирующем массиве
            grid[i] = new String(row);
        }
        // Возвращаем готовую строку
        return grid;
    }

    public static void main(String[] args) {

        // Создаём объект класса Solution1.
        Solution1 solution = new Solution1();

        // Проверяем первый пример: 2 строки, 3 столбца.
        String[] result1 = solution.createGrid(2, 3);

        // Выводим заголовок первого примера.
        System.out.println("Пример 1:");

        // Выводим каждую строку первой сетки.
        for (String row : result1) {
            System.out.println(row);
        }

        // Проверяем второй пример: 3 строки, 3 столбца.
        String[] result2 = solution.createGrid(3, 3);

        // Выводим заголовок второго примера.
        System.out.println("\nПример 2:");

        // Выводим каждую строку второй сетки.
        for (String row : result2) {
            System.out.println(row);
        }

        // Проверяем третий пример: 1 строка, 4 столбца.
        String[] result3 = solution.createGrid(1, 4);

        // Выводим заголовок третьего примера.
        System.out.println("\nПример 3:");

        // Выводим каждую строку третьей сетки.
        for (String row : result3) {
            System.out.println(row);
        }
    }
}
