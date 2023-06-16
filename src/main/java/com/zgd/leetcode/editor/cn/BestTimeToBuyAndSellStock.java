//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁴ 
// 
//
// Related Topics 数组 动态规划 👍 2997 👎 0


  
package com.zgd.leetcode.editor.cn;

public class BestTimeToBuyAndSellStock{

  /**
  * 121
  * 买卖股票的最佳时机
  * 
  * 
  *
  * 2023-06-16 15:19:42
  */  
  public static void main(String[] args) {
    Solution solution = new BestTimeToBuyAndSellStock().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {
        //因为没能买入的时间比卖出的时间晚, 即最小值是实时更新的,但是没有最大值的概念, 只有当前值-之前的最小值=利润 的最大值
        int min = 10000;
        int maxProfit = 0;
        for (int price : prices) {
            if(price < min){
                min = price;
            }
            maxProfit = Math.max(maxProfit,price-min);
        }
        return maxProfit;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}