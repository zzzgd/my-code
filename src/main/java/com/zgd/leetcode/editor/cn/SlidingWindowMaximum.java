//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
//
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1889 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowMaximum{

  /**
  * 239
  * 滑动窗口最大值
  * 
  * 
  *
  * 2022-09-28 17:51:41
  */  
  public static void main(String[] args) {
    Solution solution = new SlidingWindowMaximum().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      /**
       * 方法2超时了, 因为后面不停的遍历找最大值太耗时了. 优化的方法, 考虑保存队列
       * @param nums
       * @param k
       * @return
       */
    public int[] maxSlidingWindow(int[] nums, int k) {
        //使用队列来记录每个进来的值的下标, 因为一会要根据下标弹出数的
        LinkedList<Integer> queue = new LinkedList<>();


        int[] maxs = new int[nums.length - k + 1];
        int l = 0,r = 0;
        while (r <= nums.length-1){
            if (!queue.isEmpty() && nums[r] >= nums[queue.peek()]){
                //如果新来的值比整个队列的都大,全部清空
                queue.clear();
            }else{
                //否则就从后往前,将比这个新来的数小的都弹出
                while (!queue.isEmpty() && nums[queue.peekLast()] <=nums[r]){
                    queue.pollLast();
                }

            }
            //同时把队列里,下标在 l 滑动窗口左边的都弹出
            while (!queue.isEmpty() && queue.peekFirst()<l){
                queue.pollFirst();
            }
            //把新的元素的下标放进去队尾
            queue.offer(r);
            if (r-l +1>=k){
                //窗口足够长了,取队列首部就是最大值
                maxs[l] = nums[queue.peekFirst()];
                l++;
            }
            r++;
        }
        return maxs;
    }

    private int findMax(int[] nums, int start,int k){
        int max = Integer.MIN_VALUE;
        for (int i = start; i < start+k; i++) {
            if (nums[i] > max){
                max = nums[i];
            }
        }
        return max;
    }


      /**
       * 超时了
       * @param nums
       * @param k
       * @return
       */
      public int[] maxSlidingWindow2(int[] nums, int k) {
          //滑动,每次判断进来的最大值,同时记录第二大值
          //1. 比最大值大, 那进来的就是新的最大值
          //2. 比最大值小,有2种情况.
          //    2.1: 比出去的大,还是原来的. 2.2:比出去的小,又有2种情况:
          //         2.2.1: 出去的是最大值,重新找最大值. 2.2.2: 出去的不是最大值,最大值不变


          int max = findMax(nums, 0, k);

          int[] maxs = new int[nums.length - k + 1];
          maxs[0] = max;
          //下一个进来的值
          int next = k;
          while (next <= nums.length-1){
              //出去的值
              int out = next - k;
              if (nums[next] >= max ){
                  max = nums[next];
              }else{
                  if (nums[next] < nums[out] &&  nums[out] == max){
                      max = findMax(nums,out+1,k);
                  }
              }
              maxs[out+1] = max;
              next++;
          }
          return maxs;
      }
}
//leetcode submit region end(Prohibit modification and deletion)

}