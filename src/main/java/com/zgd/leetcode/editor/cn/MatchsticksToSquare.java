//你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能
//折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。 
//
// 如果你能使这个正方形，则返回 true ，否则返回 false 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: matchsticks = [1,1,2,2,2]
//输出: true
//解释: 能拼成一个边长为2的正方形，每边两根火柴。
// 
//
// 示例 2: 
//
// 
//输入: matchsticks = [3,3,3,3,4]
//输出: false
//解释: 不能用所有火柴拼成一个正方形。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= matchsticks.length <= 15 
// 1 <= matchsticks[i] <= 10⁸ 
// 
// Related Topics 位运算 数组 动态规划 回溯 状态压缩 👍 417 👎 0


package com.zgd.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

public class MatchsticksToSquare {

  /**
   * 473 火柴拼正方形
   * <p>
   * 这题一开始就理解猜错了, 以为是连续的元素之间组成正方形, 这种比较简单:
   * <p>
   * 要组成正方形, 首先是: 1:加起来能被4整除, 2: 每个元素不能比这个平均值大 后面发现不一定是连续元素, 想了半天去看解答, 看了几篇后豁然开朗.
   * <p>
   * 首先思路就错了,不应该想着怎么找出合适的元素来组成平均值. 这样的话暴力遍历肯定不行. 换个思路, 想象有一个正方形的凹槽, 我们只需要一根根火柴放到凹槽中, 不合适就取出来放下一个边.</p>
   * <p>
   * 也就是一个length为4的emp[]空数组, 遍历我们的火柴数组m[i], 依次放到emp[0], 放不下就换下一个火柴(所以需要把火柴从大到小排序, 至于为什么是大的先,因为小的话一开始的遍历必定会涉及到小火柴的递归叠加)
   * 如果放得下, 就递归,同时火柴数组的元素i++, 直到所有元素都遍历完也没有提前退出, 就说明合适, 同时不需要考虑火柴重复使用的问题
   * <p>
   * 2022-06-21 18:16:10
   */
  public static void main(String[] args) {
    Solution solution = new MatchsticksToSquare().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean makesquare(int[] matchsticks) {
      if (matchsticks.length < 4) {
        return false;
      }
      //1. 先算平均每个长度,不能被整除则false
      int n = 0;
      for (int matchstick : matchsticks) {
        n += matchstick;
      }
      if (n % 4 != 0) {
        return false;
      }
      int len = n / 4;

      //将数组排序
      Arrays.sort(matchsticks);
      //dfs回溯法
      return dfs(matchsticks,len,new int[4],matchsticks.length-1);
    }

    /**
     * 回溯, 想象有一个正方形的凹槽,我们把从大到小的火柴,依次放到每个边. 放得下的话就换下一根火柴. 4个边都放不下的话就回溯
     * @param matchsticks 火柴数组
     * @param len 已经算出来的,平均每个边长度
     * @param emp 想象的用来放火柴的四边形凹槽
     * @param i 当前的火柴下标
     * @return
     */
    private boolean dfs(int[] matchsticks, int len, int[] emp, int i) {
      if (i < 0){
        return true;
      }
      //火柴比正方形边长还长,否定
      if (matchsticks[i]>len){
        return false;
      }
      for (int j = 0; j < 4; j++) {
        if (emp[j] + matchsticks[i] > len){
          continue;
        }else{
          emp[j] += matchsticks[i];
          if(dfs(matchsticks,len,emp,i-1)){
            return true;
          }
          emp[j] -= matchsticks[i];
        }
      }
      return false;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}