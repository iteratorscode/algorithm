# 最低公共祖先

- [二叉搜索树的最低公共祖先](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/)
- [二叉树的最低公共祖先](https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/)


## 定义
> 对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）

## 注意点
- 题目说明了：二叉树是二叉搜索树，则满足根节点值 > 左孩子的值，根节点的值 < 右孩子的值，那么可以根据这个关系缩小遍历的范围
- 对于p,q两个节点，题目限制了必然存在于给定的二叉树中

## 二叉搜索树的最低公共祖先

### 方法1
1. 分别找到root节点到节点p和节点q的路径
2. 查找两条路径上的第一个分叉点，就是最低公共祖先

```markdown
public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
    // 题目说明了：二叉树是二叉搜索树，则满足根节点值 > 左孩子的值，根节点的值 < 右孩子的值，那么可以根据这个关系缩小遍历的范围
    // 对于p,q两个节点，题目限制了必然存在于给定的二叉树中
    // 分别找到根节点到p和q的路径，然后找到这两个路径上的第一个分叉点
    List<TreeNode> pPath = rootToNodePath(root, p);
    List<TreeNode> qPath = rootToNodePath(root, q);

    TreeNode ancestor = root;
    for (int i = 0; i < pPath.size() && i < qPath.size(); i++){
        // 不断更新分岔点
        if (pPath.get(i).val == qPath.get(i).val) {
            // 如果两个节点相同，继续往后走。否则，找到了第一个分叉点，也就是最近公共祖先
            ancestor = pPath.get(i);
        } else {
            break;
        }
    }

    return ancestor;
}

public List<TreeNode> rootToNodePath(TreeNode root, TreeNode node) {
    // 记录从root到node的路径
    List<TreeNode> path = new ArrayList<>();
    TreeNode cur = root;
    while (cur.val != node.val) {
        path.add(cur);
        if (cur.val < node.val) {
            cur = cur.right;
        } else  {
            cur = cur.left;
        }
    }
    path.add(node);
    return path;
}
```

### 方法2
递归当前节点root的左右两个子树存在以下3中情况
1. root的节点值小于p和q的节点值，那么p和q在二叉搜索树的右子树中
2. root的节点值大于p和q的节点值，那么p和q在二叉搜索树的左子树中
3. root的节点值在两个节点之间，那么root就是二者的最低公共祖先

```markdown
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // 递归查找
    // 1 当前节点值分别小于p和q的节点值，遍历二叉搜索树的右孩子
    if (root.val < p.val && root.val < q.val){
        return lowestCommonAncestor(root.right, p, q);
    }

    // 2 当前节点值大于p和q的节点值，遍历二叉搜索树的左孩子
    if (root.val > p.val && root.val > q.val) {
        return lowestCommonAncestor(root.left, p, q);
    }

    // 3 当前节点值在p和q的节点值之间，说明p和q在当前节点的左右子树中，那么当前节点就是他们的公共祖先
    return root;
}
```

## 二叉树的最低公共祖先

### 方法
分别在左右子树中寻找p和q，根据返回值判断p和q相对root的位置
- 如果p和q位于root的左右子树中，说明root是最低公共祖先
- 如果p和q都位于左子树或者右子树中，如果其中一个和root值相同，说明root是公共祖先；否则，left / right就是公共祖先

```markdown
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root.val == p.val || root.val == q.val) {
        return root;
    }

    // 如果p 和 q 位于二叉树的左子树中
    // 如果p 和 q位于二叉树的右子树中

    // 从左子树找p和q, 如果left == null，说明p和q都位于右子树中
    TreeNode left = lowestCommonAncestor(root.left, p, q);

    // 从右子树中找p和q，如果right == null, 说明p和q都位于左子树中
    TreeNode right = lowestCommonAncestor(root.right, p, q);

    // 这个判断不能少，如果在某个子树中未找到，则需要返回null。
    // 比如到达叶子节点时，判断出left = null 并且 right == null, 此时缺少这个判断会返回 root，也就是对应的叶子节点，就无法说明p和q不在左子树或者右子树中
    if (right == null && left == null) {
        return null;
    }

    if (left == null && right != null) {
        return right;
    }

    if (right == null && left != null) {
        return left;
    }

    // p 和 q 分别位于二叉树的左右子树中，那么root就是他们的最低公共祖先
    return root;
}
```