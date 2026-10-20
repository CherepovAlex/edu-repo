package com.leetcodingquest.pipeline.Array._3886SumOfSortableIntegers;

import java.util.Arrays;

public class Solution2 {
    // время O(N log N + N * d(N)), память O(N)
    public long sumOfSortableIntegers(int[] nums) {
        int n = nums.length;
        // Создаем эталонный отсортированный массив
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        long sum = 0;

        // Перебираем все делители k числа n
        // Мы идем до sqrt(n), чтобы найти все пары делителей
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                int k1 = i;       // Первый делитель
                int k2 = n / i;   // Второй делитель (парный)

                // Проверяем k1
                if (isSortable(nums, sortedNums, k1)) {
                    sum += k1;
                }

                // Если k1 и k2 разные, проверяем k2
                if (k1 != k2) {
                    if (isSortable(nums, sortedNums, k2)) {
                        sum += k2;
                    }
                }
            }
        }

        return sum;
    }

    /**
     * Проверяет, можно ли отсортировать nums, разбив его на подмассивы длины k
     * и циклически поворачивая каждый.
     */
    private boolean isSortable(int[] nums, int[] sortedNums, int k) {
        int n = nums.length;
        int segments = n / k; // Количество сегментов

        // Проходим по каждому сегменту
        for (int i = 0; i < segments; i++) {
            // Индексы начала и конца текущего сегмента
            int start = i * k;
            int end = start + k;

            // Извлекаем сегмент из исходного массива
            int[] subNums = Arrays.copyOfRange(nums, start, end);
            // Извлекаем соответствующий сегмент из отсортированного массива
            int[] subSorted = Arrays.copyOfRange(sortedNums, start, end);

            // Проверяем, является ли subNums циклическим сдвигом subSorted
            if (!isCircularShift(subNums, subSorted)) {
                return false; // Если хотя бы один сегмент не подходит, k не сортируемый
            }
        }
        return true; // Все сегменты подходят
    }

    /**
     * Проверяет, является ли target циклическим сдвигом source.
     * Предполагается, что target уже отсортирован.
     * Сложность: O(k)
     */
    private boolean isCircularShift(int[] source, int[] target) {
        int n = source.length;
        if (n == 0) return true;

        // 1. Проверка того, что source и target содержат одни и те же элементы (перестановки).
        // Используем сумму и сумму квадратов как быстрый хеш.
        long sumSource = 0, sumSqSource = 0;
        long sumTarget = 0, sumSqTarget = 0;

        for (int i = 0; i < n; i++) {
            sumSource += source[i];
            sumSqSource += (long) source[i] * source[i];

            sumTarget += target[i];
            sumSqTarget += (long) target[i] * target[i];
        }

        if (sumSource != sumTarget || sumSqSource != sumSqTarget) {
            return false;
        }

        // 2. Проверка структуры циклического сдвига отсортированного массива.
        // Отсортированный массив имеет 0 "разрывов" (где arr[i] > arr[i+1]).
        // Циклический сдвиг отсортированного массива имеет ровно 1 разрыв
        // (или 0, если сдвиг кратен длине, т.е. массив уже отсортирован).
        // Разрыв - это место, где элемент больше следующего.

        int breaks = 0;
        for (int i = 0; i < n; i++) {
            // Сравниваем элемент с следующим, учитывая цикличность (последний с первым)
            if (source[i] > source[(i + 1) % n]) {
                breaks++;
            }
        }

        // Для корректного циклического сдвига отсортированного массива разрывов должно быть 0 или 1.
        return breaks <= 1;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2};
        System.out.println(new Solution1().sortableIntegers(nums)); // 3
        nums = new int[]{7, 6, 5};
        System.out.println(new Solution1().sortableIntegers(nums)); // 0
        nums = new int[]{5, 8};
        System.out.println(new Solution1().sortableIntegers(nums)); // 3
    }
}
