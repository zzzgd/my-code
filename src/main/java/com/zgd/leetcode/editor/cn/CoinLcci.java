//硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。
// (结果可能会很大，你需要将结果模上1000000007)
//
//1 - 1
//2 - 1
//3 - 1
//5 - 5x + y
//6 - 5x + y ==
//10 - 1+1+1


// 示例1: 
//
// 
// 输入: n = 5
// 输出：2
// 解释: 有两种方式可以凑成总金额:
//5=5
//5=1+1+1+1+1
// 
//
// 示例2: 
//
// 
// 输入: n = 10
// 输出：4
// 解释: 有四种方式可以凑成总金额:
//10=10
//10=5+5
//10=5+1+1+1+1+1
//10=1+1+1+1+1+1+1+1+1+1
// 
//
// 说明： 
//
// 注意: 
//
// 你可以假设： 
//
// 
// 0 <= n (总金额) <= 1000000 
// 
//
// Related Topics 数组 数学 动态规划 👍 262 👎 0


  
package com.zgd.leetcode.editor.cn;

public class CoinLcci{

  /**
  * 面试题 08.11
  * 硬币
  * 
  * 
  *
  * 2022-11-07 14:14:14
  */  
  public static void main(String[] args) {
    Solution solution = new CoinLcci().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
    //5,2,1 = 0,1,1  0,0,
class Solution {//15  0,1+(5)  0,0,1+(10)  0,0,0,1+(14) = 2+4+


      /**
       * 关键是dp的关系如何里出来, 先放上结果 x(n) = dp[i][n] = dp[i-1][n]+dp[i][n-coin[i]]
       * i表示用了几种硬币,coin={1,5,10,25}.
       * 结果分为两部分,一种是完全没用i类型硬币,组成方式是dp[i-1][n],比如没用到25的n的组合方式,是1,5,10硬币的组合数
       * 第二部分就难理解一些,是用到了i类型硬币,dp[i][n-coin[i]],是n-coin[i]的组合数,比如n=17,[用到5]的组合数是{1,5,10}硬币在n=12的组合数
       * 这么理解,dp[1][12] ({1,5}银币在12的组合数)  其实就是没有5,有一个5,有两个5的组合,我们把它们分成3堆. dp[1][17]且必定用到了5的组合数,如果我们把5放到前面
       * 任意3堆的其中一堆,不就是dp[1][17]必带5的场景吗, 所以用到i银币的情况是 dp[i][n] = dp[i][n-coin[i]]的组合数
       */
      public int waysToChange(int n) {
          int[] coins = new int[]{1,5,10,25};
          long[][] dp = new long[4][n+1];
          //初始化
          for (int i = 0; i < coins.length; i++) {
              dp[i][0] = 1;
          }
          for (int i = 0; i < n+1; i++) {
              dp[0][i] = 1;
          }
          for (int j = 1; j < coins.length; j++) {
            for (int i = 0; i <= n; i++) {
                if(i >= coins[j]){
                    dp[j][i]=  ( dp[j-1][i]   +  dp[j][i-coins[j]]) % 1_000_000_007;
                }else{
                    dp[j][i]=   dp[j-1][i] ;
                }
            }
          }
          return (int) dp[3][n];
      }

      /**
       * 一开始只能想到这个,无非是找出25x+10y+5z+k = n 的结果组合数,虽然结果是对的,但是超时
       * 想到了 x(n) = (25) + x(n-25) 的这种动态规划的关系,但是没完全想出来
       * @param n
       * @return
       */
      //16 = 3*5+1 = 3 = 000 001 002 003  010 011 = 15+(1), 10 +(6) 5+(11)
      //21 = 4*5+1 = 4 = 000 001  002 003 004 010 011 012 020
      //16 = 0,1,3,== 0,1,1,(1)  0,0,3
    public int waysToChange2(int n) {//15 0,0,0,15  0,0,1,10  0,0,2,5, 0,0,3,0  ,0,1,0,5  0,1,1,0
        long r = 0L;
        n = n/5;
        for (int i = 0; i*5 <= n; i++) {
            for (int j = 0; j*2 <= n-i*5; j++) {
                for (int k = 0; k*1 <= n-i*5-j*2; k++) {
                    r = (r+1)% 1_000_000_00_7;
                }

            }
        }
        return (int) r;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}