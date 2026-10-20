package com.leetcodingquest.pipeline.Sorting._3886SumOfSortableIntegers;

public class Solution1 {
    // время O(n*d(n)), d(n) - количество положительных делителей, память O(1)
    // Проверять нужно только делители длины массива.
    // 1. Один циклический спуск означает, что блок можно повернуть в неубывающий порядок.
    // 2. Проверка границ блоков гарантирует, что после их независимой сортировки весь массив будет отсортирован.
    // 3. Ответ — сумма всех значений k, прошедших обе проверки.
    // 4. Метод возвращает сумму всех подходящих k
    public int sortableIntegers(int[] nums) {
        int n = nums.length;    // Сохраняем длину исходного массива
        int answer = 0;        // Используем long, чтобы безопасно хранить сумму

        for (int k = 1; k <= n; k++) {  // Перебираем возможные длины блоков
            if (n % k != 0) {   // Проверяем, делится ли n на k без остатка
                continue;       // Если нет, k не подходит по условию задачи
            }

            boolean valid = true;   // Предполагаем, что k подоходит
            int previousMax = -1;   // Максимум предыдущего блока; nums[i] >= 1

            for (int start = 0; start < n; start += k) {    // Перебираем блоки
                int end = start + k;    // Индекс сразу после последнего блока
                int minValue = nums[start]; // Минимум текущего блока
                int maxValue = nums[start]; // Максимум текущего блока
                int descents = 0;   // Количество циклических спусков в блоке

                for (int i = start; i < end; i++) { // Просматриваем текущий блок
                    minValue = Math.min(minValue, nums[i]); // Обновляем минимум
                    maxValue = Math.max(maxValue, nums[i]); // Обновляем максимум

                    int next = (i + 1 == end) ? start : i + 1;    // После конца блока возвращаемся к его началу
                    if (nums[i] > nums[next]){  // Проверяем, есть ли спуск к следующему элементу
                        descents++;     // Увеличиваем число спусков
                    }
                }
                if (descents > 1) { // Более одного циклического спуска означает, что блок не подходит
                    valid = false; // Помечаем k как неподходщее
                    break;      // Нет смысла проверять остальные блоки
                }
                if (start > 0 && previousMax > minValue){   // Проверяем порядок между блоками
                    valid = false;  // Блоки нельзя объединять в неубывающий массив
                    break;      // Прекращаем проверку этого k
                }
                previousMax = maxValue; // Запоминаем максимум текущего блока
            }
            if (valid) {    // Если все блоки прошли обе проверки
                answer += k;    // Добавляем подходящее значение k к сумме
            }
        }
        return answer;  // Возвращаем итоговую сумму
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
