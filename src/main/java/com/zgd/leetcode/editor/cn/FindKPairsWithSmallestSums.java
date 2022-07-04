//给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。 
//
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。 
//
// 请找到和最小的 k 个数对 (u1,v1), (u2,v2) ... (uk,vk) 。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//输出: [1,2],[1,4],[1,6]
//解释: 返回序列中的前 3 对数：
//     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
// 
//
// 示例 2: 
//
// 
//输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//输出: [1,1],[1,1]
//解释: 返回序列中的前 2 对数：
//     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
// 
//
// 示例 3: 
//
// 
//输入: nums1 = [1,2], nums2 = [3], k = 3 
//输出: [1,3],[2,3]
//解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums1.length, nums2.length <= 10⁵ 
// -10⁹ <= nums1[i], nums2[i] <= 10⁹ 
// nums1 和 nums2 均为升序排列 
// 1 <= k <= 1000 
// 
// Related Topics 数组 堆（优先队列） 👍 415 👎 0


  
package com.zgd.leetcode.editor.cn;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums{

  /**
  * 373
  * 查找和最小的 K 对数字
   *
  * 2022-07-04 10:12:17
  */  
  public static void main(String[] args) {
    Solution solution = new FindKPairsWithSmallestSums().new Solution();
    solution.kSmallestPairs(new int[]{1,1,2},new int[]{1,2,3},3);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    boolean isrevert = false;
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      if (!isrevert && nums1.length > nums2.length){
        isrevert = true;
        //把长度小的放过来
        return kSmallestPairs(nums2,nums1,k);
      }
      List<List<Integer>> result = new ArrayList<>();
      //使用优先级队列来比较, 想想两个数组组成的一个矩阵, 由于两个数组是排好序的,因此要取k要么
      PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>((a,b)->{
              return (nums1[a.getKey()] + nums2[a.getValue()]) - (nums1[b.getKey()] + nums2[b.getValue()]);
      }
              );
      for (int i = 0; i < Math.min(nums1.length, k); i++) {
        //先把长度短的作为行放进去
        queue.offer(new Pair<>(i,0));
      }
      //再把另一个数组作为列放进去
      while (!queue.isEmpty() && result.size() < k){
        Pair<Integer, Integer> p = queue.poll();
        List<Integer> list = new ArrayList<>();
        Integer a = p.getKey();
        Integer b = p.getValue();
        //是否翻转,影响结果比对
        list.add(isrevert ? nums2[b] : nums1[a]);
        list.add(isrevert ? nums1[a] : nums2[b]);
        result.add(list);
        //接着再放入它这个列的下一个
        if (b+1 < nums2.length){
          queue.add(new Pair<>(a,b+1));
        }
      }
      return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}