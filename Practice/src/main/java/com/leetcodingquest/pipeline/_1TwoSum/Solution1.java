package com.leetcodingquest.pipeline._1TwoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// время O(n), пространство O(n)
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        // Создаём HashMap для хранения значения числа -> его индекс
        // Ключ: число из массива, Значени: индекс этого числа
        Map<Integer,Integer> map = new HashMap<>();
        // Проходим по массиву один раз
        for (int i = 0; i < nums.length; i++) {
            // Вычисляем "комплемент" - число, которое нужно добавить к nums[i],
            // чтобы получить target
            int complement = target - nums[i];
            // Проверяем, если ли уже этот комплемент в карте
            if (map.containsKey(complement)) {
                // Если есть, возвращает пару индексов:
                // map.get(complement) - индекс комплемента (найден ранее)
                // i - индекс текущего элементы (найден сейчас)
                return new int[]{map.get(complement), i};
            }
            // Если комплемента нет, добавляем текущий элемент и его индекс в карту
            // ччобы он мог стать, комплементом для будущих жлементов
            map.put(nums[i], i);
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {2, 7, 11, 15}; int target1 = 9;
        System.out.println(Arrays.toString(new Solution1().twoSum(nums1, target1))); // [0, 1]
        int[] nums2 = new int[] {3, 2, 4}; int target2 = 6;
        System.out.println(Arrays.toString(new Solution1().twoSum(nums2, target2))); // [1, 2]
        int[] nums3 = new int[] {3, 3}; int target3 = 6;
        System.out.println(Arrays.toString(new Solution1().twoSum(nums3, target3 ))); // [0, 1]
    }
}
