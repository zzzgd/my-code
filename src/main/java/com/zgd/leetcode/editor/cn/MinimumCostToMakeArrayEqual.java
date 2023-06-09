//给你两个下标从 0 开始的数组 nums 和 cost ，分别包含 n 个 正 整数。 
//
// 你可以执行下面操作 任意 次： 
//
// 
// 将 nums 中 任意 元素增加或者减小 1 。 
// 
//
// 对第 i 个元素执行一次操作的开销是 cost[i] 。 
//
// 请你返回使 nums 中所有元素 相等 的 最少 总开销。 
//
// 
//
// 示例 1：
// [ 4,1,3,2] [1,2,3,14] [1,2,3,5]
// 输入：nums = [1,3,5,2], cost = [2,3,1,14]
//输出：8
//解释：我们可以执行以下操作使所有元素变为 2 ：
//- 增加第 0 个元素 1 次，开销为 2 。
//- 减小第 1 个元素 1 次，开销为 3 。
//- 减小第 2 个元素 3 次，开销为 1 + 1 + 1 = 3 。
//总开销为 2 + 3 + 3 = 8 。
//这是最小开销。
// 
//
// 示例 2： 
//
// 输入：nums = [2,2,2,2,2], cost = [4,2,8,1,3]
//输出：0
//解释：数组中所有元素已经全部相等，不需要执行额外的操作。
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length == cost.length 
// 1 <= n <= 10⁵ 
// 1 <= nums[i], cost[i] <= 10⁶ 
// 
//
// Related Topics 贪心 数组 二分查找 前缀和 排序 👍 33 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.Arrays;

public class MinimumCostToMakeArrayEqual{

  /**
  * 2448
  * 使数组相等的最小开销
  * 
  * 
  *
  * 2023-06-09 14:02:53
  */  
  public static void main(String[] args) {
    Solution solution = new MinimumCostToMakeArrayEqual().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public long minCost(int[] nums, int[] cost) {
        //普通的方法超时了
        //这个思路不要被cost吓到了, 首先不看cost, 如果需要找出目标值x,使得数组调整的次数最少, 那不用多说可以想象 x为横坐标,次数为纵坐标的话,是一个U型
        //可以二分法找到最小值

        //加上cost以后,把它看做是重复个num元素即可, 比如nums[0]的cost是5, 理解有5个nums[0]
        //依次为基础,再次根据二分法找到最小值
        //获取nums最大值
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > max){
                max = nums[i];
            }
        }

        int l = 0, r = max;
        while ( l < r){
            int mid = (l+r) / 2;
            //如果 mid的总cost < 右边mid+1的总cost, 说明是朝上的趋势, 最低点在mid左边. 否则在右边
            //因为比较的是mid 和 mid+1的结果,
            if(  getCost(mid,nums,cost) <= getCost(mid+1,nums,cost)){
                //最低点在右边
                r = mid;
            }else{
                //最低点在左边,或者当前就是最低点
                l = mid+1;
            }
        }
        return getCost(l,nums,cost);
    }

    private long getCost(int x,int[] nums,int[] cost){
        long res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += (long)Math.abs(nums[i] - x)*1L*cost[i];
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


    /**
     * 最普通的方法, 一个个试
     * 果不其然的超时了
     */
    class Solution2 {
        public long minCost(int[] nums, int[] cost) {
            //先按普通的笨办法做一遍看看. 首先要确定nums的范围
            int min = nums[0],max = 0;
            for (int i = 0; i < nums.length; i++) {
                if(nums[i] < min){
                    min = nums[i];
                }
                if(nums[i] >max){
                    max = nums[i];
                }
            }
            //在这个区间内取
            long minCost = Long.MAX_VALUE;
            for (int i = min; i <= max; i++) {
                long cos = 0;
                for (int j = 0; j < nums.length; j++) {
                    cos += (long)Math.abs(nums[j]-i)*cost[j];
                }
                if (cos < minCost){
                    minCost = cos;
                }
            }
            return minCost;

        }
    }
}