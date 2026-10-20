package com.leetcodingquest.pipeline.BinarySearchTree._1569NumbersOfWayaToReaorderArrayToGetSameBST;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    // константа для предотвращения переполнения
    private static final int MOD = 1_000_000_007;

    public int numOfWays(int[] nums) {
        // Создаём вспомогательный список для рекурсивных вызовов, т.к. Arrays.copyOfRange создаёт массив,
        // но нам удобнее работать с List<Integer> или просто передавать массивы
        // Для оптимизации создадим лист из nums
        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);

        // Вызываем рекурсивную функцию
        long result = countWays(list);

        // Вычитаем 1, чтобы исключить исходную перестановку из подсчета,
        // так как задача ищет количество *альтернативных* способов переупорядочить.
        // Или, если интерпретировать "переупорядочить" как "изменить порядок",
        // то исходный порядок не считается изменением.
        long finalAnswer = (result - 1 + MOD) % MOD;

        // Возвращаем результат как int, т.к. задача требует вернуть int
        return (int) finalAnswer;
    }

    // Рекурсивная функция для вычисления количества способов
    private long countWays(List<Integer> nums) {
        // Базовый случай: если 0 или 1 элемент, то 1 способ (или 0 способов, если строго меньше 1, но тут 1 способ -
        // пустое или одно значение
        if (nums.size() <= 1) return 1;

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Разделяем элементы на левые и правые поддеревья
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root)         // Если элемент меньше root, он идет в left (левое поддерево).
                left.add(nums.get(i));
            else right.add(nums.get(i));    // Если элемент больше root, он идет в right (правое поддерево)
        }

        // Рекурсивно вычисляем способы для левого и правого поддеревьев (списков)
        // Это позволяет вычислить количество способов упорядочить элементы внутри каждого поддерева независимо
        long leftWays = countWays(left);
        long rightWays = countWays(right);

        // Количество элементов в левом и правом поддеревьях
        int n = left.size();        // количество узлов в левом поддереве.
        int m = right.size();       // количество узлов в правом поддереве.

        // Вычисляем биномиальный коэффициент: C(n + m, n)
        // - количество способов выбрать n позиций для левых узлов из n + m доступных позиций,
        // - остальные позиции займут правые узлы
        long combinations = binomialCoeff(n + m, n);

        // Итоговое количество способов:
        // (ways левого поддерева) * (ways правого поддерева) * (комбинация перемешивания)
        return ((leftWays * rightWays) % MOD * combinations) % MOD;
    }

    // Функция вычисления биномиального коэффициента C(n, k) по модулю MOD
    private long binomialCoeff(int n, int k) {
        // C(n, k) = n! / (k! * (n - k)!) - Используем формулу с факториалами и модулярным обратным

        // Числитель: n!
        long numerator = 1;
        for (int i = 1; i <= n; i++) numerator = (numerator * i) % MOD;

        // Знаменатель: k! * (n - k)!
        long denominator = (factorial(k) * factorial(n - k)) % MOD;

        // Результат: numerator * modInverse(denominator, MOD)
        // Деление по модулю заменяется на умножение на модулярный обратный элемент
        return (numerator * modInverse(denominator, MOD)) % MOD;
    }

    // Функция для простого вычисления факториала по модулю MOD
    private long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++)
           result = (result * i) % MOD;
        return result;
    }

    // Функция для вычисления обратного элемента по модулю MOD (модулярного обратного элемента)
    // Используем теорему Ферма: a^(p-2) = a^(-1) (mod p), где p - простое число
    private long modInverse(long a, int m) {
        return power(a, m - 2, m);
    }

    // Функция для вычисления a^b (mod m) (модулярного возведения в степень)
    // Позволяет возводить в большую степень за O(log exp) времеи
    private long power(long base, long exp, int mod) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }

    // Метод main для демонстрации работы на примерах
    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        // Пример 1
        int[] nums1 = {2, 1, 3};
        int result1 = solution.numOfWays(nums1);
        System.out.println("Пример 1:");
        System.out.println("Вход: " + arrayToString(nums1));
        System.out.println("Выход: " + result1);
        System.out.println("Ожидаемый: 1");
        System.out.println("-------------------------");

        // Пример 2
        int[] nums2 = {3, 4, 5, 1, 2};
        int result2 = solution.numOfWays(nums2);
        System.out.println("Пример 2:");
        System.out.println("Вход: " + arrayToString(nums2));
        System.out.println("Выход: " + result2);
        System.out.println("Ожидаемый: 5");
        System.out.println("-------------------------");

        // Пример 3
        int[] nums3 = {1, 2, 3};
        int result3 = solution.numOfWays(nums3);
        System.out.println("Пример 3:");
        System.out.println("Вход: " + arrayToString(nums3));
        System.out.println("Выход: " + result3);
        System.out.println("Ожидаемый: 0");
    }

    // Вспомогательный метод для красивого вывода массива
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
