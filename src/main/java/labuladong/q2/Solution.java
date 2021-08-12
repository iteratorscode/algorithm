package labuladong.q2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

/**
 * 二叉树的最小高度
 *
 * @author iterators
 * @since 2021/08/12
 */
public class Solution {

    static class TreeNode {

        public Integer value;

        public TreeNode left;

        public TreeNode right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.value = 0;

        int minHeight = getMinHeight(root);
        System.out.println("minHeight = " + minHeight);
    }

    private static int getMinHeight(TreeNode root) {

        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        Set<TreeNode> visitedTreeNodeSet = new HashSet<>();
        treeNodes.offer(root);
        visitedTreeNodeSet.add(root);

        int height = 1;
        while (!treeNodes.isEmpty()) {

            // 获取当前层的节点数目
            int size = treeNodes.size();
            // 从当前节点向四周遍历
            for (int i = 0; i < size; i++) {
                TreeNode node = treeNodes.poll();
                // 当前层上找到了一个叶子节点，此时就是树的最小高度，返回
                if (node!= null && node.right == null && node.left == null) {
                    return height;
                }
                Optional.ofNullable(node.left).ifPresent(treeNodes::offer);
                Optional.ofNullable(node.right).ifPresent(treeNodes::offer);
            }
            // 当前层上的节点遍历完，数的高度加1，遍历下一层的所有节点
            height++;
        }

        return height;
    }

}
