//一家商店正在打折销售糖果。每购买 两个 糖果，商店会 免费 送一个糖果。 
//
// 免费送的糖果唯一的限制是：它的价格需要小于等于购买的两个糖果价格的 较小值 。 
//
// 
// 比方说，总共有 4 个糖果，价格分别为 1 ，2 ，3 和 4 ，一位顾客买了价格为 2 和 3 的糖果，那么他可以免费获得价格为 1 的糖果，但不能获得
//价格为 4 的糖果。 
// 
//
// 给你一个下标从 0 开始的整数数组 cost ，其中 cost[i] 表示第 i 个糖果的价格，请你返回获得 所有 糖果的 最小 总开销。 
//
// 
//
// 示例 1： 
//
// 输入：cost = [1,2,3]
//输出：5
//解释：我们购买价格为 2 和 3 的糖果，然后免费获得价格为 1 的糖果。
//总开销为 2 + 3 = 5 。这是开销最小的 唯一 方案。
//注意，我们不能购买价格为 1 和 3 的糖果，并免费获得价格为 2 的糖果。
//这是因为免费糖果的价格必须小于等于购买的 2 个糖果价格的较小值。
// 
//
// 示例 2： 
//
// 输入：cost = [6,5,7,9,2,2]
//输出：23
//解释：最小总开销购买糖果方案为：
//- 购买价格为 9 和 7 的糖果
//- 免费获得价格为 6 的糖果
//- 购买价格为 5 和 2 的糖果
//- 免费获得价格为 2 的最后一个糖果
//因此，最小总开销为 9 + 7 + 5 + 2 = 23 。
// 
//
// 示例 3： 
//
// 输入：cost = [5,5]
//输出：10
//解释：由于只有 2 个糖果，我们需要将它们都购买，而且没有免费糖果。
//所以总最小开销为 5 + 5 = 10 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= cost.length <= 100 
// 1 <= cost[i] <= 100 
// 
//
// Related Topics 贪心 数组 排序 👍 14 👎 0


  
package com.zgd.leetcode.editor.cn;

public class MinimumCostOfBuyingCandiesWithDiscount{

  /**
  * 2144
  * 打折购买糖果的最小开销
  * 
  * 
  *
  * 2023-06-19 18:10:24
  */  
  public static void main(String[] args) {
    Solution solution = new MinimumCostOfBuyingCandiesWithDiscount().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumCost(int[] cost) {
        //先排序, 然后两个两个的取
        //使用快速排序法
        quickSort(cost,0,cost.length-1);
        int n = 1;
        int res = 0;
        for (int i = cost.length-1; i >= 0 ; i--) {
            if(n % 3 != 0){
                res += cost[i];
            }
            n++;
        }
        return res;
    }

    private void quickSort(int[] arr,int a,int b){
        if(a>=b){
            return;
        }
        int l = a;
        int r = b;
        int x = arr[a];
        while(a < b){
            while(arr[b] >= x && b > a){
                b--;
            }
            if(b > a){
                //此时从后往前找到了下标b小于x的
                arr[a] = arr[b];
                a++;
            }

            while(arr[a] <= x && b > a){
                a++;
            }
            if(b > a){
                //此时从后往前找到了下标a 大于x的
                arr[b] = arr[a];
                b--;
            }
        }
        //此时a=b;
        arr[a] = x;
        //继续拆分
        quickSort(arr,l,a);
        quickSort(arr,a+1,r);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}