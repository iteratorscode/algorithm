package book.q2;

import java.util.Stack;

/**
 * 通过递归函数和栈操作反转栈元素
 *
 * @author iterators
 * @date 2021.02.19
 */
public class Solution {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Integer topElement = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(topElement);
    }

    private static Integer getAndRemoveLastElement(Stack<Integer> stack) {
        Integer topElement = stack.pop();
        if (stack.isEmpty()) {
            return topElement;
        }
        Integer last = getAndRemoveLastElement(stack);
        stack.push(topElement);
        return last;
    }
}
