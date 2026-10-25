package com.leetcodingquest.pipeline.Array._35SearchInsertPosition;

public class Solution1 {
    // время O(log n), память O(1)
    public int searchInsert(int[] nums, int target) {
        int left = 0;   // Левая граница диапазона поиска - первый индекс массива
        int right = nums.length - 1;    // Правая граница диапазона поиска - индекс последнего элемента
        // Пока в диапазон остаётся хотя бы один элемент, продолжаем поиск
        while (left <= right) {
            // Находим середину текущего диапазона поиска
            int mid = left + (right - left) / 2;    // Деление int на int отбрасывает дробную часть
            // Если средний элемент равен целевому значению
            if (nums[mid] == target) return mid;    // возвращаем индекс найденного элемента
            // Если средний элемент меньше цели,
            if (nums[mid] < target) {    // Исключаем левую половину вместе с mid
                left = mid + 1;
            } else {    // Иначе средний элемент больше цели
                right = mid - 1;    // Исключем правую половину вместе с mid
            }
        }   // Элемент не найден
        // left указывает на правильную позицию вставки
        return left;
    }

    public static void main(String[] args) {
        // Создаём объект класса, чтобы вызвать нестатический метод.
        Solution1 solution = new Solution1();

        // Пример 1: целевое число присутствует в массиве.
        int[] nums1 = {1, 3, 5, 6};
        int target1 = 5;
        System.out.println(solution.searchInsert(nums1, target1)); // 2

        // Пример 2: целевое число нужно вставить в середину.
        int[] nums2 = {1, 3, 5, 6};
        int target2 = 2;
        System.out.println(solution.searchInsert(nums2, target2)); // 1

        // Пример 3: целевое число нужно вставить в конец.
        int[] nums3 = {1, 3, 5, 6};
        int target3 = 7;
        System.out.println(solution.searchInsert(nums3, target3)); // 4
    }
}
