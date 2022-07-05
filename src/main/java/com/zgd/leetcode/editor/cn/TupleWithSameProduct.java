//给你一个由 不同 正整数组成的数组 nums ，请你返回满足 a * b = c * d 的元组 (a, b, c, d) 的数量。其中 a、b、c 和 
//d 都是 nums 中的元素，且 a != b != c != d 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,4,6]
//输出：8
//解释：存在 8 个满足题意的元组：
//(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
//(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
// 
//
// 示例 2： 
// 1,2,3,4,5,6,10
// 
//输入：nums = [1,2,4,5,10]
//输出：16
//解释：存在 16 个满足题意的元组：
//(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
//(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
//(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,4,5)
//(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 1 <= nums[i] <= 10⁴ 
// nums 中的所有元素 互不相同 
// 
// Related Topics 数组 哈希表 👍 20 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class TupleWithSameProduct{

  /**
  * 1726
  * 同积元组
  * 
  * 
  *
  * 2022-07-04 18:18:52
  */  
  public static void main(String[] args) {
    Solution solution = new TupleWithSameProduct().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int tupleSameProduct(int[] nums) {
      HashMap<Integer, Integer> map = new HashMap<>();
      //计算所有两两乘积,放到map里计数, 然后再算总数
      for (int i = 0; i < nums.length-1; i++) {
        for (int j = i+1; j < nums.length; j++) {
          int n = nums[i] * nums[j];
          Integer count = map.get(n);
          if (count == null){
            count = 0;
          }
          count++;
          map.put(n,count);
        }
      }
      int ans = 0;
      //2个相同的乘积有 2*1*2*2种, 3个相同的有 3*2*2*2种, n个就有 n*(n-1)*2*2
      //比如乘积为10的有1,10和2,5 可以看做A和B,从概率上说A和B的排列方式有2*1种. 然后A内部有2种排列,B内部也有两种排列,所以是2*1*2*2
      for (Map.Entry<Integer, Integer> en : map.entrySet()) {
        if (en.getValue() < 2){
          continue;
        }
        Integer count = en.getValue();
        ans += count*(count-1)*2*2;

      }
      return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}