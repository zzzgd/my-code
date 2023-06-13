//给你一个整数数组 nums 和两个整数 indexDiff 和 valueDiff 。 
//
// 找出满足下述条件的下标对 (i, j)： 
//
// 
// i != j, 
// abs(i - j) <= indexDiff 
// abs(nums[i] - nums[j]) <= valueDiff 
// 
//
// 如果存在，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 1,1,2,3  0,3,1,2
//输入：nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
//输出：true
//解释：可以找出 (i, j) = (0, 3) 。
//满足下述 3 个条件：
//i != j --> 0 != 3
//abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
//abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
// 
//
// 示例 2： 
//
//  1,1,5,5,9,9  0,3,1,4,2,5
//输入：nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
//输出：false
//解释：尝试所有可能的下标对 (i, j) ，均无法满足这 3 个条件，因此返回 false 。
// 
// 1,3,6,2  [1,2,3,6] [0,3,1,2] 1,2
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 1 <= indexDiff <= nums.length 
// 0 <= valueDiff <= 10⁹ 
// 
//
// Related Topics 数组 桶排序 有序集合 排序 滑动窗口 👍 693 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateIii{

  /**
  * 220
  * 存在重复元素 III
  * 
  * 
  *
  * 2023-06-12 16:22:01
  */  
  public static void main(String[] args) {
    Solution solution = new ContainsDuplicateIii().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        //用到滑动窗口和桶的思路
        //1. 我们需要判断 indexDiff区间内, valueDiff区间是否有两个值. indexDiff这里很明显用滑动窗口, 问题是valueDiff这里不太好判断
        //2. 提示用到桶排序. 可以考虑把 值x 的前后valueDiff 这个区间压缩计算出一个桶的坐标, 然后下一个值x+1同样判断这个坐标,如果两者区间有交集,计算出的坐标就会重复.
        //3. 比如valueDiff是20, x是30, [0,19][20,39] , 如果在同一个桶, 则肯定是两者之差<20, 否则判断前后两个桶,并取值判断
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int t = nums[i];
            int idx = mapIdx(t, valueDiff);
            if(map.containsKey(idx)){
                return true;
            }
            //判断前后
            if(map.containsKey(idx-1) && t - map.get(idx-1) <= valueDiff ){
                return true;
            }
            if(map.containsKey(idx+1) && map.get(idx+1) - t <= valueDiff ){
                return true;
            }
            map.put(idx,t);
            //同时移除掉idxDiff以前的值, 可能会有疑问,这里移除idx区间以前的桶,不会把indexDiff区间内其他这个桶的值移除掉吗?
            //其实仔细想想这种情况不会出现,因为如果idx区间内有两个值x和y的桶下标是一样的,不就已经满足条件返回true了吗
            if(i >= indexDiff){
                map.remove(mapIdx(nums[i-indexDiff],valueDiff));
            }
        }
        return false;
    }

    private int mapIdx(int i, int valueDiff){
        if(valueDiff == 0){
            return i;
        }
        //要考虑负数,0~9, -1~-10
        return i >= 0? i/valueDiff :  (i+1)/valueDiff -1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}