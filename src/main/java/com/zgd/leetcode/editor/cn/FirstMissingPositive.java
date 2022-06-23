//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。 
//请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,0]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,4,-1,1]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,8,9,11,12]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
// Related Topics 数组 哈希表 👍 1500 👎 0


package com.zgd.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {

  /**
   * 41 缺失的第一个正数
   * <p>
   * 如果用普通方法,能满足时间复杂度O(n),但是却需要N(数组长度)的空间,优化的思路,就是在原数组基础上打标记.
   * 一个N长度的数组,没出现过的最小正整数x,一定是1~N+1,如果数组里的值都不重复且依次都在1~N之间,则x一定是N+1.如果重复或者不在这个区间,则x还能更小
   * <p>
   * 2022-06-23 16:46:34
   */
  public static void main(String[] args) {
    Solution solution = new FirstMissingPositive().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /**
     * 打标记法+数组元素替换. 一个N长度的数组,没出现过的最小正整数x,一定是1~N+1,如果数组里的值都不重复且依次都在1~N之间,则x一定是N+1.如果重复或者不在这个区间,则x还能更小 我们可以遍历每个数组元素,如果元素在1~N之间,将这个元素n-1位置的下标取负数来作为一个标记.
     * 那这样有可能会把后面还没遍历到的数变成负数,所以我们判断的时候需要取绝对值 遍历完以后,再遍历一次,如果该处坐标i的值为负数,说明这个i+1这个数已经出现过了,这样我们就能找到最小没出现的数
     * <p>
     * 解答成功: 执行耗时:2 ms,击败了94.44% 的Java用户 内存消耗:97.6 MB,击败了13.12% 的Java用户
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
      //1.第一次遍历,先处理负数元素,将其设置为N+1,从而排除后面标记负数的干扰.
      //这里注意0也要处理,因为如果保留0,打标记的时候0的负数还是0
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] <= 0) {
          nums[i] = nums.length + 1;
        }
      }
      //2.第二次遍历,判断元素n是否在1~N区间,在的话,将n-1处坐标的元素打上标记取负数
      for (int i = 0; i < nums.length; i++) {
        int abs = Math.abs(nums[i]);
        if (abs >= 1 && abs <= nums.length) {
          //打标记,如出现3,就在2打标记,出现1,就在0打标记, 注意这里如果已经是负数, 不要再打标记了,否则负负得正
          nums[abs - 1] = -Math.abs(nums[abs - 1]);
        }
      }

      //3. 第三次遍历.找到没被打标记的,标记的下标+1才=实际值
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] > 0) {
          return i + 1;
        }
      }
      //没找到,那就是整个数组都在1~N且不重复
      return nums.length + 1;
    }


  }
//leetcode submit region end(Prohibit modification and deletion)

  /**
   * 方法三
   * 位图bitmap + 打标记
   * 解答成功: 执行耗时:3 ms,击败了28.76% 的Java用户 内存消耗:96.3 MB,击败了95.32% 的Java用户
   * @param nums
   * @return
   */
  public int firstMissingPositive3(int[] nums) {
    int N = nums.length;
    int totalBit = N + 1;
    //1个int8位, 从1开始而不是从0开始,所以需要N+1个bit,8个1组,总共有这么多组
    int groupNum = (totalBit - 1) / 8 + 1;
    int[] bitmap = new int[groupNum];
    //第1次循环,对出现过的 在对应的位图打标记
    for (int i = 0; i < N; i++) {
      int num = nums[i];
      if (num >= 1 && num <= N) {
        //同样的打标记,只不过在位图里打
        int group = num / 8;
        int bitidx = num % 8;
        bitmap[group] |= (1 << bitidx );
      }
    }
    //第2次循环,循环位图,找到第一个没被标记的

    for (int i = 0; i < groupNum; i++) {
      for (int j = 0; j < 8; j++) {
        //0是不算的,从1开始
        if (i == 0 && j == 0){
          continue;
        }
        if ((1 << j) != (bitmap[i] & (1 << j ))) {
          return i * 8 + j;
        }
      }
    }
    return N + 1;
  }

  /**
   * 普通方法,其实不满足空间复杂度O1的要求 执行耗时:26 ms,击败了5.84% 的Java用户 内存消耗:99.5 MB,击败了10.02% 的Java用户
   *
   * @param nums
   * @return
   */
  public int firstMissingPositive(int[] nums) {
    Set<Integer> set = new HashSet<>();
    int n = 1;
    for (int num : nums) {
      set.add(num);
      while (set.contains(n)) {
        n++;
      }
    }
    return n;
  }
}