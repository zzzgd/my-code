//给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 二分查找 👍 2331 👎 0


  
package com.zgd.leetcode.editor.cn;

public class FindFirstAndLastPositionOfElementInSortedArray{

  /**
  * 34
  * 在排序数组中查找元素的第一个和最后一个位置
  * 
  * 
  *
  * 2023-06-13 15:33:18
  */  
  public static void main(String[] args) {
    Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        //思路. 二分查找, 然后考虑有可能前后有相同的值,再判断
        int l = 0,r = nums.length-1;
        while(l < r ){
            int mid = (l+r)/2;
            if(nums[mid] < target){
                //目标值在右边 ,注意这里需要加一, 否则可能会一直不满足条件死循环
                l = mid+1;
            }else if(nums[mid] == target){
                //找到目标值
                l = mid;
                r = mid;
                break;
            }else{
                r = mid;
            }
        }
        //此时必定是l=r;
        if(nums.length == 0 || nums[l] != target){
            return new int[]{-1,-1};
        }
        //判断左边界和右边界
        while(l > 0 && nums[l-1] == target){
            l--;
        }
        while(r < nums.length-1 && nums[r+1] == target){
            r++;
        }
        return  new int[]{l,r};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}