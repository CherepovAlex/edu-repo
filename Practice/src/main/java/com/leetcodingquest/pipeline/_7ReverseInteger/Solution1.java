package com.leetcodingquest.pipeline._7ReverseInteger;

public class Solution1 {
    // Метод переворачивает число x
    // время O(log10(x)), память O(1)
    public int reverse(int x) {
        int reversed = 0;   // Храним число, которое постепенно собираем в обратном порядке
        while (x != 0) {    // Повторяем действия, пока в исходном числе остаются цифры
            int digit = x % 10; // Извлекаем последнюю цифру исходного числа
            x /= 10;    // Удаляем последнюю цифру из исходного числа
            // Проверяемя, не выйдет ли за верхнюю границу int при умножении на 10
            // Если reversed больше 214748364, умножение на 10 уже приведёт к переполнению
            // Если равно 214748364, последняя цифра может быть не больше 7, иначе получим число больше 2147483647
            if (reversed > Integer.MAX_VALUE / 10 ||    // 2147483647, -2147483648
                    (reversed == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;   // Возвращаем 0, если положительное переполнение неизбежно
            }
            // Проверяем, не выйдет ли за нижнюю границу int при умножении на 10
            // Если reversed меньше -214748364, умножение на 10 приведёт к выходу за нижнюю границу.
            // Если равно -214748364, последняя цифра должна быть не меньше -8, чтобы итог не стал меньше -2147483648
            if (reversed < Integer.MIN_VALUE / 10 ||
                    (reversed == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;   // Возвращаем 0, если отрицательное переполнение неизбежно
            }
            reversed = reversed * 10 + digit;   // Добавляем извлечённую цифру в конец результата
        }
        return reversed;    // Возвращаем перевёрнутое число после обработки всех цифр
    }

    public static void main(String[] args) {
        int x = 123;
        System.out.println(new Solution1().reverse(x)); // 321
        x = -123;
        System.out.println(new Solution1().reverse(x)); // -321
        x = 120;
        System.out.println(new Solution1().reverse(x)); // 21
        x = 1534236469;
        System.out.println(new Solution1().reverse(x)); // 0 из-за переполнения.
    }
}
