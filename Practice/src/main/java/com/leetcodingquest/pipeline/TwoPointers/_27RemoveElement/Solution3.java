package com.leetcodingquest.pipeline.TwoPointers._27RemoveElement;

public class Solution3 {

    /**
     * Удаляет все вхождения val из массива nums на месте.
     * Возвращает количество элементов, не равных val.
     *
     * @param nums входной массив
     * @param val  значение, которое нужно удалить
     * @return количество элементов, не равных val
     */
    public int removeElement(int[] nums, int val) {
        // slow указывает на позицию, куда нужно записать следующий элемент, который НЕ равен val.
        int slow = 0;
        // fast проходит по всем элементам массива, чтобы найти "хорошие" элементы.
        for (int fast = 0; fast < nums.length; fast++) {
            // Проверяем, является ли текущий элемент (по индексу fast) тем, который нужно оставить (т.е. он НЕ равен val)
            if (nums[fast] != val) {
                // Если элемент "хороший", записываем его в позицию slow.
                // Так как порядок не важен, мы просто копируем значение.
                nums[slow] = nums[fast];

                // Увеличиваем slow на 1, чтобы следующий "хороший" элемент
                // записался на следующую позицию.
                slow++;
            }
            // Если nums[fast] == val, мы ничего не делаем.
            // Мы просто продолжаем цикл, и fast увеличивается автоматически.
        }

        // После завершения цикла slow равно количеству элементов,
        // которые мы успешно записали (т.е. не равных val).
        return slow;
    }

    public static void main(String[] args) {
        // Создаем экземпляр класса для вызова метода
        Solution3 solution = new Solution3();

        // Пример 1
        int[] nums1 = {3, 2, 2, 3};
        int val1 = 3;
        int k1 = solution.removeElement(nums1, val1);
        System.out.println("Пример 1: k = " + k1);
        System.out.print("Массив после удаления: ");
        for (int i = 0; i < k1; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println();

        // Пример 2
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;
        int k2 = solution.removeElement(nums2, val2);
        System.out.println("Пример 2: k = " + k2);
        System.out.print("Массив после удаления: ");
        for (int i = 0; i < k2; i++) {
            System.out.print(nums2[i] + " ");
        }
        System.out.println();
    }
}