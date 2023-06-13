//给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i 
//- j) <= k 。如果存在，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,1], k = 3
//输出：true 
//
// 示例 2： 
//
// 
//输入：nums = [1,0,1,1], k = 1
//输出：true 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,3,1,2,3], k = 2
//输出：false 
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 0 <= k <= 10⁵ 
// 
//
// Related Topics 数组 哈希表 滑动窗口 👍 603 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateIi{

  /**
  * 219
  * 存在重复元素 II
  * 
  * 
  *
  * 2023-06-13 15:20:21
  */  
  public static void main(String[] args) {
    Solution solution = new ContainsDuplicateIi().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //滑动窗口, 需要满足支持频繁增删,又能迅速定位等值匹配, 可以用map,或者set
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            //窗口,要移除前面的
            if(i >= k){
                set.remove(nums[i-k]);
            }
        }
        return false;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}