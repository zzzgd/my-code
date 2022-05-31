//给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[20,9],[15,7]]
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
// -100 <= Node.val <= 100 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 643 👎 0


package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeZigzagLevelOrderTraversal {

  /**
   * 103
   * 二叉树的锯齿形层序遍历
   * <p>
   * <p>
   * <p>
   * 2022-05-31 19:55:54
   */
  public static void main(String[] args) {
    Solution solution = new BinaryTreeZigzagLevelOrderTraversal().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)


  class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
      //树的bfs， 宽度优先遍历法， 先把每个节点一层一层的放到list
      //然后结果再按奇偶行分别从左到右或者从右到左取上一列的值
      List<TreeNode> rowList = new ArrayList<>();

      List<List<Integer>> list = new ArrayList<>();
      if (root == null) {
        return list;
      }
      //记录行号
      int r = 0;
      rowList.add(root);
      //第一行要手动传
      List<Integer> first = new ArrayList<>();
      first.add(root.val);
      list.add(first);
      //如果上一栈为空了，说明数据遍历完了
      while (!rowList.isEmpty()) {
        //从上一行的列表中依次取出每一个元素
        TreeNode node;
        //每层的节点数是 rowList.size()
        int size = rowList.size();
        for (int i = 0; i < size; i++) {
          node = rowList.remove(0);
          //先把值放进去list，先不考虑顺序, 继续放到这个list。 等遍历完size，一层就处理完了变成下一层
          if (node.right != null) {
            rowList.add(node.right);
          }
          if (node.left != null) {
            rowList.add(node.left);
          }
        }

        //一行扫描完以后，取这一行的值
        if (rowList.size() > 0) {
          List<Integer> row = new ArrayList<>();
          for (int i = 0; i < rowList.size(); i++) {
            //根据奇偶按不同的顺序取此行的值,奇数需要从右到左
            if (r % 2 == 1) {
              row.add(rowList.get(rowList.size() - 1 - i).val);
            } else {
              row.add(rowList.get(i).val);
            }
          }
          list.add(row);
        }
        r++;
      }
      return list;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
