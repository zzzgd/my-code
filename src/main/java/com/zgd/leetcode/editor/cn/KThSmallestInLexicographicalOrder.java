//给定整数 n 和 k，返回 [1, n] 中字典序第 k 小的数字。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 13, k = 2
//输出: 10
//解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
// 
//
// 示例 2: 
//
// 
//输入: n = 1, k = 1
//输出: 1
// 
//
// 
//
// 提示: 
//
// 
// 1 <= k <= n <= 10⁹ 
// 
// Related Topics 字典树 👍 518 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class KThSmallestInLexicographicalOrder{

  /**
  * 440
  * 字典序的第K小数字
  * 
  * 
  *
  * 2022-07-18 15:34:35
  */  
  public static void main(String[] args) {
    Solution solution = new KThSmallestInLexicographicalOrder().new Solution();
    solution.findKthNumber(100,90);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthNumber(int n, int k) {
      //确定k的位置
      //1. 首先通过getCount可以知道前缀为i在n里的个数
      //2. 如1在30中, 就是1,10,11,12,13... 共11个, 10在30中: 10 1个. 如果要定位k=5,发现i=1有11个已经大于k,
      // 由于字典序的原因(i=1的在前),k=5肯定在这个11的区间
      //如何缩小这个区间呢? 前缀*10, 即可排除前缀i这项,现在i=10,还有共10个,还是大于k. 再乘以10,前缀变100,共0个,小于k了,因此回退一步,k=5在前缀=10中
      //接着只需要递增前缀10,看11的前缀数量

      return findKthNumber2(n,k);
    }

    public int findKthNumber3(int n, int k) {
      int curr = 1;
      k--;
      while (k > 0) {
        int steps = getCount(curr, n);
        if (steps <= k) {
          k -= steps;
          curr++;
        } else {
          curr = curr * 10;
          k--;
        }
      }
      return curr;
    }
   private int findKthNumber2(int n, int k) {  //1,10,11,12,13,2,3,
      int prefix = 1;
      int count = 0;
     /****************************
      * 一开始想不通为啥这里k要减一,其实是因为k是从1开始的,而我们习惯count计数从0开始.
      * 或者这么理解,第k个值, 假如我们找到正好count个数=k-1,那此时prefix经过++以后,就是我们要的值
      * **************************
      */
     k--;
     while (count < k){
        int c = getCount(prefix, n);
        if (c+count<=k){
          //这里为什么是小于等于,因为k经过k--;表示的KK(原来的)前面的 k(后面的)个数,而小于不用多说,表示该前缀及下面所有节点加起来都不够,
          //当等于的时候,表示KK前面的k个数都找到了,同样的,我们可以找下一个前缀了,因此这两个逻辑是一样的
          //如果加上这个前缀下的节点也小于k, 前缀加1,相当于字典树找右边的节点
          prefix++;
          count+=c;
        }else{
          //如果加上这个前缀下的节点大于等于k,前缀乘以10,相当于玩下找
          prefix=prefix*10;
          count++;
        }
      }

      return prefix;
    }

    public int getSteps(int curr, long n) {
      int steps = 0;
      long first = curr;
      long last = curr;
      while (first <= n) {
        steps += Math.min(last, n) - first + 1;
        first = first * 10;
        last = last * 10 + 9;
      }
//      System.out.println("curr = " + curr + ", n = " + n+":"+steps);
      return steps;
    }
    /**
     * 获取i为前缀在n中的个数. 如1在30中, 就是1,10,11,12,13... 共11个, 10在30中: 10 1个
     * @param i
     * @param n
     * @return
     */
    private int getCount(int i,long n){
      int count = 0;
      /**********************************
       * 这里的a和b一定要用long类型, 否则会溢出变为负数!!
       * ***/
      for (long a = i,b=(i+1); a<=n ; a*=10,b*=10) {
        count+=(Math.min(n +1,b) - a);
      }
//      System.out.println("i = [" + i + "], n = [" + n + "]"+":"+count);
      return count;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}