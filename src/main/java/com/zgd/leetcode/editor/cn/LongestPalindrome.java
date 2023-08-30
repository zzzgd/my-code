//给定一个包含大写字母和小写字母的字符串
// s ，返回 通过这些字母构造成的 最长的回文串 。 
//
// 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。 
//
// 
//
// 示例 1: 
//
// 
//输入:s = "abccccdd"  a1b1c4d2
//输出:7
//解释:
//我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
// 
//
// 示例 2: 
//
// 
//输入:s = "a"
//输出:1
// 
//
// 示例 3： 
//
// 
//输入:s = "aaaaaccc" a5c3
//输出:7 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length <= 2000 
// s 只由小写 和/或 大写英文字母组成 
// 
//
// Related Topics 贪心 哈希表 字符串 👍 550 👎 0


  
package com.zgd.leetcode.editor.cn;

public class LongestPalindrome{

  /**
  * 409
  * 最长回文串
  * 
  * 
  *
  * 2023-08-30 14:23:52
  */  
  public static void main(String[] args) {
    Solution solution = new LongestPalindrome().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      public int longestPalindrome(String s) {
          //"aaaaaccc" a5c3  梳理,把字符串按字母和数字统计起来
          int[] charnum = new int[52];
          char[] chararr = s.toCharArray();
          for (int i = 0; i < chararr.length; i++) {
              int idx = -1;
              if(chararr[i] > 'Z'){
                  idx = chararr[i] - 'a' + 26;
              }else{
                  idx = chararr[i] - 'A';
              }
              charnum[idx]++;
          }
          //所有字母数量 n/2*2 相加 ,然后如果还有剩余的再加一位放在最中间,就组成了最长回文
          boolean left =false;
          int res = 0;
          for (int i = 0; i < charnum.length; i++) {
              res += charnum[i]/2*2;
              if(charnum[i]%2!=0){
                  left = true;
              }
          }
          return left ? res+1 : res;

      }
      /**
       * 看错题了
       * @param s
       * @return
       */
    public int longestPalindrome2(String s) {
        char[] charr = s.toCharArray();
        boolean[][] huiwen = new boolean[charr.length][charr.length];

        int maxnum = 0;
        for (int i = 0; i < charr.length; i++) {
            for (int j = i; j >=0 ; j--) {
                //如果两者重叠,是回文
                if(i == j){
                    huiwen[j][i] = true;
                }else{
                    if(charr[i] == charr[j]){
                        if(j+1==i){
                            //如果相邻且相同,是回文
                            huiwen[j][i] = true;
                        }else{
                            if(huiwen[j+1][i-1]){
                                //否则判断两者相同并且内部是回文
                                huiwen[j][i] = true;
                            }
                        }
                    }
                }
                if(huiwen[j][i]){
                    maxnum = Math.max(maxnum,i-j+1);
                }
            }
        }
        return maxnum;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}