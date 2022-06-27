//给定两个整数 a 和 b ，返回 任意 字符串 s ，要求满足： 
//
// 
// s 的长度为 a + b，且正好包含a 个 'a' 字母与 b 个 'b' 字母； 
// 子串 'aaa' 没有出现在 s 中； 
// 子串 'bbb' 没有出现在 s 中。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：a = 1, b = 2
//输出："abb"
//解释："abb", "bab" 和 "bba" 都是正确答案。
// 
//
// 示例 2： 
//
// 
//输入：a = 4, b = 1
//输出："aabaa" 
//
// 
//
// 提示： 
//
// 
// 0 <= a, b <= 100 
// 对于给定的 a 和 b，保证存在满足要求的 s 
// 
// Related Topics 贪心 字符串 👍 75 👎 0


  
package com.zgd.leetcode.editor.cn;

public class StringWithoutAaaOrBbb{

  /**
  * 984
  * 不含 AAA 或 BBB 的字符串
  * 
  * 
  *
  * 2022-06-27 11:19:52
  */  
  public static void main(String[] args) {
    Solution solution = new StringWithoutAaaOrBbb().new Solution();
    solution.strWithout3a3b(1,3);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String strWithout3a3b(int a, int b) {
      //可以看成是若干的 aab或者bba, 以及ab或ba组成.
      // 至于为什么b>a的时候bb放在前,反之aa放在前,因为考虑到b>a的时候,后面需要一直拼b,如果bb放在后可能出现三个b
      String aab = b > a ? "bba":"aab";
      String ab = b > a ? "ba":"ab";
      String s = "";
      int n = a + b;
      //循环,跳出的条件是其中一个为0了
      while (a > 0 && b > 0){
        //当两者不相同的时候,则说明其中一个>=1, 另一个>=2
        if (a != b && a != b ) {
          s += aab;
          if ("bba".equals(aab)){
            a-=1;
            b-=2;
          }else{
            a-=2;
            b-=1;
          }
        }else{
          s += ab;
          a--;
          b--;
        }
      }
      //跳出循环,必然是至少其中一个字母已经拼完,拼剩余一个字母
      while (a > 0){
        s += "a";
        a--;
      }
      while (b > 0){
        s += "b";
        b--;
      }
      //最后拼出来有可能超出,需要截取
      return s.substring(0,n);
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}