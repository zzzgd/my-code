//三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，
// 你需要对结果模1000000007。
//
// 示例1: 
//
// 
// 输入：n = 3 
// 输出：4
// 说明: 有四种走法,3,111,12,21 n=2有2种, n=1有1中.  X(3) = B(1步)+X(2) OR B(2步)+X(1) OR B(3步)+X(0) = 2+1+1
//
//n=4 1111,112,121,211,22,13,31 7种  X(4)=B(1步)+X(3) OR B(2步)+X(2) OR B(3步)+X(1) = 4+2+1 = 7;
//
// 示例2: 
//
// 
// 输入：n = 5
// 输出：13    X(4)=B(1步)+X(4) OR B(2步)+X(3) OR B(3步)+X(2) = 7+4+2
// 
//
// 提示: 
//
// 
// n范围在[1, 1000000]之间 
// 
//
// Related Topics 记忆化搜索 数学 动态规划 👍 90 👎 0


  
package com.zgd.leetcode.editor.cn;

public class ThreeStepsProblemLcci{

  /**
  * 面试题 08.01
  * 三步问题
  * 
  * 
  *
  * 2022-10-31 11:06:15
  */  
  public static void main(String[] args) {
    Solution solution = new ThreeStepsProblemLcci().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      private static final int MOD = 1000_000_007;
      /**
       * 一开始想到的是回溯法,但是肯定时间会超. 看tips说动态规划,找了下发现这个规律. n=0和n=1都是1次,n=2是2次,n=3是4次
       * 不难发现以下规律: X(n) = X(N-1) + X(N-2) + X(N-3)
       * X(3) = B(1步)+X(2) OR B(2步)+X(1) OR B(3步)+X(0) = 2+1+1
       * //n=4 1111,112,121,211,22,13,31 7种  X(4)=B(1步)+X(3) OR B(2步)+X(2) OR B(3步)+X(1) = 4+2+1 = 7;
       * // 输入：n = 5
       * // 输出：13    X(5)=B(1步)+X(4) OR B(2步)+X(3) OR B(3步)+X(2) = 7+4+2
       * @param n
       * @return
       */
    public int waysToStep(int n) {
        //即一个上n级楼梯的问题,可以分解为,他分别先上1步,2步,3步,以及后面n-1,n-2和n-3阶楼梯的问题
        int[] x = new int[n+1 <= 3?4:n+1];
        x[0] = x[1] = 1;
        x[2] = 2; //1,1 和 2
        x[3] = 4;//1,1,1 和 1,2 和 2,1 和 3
        if (n<=3){
            return x[n];
        }
        for (int i = 4; i <= n ; i++) {
            long t = ((long)x[i-1] + (long)x[i-2] +(long)x[i-3]);
            x[i] = (int) (t % MOD);
        }
        return (int)x[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}