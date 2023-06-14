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
    solution.subarrayLCM(new int[]{2,10,4,7,5,10,5},20);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarrayLCM(int[] nums, int k) {
        //首先第一步,找到数组中能被k整除的且不等于1的,连续的
        List<List<Integer>> chushulist = new ArrayList<>();
        List<Integer> chushu = new ArrayList<>();
        for (int num : nums) {
            //如果k能被num整除,且num不等于1, 或者k是
            if(k % num == 0 ){
                chushu.add(k/num);
            }else{
                if(!chushu.isEmpty()){
                    chushulist.add(new ArrayList<>(chushu));
                    chushu.clear();
                }
            }
        }
        if(!chushu.isEmpty()) {
            chushulist.add(new ArrayList<>(chushu));
        }

        //第二步,从下标0开始,找到一个区间, 这个区间内要么有个数的除数是1, 要么这些除数互相不能被全部整除
        int n = 0;
        for (List<Integer> chushuarr : chushulist) {
            for (int i = 0; i < chushuarr.size(); i++) {
                for (int j = i; j < chushuarr.size(); j++) {
                    if(hasGongBei(i,j,chushuarr)){
                        //i~j之间找到了最小公倍数的组合,以及i~j以后每加一个元素都算一个,j后面都不用看了
                        n+=(chushuarr.size()-j);
                        break;
                    }
                }
            }
        }
        return n;
    }

      /**
       * 计算i~j之间是否为最小公倍数的要素就是, i~j之间的除数,注意是除数, 两两相除,是否有不能被整除的. 比如 2,4,10 和 20, 除数是10,5,2, 其中除数5和2不能整除,所以这组是最小公倍数
       * 同理, 如果是2,4,10和40, 除数是20,10,4, 除数互相能被整除,则不是一组最小公倍数
       * @param i
       * @param j
       * @param chushulist
       * @return
       */
    private boolean hasGongBei(int i,int j,List<Integer> chushulist){
        //如果除数为1,则为自身
        if(  chushulist.get(j) == 1 )return true;
        //判断j和前面i~j之间的元素的除数 是否有非1的最大公约数,比如 4,5,20和100,除数是25和20,5,有共同的最大公约数5,则这组也就不是最小公倍数
        Integer jchu = chushulist.get(j);
        //判断他们是不是有共同的最大公约数
        int lastGongyue = -1;
        for (int k = i; k < j; k++) {
            Integer kchu = chushulist.get(k);
            if(kchu == jchu){
                continue;
            }
            int n = gongYueShu(jchu, kchu);
            //各个值有不同的最大公约数,或者都是1,才满足条件,相反不满足,比如2,6,4,10,8, 都是2. 但是要剔除相等的部分
            if(lastGongyue != -1 && n != lastGongyue){
                //只要有一对不为公约数,这组就可以是最小公倍数
                return true;
            }
            lastGongyue = n;
        }
        return lastGongyue == 1;
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