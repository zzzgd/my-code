//给你一个整数数组 nums 和一个整数 k ，请你统计并返回 nums 的 子数组 中满足 元素最小公倍数为 k 的子数组数目。 
//
// 子数组 是数组中一个连续非空的元素序列。 
//
// 数组的最小公倍数 是可被所有数组元素整除的最小正整数。 
// 10,2,5,4,2,4
// 2,10,4,5,10,5  20
//
// 示例 1 ： 
//
// 输入：nums = [3,6,2,7,1], k = 6
//输出：4
//解释：以 6 为最小公倍数的子数组是：
//- 3,6
//- 3,6,2
//- 6
//- 6,2
// 
// 3,6,2
// 示例 2 ： 
//
// 输入：nums = [3], k = 2
//输出：0
//解释：不存在以 2 为最小公倍数的子数组。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 1 <= nums[i], k <= 1000 
// 
//
// Related Topics 数组 数学 数论 👍 28 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class NumberOfSubarraysWithLcmEqualToK{

  /**
  * 2470
  * 最小公倍数为 K 的子数组数目
  * 
  * 
  *
  * 2023-06-14 09:54:56
  */  
  public static void main(String[] args) {
    Solution solution = new NumberOfSubarraysWithLcmEqualToK().new Solution();
    solution.subarrayLCM(new int[]{3,6,2,7,1},6);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarrayLCM(int[] nums, int k) {
        //首先第一步,找到数组中能被k整除的且不等于1的,连续的放到list
        List<List<Integer>> llist = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            //如果k能被num整除,且num不等于1, 或者k是
            if(k % num == 0 ){
                list.add(num);
            }else{
                if(!list.isEmpty()){
                    llist.add(new ArrayList<>(list));
                    list.clear();
                }
            }
        }
        if(!list.isEmpty()) {
            llist.add(new ArrayList<>(list));
        }

        //第二步,从左到右,把数两两合并,得到他们的最小公倍数,比如 2,10,4,5. 2和10合并最小公倍数是10,接着10和4,最小公倍数是20
        int n = 0;
        for (List<Integer> arr : llist) {
            for (int i = 0; i < arr.size(); i++) {
                int gbs = arr.get(i);
                for (int j = i; j < arr.size(); j++) {
                    //两两求最小公倍数来合并
                    gbs = gongBeiShu(gbs,arr.get(j));
                    if(gbs == k){
                        //一旦找到符合的,后面的都符合,数量有几个就加几个
                        n += arr.size()-j;
                        break;
                    }else if(gbs > k){
                        //超出了预期,后面都没有了
                        break;
                    }
                }
            }
        }

        return n;
    }

      /**
       * 求两者之间的最小公倍数, 就是 a*b/最大公约数(a,b)
       * @param a
       * @param b
       * @return
       */
    private int gongBeiShu(int a,int b){
        return a*b/gongYueShu(a,b);
    }

    //最对大公约数, 辗转相除法, gcd(A, B) = gcd(B, A mod B)   其中:A > B
    private int gongYueShu(int a,int b){
        if(a < b){
            int t = b;
            b = a;
            a = t;
        }
        while(a % b != 0){
           int t = a % b;
            a = b;
            b = t;
        }
        return b;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}