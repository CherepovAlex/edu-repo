package com.leetcodingquest.pipeline.Math._2409CountDaysSpentTogether;

public class Solution2 {
    // время O(1), память O(1)
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // Массив с количеством дней в каждом месяце (невисокосный год), индекс 0 — январь
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Переводим все 4 даты в номер дня в году (1..365)
        int a1 = toDayOfYear(arriveAlice, daysInMonth);   // день приезда Элис
        int a2 = toDayOfYear(leaveAlice, daysInMonth);   // день отъезда Элис
        int b1 = toDayOfYear(arriveBob, daysInMonth);   // день приезда Боба
        int b2 = toDayOfYear(leaveBob, daysInMonth);   // день отъезда Боба

        // Левая граница пересечения — более поздний из приездов
        int start = Math.max(a1, b1);

        // Правая граница пересечения — более ранний из отъездов
        int end = Math.min(a2, b2);

        // Если начало позже конца — общих дней нет
        if (start > end) {
            return 0;
        }

        // Количество дней включительно: end - start + 1
        return end - start + 1;
    }

    // Вспомогательный метод: переводит "MM-DD" в номер дня в году (1..365)
    private int toDayOfYear(String date, int[] daysInMonth) {
        // Извлекаем месяц (первые два символа) и день (последние два)
        int month = Integer.parseInt(date.substring(0, 2)); // "08" -> 8
        int day = Integer.parseInt(date.substring(3, 5)); // "15" -> 15

        // Начинаем счёт: накапливаем дни всех предыдущих месяцев
        int total = 0;
        for (int i = 0; i < month - 1; i++) {
            total += daysInMonth[i]; // добавляем длину i-го месяца
        }

        // Прибавляем текущий день месяца
        total += day;
        return total;
    }

    public static void main(String[] args) {
        String arriveAlice = "08-15";
        String leaveAlice = "08-18";
        String arriveBob = "08-16";
        String leaveBob = "08-19";
        System.out.println(new Solution2().countDaysTogether(arriveAlice, leaveAlice, arriveBob, leaveBob)); // 3
        String arriveAlice1 = "10-01";
        String leaveAlice1 = "10-31";
        String arriveBob1 = "11-01";
        String leaveBob1 = "12-31";
        System.out.println(new Solution2().countDaysTogether(arriveAlice1, leaveAlice1, arriveBob1, leaveBob1)); // 0
    }
}
