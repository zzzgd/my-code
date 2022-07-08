//给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。 
//
// 假设二叉树中至少有一个节点。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: root = [2,1,3]
//输出: 1
// 
//
// 示例 2: 
//
// 
//
// 
//输入: [1,2,3,4,null,5,6,null,null,7]
//输出: 7
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [1,10⁴] 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 352 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class FindBottomLeftTreeValue{

  /**
  * 513
  * 找树左下角的值
  * 
  * 
  *
  * 2022-07-08 16:32:12
  */  
  public static void main(String[] args) {
    Solution solution = new FindBottomLeftTreeValue().new Solution();
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
    public int findBottomLeftValue(TreeNode root) {
      ArrayDeque<TreeNode> treeNodes = new ArrayDeque<>();
      treeNodes.add(root);
      return wfs(treeNodes,0);
    }

  /**
   * 宽度优先
   * @param node
   * @param res
   * @return
   */
    //测试用例:[1,2,3,4,null,5,6,null,null,7] 测试结果:4 期望结果:7
  //   1
  // 2   3
  //4 /   5  6
  //     7
    private int wfs(Queue<TreeNode> queue,int res){
      while(!queue.isEmpty()){
        for (int i = 0; i < queue.size(); i++) {
          TreeNode poll = queue.poll();
          if (poll == null){
            continue;
          }
          res = poll.val;
          //从右往左放到队列
          if (poll.right != null){
            queue.add(poll.right);
          }
          if (poll.left != null){
            queue.add(poll.left);
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