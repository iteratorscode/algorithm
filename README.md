# algorithm

## 解题技巧

### 数据预处理

#### 预排序
使用排序算法对原始数据先排序`Arrays.sort(nums)`，然后利用贪心或者双指针的策略对问题进行求解

**经典题型**
- [救生艇](https://leetcode-cn.com/problems/boats-to-save-people/)

#### 前缀和数组
用于快速的求解某个区间所有元素的和。前缀和数组的表示形式：

```markdown
// 元素数组为nums
int[] nums = {};
// 前缀和数组的长度为原数组的长度 +1
int[] prefixSum = new int[nums.length + 1];

// 前缀和数组中的每i项满足: 前缀和数组第i-1项的值 + 元素数组第i项的值
prefixSum[i] = prefix[i - 1] + nums[i - 1]

```

#### 差分数组
用于快速的将某个区间的所有元素都增加某个值。差分数组的表示形式为：

```markdown
// 原始数组为nums
int[] nums = {};

// 差分数组的长度为原始数组的长度
int diff = new int[nums.length];

// 差分数组中的第i项可以表示成：原始数组第i项和第i-1项的差值
diff[i] = nums[i] - nums[i-1]; 其中diff[0] = nums[0]
```

#### 双指针

#### 堆
大顶堆：每个结点的值都大于或等于其左右孩子结点的值。<br/>
小顶堆：每个结点的值都小于或等于其左右孩子结点的值。<br/>
如果是排序，求升序用大顶堆，求降序用小顶堆。<br/>
一般我们说 topK 问题，就可以用大顶堆或小顶堆来实现，最大的 K 个：小顶堆; 最小的 K 个：大顶堆。

**经典题型**
- [数据流的中位数](https://leetcode-cn.com/problems/find-median-from-data-stream/)
- [数据流中最大的K个数]()
- [数据流中最小的K个数]()

#### 广度优先搜索
求从A --> B 的最短路径

**经典题目**
- [K 站中转内最便宜的航班](https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/)

#### 深度优先搜索

**经典题目**
- [所有可能的路径](https://leetcode-cn.com/problems/all-paths-from-source-to-target/)

#### 动态规划


### 常用的Java数据结构

#### 堆`PriotityQueue`

```markdown
// 大顶堆
PriorityQueue<Integer> minHead = new PriorityQueue<Integer>((a, b) -> (b - a));

// 小顶堆,  默认创建的是小顶堆，使用的自然序堆元素排序
PriorityQueue<Integer> minHead = new PriorityQueue<Integer>();
```

#### 有序`TreeMap`