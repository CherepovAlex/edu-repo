package com.leetcodingquest.pipeline.LinkedList._2AddTwoNumbers;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {this.val = val;}
    ListNode(int val, ListNode next) {this.val = val;this.next = next;}
}
// время O(max(m, n)), память O(max(m, n))
public class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // Фиктивный узел перед началом результирующего списка
        ListNode dummy = new ListNode(0);

        // Указатель на последний узел результирующего списка
        ListNode current = dummy;

        // Перенос из предыдущего разряда
        int carry = 0;

        // Продолжаем,пока есть цифры или остался перенос
        while (l1 != null || l2 != null || carry != 0) {

            // Если первый список закончился, берём 0
            int x = (l1 != null) ? l1.val : 0;

            // Если второй список закончился, берём 0
            int y = (l2 != null) ? l2.val : 0;

            // Складываем цифры и перенос предыдущего разряда
            int sum = x + y + carry;

            // Получаем цфиру текущего разряда
            int digit = sum % 10;

            // Вычисляем перенос в следующий разряд
            carry = sum / 10;

            // Создаём узел с полученной цифрой
            current.next = new ListNode(digit);

            // Перемещаем указатель на новый последний узел
            current = current.next;

            // Переходим к следующему узлу первого списка, если он существует
            if (l1 != null) {
                l1 = l1.next;
            }

            // Переходим к следующему узлу первого списка, если он существует
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //Возвращаем первый настоящий узел результата, пропуская фиктивный узел dummy
        return dummy.next;
    }

    public static void printLinkedList(ListNode head) {
        if (head == null) {System.out.println("[]");return;}
        StringBuilder sb = new StringBuilder(); sb.append("[");
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {sb.append(", ");}
            current = current.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        printLinkedList(new Solution1().addTwoNumbers(l1, l2));
    }
}
