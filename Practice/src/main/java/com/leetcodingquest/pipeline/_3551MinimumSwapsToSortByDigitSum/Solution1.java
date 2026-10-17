package com.leetcodingquest.pipeline._3551MinimumSwapsToSortByDigitSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    // Копирование - O(n), Сортировка O(n log n * d), Создание HashMap O(n), Подсчет обменов O(n)
    // Время O(n log n), Память O(n)
    // Метод возвращает сумму цифр положительного числа
    private int digitSum(int num) {
        int sum = 0;            // Переменная для накопления суммы цифр

        while (num > 0) {       // Пока в числе остаются цифры.
            sum += num % 10;    // Берём последнюю цифру и добавляем к сумме
            num /= 10;          // Удаляем последнюю цифру числа
        }

        return sum;             // Возвращает вычисленную сумму цифр
    }

    // Метод вычисляет минимальное количество обменов
    public int minSwaps(int[] nums) {

        int n = nums.length;        // Сохраняем длину исходного массива

        Integer[] sorted = new Integer[n]; // Создаём массив для сортировки

        for (int i = 0; i < n; i++) {   // Перебираем все элементы в nums
            sorted[i] = nums[i];        // Копируем число в новый массив
        }

        // Сортируем: сначала по сумме цифр (возрастание), затем по значению самого числа - компаратор
        Arrays.sort(sorted, (a,b) -> {
            int sumA = digitSum(a);     // Вычисляем сумму цифр числа a
            int sumB = digitSum(b);     // Вычисляем сумму цифр числа b

            if (sumA != sumB) {         // Если суммы цифр различаются
                return sumA - sumB;     // меньшая сумма должна идти первой.
            }

            return Integer.compare(a,b); // При равных суммах сравниваем числа.
        });

        // Храним текущее положение каждого числа в изменяемом nums.
        Map<Integer, Integer> position = new HashMap<>();

        for (int i = 0; i < n; i++) {   // Перебираем исходный массив.
            position.put(nums[i], i);   // Запоминаем индекс каждого числа
        }

        int swaps = 0;      // Счётчик выполненных обменов

        for (int i = 0; i < n; i++) {   // Проверяем позиции слева направо.

            if (nums[i] == sorted[i]) { // Если число уже на нужном месте
                continue;               // переходим к следующей позиции
            }

            int target = sorted[i];         // Число, которое должно стоять здесь
            int j = position.get(target);   // Находим его текущий индекс
            int current = nums[i];          // Сохраняем число, стоящее здесь сейчас

            nums[i] = target;               // Ставим целевое число на нужное место.
            nums[j] = current;              // Перемещаем прежнее число на индекс j

            position.put(target, i);        // Обновляем позицию целевого числа
            position.put(current, j);       // Обновляем позицию второго числа

            swaps++;                        // Учитываем один выполненный обмен
        }

        return swaps;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {37, 100};
        System.out.println(new Solution1().minSwaps(nums)); // [100, 37], 1
        int[] nums1 = new int[] {18, 43, 34, 16};
        System.out.println(new Solution1().minSwaps(nums1)); // [16, 34, 43, 18], 2
        int[] nums2 = new int[] {22, 14, 33, 7};
        System.out.println(new Solution1().minSwaps(nums1)); // [22, 14, 33, 7], 0
    }
}
