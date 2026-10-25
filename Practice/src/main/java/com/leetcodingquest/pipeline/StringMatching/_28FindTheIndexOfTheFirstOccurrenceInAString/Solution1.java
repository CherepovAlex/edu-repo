package com.leetcodingquest.pipeline.StringMatching._28FindTheIndexOfTheFirstOccurrenceInAString;

public class Solution1 {
    // время O(n + m), память O (m)
    // Метод ищет первое вхождение needle в haystack.
    // Возвращает индекс начала совпадения или -1.
    public int strStr(String haystack, String needle) {

        // Получаем длину основной строки.
        int n = haystack.length();

        // Получаем длину искомой подстроки.
        int m = needle.length();

        // Строим массив LPS для искомой подстроки.
        int[] lps = buildLps(needle);

        // i — индекс текущего символа haystack.
        int i = 0;

        // j — индекс текущего символа needle.
        int j = 0;

        // Продолжаем, пока не закончится haystack.
        while (i < n) {
            // Если символы совпали, продвигаемся по обеим строкам.
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                // Если совпали все символы needle,
                // возвращаем индекс начала найденной подстроки.
                if (j == m) {
                    return i - j;
                }
            } else {
                // При несовпадении используем информацию из LPS.
                if (j > 0) {
                    // Откатываем j к длине подходящего префикса.
                    // Индекс i при этом не меняем.
                    j = lps[j - 1];
                } else {
                    // Если совпадений в needle пока нет,
                    // переходим к следующему символу haystack.
                    i++;
                }
            }
        }

        // Если дошли до конца haystack и не нашли needle.
        return -1;
    }

    // Метод строит массив LPS для строки needle.
    // LPS означает Longest Proper Prefix which is also Suffix.
    private int[] buildLps(String needle) {

        // Получаем длину искомой строки.
        int m = needle.length();

        // Создаём массив длиной m.
        // В каждой ячейке хранится длина подходящего префикса.
        int[] lps = new int[m];

        // len — длина предыдущего совпавшего префикса.
        int len = 0;

        // lps[0] всегда равно 0:
        // у строки из одного символа нет собственного префикса.
        lps[0] = 0;

        // Начинаем вычисление со второго символа needle.
        int i = 1;

        // Заполняем массив, пока не обработаем всю needle.
        while (i < m) {

            // Если текущий символ совпал с символом префикса.
            if (needle.charAt(i) == needle.charAt(len)) {

                // Увеличиваем длину совпавшего префикса.
                len++;

                // Записываем длину в текущую ячейку LPS.
                lps[i] = len;

                // Переходим к следующему символу.
                i++;

            } else {

                // Если совпадения нет, но len больше нуля,
                // пробуем более короткий подходящий префикс.
                if (len > 0) {

                    // Берём длину предыдущего префикса из LPS.
                    // Индекс i пока не увеличиваем.
                    len = lps[len - 1];

                } else {

                    // Подходящего префикса нет.
                    lps[i] = 0;

                    // Переходим к следующему символу.
                    i++;
                }
            }
        }

        // Возвращаем построенный массив.
        return lps;
    }

    public static void main(String[] args) {

        // Создаём объект класса Solution1.
        Solution1 solution = new Solution1();

        // Пример 1: подстрока найдена в начале.
        System.out.println(
                solution.strStr("sadbutsad", "sad")
        ); // Ожидаемый результат: 0

        // Пример 2: искомая подстрока отсутствует.
        System.out.println(
                solution.strStr("leetcode", "leeto")
        ); // Ожидаемый результат: -1

        // Пример 3: подстрока найдена внутри строки.
        System.out.println(
                solution.strStr("hello", "ll")
        ); // Ожидаемый результат: 2
    }
}