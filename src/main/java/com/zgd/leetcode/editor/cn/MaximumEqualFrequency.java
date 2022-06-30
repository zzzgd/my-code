//给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度： 
//
// 
// 从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。 
// 
//
// 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,2,1,1,5,3,3,5]
//输出：7
//解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数
//字都出现了两次。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
//输出：13
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁵ 
// 
// Related Topics 数组 哈希表 👍 61 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.HashMap;

public class MaximumEqualFrequency{

  /**
  * 1224
  * 最大相等频率
  * 
  * 这个主要是需要判断的逻辑很多
  *
  * 2022-06-30 15:30:21
  */  
  public static void main(String[] args) {
    Solution solution = new MaximumEqualFrequency().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxEqualFreq(int[] nums) {
      //1222 113  ---- 1->3 2->2 3->2
      //定义两个map,1是 某个数和出现的频次. 2是 这个频次和它有多少组. 同时记录最大频次x
      //4种情况 n是当前长度
      //1. aabbbcc 比较常见的,最大频次x=3只有1组,剩下的都是x-1频次的
      //2. aaabbbcddd  最大频次的有n组, 剩下的唯一一个数只出现1次
      //3. aaaaaa      只有1种数 (频次为n)
      //4. abcde       只有1个频次,每个频次只有1个数(有n个组)
      HashMap<Integer, Integer> numCountMap = new HashMap<>();
      HashMap<Integer, Integer> countGroupMap = new HashMap<>();
      int maxCount = 0;
      int max = 0;
      for (int i = 0; i < nums.length; i++) {
        int n = nums[i];
        //因为i是从0开始,用ii从1开始方便理解,表示截止的长度
        int ii = i+1;
        Integer count = numCountMap.getOrDefault(n, 0);
        if (count > 0){
          //说明不是第一次插入值, 把这个组从低频次放到频次+1
          Integer group = countGroupMap.get(count);
          countGroupMap.put(count,group-1);
        }
        count++;
        numCountMap.put(n,count);
        Integer group = countGroupMap.getOrDefault(count,0);
        countGroupMap.put(count,group+1);

        maxCount = Math.max(maxCount,count);
        //进入判断
        //1. aabbbcc 比较常见的,最大频次x=3只有1组,剩下(n-x)的都是x-1频次的, group[x] == 1 && group[x-1]*(x-1) ==i - x
        //2. aaabbbcddd  最大频次的有n组, 剩下的唯一一个数只出现1次
        //3. aaaaaa      只有1种数 (频次为n) +1个杂数
        //4. abcde       只有1个频次,每个频次只有1个数(有n个组)
        Integer maxCountGroup = countGroupMap.getOrDefault(maxCount, 0);
        if (1 == maxCountGroup
                && (maxCount-1)*countGroupMap.getOrDefault(maxCount-1,0)+ maxCount ==ii){
          max = ii;
        }else if (maxCount * maxCountGroup +1 ==ii){
          max = ii;
        } else if (maxCount == ii) {
          max = ii;
        }else if (maxCountGroup == ii){
          max = ii;
        }
      }
      return  max;



    }
}
//leetcode submit region end(Prohibit modification and deletion)

}