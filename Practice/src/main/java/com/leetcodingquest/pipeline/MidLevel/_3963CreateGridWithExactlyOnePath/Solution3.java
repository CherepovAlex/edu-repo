package com.leetcodingquest.pipeline.MidLevel._3963CreateGridWithExactlyOnePath;

public class Solution3 {
    public String[] createGrid(int m, int n) {
        // Создаем массив строк для сетки
        String[] grid = new String[m];

        // Заполняем сетку препятствиями '#'
        // Но мы будем строить строки посимвольно

        // Для первой строки: все символы '.', кроме последнего, если n > 1?
        // Нет, по нашей логике:
        // Первая строка: все '.' (от 0 до n-1)
        // Остальные строки: все '#' кроме последнего символа (n-1), который '.'

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    // Первая строка: все свободные клетки
                    row[j] = '.';
                } else {
                    // Остальные строки: свободна только последняя клетка
                    if (j == n - 1) {
                        row[j] = '.';
                    } else {
                        row[j] = '#';
                    }
                }
            }
            grid[i] = new String(row);
        }

        return grid;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();

        // Пример 1
        int m1 = 2, n1 = 3;
        String[] result1 = solution.createGrid(m1, n1);
        System.out.println("Пример 1:");
        for (String row : result1) {
            System.out.println(row);
        }

        // Пример 2
        int m2 = 3, n2 = 3;
        String[] result2 = solution.createGrid(m2, n2);
        System.out.println("\nПример 2:");
        for (String row : result2) {
            System.out.println(row);
        }

        // Пример 3
        int m3 = 1, n3 = 4;
        String[] result3 = solution.createGrid(m3, n3);
        System.out.println("\nПример 3:");
        for (String row : result3) {
            System.out.println(row);
        }
    }
}