//给你一个字符串 num 和一个整数 k 。其中，num 表示一个很大的整数，字符串中的每个字符依次对应整数上的各个 数位 。 
//
// 你可以交换这个整数相邻数位的数字 最多 k 次。 
//
// 请你返回你能得到的最小整数，并以字符串形式返回。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：num = "4321", k = 4
//输出："1342"
//解释：4321 通过 4 次交换相邻数位得到最小整数的步骤如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：num = "100", k = 1
//输出："010"
//解释：输出可以包含前导 0 ，但输入保证不会有前导 0 。
// 
//
// 示例 3： 
//
// 
//输入：num = "36789", k = 1000
//输出："36789"
//解释：不需要做任何交换。
// 
//
// 示例 4： 
//
// 
//输入：num = "22", k = 22
//输出："22"
// 
//
// 示例 5： 
//
// 
//输入：num = "9438957234785635408", k = 23
//输出："0345989723478563548"
// 
//0 943895723478563548  23-17=6
// 03 94895723478563548  6-2=4
// 034 9895723478563548  4-1=3
// 0345 989723478563548
//
// 提示： 
//
// 
// 1 <= num.length <= 30000 
// num 只包含 数字 且不含有 前导 0 。 
// 1 <= k <= 10^9 
// 
//
// Related Topics 贪心 树状数组 线段树 字符串 👍 81 👎 0


  
package com.zgd.leetcode.editor.cn;

public class MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits{

  /**
  * 1505
  * 最多 K 次交换相邻数位后得到的最小整数
  * 
  * 选择排序,冒泡排序,插入排序
  *
  * 2023-07-28 11:03:45
  */  
  public static void main(String[] args) {
    Solution solution = new MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 思路,这个k是相邻互换,然后我们需要最小的值, 肯定优先改最前面的数, 把最前面的数替换成k次交换里最小的
     * 那这样一看就很小冒泡排序, 或者说选择排序
     */
    public String minInteger(String num, int k) {
        int len = num.length();
        char[] arr = num.toCharArray();
        //从头往后调整, 把最小的数依次放到前面
        for (int i = 0; i < len && k > 0; i++) {
            //从i~len 找到最小值, 同时 j不能超过k的次数限制
            int jmin = i;
            for (int j = i+1; j < len && j-i <= k; j++) {
                if(arr[j] < arr[jmin]){
                    jmin = j;
                }
            }
            if (jmin != i){
                //如果找到了,把jmin的元素放到i的位置, 其他的后移,并且消耗k的次数
                char t = arr[jmin];
                for (int j = jmin; j>i ; j--) {
                    arr[j] =arr[j-1];
                }
                arr[i] = t;
                k -= (jmin-i);
            }
        }
        return new String(arr);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}