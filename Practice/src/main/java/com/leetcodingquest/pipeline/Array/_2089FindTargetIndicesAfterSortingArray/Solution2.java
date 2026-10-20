package com.leetcodingquest.pipeline.Array._2089FindTargetIndicesAfterSortingArray;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    // время O(n), память O(k)
    // Принимаем массив и целевое число; возвращаем список индексов
    public List<Integer> targetIndices(int[] nums, int target) {
        // Создаем список для хранения результатов (целевых индексов)
        List<Integer> result = new ArrayList<>();
        int countLess = 0;    // Количество элементов, строго меньше target
        int countEqual = 0;   //   Количество элементов, равных target
        // Проходим по массиву один раз (O(N))
        for (int num : nums) {
            if (num < target) {
                countLess++;      // Если текущий элемент меньше целевого, увеличиваем счетчик "меньше"
            } else if (num == target) {
                countEqual++;        // Если текущий элемент равен целевому, увеличиваем счетчик "равно"
            }
            // Если num > target, мы ничего не делаем, так как эти элементы
            // будут находиться после target в отсортированном массиве и не влияют
            // на позицию самого target.
        }
        // Теперь, когда массив был бы отсортирован:
        // Все элементы, меньшие target, занимают индексы от 0 до countLess - 1.
        // Следовательно, первое вхождение target будет иметь индекс countLess.
        // Второе вхождение — индекс countLess + 1, и так далее.
        // У нас есть countEqual таких элементов.
        // Генерируем индексы от countLess до (countLess + countEqual - 1)
        for (int i = countLess; i < countLess + countEqual; i++) {
            result.add(i);            // Добаляем очередной целевой индекс в возрастающем порядке
        }
        // Возвращаем полученный список индексов
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 5, 2, 3};
        int target1 = 2;
        List<Integer> result1 = new Solution2().targetIndices(nums1, target1); // 1, 2
        for (int res : result1) System.out.println(res);

        int[] nums2 = {1, 2, 5, 2, 3};
        int target2 = 3;
        List<Integer> result2 = new Solution2().targetIndices(nums2, target2);
        for (int res : result2) System.out.println(res);// 3

        int[] nums3 = {1, 2, 5, 2, 3};
        int target3 = 5;
        List<Integer> result3 = new Solution2().targetIndices(nums3, target3);
        for (int res : result3) System.out.println(res); // 4
    }
}
