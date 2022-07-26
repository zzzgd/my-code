//给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。 
//
// 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。 
// 1234 1243
// 1243 1342 1324  先从后往前找,如果这个值比后面的都大,说明这组重新排列不可能有比他大的. 直到找到有个值x比后面的数小. 接着对这几个数排序,找到比x大的放在最开始,后面几个数按从小到大排列

//1432
//15432
//12432  1 3224
// 123 132
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：21
// 
//
// 示例 2： 
//
// 
//输入：n = 21
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2³¹ - 1 
// 
// Related Topics 数学 双指针 字符串 👍 294 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NextGreaterElementIii{

  /**
  * 556
  * 下一个更大元素 III
  * 
  * 
  *
  * 2022-07-25 15:39:07
  */  
  public static void main(String[] args) {
    Solution solution = new NextGreaterElementIii().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nextGreaterElement(int n) {
        //思路
        //这个题是找比这个数更大的一个组合的数, 不难想到应该尽量不变前面的数字,因为会变动很大. 所以应该从后面变动数字, 保证变动后是比这个数大一点
        //先从后往前找,如果这个数字比后面的都大,说明这组数字已经是最大的,再重新排列不可能有比他大的. 直到找到有个值x比后面的数小. 接着对这几个数排序,找到比x大的放在最开始,后面几个数按从小到大排列
        //举个例子 2,1,5,4,3 他的下一个数必定是 2,3,1,4,5. {5,4,3}递减的不用管,直到1这里打破了递减. 因此将数一分为二,2和{1,5,4,3}. 排序后吧1后面的数,3放到前面,{3,1,4,5},再合并2就是结果{2,3,1,4,5}
        // 这个问题看起来代码很多,但是思路很清晰
        String ns = n+"";
        if (ns.length() < 2){
            return -1;
        }
        //先转为int数组
        int[] nn = new int[ns.length()];
        for (int i = 0; i < ns.toCharArray().length; i++) {
            nn[i] = ns.toCharArray()[i] - '0';
        }

        //1. 从后万千找,如果x比后面的都大,继续找.直到找到那个x不比后面的更大
        List<Integer> list = new ArrayList<>();
        int x = -1;
        int max = nn[nn.length-1];
        list.add(max);
        for (int i = nn.length-2; i >= 0 ; i--) {
            if (nn[i] >= max){
                max = nn[i];
                list.add(nn[i]);
            }else{
                //找到了
                x = i;
                list.add(nn[i]);
                break;
            }

        }
        if (x < 0){
            //说明没有找到这个能让组合的数更大的x
            return -1;
        }
        //排序,然后把比x大的那个数放到前面
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            //1,2,4
            if (list.get(i) > nn[x]){
                //找到比x大的那个数字,注意这里不是简单的调换.如
                //     2,4,1  -> 排序 1,2,4  -> 4,1,2
                //是把4 放到第一位,后面的不管
               ;
                //放到首位
                list.add(0, list.remove(i));
                break;
            }
        }
        StringBuilder lastp = new StringBuilder();
        for (Integer integer : list) {
            lastp.append(integer);
        }
        //再把前后两者合并起来
        long l = Long.parseLong(ns.substring(0, x) + lastp.toString());
        if (l > Integer.MAX_VALUE){
            return -1;
        }
        return (int) l;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}