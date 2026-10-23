package com.leetcodingquest.pipeline.BitManipulation._3644MaximumKToSortAPermutation;

public class Solution1 {
    // время O(n), память O(1)
    // метод возвращает максимально допустимаое k
    public int sortPermutation(int[] nums) {
        int k = 0; // Здесь будет храниться общее побитовое И неправильных элементов.
        boolean first = true; // Показывает, встретили ли мы первый неправильный элемент.

        for (int i = 0; i < nums.length; i++) { // Перебираем все индексы массива.

            if (nums[i] != i) { // Проверяем, находится ли число не на своей позиции.

                if (first) { // Если это первый неправильный элемент,
                    k = nums[i]; // записываем его значение в k.
                    first = false; // Отмечаем, что первый элемент уже обработан.
                } else { // Если неправильный элемент уже встречался ранее,
                    k &= nums[i]; // оставляем в k только общие единичные биты.
                }
            }
        }
        return k; // Возвращаем максимальное допустимое k; для отсортированного массива это 0.
    }

    public static void main(String[] args) {
        int[] nums = {0, 3, 2, 1};
        System.out.println(new Solution1().sortPermutation(nums));  // 1
        int[] nums1 = {0, 1, 3, 2};
        System.out.println(new Solution1().sortPermutation(nums1)); // 2
        int[] nums2 = {3, 2, 1, 0};
        System.out.println(new Solution1().sortPermutation(nums2)); // 0
    }
}
