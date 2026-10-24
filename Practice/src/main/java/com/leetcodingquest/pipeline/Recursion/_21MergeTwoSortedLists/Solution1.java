package com.leetcodingquest.pipeline.Recursion._21MergeTwoSortedLists;

public class Solution1 {
    // время O(m + n), памть O(1)
    /**
     * Метод для объединения двух отсортированных связанных списков.
     *
     * @param list1 Голова первого отсортированного списка
     * @param list2 Голова второго отсортированного списка
     * @return Голова объединенного отсортированного списка
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Создаём вспомогательный узел перед началом результата
        ListNode dummy = new ListNode(-1); // или 0
        // tail указывает на последний узел сформированного списка
        ListNode tail = dummy;
        //Пока в обоих списках есть необработанные узлы
        while (list1 != null && list2 != null) {
            // Если значение первого списка меньше или равно второму
            if (list1.val <= list2.val) {
                // Присоединяем текущий узел первого списка
                tail.next = list1;
                // Перемещаем list1 к следующему узлу
                list1 = list1.next;
            } else {
                // Присоединяем текущий узел второго списка
                tail.next = list2;
                // Перемещаем list2 к следующему узлу
                list2 = list2.next;
            }
            // Передвигаем конец результата на добавленный узел
            tail = tail.next;
        }
        // Присоединяем остаток list1, если он ещё существует
        if (list1 != null) tail.next = list1;
        // Присоединяем остаток list2, если он ещё существует
        if (list2 != null) tail.next = list2;
        // tail.next = (list1 != null) ? list1 : list2;
        // Возвращаем первый настоящий узел
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode result1 = solution.mergeTwoLists(list1, list2);
        printList(result1);

        ListNode empty1 = null;
        ListNode empty2 = null;
        ListNode result2 = solution.mergeTwoLists(empty1, empty2);
        printList(result2);

        ListNode empty3 = null;
        ListNode list3 = new ListNode(0);
        ListNode result3 = solution.mergeTwoLists(empty3, list3);
        printList(result3);
    }

    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}