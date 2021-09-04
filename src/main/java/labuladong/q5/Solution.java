package labuladong.q5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 区间合并
 *
 * @author iterators
 * @since 2021/08/29
 */
public class Solution {

    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            return intervals;
        }
        // 将所有元素按照左边第一个位置排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++){
            // 获取上一个区间
            int[] interval = res.get(res.size() - 1);
            int L = intervals[i][0]; int R = intervals[i][1];
            // 当前区间和上一个区间发生重叠，合并
            if (interval[1] >= L) {
                interval[1] = Math.max(R, interval[1]);
            } else {
                // 未重叠，当前区间加入结果集最后
                res.add(intervals[i]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
