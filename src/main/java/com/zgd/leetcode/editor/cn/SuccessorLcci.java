//设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。 
//
// 如果指定节点没有对应的“下一个”节点，则返回null。 
//
// 示例 1: 
//
// 输入: root = [2,1,3], p = 1
//
//  2
// / \
//1   3
//
//输出: 2 
//
// 示例 2: 
//
// 输入: root = [5,3,6,2,4,null,null,1], p = 6
//
//      5
//     / \
//    3   6
//   / \
//  2   4
// /   
//1
//
//输出: null 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 223 👎 0

/**
 * 这个题的意思其实是从最左边,数到最右边, 即上面的树就是 1,2,3,4,5,6
 */
  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SuccessorLcci{

  /**
  * 面试题 04.06
  * 后继者
  * 
  * 
  *
  * 2023-06-07 11:30:20
  */


public static void main(String[] args) {
    Solution solution = new SuccessorLcci().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
/**
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        List<TreeNode> res = new ArrayList<>();
        dfs(root,res);
        Iterator<TreeNode> it = res.iterator();
        while(it.hasNext()){
            if (it.next() == p){
                if(it.hasNext()){
                    return it.next();
                }
            }
        }
        return null;
    }

    public void dfs(TreeNode node, List<TreeNode> res){
        if(node == null){
            return;
        }
        //先一直左
        dfs(node.left,res);
        //自身
        res.add(node);
        //右
        dfs(node.right,res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
}