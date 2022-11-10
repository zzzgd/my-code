//二叉树上有 n 个节点，按从 0 到 n - 1 编号，其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。 
//
// 只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。 
//
// 如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。右子节点也符合该规则。 
//
// 注意：节点没有值，本问题中仅仅使用节点编号。 
//
// 
//
// 示例 1： 
//      0
//    1   2
//       3
// 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
//输出：true
// 
//
// 示例 2： 
//      0
//   1     2
//      3
// 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
//输出：false
// 
//
// 示例 3： 
//     0
//   1
//
// 输入：n = 2, leftChild = [1,0], rightChild = [-1,-1]
//输出：false
// 
//
// 示例 4： 
//
// 
//
// 输入：n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10^4 
// leftChild.length == rightChild.length == n 
// -1 <= leftChild[i], rightChild[i] <= n - 1 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 并查集 图 二叉树 👍 99 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateBinaryTreeNodes{

  /**
  * 1361
  * 验证二叉树
  * 
  * 
  *
  * 2022-11-08 18:04:31
  */  
  public static void main(String[] args) {
    Solution solution = new ValidateBinaryTreeNodes().new Solution();
    solution.validateBinaryTreeNodes(4,new int[]{3,-1,1,-1},new int[]{-1,-1,0,-1});
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //   2
      //  1  0
      //    3

      /**
       * 解答成功:
       * 	执行耗时:1502 ms,击败了5.06% 的Java用户
       * 	内存消耗:213.7 MB,击败了5.06% 的Java用户
       *
       * =_= 先不管结果如何,至少是在一遍遍试错中做出来了.
       * 其实理解题目后,思路不难, 就是深度遍历, 只要新的节点,再之前的节点中没有出现过, 就算一棵树(没有闭环). 同时要求所有节点都用到(即深度遍历后的list,等于给出的非null节点数)
       * 这里有两个坑,就是我们需要从0~n的位置都跑一边,看看是否只有一个正确的结果
       * 其次就是不能有闭环,在dfs的时候,一旦有闭环就会死循环,所以我们判断有闭环的时候,就得return一个标记不要循环了.这个标记就是null. 同时为了知道我们是否标记过,需要记录遍历过的节点,path
       *
       * 这个有空再看最优解的解析吧
       *
       */
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        //要成为一个正确的树,至少2个条件.
        //1. 深度遍历, 已经走过的节点,不能再走
        //2. 左边走过的分支节点,右边不能再走
        //root节点为0号标记 ---- 错误, 题目并没有说一定是0号节点为根节点. 那就每个节点都试一次,看看是否只能有一个树
        int num = getNodeNum(leftChild, rightChild);
        int tree = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> dfs = dfs(leftChild, rightChild, i,new ArrayList<>());
            //因为dfs还会把root节点放进去
            if( dfs != null && dfs.size()==(num+1)){
                tree++;
            }
            if (tree > 1){
                return false;
            }
        }
        return tree == 1;
    }

      /**
       *    2
       *  1   0
       *     3
       */
      private int getNodeNum(int[] leftChild, int[] rightChild) {
          //计算两个数组除了-1以外有几个节点
          int num = 0;
          for (int i = 0; i < leftChild.length; i++) {
              if (leftChild[i] >=0){
                  num++;
              }
          }
          for (int i = 0; i < rightChild.length; i++) {
              if (rightChild[i] >=0){
                  num++;
              }
          }
          return num;
      }

      Map<Integer,List<Integer>> cache = new HashMap<>();
      private List<Integer> dfs(int[] leftChild, int[] rightChild,int node,List<Integer> path){
          List<Integer> childlist = new ArrayList<>();
        if (node == -1){
            return childlist;
        }
        if (path.contains(node)){
            return null;
        }
        if (cache.containsKey(node)){
            List<Integer> childlis = cache.get(node);
            if (childlis == null){
                return null;
            }
            path.addAll(childlis);
            return childlis;
        }
          path.add(node);
          childlist.add(node);
        int left = leftChild[node];
        int right = rightChild[node];
          List<Integer> leftlist = dfs(leftChild, rightChild, left,path);
          if (leftlist == null){
              cache.put(node,null);
              return null;
          }

          childlist.addAll(leftlist);
          List<Integer> rightlist = dfs(leftChild,rightChild,right,path);
          if (rightlist == null){
              cache.put(node,null);
              return null;
          }
          childlist.addAll(rightlist);
            cache.put(node,childlist);
        return childlist;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}