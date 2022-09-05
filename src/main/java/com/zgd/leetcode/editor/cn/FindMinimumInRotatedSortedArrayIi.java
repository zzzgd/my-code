//已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，
// 原数组 nums = [0,1,4,4,5,6,7] 在变
//化后可能得到：
//
// 
// 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4] 
// 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7] 
// 
//
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], 
//..., a[n-2]] 。 
//
// 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。 
//
// 你必须尽可能减少整个过程的操作步骤。 
//

//
// 示例 1： 
//
// 
//输入：nums = [1,3,5]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,0,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 5000 
// -5000 <= nums[i] <= 5000 
// nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转 
// 
//
// 
//
// 进阶：这道题与 寻找旋转排序数组中的最小值 类似，但 nums 可能包含重复元素。允许重复会影响算法的时间复杂度吗？会如何影响，为什么？ 
//
// Related Topics 数组 二分查找 👍 541 👎 0


  
package com.zgd.leetcode.editor.cn;

public class FindMinimumInRotatedSortedArrayIi{

  /**
  * 154
  * 寻找旋转排序数组中的最小值 II
  * 
  * 
  *
  * 2022-08-31 17:41:54
  */  
  public static void main(String[] args) {
    Solution solution = new FindMinimumInRotatedSortedArrayIi().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMin(int[] nums) {

        // 1,2,3,4,5,6 -> 6,1,2,3,4,5 -> 5,6,1,2,3,4 重点就是找到突然变小的值,如果找到了就是这个. 如果没找到就是第一个
        // 1,2,3,4,5,5,6 6,1,2,3,4,5,5  5,6,1,2,3,4,5  5,5,6,1,2,3,4,5

        int cur = nums[0];
        if (nums.length == 1){
          return cur;
        }
        int next =1;
        while (next < nums.length && cur <= nums[next]){
            cur = nums[next];
            next++;
        }
        if (next < nums.length){
            //说明是下一个元素比自己小
            return nums[next];
        }
        //否则说明遍历到了末尾,直接取第一个
        return nums[0];

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}