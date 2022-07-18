//给定数组 nums 和一个整数 k 。我们将给定的数组 nums 分成 最多 k 个相邻的非空子数组 。 分数 由每个子数组内的平均值的总和构成。 
//
// 注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。 
//
// 返回我们所能得到的最大 分数 是多少。答案误差在 10⁻⁶ 内被视为是正确的。 
//
// 
//
// 示例 1: 
//
// 1,2,5,8,9
//输入: nums = [9,1,2,3,9], k = 3
//输出: 20.00000
//解释: 
//nums 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20. 
//我们也可以把 nums 分成[9, 1], [2], [3, 9]. 
//这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
// 
//
// 示例 2: 
//
// 
//输入: nums = [1,2,3,4,5,6,7], k = 4
//输出: 20.50000
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 100 
// 1 <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
// Related Topics 数组 动态规划 👍 225 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class LargestSumOfAverages{

  /**
  * 813
  * 最大平均值和的分组
  * 
  * 
  *
  * 2022-07-08 17:55:27
  */  
  public static void main(String[] args) {
    Solution solution = new LargestSumOfAverages().new Solution();
    solution.largestSumOfAverages(new int[]{4,1,7,5,6,2,3},4);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double largestSumOfAverages(int[] nums, int k) {
      //动态规划,从0到n
      //动态规划第一步,设置一个数组
      double[][] dp = new double[k+1][nums.length];
      //相当于一个矩阵,纵坐标是 第几组, 横坐标是第n组到该位置最大的分数

      //动态规划第二部,初始化这个数组.可以得知第一组的情况是直接算平均分
      double sum = 0;
      for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        dp[1][i] = sum/(i+1);
      }

      //动态规划第三步,由初始值,算出后面的各项值
      //先是两层循环,因为动态规划数组就是二维数组
      for (int kk = 2; kk <= k; kk++) {
        //因为每组不能为空,所以第2组的开始应该从下标1开始,第3组从下标2开始
        for (int i = kk-1; i < nums.length; i++) {

          //这里是重点,dp[k][i],也就是第k组的i位,它的任意值是k-1组的j(j<i)位, 加上 (i-j)的平均值.
          //比如1,2,3,4,5. 第2组的3位(1~4),可以是第1组的1位(1~2)和(3~4)的平均值
          //这个循环都是在找第kk组第i位的最大值,从后往前找
          sum = 0;
          for (int j = i; j >= kk-1 ; j--) {
            sum+= nums[j];
            dp[kk][i] = Math.max(dp[kk][i], dp[kk-1][j-1] + sum/(i-j+1) );
          }
        }
      }
      return dp[k][nums.length-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}