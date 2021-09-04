# algorithm

## 解题技巧

### 数据预处理

#### 预排序
使用排序算法对原始数据先排序`Arrays.sort(nums)`，然后利用贪心或者双指针的策略对问题进行求解

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

### 常用的Java数据结构

#### 堆`PriotityQueue`

#### 有序`TreeMap`