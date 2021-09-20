package leetcode.dequeue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调队列：队首元素是队列中所有元素的最大值或者最小值的特殊队列
 *
 * @author iterators
 * @since 2021/09/20
 */
public class Solution {

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.offer(1);
        System.out.println("maxQueue.max() = " + maxQueue.max());
        maxQueue.offer(2);
        System.out.println("maxQueue.max() = " + maxQueue.max());
        maxQueue.offer(3);
        System.out.println("maxQueue.max() = " + maxQueue.max());
        maxQueue.poll();
        maxQueue.offer(0);
        System.out.println("maxQueue.max() = " + maxQueue.max());
    }

    static class MaxQueue {

        Deque<Integer> queue;
        Deque<Integer> data;

        public MaxQueue() {
            queue = new LinkedList<>();
            data = new LinkedList<>();
        }

        public void offer(Integer x) {
            data.offer(x);
            while (!queue.isEmpty() && x > queue.peekLast()) {
                queue.pollLast();
            }
            queue.offerLast(x);
        }

        public void poll() {
            if (!data.isEmpty() && data.poll().equals(queue.peekFirst())) {
                queue.pollFirst();
            }
        }

        public int top() {
            return data.isEmpty() ? -1 : data.peek();
        }

        public int max() {
            return queue.isEmpty() ? -1 : queue.peekFirst();
        }
    }
}
