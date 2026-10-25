package com.leetcodingquest.pipeline.BinarySearch._35SearchInsertPosition;

public class Solution3 {
    /**
     * Находит индекс, на который нужно вставить target в отсортированный массив nums.
     * Если target уже есть в массиве, возвращает его индекс.
     * Сложность: O(log n) по времени, O(1) по памяти.
     */
    public int searchInsert(int[] nums, int target) {
        // Инициализируем левый указатель на начало массива (индекс 0)
        int left = 0;
        // Инициализируем правый указатель на конец массива (последний индекс)
        int right = nums.length - 1;

        // Цикл выполняется, пока левый указатель не станет больше правого
        while (left <= right) {
            // Находим середину диапазона.
            // Использование (left + right) / 2 может вызвать переполнение int,
            // поэтому лучше использовать left + (right - left) / 2.
            int mid = left + (right - left) / 2;

            // Если элемент в середине равен целевому, мы нашли его!
            if (nums[mid] == target) {
                return mid; // Возвращаем индекс найденного элемента
            }
            // Если элемент в середине меньше целевого, ищем в правой половине
            else if (nums[mid] < target) {
                left = mid + 1; // Сдвигаем левую границу вправо от mid
            }
            // Если элемент в середине больше целевого, ищем в левой половине
            else {
                right = mid - 1; // Сдвигаем правую границу влево от mid
            }
        }

        // Если цикл завершился, значит target не найден.
        // В этот момент left указывает на позицию вставки.
        // Почему left? Потому что когда цикл останавливается, left > right.
        // left всегда указывает на первую позицию, где элемент > target.
        return left;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();

        // Пример 1
        int[] nums1 = {1, 3, 5, 6};
        int target1 = 5;
        System.out.println("Пример 1: " + solution.searchInsert(nums1, target1)); // Ожидается: 2

        // Пример 2
        int[] nums2 = {1, 3, 5, 6};
        int target2 = 2;
        System.out.println("Пример 2: " + solution.searchInsert(nums2, target2)); // Ожидается: 1

        // Пример 3
        int[] nums3 = {1, 3, 5, 6};
        int target3 = 7;
        System.out.println("Пример 3: " + solution.searchInsert(nums3, target3)); // Ожидается: 4
    }
}