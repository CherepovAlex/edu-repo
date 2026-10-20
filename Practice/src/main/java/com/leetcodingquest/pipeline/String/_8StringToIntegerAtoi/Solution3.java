package com.leetcodingquest.pipeline.String._8StringToIntegerAtoi;

public class Solution3 {
    public int myAtoi(String s) {
        int n = s.length();              // длина строки — понадобится для границ цикла
        int i = 0;                       // индекс-курсор текущего символа

        // Шаг 1: пропускаем ведущие пробелы
        while (i < n && s.charAt(i) == ' ') {
            i++;                         // сдвигаем курсор, пока видим пробел
        }

        // Если дошли до конца строки — цифр нет, возвращаем 0
        if (i == n) return 0;

        // Шаг 2: определяем знак
        int sign = 1;                    // по умолчанию число положительное
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1; // '-' → -1, '+' → 1
            i++;                         // сдвигаем курсор за знаком
        }

        // Шаг 3: читаем цифры в long-аккумулятор
        long result = 0;                 // long, чтобы безопасно ловить переполнение
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';       // преобразуем символ '0'..'9' в число 0..9
            result = result * 10 + digit;        // добавляем цифру в конец числа

            // Шаг 4: проверка переполнения на каждом шаге
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;        // обрезаем сверху
            }
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;        // обрезаем снизу
            }

            i++;                                 // переходим к следующему символу
        }

        // Применяем знак и приводим long к int (значение уже гарантированно в диапазоне)
        return (int) (sign * result);
    }

        public static void main(String[] args) {
        String s = "42";
        System.out.println(new Solution3().myAtoi(s)); // 42
        s = " -042";
        System.out.println(new Solution3().myAtoi(s)); // -42
        s = "1337c0d3";
        System.out.println(new Solution3().myAtoi(s)); // 1337
        s = "0-1";
        System.out.println(new Solution3().myAtoi(s)); // 0
        s = "words and 987";
        System.out.println(new Solution3().myAtoi(s)); // 0
    }
}