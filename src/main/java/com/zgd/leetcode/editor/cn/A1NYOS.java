//给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。 
//
// 
//
// 示例 1： 
//
// 
//输入: nums = [0,1]
//输出: 2
//说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。 
//
// 示例 2： 
//
// 
//输入: nums = [0,1,0]
//输出: 2
//说明: [0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// nums[i] 不是 0 就是 1 
// 
//
// 
//
// 注意：本题与主站 525 题相同： https://leetcode-cn.com/problems/contiguous-array/ 
// Related Topics 数组 哈希表 前缀和 👍 66 👎 0


package com.zgd.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class A1NYOS {

  /**
   * 剑指 Offer II 011
   * 0 和 1 个数相同的子数组
   * <p>
   * 这个题可以想象成一个 坐标系, 上面一条曲线. 设置变量y,遇到1则y+1,遇到0则y-1, 横坐标是索引,纵坐标是计算之和.
   * 那两个坐标,他们y相同,则说明他们之间1和0的数量一样.所以问题就是找到最长的这两个坐标
   * <p>
   * 100001001
   * <p>
   * 2022-05-20 15:14:09
   */
  public static void main(String[] args) {
    Solution solution = new A1NYOS().new Solution();
    solution.findMaxLength(new int[]{0, 1, 0});
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int findMaxLength(int[] nums) {

      //用map来表示这个曲线,key是y,value是x
      Map<Integer, Integer> map = new HashMap<>();
      //设置一个值y,初始值设置为x=-1,遍历数组,遇到1加1,遇到0减1
      int y = 0;
      map.put(y,-1);
      int max = 0;
      for (int i = 0; i < nums.length; i++) {
        y += nums[i] == 1 ? 1: -1;
        //判断 如果y相同,说明他们之间的1和0数量一样,判断x区间 //注意这里不是判断y==0
        if (map.containsKey(y)){
          max = Math.max(max,i - map.get(y));
        }else{
          //这里要在y没找到相同的时候再put, 确保y对应的i是最小的, 这样才能保证最长距离
          map.put(y,i);
        }
      }

      return max;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
