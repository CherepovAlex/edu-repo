package com.leetcodingquest.pipeline.Staff._3942MinimumOperationsToSortAPermutation;

public class Solution3 {
    public int sortPermutation(int[] nums) {
        int n = nums.length;

        // Вариант 1: Без реверса. Проверяем, является ли nums циклическим сдвигом отсортированного массива.
        int shiftsWithoutReverse = checkCyclicShift(nums, n);

        // Вариант 2: С реверсом. Реверсируем массив и проверяем его.
        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = nums[n - 1 - i];
        }
        int shiftsWithReverse = checkCyclicShift(reversed, n);

        // Если ни один вариант не дал результата, возвращаем -1
        if (shiftsWithoutReverse == -1 && shiftsWithReverse == -1) {
            return -1;
        }

        // Если один из вариантов не подошел, берем другой
        if (shiftsWithoutReverse == -1) {
            return 1 + shiftsWithReverse; // +1 за операцию реверса
        }
        if (shiftsWithReverse == -1) {
            return shiftsWithoutReverse;
        }

        // Если оба подошли, берем минимальное количество операций
        return Math.min(shiftsWithoutReverse, 1 + shiftsWithReverse);
    }

    // Метод проверяет, является ли массив циклическим сдвигом отсортированного [0, 1, ..., n-1]
    // Если да, возвращает количество сдвигов влево, необходимых для получения отсортированного массива.
    // Если нет, возвращает -1.
    private int checkCyclicShift(int[] arr, int n) {
        // Находим индекс элемента 0
        int zeroIndex = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                zeroIndex = i;
                break;
            }
        }

        // Если 0 не найден (что невозможно для перестановки 0..n-1), но на всякий случай
        if (zeroIndex == -1) {
            return -1;
        }

        // Проверяем, идет ли последовательность 0, 1, 2, ... после нуля
        // Количество сдвигов, чтобы 0 оказался на нулевой позиции, равно zeroIndex
        int shifts = zeroIndex;

        for (int i = 0; i < n; i++) {
            // Текущий элемент должен быть равен i
            // Индекс в массиве, где должен стоять элемент i, это (zeroIndex + i) % n
            int expectedIndex = (zeroIndex + i) % n;
            if (arr[expectedIndex] != i) {
                // Нарушение порядка, массив не является циклическим сдвигом отсортированного
                return -1;
            }
        }

        // Если проверка прошла успешно, возвращаем количество сдвигов
        return shifts;
    }
}
