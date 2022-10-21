//求出大于或等于 N 的最小回文素数。 
//
// 回顾一下，如果一个数大于 1，且其因数只有 1 和它自身，那么这个数是素数。 
//
// 例如，2，3，5，7，11 以及 13 是素数。 
//
// 回顾一下，如果一个数从左往右读与从右往左读是一样的，那么这个数是回文数。 
//
// 例如，12321 是回文数。 
//
// 
//
// 示例 1： 
//
// 输入：6
//输出：7
// 
//
// 示例 2： 
//
// 输入：8
//输出：11
// 
//
// 示例 3： 
//
// 输入：13
//输出：101 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 10^8 
// 答案肯定存在，且小于 2 * 10^8。 
// 
//   1
//  3 5
// 7 11 13 17
// 
//
// 
//
// Related Topics 数学 👍 82 👎 0


  
package com.zgd.leetcode.editor.cn;

public class PrimePalindrome{

  /**
  * 866
  * 回文素数
  * 
  * 
  *
  * 2022-10-20 16:54:00
  */  
  public static void main(String[] args) {
    Solution solution = new PrimePalindrome().new Solution();
    solution.primePalindrome(13);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      /**
       * 这个题目的难点在于回文的获取. 思路: 1,取到数字,获取它的前半部分和后半部分 2:将前半部分颠倒,和后半部分比较, 如果<=后半部分, 说明直接镜像前半部分
       * 得到的结果,也会<=原来的值. 所以需要将前半部分+1后再镜像. 3:如果前半部分+1满100进制多了1位, 则回文也应该多1位
       * @param n
       * @return
       */
    public int primePalindrome(int n) {
        if (n== 1){
            //1 不是素数
            return 2 ;
        }
        int i = n;
        //如果不是回文,先获取下一个回文,然后每次循环都取下一个回文
        if(!isHuiwen(i+"")){
            i = getNextHuiwen(i+"");
        }
        for(;i < 200_000_000;i = getNextHuiwen(i+"")){

            if (isSushu(i)){
                return i;
            }
        }
        return 0;

    }

    private  boolean isHuiwen(String nn){
        for (int j = 0; j < nn.length() / 2; j++) {
            if (nn.charAt(j) != nn.charAt(nn.length() - j -1)){
                return false;
            }
        }
        return true;
    }
    private boolean isSushu(int i){
        //判断素数
        if (i == 1){
            return false;
        }
        if (i == 2 || i == 3){
            return true;
        }
        for (int j = 2; j*j <= i; j++) {
            if (i%j == 0){
                return false;
            }
        }
        return true;
    }

      /**
       * 获取下一个回文
       * @param nn
       * @return
       */
      private int getNextHuiwen(String nn) {
          //如果不是回文,跳到下一个回文
          char[] chars = nn.toCharArray();
          // 999 -> 1001  3位变4位  888 -> 898 保持3位
          // 1221 -> 1331, 9999 -> 10001 -> 4位变5位
          //总结,如果数长度是单数,取前一半+1递增, 后镜像
          //如果数长度是偶数, 取前一半递增, 后镜像
          int len = chars.length;
          int halfLen = (len+1)/2;
          //判断当前是不是回文,是的话,需要将前面一部分+1, 不是回文的话, 直接将前部分镜像,但是如果镜像后的结果比原来的还小,需要+1.
          //1322 -> 1331 可以 1399 -> 1331 不行
          //130 -> 131 可以  139 -> 131 不行
          int part1 = Integer.parseInt(new StringBuilder(nn.substring(0, halfLen)).reverse().toString());
          int part2 = Integer.parseInt(nn.substring(len - halfLen));


          int half = Integer.parseInt(nn.substring(0, halfLen));
          String newhalf =part1 <= part2 ?(half + 1)+"": (half+"" );
          char[] newchars;
          if (newhalf.length() > halfLen){
              //如果99..多了一位. 总位数也要多一位
              newchars = new char[len+1];
          }else{
              newchars = new char[len];
          }
          int newLen = newchars.length;
          int newHalfLen = (newLen+1)/2;
          for (int k = 0; k < newHalfLen; k++) {
              newchars[k] = newhalf.charAt(k);
              newchars[newLen-1 - k] = newhalf.charAt(k);
          }
          //转成下一个数字
         return Integer.parseInt(new String(newchars));
      }
  }
//leetcode submit region end(Prohibit modification and deletion)

}