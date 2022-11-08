//给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历） 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[15,7],[9,20],[3]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 630 👎 0


  
package com.zgd.leetcode.editor.cn;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversalIi{

  /**
  * 107
  * 二叉树的层序遍历 II
  * 
  * 
  *
  * 2022-11-08 15:36:09
  */  
  public static void main(String[] args) {
    Solution solution = new BinaryTreeLevelOrderTraversalIi().new Solution();
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //典型的宽度遍历. 使用ArrayDeque/LinkedList 来当栈,或者直接用list的add(0,x)
        ArrayDeque<List<Integer>> stack = new ArrayDeque<>();
        LinkedList<TreeNode>  queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int rowSize = queue.size();
            List<Integer> rowList = new ArrayList<>();
            for (int i = 0; i < rowSize; i++) {
                //先取出来,遍历它的子节点重新放到后面
                TreeNode node = queue.pop();
                if (node == null){
                    continue;
                }
                rowList.add(node.val);
                //重新放进去
                TreeNode left = node.left;
                if (left != null ){
                    queue.offer(left);
                }
                TreeNode right = node.right;
                if (right != null ){
                    queue.offer(right);
                }
            }
            //放到栈,lifo
            if (!rowList.isEmpty()){
                stack.push(rowList);
            }
        }
        List<List<Integer>> res = new ArrayList<>(stack.size());
        //再遍历stack
        while (!stack.isEmpty()){
            res.add(stack.poll());
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