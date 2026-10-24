package com.leetcodingquest.pipeline.String._2409CountDaysSpentTogether;

public class Solution1 {

    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    // Преобразуем дату MM-DD в порядковый номер дня года
    private int dayOfYear(String date){
        //Получаем номер месяца из певых двух символов
        int month = Integer.parseInt(date.substring(0, 2));
        // Получаем номер дня из последних двух символов
        int day = Integer.parseInt(date.substring(3, 5));
        // Начинаем с нуля накопленных дней
        int totalDays = 0;
        // Суммируем дни всех месяцев, предшествующих указанному
        for (int i = 0; i < month - 1; i++) totalDays += DAYS_IN_MONTH[i];
        // Прибавляем день текущего месяца и возвращаем результат
        return totalDays + day;
    }
    // время O(1), память O(1)
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // Переводим дату приезда Элис в номер дня года
        int aliceStart = dayOfYear(arriveAlice);
        // Переводим дату отъезда Элис в номер дня года
        int aliceEnd = dayOfYear(leaveAlice);
        // Переводим дату приезда Боба в номер дня года
        int bobStart = dayOfYear(arriveBob);
        // Переводим дату отъезда Боба в номер дня года
        int bobEnd = dayOfYear(leaveBob);
        // Совместное пребывание начинается с более позднего приезда
        int start = Math.max(aliceStart, bobStart);
        // Совместное пребывание заканчивается с более ранним отъездом
        int end = Math.min(aliceEnd, bobEnd);
        // Если начало позже конца, периоды не пересекаются
        if (start > end) return 0;
        // Считаем все совместные дни, включая начало и конец
        return end - start + 1;
    }

    public static void main(String[] args) {
        String arriveAlice = "08-15"; String leaveAlice = "08-18";
        String arriveBob = "08-16"; String leaveBob = "08-19";
        System.out.println(new Solution1().countDaysTogether(arriveAlice, leaveAlice, arriveBob, leaveBob)); // 3
        String arriveAlice1 = "10-01"; String leaveAlice1 = "10-31";
        String arriveBob1 = "11-01"; String leaveBob1 = "12-31";
        System.out.println(new Solution1().countDaysTogether(arriveAlice1, leaveAlice1, arriveBob1, leaveBob1)); // 0
    }
}
