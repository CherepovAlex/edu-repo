package com.leetcodingquest.pipeline.Staff._3644MaximumKToSortAPermutation;

public class Solution2 {
    // время O(n), память O(1)
    // метод возвращает максимально допустимаое k
    public int sortPermutation(int[] nums) {
        int ans = -1;                          // Инициализируем -1 (все биты 1) — нейтральное значение для побитового И
        for (int i = 0; i < nums.length; i++) { // Проходим по каждому индексу i
            if (nums[i] != i) {                // Если текущий элемент стоит не на своей позиции после сортировки
                ans &= nums[i];                // Побитово И с этим смещённым элементом, сужая общие биты
            }
        }
        return ans == -1 ? 0 : ans;            // Если ans остался -1, смещённых элементов нет — массив отсортирован, возвращаем 0
    }

    public static void main(String[] args) {
        int[] nums = {0, 3, 2, 1};
        System.out.println(new Solution2().sortPermutation(nums));
        int[] nums1 = {0, 1, 3, 2};
        System.out.println(new Solution2().sortPermutation(nums1));
        int[] nums2 = {3, 2, 1, 0};
        System.out.println(new Solution2().sortPermutation(nums2));
    }
}