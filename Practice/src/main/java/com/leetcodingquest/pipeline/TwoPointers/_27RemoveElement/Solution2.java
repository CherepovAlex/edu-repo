package com.leetcodingquest.pipeline.TwoPointers._27RemoveElement;

public class Solution2 {
    public int removeElement(int[] nums, int val) {
        // k — указатель на позицию, куда запишем следующий элемент, не равный val
        int k = 0;

        // Проходим по всем элементам массива от начала до конца
        for (int i = 0; i < nums.length; i++) {
            // Если текущий элемент НЕ равен удаляемому значению
            if (nums[i] != val) {
                // Записываем его в позицию k (в начало «хорошей» части массива)
                nums[k] = nums[i];
                // Сдвигаем указатель k вправо — следующий хороший элемент пойдёт следом
                k++;
            }
            // Если nums[i] == val — просто пропускаем его, ничего не записываем
        }

        // k теперь равно количеству элементов, не равных val
        return k;
    }

    public static void main(String[] args) {
        // Создаём объект решения
        Solution2 sol = new Solution2();

        // Тест 1
        int[] nums1 = {3, 2, 2, 3};
        int k1 = sol.removeElement(nums1, 3);
        System.out.println("k = " + k1 + ", nums = " + java.util.Arrays.toString(nums1));

        // Тест 2
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int k2 = sol.removeElement(nums2, 2);
        System.out.println("k = " + k2 + ", nums = " + java.util.Arrays.toString(nums2));

        // Тест 3 (пустой массив)
        int[] nums3 = {};
        int k3 = sol.removeElement(nums3, 5);
        System.out.println("k = " + k3 + ", nums = " + java.util.Arrays.toString(nums3));
    }
}
