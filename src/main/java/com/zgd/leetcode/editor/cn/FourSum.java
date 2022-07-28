//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
// -2,-1,0,0,1,2
// -5,-3,-2,-1,0,1,2,4,5,6 >> -5,6,-3,2|-5,6,-2,2
// -4,-3,-2,-1,1,2,3,4
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 双指针 排序 👍 1310 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum{

  /**
  * 18
  * 四数之和
  * 
  * 
  *
  * 2022-07-28 15:57:37
  */  
  public static void main(String[] args) {
    Solution solution = new FourSum().new Solution();
    solution.fourSum(new int[]{2,2,2,2,2},8);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      /**
       * 代码很多,思路倒是不复杂. 看了一篇解析好像也差不多代码4层for循环.
       * 这里要注意的点有:
       * 1. 设置4个指针. 每个指针一层循环. 最开始可以先排序,方便更快的从头尾找到合适的结果
       * 2. 4个指针分别位置是 0 <= x < xx < yy < y < nums.length, 然后x和xx自增,yy和y自减
       * 3. 同时需要注意的是,类似于[2,2,2,2,2]的, 需要判断如果出现和外层一样重复的元素,要进行跳过,外层都是已经判断好的
       */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>>  res = new ArrayList<>();
        for (int y = nums.length - 1;y > 2;y--){
            if (y < nums.length - 1 && nums[y] == nums[y+1]){
                continue;
            }
            for(int x = 0;y - x > 2;x++){
                if (x > 0 && nums[x] == nums[x-1]){
                    continue;
                }
                long n = nums[x] + nums[y];
                for (int yy = y-1;yy > x+1 ;yy--){
                    if (yy < y-1 && nums[yy] == nums[yy+1]){
                        continue;
                    }
                    for(int xx = x+1;yy > xx ;xx++) {
                        if (xx > x+1 && nums[xx] == nums[xx-1]){
                            continue;
                        }
                        long m = nums[xx] + nums[yy];
                        if (n + m == target) {
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[x]);
                            list.add(nums[xx]);
                            list.add(nums[yy]);
                            list.add(nums[y]);
                            res.add(list);
                        } else if (n + m > target) {
                            //如果这个时候加起来之和已经比target大了,就应该将大的yy往前推
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}