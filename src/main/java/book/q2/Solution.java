package book.q2;

import java.util.LinkedList;

/**
 * 利用栈和递归操作反转栈
 *
 * @author iterators
 * @date 2021/2/19 下午5:26
 */
public class Solution {

    public void reverseStack(LinkedList<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Integer lastElement = getAndRemoveLastElement(stack);
        reverseStack(stack);
        stack.push(lastElement);
    }

    private Integer getAndRemoveLastElement(LinkedList<Integer> stack) {
        Integer topElement = stack.pop();
        if (stack.isEmpty()) {
            return topElement;
        }
        Integer element = getAndRemoveLastElement(stack);
        stack.push(topElement);
        return element;
    }

}
