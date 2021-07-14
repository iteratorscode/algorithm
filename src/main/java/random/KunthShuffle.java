package random;

import java.util.Arrays;
import java.util.Random;

/**
 * 洗牌算法
 * @apiNote https://cloud.tencent.com/developer/article/1462951,
 *       https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E6%B4%97%E7%89%8C%E7%AE%97%E6%B3%95.md
 *
 * @author zhanghui
 * @date 2021/7/14 7:42 下午
 */
public class KunthShuffle {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        kunthShuffle(arr);
        System.out.println("arr = " + Arrays.toString(arr));
    }

    /**
     * 对数组中的元素随机排序，确保每个元素的概率是相同的
     *
     * @apiNote java.util.Collections#shuffle(java.util.List, java.util.Random) 实现了相同的功能
     */
    private static void kunthShuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 获取区间 i->j 的一个随机数
            int randIndex = randInt(i, arr.length);
            System.out.println("randIndex = " + randIndex);
            swapArrayItem(arr, i, randIndex);
        }
    }

    private static int randInt(int i, int j) {
        return new Random().nextInt((j - i) * 10) % (j - i) + i;
    }

    private static void swapArrayItem(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
