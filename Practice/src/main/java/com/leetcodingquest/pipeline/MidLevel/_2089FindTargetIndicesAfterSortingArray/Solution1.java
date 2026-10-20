package com.leetcodingquest.pipeline.MidLevel._2089FindTargetIndicesAfterSortingArray;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
    // время O(n), память O(k)
    // Принимаем массив и целевое число; возвращаем список индексов
    public List<Integer> targetIndices(int[] nums, int target) {

        int smaller = 0;    // Счетчик элементов, которые меньше target
        int equal = 0;      // Счетчик элементов, которые равны target

        for (int num: nums) {   // Перебираем каждый элемент исходного массива
            if (num < target){  // Проверяем, меньше ли текущий элемент целевого числа.
                smaller++;      // Если меньше, увеличиваем счетчик меньших элементов
            } else if (num == target) { // Иначе проверяем, равен ли элемент target
                equal++;        // Если равен, увеличиваем счётчик целевых элементов
            }
        }

        List<Integer> result = new ArrayList<>();   // Создаём пустой список для отсева

        for (int i = 0; i < equal; i++ ){       // Выполняем цикл столько раз, сколь найдено target
            result.add(smaller + i);            // Добаляем очередной целевой индекс в возрастающем порядке
        }
        return result;  // Возвращаем список найденных индексов
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 5, 2, 3};
        int target1 = 2;
        List<Integer> result1 = new Solution1().targetIndices(nums1, target1); // 1, 2
        for (int res : result1) System.out.println(res);

        int[] nums2 = {1, 2, 5, 2, 3};
        int target2 = 3;
        List<Integer> result2 = new Solution1().targetIndices(nums2, target2);
        for (int res : result2) System.out.println(res);// 3

        int[] nums3 = {1, 2, 5, 2, 3};
        int target3 = 5;
        List<Integer> result3 = new Solution1().targetIndices(nums3, target3);
        for (int res : result3) System.out.println(res); // 4
    }
}
