package com.leetcodingquest.pipeline.Stack._20ValidParentheses;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1 {
    // время O(n), память O(n)
    // Метод принимает строку и возвращает true или false
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();    // Создаём пустой стек для открыывающих скобок
        for (char c: s.toCharArray()){  // Преобразуем строку в массив символов и перебираем каждый символ
            if (c == '(' || c == '{' || c == '['){  // Проверяем, является ли символ открывающей скобкой
                // Метод push() помещает символ на вершину стека
                stack.push(c);  // Помещаем открывающую скобку на вершину стека
            } else {    // Если символ не открывающий, значит, это закрывающая скобка
                if (stack.isEmpty()){   // Проверяем, есть ли открывающая скобка для закрытия
                    return false;   // Стек пуст: закрывающая скобка не имеет пары
                }
                // Метод pop() возвращает верхний элемент и удаляет его из стека.
                char top = stack.pop(); // Извлекаем последнюю незакрытую открывающую скобку

                if (c == ')' && top != '(' ||   // Проверяем, что ) соответствует (
                    c == '}' && top != '{' ||   // Проверяем, что } соответствует {
                    c == ']' && top != '['){    // Проверяем, что ] соответствует [
                    return false;   // Типы скобок не совпадают, поэтому строка недопустима
                }
            }
        }
        return stack.isEmpty(); // true, если все скобки закрыты, иначе false
    }

    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([])";
        String s5 = "([)]";
        System.out.println(new Solution1().isValid(s1));    //  true
        System.out.println(new Solution1().isValid(s2));    //  true
        System.out.println(new Solution1().isValid(s3));    //  false
        System.out.println(new Solution1().isValid(s4));    //  true
        System.out.println(new Solution1().isValid(s5));    //  false
    }
}
