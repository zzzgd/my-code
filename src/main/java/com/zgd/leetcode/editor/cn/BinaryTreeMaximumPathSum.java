//二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定
//经过根节点。 
//
// 路径和 是路径中各节点值的总和。 
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6 
//
// 示例 2：   -10
//         9   20
//       \ \   15 7
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围是 [1, 3 * 10⁴] 
// -1000 <= Node.val <= 1000
// 
//
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1980 👎 0


  
package com.zgd.leetcode.editor.cn;

public class BinaryTreeMaximumPathSum{

  /**
  * 124
  * 二叉树中的最大路径和
  * 
  * 
  *
  * 2023-06-16 11:19:41
  */  
  public static void main(String[] args) {
    Solution solution = new BinaryTreeMaximumPathSum().new Solution();

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
    /**
     * 因为节点取值是 -1000 到 1000
     */
    int maxval = -1000 ;
    public int maxPathSum(TreeNode root) {
        //首先得用到深度优先遍历, 每次遍历完一个节点, 判断它的左,右分支哪个大, 然后和自身相加, 作为自己最新的值. 与此同时和另一个小的分支也加一下,判断能否打破现在最大的结果值.如果能则更新(表示 左-自-右 这样的路径)
        // ：        -10
        //         9   20
        //       \ \   15 7
        int dfs = dfs(root);
        return maxval > dfs ? maxval : dfs;

    }

    private int dfs(TreeNode root){
        if(root == null){
            return 0;
        }
        //左分支最大路径值
        int left = dfs(root.left);
        int right = dfs(root.right);
        //选择两个分支大的那个,加上自身, 就是一条路径
        int a =  Math.max(left, right) + root.val;
        // 三种情况, 一,自身, 二, 自身+一条分支最大累计, 三. 自身+两个分支, 其中前两者能作为返回值继续给父节点计算
        int max1_2 = Math.max(root.val,Math.max(left,right)+root.val);
        int max3 = left+root.val+right;
        maxval = maxval >max1_2 ? maxval : max1_2;
        maxval = maxval > max3 ? maxval : max3;
        return max1_2;
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