package com.leetcodingquest.pipeline.TwoPointers._26RemoveDuplicatesFromSortedArray;

public class Solution {
    // время O(n), память O(1)
    public int removeDuplicates(int[] nums) {
        // Если массив пустой, уникальных элементов нет о условию массив не пустой, но проверка полезна
        if (nums.length == 0) return 0;

        // Индекс последнего уникального элемента. Первый элемент массива всегда считаем уникальным
        int i = 0;

        // Перебираем массив со второго элемента до конца
        for (int j = 1; j < nums.length; j++) {
            // Если текущий элемент отличается от последнего найденного уникального элемента, найдено новое число
            if (nums[j] != nums[i]) {
                // Перемещаем указатель уникальных элементов на следующую позицию
                i++;
                // Записываем новое уникальное число в следующую свободную позицию
                nums[i] = nums[j];
            }
        }
        // Индекс последнего уникального элемента начинается с 0, поэтому количество элементов равно i + 1
        return i + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // Пример 1: массив с одним повторяющимся числом.
        int[] nums1 = {1, 1, 2};
        int k1 = solution.removeDuplicates(nums1);
        System.out.println("Пример 1: k = " + k1 + ", nums = [");
        for (int i = 0; i < k1; i++) {
            System.out.println(nums1[i]);
            if (i < k1 - 1) System.out.println(", ");
        }
        System.out.println("]");
        // Пример 2: массив с несколькими группами дубликатов.
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        // Удаляем дубликаты и получаем количество уникальных чисел.
        int k2 = solution.removeDuplicates(nums2);

        // Выводим количество и первые k2 элементов.
        System.out.print("Пример 2: k = " + k2 + ", nums = [");
        for (int i = 0; i < k2; i++) {
            System.out.print(nums2[i]);
            if (i < k2 - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        // Пример 3: все элементы одинаковые.
        int[] nums3 = {5, 5, 5, 5};

        // Удаляем дубликаты и получаем количество уникальных чисел.
        int k3 = solution.removeDuplicates(nums3);

        // Выводим количество и первые k3 элементов.
        System.out.print("Пример 3: k = " + k3 + ", nums = [");
        for (int i = 0; i < k3; i++) {
            System.out.print(nums3[i]);
            if (i < k3 - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
