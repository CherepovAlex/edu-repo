package com.leetcodingquest.pipeline.Senior._3551MinimumSwapsToSortByDigitSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// время: O(n log n), память: O(n)
public class Solution2 {
    public int minSwaps(int[] nums) {

        int n = nums.length;

        // Создаём массив объектов, чтобы хранить пары (число, исходный индекс)
        // Это нужно, потому что при сортировке мы теряем информацию о том,
        // где изначально находилось число.

        Integer[][] pairs = new Integer[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];   // Само число
            pairs[i][1] = i;         // Его исходный индекс
        }

        // Сортируем массив пар по заданным правилам:
        // 1. Сначала по сумме цифр (возрастание)
        // 2. Если суммы равны, по самому числу (возрастание)

        Arrays.sort(pairs, (a, b) -> {
            int sumA = getDigitSum(a[0]);
            int sumB = getDigitSum(b[0]);

            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(a[0], b[0]);
        });

        // Теперь pairs[i] содержит число, которое должно стоять в позиции i в отсортированном массиве.
        // Нам нужно понять: где сейчас находится число, которое должно быть на позиции i?
        // Или проще: для каждого элемента в исходном массиве, куда он должен попасть?

        // Давайте построим карту: Число -> Индекс в отсортированном массиве.
        // Поскольку числа уникальны, это безопасно.
        Map<Integer, Integer> sortedIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sortedIndexMap.put(pairs[i][0], i);
        }

        // Теперь пойдём по исходному массиву nums и найдём циклы
        // visited массив поможет нам не пересчитывать элементы, уже вошедшие в цикл.
        boolean[] visited = new boolean[n];
        int cycles = 0;

        for (int i = 0; i < n; i++) {
            // Если элемент уже посещён или он уже стоит на своём месте (цикл длины 1), пропускаем
            if (visited[i]) {
                continue;
            }

            // Начинаем новый цикл
            cycles++;
            int currentIdx = i;

            // Идём по циклу, пока не вернёмся к началу
            while (!visited[currentIdx]) {
                visited[currentIdx] = true;
                // Текущий элемент nums[currentIdx] должен перейти на позицию,
                // указанную в нашей карте для этого числа.
                // Но подождите! Логика цилов обычно строится так:
                // Мы хотим, чтобы элемент по позиции i оказался на позиции targetIndex.
                // В нашем случае, мы смотрим на исходный массив.
                // Элемент nums[i] должен в итоге оказаться на позиции sortedIndexMap.get(nums[i]).
                // Значит, следующая позиция в цикле - это то, куда "уходит" текущий элемент.
                // Однако, стандартный алгоритм подсчета циклов для минимальных перестановок
                // работает с позициями.

                // Уточнение логики:
                // Пусть у нас есть перестановка P. Мы ъотим получить тождественную.
                // Мы смотрим на позицию i. На ней лежит элемент X.
                // Где должен лежать элемент X в отсортированном массиве? На позиции J = sortedIndexMap.get(X).
                // Значит, мы должны поменять местами элемент на позиции i и элемент на позиции J?
                // Нет, это не совсем так для подсчета циклов

                // Правильный подход для подсчета циклов:
                // Мы рассматриваем граф, где вершины - индксы 0..n-1.
                // Ребро идёт от i к j, если элемент, стоящий на позиции i,
                // должен в итоге стоять на позиции j.
                // j = sortedIndexMap.get(nums[i]).
               int targetPos = sortedIndexMap.get(nums[currentIdx]);
               currentIdx = targetPos;
            }
        }

        // Минимальное количество перестановок = N - количество циклов
        return n - cycles;
    }

    // Вспомогательный метод для вычисления суммы цифр
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {37, 100};
        System.out.println(new Solution2().minSwaps(nums)); // [100, 37], 1
        int[] nums1 = new int[] {18, 43, 34, 16};
        System.out.println(new Solution2().minSwaps(nums1)); // [16, 34, 43, 18], 2
        int[] nums2 = new int[] {22, 14, 33, 7};
        System.out.println(new Solution2().minSwaps(nums2)); // [22, 14, 33, 7], 0
    }
}
