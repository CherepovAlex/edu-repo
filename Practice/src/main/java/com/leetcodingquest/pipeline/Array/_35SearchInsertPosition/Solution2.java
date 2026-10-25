package com.leetcodingquest.pipeline.Array._35SearchInsertPosition;

public class Solution2 {
    // Метод, который решает задачу: возвращает индекс target или позицию вставки
    public int searchInsert(int[] nums, int target) {
        // Левый указатель — начало рассматриваемого диапазона массива
        int left = 0;
        // Правый указатель — конец рассматриваемого диапазона массива (включительно)
        int right = nums.length - 1;

        // Пока диапазон не сузился до пустого (left <= right означает, что ещё есть что смотреть)
        while (left <= right) {
            // Вычисляем середину так, чтобы избежать переполнения int:
            // left + (right - left) / 2 эквивалентно (left + right) / 2, но безопаснее
            int mid = left + (right - left) / 2;

            // Если элемент в середине равен target — нашли, возвращаем его индекс
            if (nums[mid] == target) {
                return mid;
            // Если элемент в середине меньше target — искомое значение находится правее
            } else if (nums[mid] < target) {
                // Сужаем диапазон, сдвигая левую границу за середину
                left = mid + 1;
            // Иначе элемент в середине больше target — искомое значение левее
            } else {
                // Сужаем диапазон, сдвигая правую границу перед серединой
                right = mid - 1;
            }
        }
        // Цикл завершился: left > right.
        // В этот момент left указывает на позицию, куда нужно вставить target,
        // чтобы сохранить отсортированный порядок. Возвращаем её.
        return left;
    }

    // Точка входа для локального запуска и проверки примеров
    public static void main(String[] args) {
        // Создаём экземпляр класса, чтобы вызывать нестатический метод searchInsert
        Solution2 solution = new Solution2();

        // Пример 1: nums = [1,3,5,6], target = 5 — ожидаем 2
        int[] nums1 = {1, 3, 5, 6};
        // Вызываем метод и печатаем результат
        System.out.println(solution.searchInsert(nums1, 5)); // 2

        // Пример 2: nums = [1,3,5,6], target = 2 — ожидаем 1
        int[] nums2 = {1, 3, 5, 6};
        // Вызываем метод и печатаем результат
        System.out.println(solution.searchInsert(nums2, 2)); // 1

        // Пример 3: nums = [1,3,5,6], target = 7 — ожидаем 4
        int[] nums3 = {1, 3, 5, 6};
        // Вызываем метод и печатаем результат
        System.out.println(solution.searchInsert(nums3, 7)); // 4
    }
}
