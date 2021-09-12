package leetcode.lowestcommonancestor;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树的最低公共祖先
 *
 * 参考题型1：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 * 参考题型2：https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 *
 * @author iterators
 * @since 2021/09/12
 */
public class LowestCommonAncestor {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

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
}
