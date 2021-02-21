package book.q3;

import java.util.LinkedList;

/**
 * 用一个栈实现另一栈的从大到小排序
 *
 * @author zhanghui
 * @date 2021/2/20 下午4:22
 */
public class Solution {

    public static void sort(LinkedList<Integer> stack) {
        // helpStack中的元素从小到大(栈顶元素最大)
        LinkedList<Integer> helpStack = new LinkedList<>();
        do {
            Integer element = stack.pop();
            // 当前元素小于辅助栈的栈顶元素, 将辅助栈中所有元素重新放入原始栈中
            if (!helpStack.isEmpty() && helpStack.peek() > element) {
                while (!helpStack.isEmpty()) {
                    stack.push(helpStack.pop());
                }
            }
            helpStack.push(element);
        } while (!stack.isEmpty());
        // helpStack元素重新入栈stack
        do {
            stack.push(helpStack.pop());
        } while (!helpStack.isEmpty());
    }
}
