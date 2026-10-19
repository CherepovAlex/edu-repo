package com.leetcodingquest.pipeline._4MedianOfTwoSortedArrays;

public class Solution1 {
    // время O(log(min(m, n)), память O(1)
    // Метод возвращает медиану двух отсортированных массивов
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Проверяем, не длиннее ли первый массив второго
        if (nums1.length > nums2.length) {
            // Меняем массивы местами, чтобы бинарный поиск выполнялся по более короткому массиву
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length; // Сохраняем длину первого массива
        int n = nums2.length; // Сохраняем длину второго массива
        // Определяем границы бинарнорного поиска
        int left = 0;   // Минимально возможное число элементов nums1 в левой части
        int right = m;  // Максимально возможное число элементов nums1 в левой части
        int half = (m + n + 1) / 2;     // Количество элементов, которые должны находиться в левой части
        // +1 нужно - при нечётном общем количестве элементов слева было на один элемент больше
        // Продолжаем бинарный поиск, пока даипазон возможных разделений не опустеет
        while (left <= right) {
            int i = left + (right - left) / 2; // Выбираем середину диапазона как позицию разделения nums1
            int j = half - i;           // Вычисляем позицию разделения nums2, чтобы размер левой части был правильным

            // Последний элемент слева в nums1 или минус бесконечность, если слева элементов нет
            int left1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            // Первый элемент справа в nums1 или плюс бесконечность, если справа элементов нет
            int right1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            // Последний элемент слева в nums2 или минус бесконечность, если слева элементов нет
            int left2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            // Первый элемент справа в nums2 или плюс бесконечность, если справа элементов нет
            int right2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // Проверяем, что все элементы левой части не больше элементов правой части
            if (left1 <= right2 && left2 <= right1) {
                if ((m + n) % 2 == 1) {  // Проверяем, нечётно ли общее количество элементов
                    return Math.max(left1, left2);  // Для нечётной длины, медиана - максимальный элементо левой части
                }
                int maxLeft = Math.max(left1, left2);     // Находим максимальный элемент среди элементов слева
                int minRight = Math.min(right1, right2);  // Находим минимальный элемент среди элементов справа
                // Для чётной длины возвращает среднее двух центральных элементов как double
                return (maxLeft + minRight) / 2.0;
            } else if (left1 > right2) {
                // Проверяем, не оказался ли левый элемент nums1 больше правого элемента nums2
                right = i - 1;  // Если да, то уменьшаем число элементов nums1 в левой части
            } else {
                // Иначе левый элементв nums2 больше правого элемента nums1
                left = i + 1;   // Увеличиваем число элементов nums1 в левой части
            }
        }
        // Защитный слйчай: при корректных отсортированных входных данных сюда попасть нельзя
        throw new IllegalArgumentException("Arrays should be sorted");
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(new Solution1().findMedianSortedArrays(nums1, nums2)); // 2.0

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        System.out.println(new Solution1().findMedianSortedArrays(nums1, nums2)); // 2.5

        nums1 = new int[]{1};
        nums2 = new int[]{};
        System.out.println(new Solution1().findMedianSortedArrays(nums1, nums2)); // 1.0
    }
}
