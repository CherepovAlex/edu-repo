package com.leetcodingquest.pipeline.MidLevel._3963CreateGridWithExactlyOnePath;

import java.util.Arrays;                                             // импорт Arrays для возможного использования (не обязателен, но полезен)

public class Solution2 {                                             // объявляем класс решения

    public String[] createGrid(int m, int n) {                       // метод принимает число строк m и столбцов n
        String[] grid = new String[m];                               // создаём массив из m строк — это будет наша сетка

        if (m == 1) {                                                // если сетка состоит из одной строки
            char[] row = new char[n];                                // создаём буфер под одну строку длины n
            Arrays.fill(row, '.');                                   // заполняем её полностью свободными клетками
            grid[0] = new String(row);                               // единственная строка становится результатом
            return grid;                                             // возвращаем — единственный путь guaranteed
        }                                                            // конец обработки случая m == 1

        if (n == 1) {                                                // если сетка состоит из одного столбца
            for (int i = 0; i < m; i++) {                            // проходим по всем строкам
                grid[i] = ".";                                       // каждая строка — одна свободная клетка
            }                                                        // конец цикла
            return grid;                                             // возвращаем — путь единственный (вертикальный)
        }                                                            // конец обработки случая n == 1

        for (int i = 0; i < m; i++) {                                // проходим по всем строкам результирующей сетки
            char[] row = new char[n];                                // создаём буфер под строку i
            Arrays.fill(row, '#');                                   // по умолчанию вся строка — препятствия
            if (i == 0) {                                            // если это верхняя строка
                Arrays.fill(row, '.');                               // весь верхний ряд делаем свободным
            } else {                                                 // иначе (строка не первая)
                row[n - 1] = '.';                                    // открываем только последнюю клетку — правый столбец
            }                                                        // конец условного блока
            grid[i] = new String(row);                               // сохраняем готовую строку в сетку
        }                                                            // конец цикла по строкам

        return grid;                                                 // возвращаем построенную сетку
    }                                                                // конец метода createGrid

    public static void main(String[] args) {                         // точка входа для ручного тестирования
        Solution2 sol = new Solution2();                             // создаём экземпляр класса решения
        System.out.println(Arrays.toString(sol.createGrid(2, 3)));  // печатаем результат для примера 1
        System.out.println(Arrays.toString(sol.createGrid(3, 3)));  // печатаем результат для примера 2
        System.out.println(Arrays.toString(sol.createGrid(1, 4)));  // печатаем результат для примера 3
    }                                                                // конец main
}                                                                    // конец класса