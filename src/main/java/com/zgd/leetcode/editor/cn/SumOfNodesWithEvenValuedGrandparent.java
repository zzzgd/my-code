//给你一棵二叉树，请你返回满足以下条件的所有节点的值之和： 
//
// 
// 该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。） 
// 
//
// 如果不存在祖父节点值为偶数的节点，那么返回 0 。 
//
// 
//
// 示例： 
//
// 
//
// 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
//输出：18
//解释：图中红色节点的祖父节点的值为偶数，蓝色节点为这些红色节点的祖父节点。
//              6
//           7      8
//         2   7     1   3
//        9 /  1 4   / /  / 5
//
//
// 提示： 
//
// 
// 树中节点的数目在 1 到 10^4 之间。 
// 每个节点的值在 1 到 100 之间。 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 93 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayDeque;

public class SumOfNodesWithEvenValuedGrandparent{

  /**
  * 1315
  * 祖父节点值为偶数的节点和
  * 
  * 
  *
  * 2023-12-11 18:02:12
  */  
  public static void main(String[] args) {
    Solution solution = new SumOfNodesWithEvenValuedGrandparent().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int sumEvenGrandparent(TreeNode root) {
        //bfs 宽度优先
        return bfs(root);

    }

    private int bfs(TreeNode root){
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        //记录祖孙三代
        int res = 0;
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = deque.pop();
                if (pop.left != null){
                    deque.addLast(pop.left);
                }
                if (pop.right != null){
                    deque.addLast(pop.right);
                }
                //如果当前是偶数,判断孙子
                if (pop.val%2==0){
                    if (pop.left != null && pop.left.left != null){
                        res+=pop.left.left.val;
                    }
                    if (pop.left != null && pop.left.right != null){
                        res+=pop.left.right.val;
                    }
                    if (pop.right != null && pop.right.left != null){
                        res+=pop.right.left.val;
                    }
                    if (pop.right != null && pop.right.right != null){
                        res+=pop.right.right.val;
                    }
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

 public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
}