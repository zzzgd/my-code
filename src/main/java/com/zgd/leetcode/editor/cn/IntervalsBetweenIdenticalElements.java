//给你一个下标从 0 开始、由 n 个整数组成的数组 arr 。 
//
// arr 中两个元素的 间隔 定义为它们下标之间的 绝对差 。更正式地，arr[i] 和 arr[j] 之间的间隔是 |i - j| 。 
//
// 返回一个长度为 n 的数组 intervals ，其中 intervals[i] 是 arr[i] 和 arr 中每个相同元素（与 arr[i] 的值相同
//）的 间隔之和 。 
//
// 注意：|x| 是 x 的绝对值。 
//
// 
//
// 示例 1： 
//  2-0,4   1-1,3    3-2,5,6
// 0 2, 0 ,
// 输入：arr = [2,1,3,1,2,3,3]
//输出：[4,2,7,2,4,4,5]
//解释：
//- 下标 0 ：另一个 2 在下标 4 ，|0 - 4| = 4
//- 下标 1 ：另一个 1 在下标 3 ，|1 - 3| = 2
//- 下标 2 ：另两个 3 在下标 5 和 6 ，|2 - 5| + |2 - 6| = 7
//- 下标 3 ：另一个 1 在下标 1 ，|3 - 1| = 2
//- 下标 4 ：另一个 2 在下标 0 ，|4 - 0| = 4
//- 下标 5 ：另两个 3 在下标 2 和 6 ，|5 - 2| + |5 - 6| = 4
//- 下标 6 ：另两个 3 在下标 2 和 5 ，|6 - 2| + |6 - 5| = 5
// 
//
// 示例 2： 
//
// 输入：arr = [10,5,10,10]
//输出：[5,0,3,4]
//解释：
//- 下标 0 ：另两个 10 在下标 2 和 3 ，|0 - 2| + |0 - 3| = 5
//- 下标 1 ：只有这一个 5 在数组中，所以到相同元素的间隔之和是 0
//- 下标 2 ：另两个 10 在下标 0 和 3 ，|2 - 0| + |2 - 3| = 3
//- 下标 3 ：另两个 10 在下标 0 和 2 ，|3 - 0| + |3 - 2| = 4
// 
//
// 
//
// 提示： 
//
// 
// n == arr.length 
// 1 <= n <= 10⁵ 
// 1 <= arr[i] <= 10⁵ 
// 
//
// Related Topics 数组 哈希表 前缀和 👍 44 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntervalsBetweenIdenticalElements{

  /**
  * 2121
  * 相同元素的间隔之和
  * 
  * 
  *
  * 2022-10-25 15:17:35
  */  
  public static void main(String[] args) {
    Solution solution = new IntervalsBetweenIdenticalElements().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

      public long[] getDistances3(int[] arr) {

          //思路,遍历,把每个下标的值,都存到map里,key是值,value是下标列表
          //在遍历的同时,取出它已经出现过的下标,计算距离并加上
          Map<Integer, List<Integer>> map = new HashMap<>();
          long[] ints = new long[arr.length];
          for (int i = 0; i < arr.length; i++) {
              List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
              //对于之前出现过的下标来说, 累计起来的间隔
              long ln = 0;
              if (!list.isEmpty()){
                  for (Integer n : list) {
                      ln+=i - n;
                      ints[n] += i - n;
                  }
              }
              ints[i]=ln;
              list.add(i);
              map.put(arr[i],list);
          }
          return ints;
      }
      /**
       * 一开始,使用的方法,是遍历,然后保存每个数出现的位置. 然后遍历坐标,计算这个坐标的值的所有位置绝对值之和, 复杂度很大,超时
       * 其实只要搞懂一个点, 比如...1,2,3,1,2,5,... 假如我们已知第1个`2`它的所有绝对值之和的结果是x,那第2个`2`在它右边3格位置,
       * 也就是距离+3, 同时第1个2的左边所有`2`和它的距离都+3. 同理右边所有`2`的距离都-3.
       * 1. i的前缀和 = a的前缀和 + (i到前一位同样的数a的距离 * a前面同样数的个数)
       * 2. 且i处的结果值 = 前缀和 + 后缀和
       * @param arr
       * @return
       */
      //	测试用例:[2,1,3,1,2,3,3]
      //	测试结果:[4,2,7,2,4,4,0]
      //	期望结果:[4,2,7,2,4,4,5]
    public long[] getDistances(int[] arr) {

        //map保存的key是数值, value[0]是这个数值从左到右最新的下标,value[1]是从左到右出现的次数
        Map<Integer, int[]> map = new HashMap<>();
        //先从左到右求前缀和
        long[] qianzhui = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int[] ints = map.get(arr[i]);
            if (ints == null){
                ints = new int[2];
                map.put(arr[i],ints);
                ints[0] = i;
                ints[1] = 1;
                continue;
            }
            //对于之前出现过的下标来说, 累计起来的间隔
            int juli = i - ints[0];
            //位于i处的前缀和是, 和i同数值的前一位x的前缀和 + (i和x的距离) * x前面的个数(包含x)
            qianzhui[i] = qianzhui[ints[0]] + juli*ints[1];
            ints[0] = i;
            ints[1] += 1;
        }
        //计算后缀和
        long[] houzhui = new long[arr.length];
        map = new HashMap<>();
        for (int i = arr.length-1  ; i >= 0; i--) {
            int[] ints = map.get(arr[i]);
            if (ints == null){
                ints = new int[2];
                map.put(arr[i],ints);
                ints[0] = i;
                ints[1] = 1;
                continue;
            }
            //对于之前出现过的下标来说, 累计起来的间隔
            int juli = ints[0] - i;
            //位与i的后缀和是, 和i同数值的后一位x的后缀和 + (i和x的距离) * x前面的个数(包含x)
            houzhui[i] = houzhui[ints[0]] + juli*ints[1];
            ints[0] = i;
            ints[1] += 1;
        }
        //前后相加
        for (int i = 0; i < qianzhui.length; i++) {
            qianzhui[i] += houzhui[i];
        }
        return qianzhui;
    }


      /**
       * 这个方法会超时, 首先需要遍历一次,时间复杂度n, 然后有需要遍历一次,并且每个下标还需要遍历一次它的其他位置,假设平均每个值有k个不同的下标,则需要遍历k*n次
       * @param arr
       * @return
       */
      public long[] getDistances2(int[] arr) {

          //思路,遍历,把每个下标的值,都存到map里,key是值,value是下标列表
          //再次遍历,把这个值对应的下标都取出来,然后计算他们下标的绝对值
          Map<Integer, List<Integer>> map = new HashMap<>();
          for (int i = 0; i < arr.length; i++) {
              List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
              list.add(i);
              map.put(arr[i],list);
          }
          long[] ints = new long[arr.length];
          for (int i = 0; i < ints.length; i++) {
              List<Integer> list = map.get(arr[i]);
              int n = 0;
              if (!list.isEmpty()){
                  for (Integer m : list) {
                      n+= Math.abs(i-m);
                  }
              }
              ints[i] = n;
          }
          return ints;
      }
}
//leetcode submit region end(Prohibit modification and deletion)

}