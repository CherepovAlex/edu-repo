package com.leetcodingquest.pipeline.Staff._3942MinimumOperationsToSortAPermutation;

public class Solution2 {

    public int minOperations(int[] nums) {
        int n = nums.length;                                          // длина массива

        if (n == 1) {                                                 // массив из одного элемента уже отсортирован
            return 0;                                                 // операций не нужно
        }

        if (n == 2) {                                                 // особый случай длины 2
            if (nums[0] == 0 && nums[1] == 1) {                       // уже отсортирован
                return 0;                                             // 0 операций
            }                                                         // иначе это [1,0]
            return 1;                                                 // один реверс или один сдвиг
        }

        int zeroIndex = -1, maxIndex = -1;                            // позиции 0 и n-1
        for (int i = 0; i < n; i++) {                                 // один проход по массиву
            if (nums[i] == 0) {                                       // нашли 0
                zeroIndex = i;                                        // запоминаем индекс
            } else if (nums[i] == n - 1) {                            // нашли максимум n-1
                maxIndex = i;                                         // запоминаем индекс
            }
        }

        boolean canInc = true;                                        // можно ли сдвигами получить возрастание
        for (int i = 0, j = zeroIndex; i < n; i++, j = (j + 1) % n) { // идём по кругу от позиции 0
            if (nums[j] != i) {                                       // на i-м шаге ожидаем значение i
                canInc = false;                                       // не совпало — возрастание невозможно
                break;                                                // досрочно выходим
            }
        }

        boolean canDec = true;                                        // можно ли сдвигами получить убывание
        for (int i = 0, j = maxIndex; i < n; i++, j = (j + 1) % n) {  // идём по кругу от позиции n-1
            if (nums[j] != n - 1 - i) {                               // ожидаем n-1, n-2, ...
                canDec = false;                                       // не совпало — убывание невозможно
                break;                                                // досрочно выходим
            }
        }

        int ans = Integer.MAX_VALUE;                                  // минимум операций

        if (canInc) {                                                 // случай 1: сдвигами получаем возрастание
            int cost1 = zeroIndex;                                    // просто left-rotate zeroIndex раз
            int cost2 = n - zeroIndex + 2;                            // реверс + сдвиг + реверс = (n-zeroIndex) + 2
            ans = Math.min(ans, Math.min(cost1, cost2));              // берём меньшее
        }

        if (canDec) {                                                 // случай 2: сдвигами получаем убывание, затем реверс
            int cost1 = maxIndex + 1;                                 // сдвиг maxIndex раз до убывания + 1 реверс
            int cost2 = n - maxIndex + 1;                             // сначала реверс, затем сдвиг до убывания + реверс
            ans = Math.min(ans, Math.min(cost1, cost2));              // берём меньшее
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;                   // если ни один случай не подошёл — -1, иначе ans
    }
}
