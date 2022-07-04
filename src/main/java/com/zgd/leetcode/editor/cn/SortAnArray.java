//给你一个整数数组 nums，请你将该数组升序排列。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,2,3,1]
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：nums = [5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -5 * 10⁴ <= nums[i] <= 5 * 10⁴ 
// 
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 👍 603 👎 0


  
package com.zgd.leetcode.editor.cn;

public class SortAnArray{

  /**
  * 912
  * 排序数组
  * 
  * 
  *
  * 2022-07-04 16:20:30
  */  
  public static void main(String[] args) {
    Solution solution = new SortAnArray().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] sortArray(int[] nums) {
      quicksort(nums,0,nums.length-1);
      return nums ;
    }

    private void quicksort(int[] nums,int a,int b){
      if (a >= b ){
        return;
      }
      int l = a,r = b;
      //基准值
      int x = nums[l];
      while(l < r){
        while (l < r && nums[r]>=x){
          r--;
        }
        if (l < r){
          nums[l++] = nums[r];
        }
        while (l < r && nums[l]<=x){
          l++;
        }
        if (l < r){
          nums[r--] = nums[l];
        }
      }
      nums[l]= x;
      quicksort(nums,a,l);
      quicksort(nums,l+1,b);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}