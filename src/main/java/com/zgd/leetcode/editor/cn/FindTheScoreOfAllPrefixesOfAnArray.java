//定义一个数组 arr 的 转换数组 conver 为： 
//
// 
// conver[i] = arr[i] + max(arr[0..i])，其中 max(arr[0..i]) 是满足 0 <= j <= i 的所有 
//arr[j] 中的最大值。 
// 
//
// 定义一个数组 arr 的 分数 为 arr 转换数组中所有元素的和。 
//
// 给你一个下标从 0 开始长度为 n 的整数数组 nums ，请你返回一个长度为 n 的数组 ans ，其中 ans[i]是前缀 nums[0..i] 的分
//数。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [2,3,7,5,10]
//输出：[4,10,24,36,56]
//解释：
//对于前缀 [2] ，转换数组为 [4] ，所以分数为 4 。
//对于前缀 [2, 3] ，转换数组为 [4, 6] ，所以分数为 10 。
//对于前缀 [2, 3, 7] ，转换数组为 [4, 6, 14] ，所以分数为 24 。
//对于前缀 [2, 3, 7, 5] ，转换数组为 [4, 6, 14, 12] ，所以分数为 36 。
//对于前缀 [2, 3, 7, 5, 10] ，转换数组为 [4, 6, 14, 12, 20] ，所以分数为 56 。
//
// 原: 2,3,7,5,10
// max: 2,3,7,7,10
// cover: 4,6,14,12,20
// 示例 2： 
//
// 输入：nums = [1,1,2,4,8,16]
//输出：[2,4,8,16,32,64]
//解释：
//对于前缀 [1] ，转换数组为 [2] ，所以分数为 2 。
//对于前缀 [1, 1]，转换数组为 [2, 2] ，所以分数为 4 。
//对于前缀 [1, 1, 2]，转换数组为 [2, 2, 4] ，所以分数为 8 。
//对于前缀 [1, 1, 2, 4]，转换数组为 [2, 2, 4, 8] ，所以分数为 16 。
//对于前缀 [1, 1, 2, 4, 8]，转换数组为 [2, 2, 4, 8, 16] ，所以分数为 32 。
//对于前缀 [1, 1, 2, 4, 8, 16]，转换数组为 [2, 2, 4, 8, 16, 32] ，所以分数为 64 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁹ 
// 
//
// Related Topics 数组 前缀和 👍 3 👎 0


  
package com.zgd.leetcode.editor.cn;

public class FindTheScoreOfAllPrefixesOfAnArray{

  /**
  * 2640
  * 一个数组所有前缀的分数
  * 
  * 
  *
  * 2023-06-14 16:10:48
  */  
  public static void main(String[] args) {
    Solution solution = new FindTheScoreOfAllPrefixesOfAnArray().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public long[] findPrefixScore(int[] nums) {
        // 原: 2,3,7,5,10
        // max: 2,3,7,7,10
        // cover: 4,6,14,12,20
        //这个题,首先定义一个数组max,max[i]是0~i之间最大值. max[i+1] = nums[i]>max[i] ? nums[i] : max[i]
        int[] max = new int[nums.length];
        max[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max[i] = nums[i]>max[i-1] ? nums[i] : max[i-1];
        }
        //计算和
        long[] res = new long[nums.length];
        res[0] = nums[0]+max[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i-1]+nums[i]+max[i];
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}