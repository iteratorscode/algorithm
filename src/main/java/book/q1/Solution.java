package book.q1;

import java.util.LinkedList;

/**
 * 模版类
 *
 * @author iterators
 * @date 2021/2/19 下午3:58
 */
public class Solution {

    /**
     * 具有返回最小元素功能的栈
     *
     * @implNote 使用两个栈，一个栈用于压入数据，另一个栈用于返回当前的最小元素。(1)数据押入和探出时，更新当前栈中的最小元素
     */
    static class MinStack {

        private static final String STACK_EMPTY_INFO = "current stack is empty";

        private LinkedList<Integer> dataStack = new LinkedList<>();
        private LinkedList<Integer> minStack = new LinkedList<>();

        public void push(Integer element) {
            dataStack.push(element);
            if (minStack.isEmpty()) {
                minStack.push(element);
            } else {
                Integer topElement = getMin();
                if (element <= topElement) {
                    minStack.push(element);
                }
            }
        }

        public Integer peek() {
            return dataStack.peek();
        }

        public Integer pop() {
            if (dataStack.isEmpty()) {
                throw new RuntimeException(STACK_EMPTY_INFO);
            }
            // 数据栈中的元素值只可能 >= 最小栈中栈顶元素，二者相等时，将最小栈中元素移除
            Integer element = dataStack.pop();
            if (getMin().equals(element) ) {
                minStack.pop();
            }
            return element;
        }

        public Integer getMin() {
            checkEmpty();
            return minStack.peek();
        }

        private void checkEmpty() {
            if (dataStack.isEmpty()) {
                throw new RuntimeException(STACK_EMPTY_INFO);
            }
        }
    }
}
