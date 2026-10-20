package com.leetcodingquest.pipeline.MidLevel._4MedianOfTwoSortedArrays;

public class Solution2 {
    // время O(log(min(m,n)), память O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Убедимся, что nums1 - это меньший массив, это критично для обеспечения O(log(min(m,n))
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length; int n = nums2.length;
        // Диапазон для бинарного поиска индекса разбиения в nums1
        int low = 0; int high = m;

        while (low <= high) {
            // i - количество элементов в левой части из nums1
            // Мы берём половину от текущего диапазона, чтобы избежать переполнения int
            int partitionNums1 = (low + high) / 2;
            // j - количество элементов в левой части из nums2
            // Общее количество элементов в левой части должно быть (m + n + 1) /2
            int partitionNums2 = (m + n + 1) / 2 - partitionNums1;
            // Если partitionNums1 == 0, значит в левой части нет элементов из nums1
            // maxLeftNums1 должен быть "минус бесконечность", чтобы он не влиял на сравнение
            double maxLeftNums1 = (partitionNums1 == 0) ? Integer.MIN_VALUE : nums1[partitionNums1 - 1];
            // Если partitionNums1 == m, значит все элементы nums1 в левой части.
            // minRightNums1 должен быть "плюс бесконечность", чтобы он не влиял на сравнение
            double minRightNums1 = (partitionNums1 == m) ? Integer.MAX_VALUE : nums1[partitionNums1];
            // Аналогично для nums2
            double maxLeftNums2 = (partitionNums2 == 0) ? Integer.MIN_VALUE : nums2[partitionNums2 - 1];
            double minRightNums2 = (partitionNums2 == n) ? Integer.MAX_VALUE: nums2[partitionNums2];

            // Проверяем нарушены ли условия упорядоченности
            if (maxLeftNums1 > minRightNums2) {
                // Элемент из левой части nums1 больше минимального элемента правой части nums2
                // Значит, мы взяли слишком мало элементов из nums1 в левую часть (и слишком много из nums2)
                // Нужно увеличить partitionNums1 (двинуться вправо
                low = partitionNums1 + 1;
            } else {
                // Идеальное разбиение найдено! Вычислем медиану
                if ((m + n) % 2 == 0) {
                    // Если общая длина четная, медиана - среднее арифметическое двух средних
                    return (Math.max(maxLeftNums1, maxLeftNums2) + Math.min(minRightNums1, minRightNums2)) / 2.0;
                } else {
                    // Если общая длина нечетная, медиана - максимум из левой части
                    return Math.max(maxLeftNums1, maxLeftNums2);
                }
            }
        }
        // Если цикл завершился, значит решение найдено на предыдущих шагах,
        // но формально, это должно быть недостижимо при корректном вводе
        throw new IllegalArgumentException("Input arrays are not sorted or invalid.");
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(new Solution2().findMedianSortedArrays(nums1, nums2)); // 2.0

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        System.out.println(new Solution2().findMedianSortedArrays(nums1, nums2)); // 2.5

        nums1 = new int[]{1};
        nums2 = new int[]{};
        System.out.println(new Solution2().findMedianSortedArrays(nums1, nums2)); // 1.0
    }
}
