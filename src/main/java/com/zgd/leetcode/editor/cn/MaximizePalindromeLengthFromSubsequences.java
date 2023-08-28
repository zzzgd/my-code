//给你两个字符串 word1 和 word2 ，请你按下述方法构造一个字符串： 
//
// 
// 从 word1 中选出某个 非空 子序列 subsequence1 。 
// 从 word2 中选出某个 非空 子序列 subsequence2 。 
// 连接两个子序列 subsequence1 + subsequence2 ，得到字符串。 
// 
//
// 返回可按上述方法构造的最长 回文串 的 长度 。如果无法构造回文串，返回 0 。 
//
// 字符串 s 的一个 子序列 是通过从 s 中删除一些（也可能不删除）字符而不更改其余字符的顺序生成的字符串。 
//
// 回文串 是正着读和反着读结果一致的字符串。 
//
// 
//
// 示例 1： 
//   abbc ab,c
// 输入：word1 = "cacb", word2 = "cbba"
//输出：5
//解释：从 word1 中选出 "ab" ，从 word2 中选出 "cba" ，得到回文串 "abcba" 。 
//
// 示例 2： 
// ab ba - ab ab = 4
// ab ba - a/b = 3
// 输入：word1 = "ab", word2 = "ab"
//输出：3
//解释：从 word1 中选出 "ab" ，从 word2 中选出 "a" ，得到回文串 "aba" 。 
//
// 示例 3： 
//
// 输入：word1 = "aa", word2 = "bb"
//输出：0
//解释：无法按题面所述方法构造回文串，所以返回 0 。 
//
// 
//
// 提示： 
//
// 
// 1 <= word1.length, word2.length <= 1000 
// word1 和 word2 由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 53 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.Arrays;

public class MaximizePalindromeLengthFromSubsequences{

  /**
  * 1771
  * 由子序列构造的最长回文串的长度
  * 
  * 
  *
  * 2023-08-28 16:16:46
  */  
  public static void main(String[] args) {
    Solution solution = new MaximizePalindromeLengthFromSubsequences().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestPalindrome(String word1, String word2) {
        //又又又是dp
        //遇到这类问题,按之前的经验就是,不要慌, 先把特殊条件去掉来看, 那就是两个字符串合并后最长的回文.
        // 我们得知1个字符的回文长度是1, 且n个长度能通过n-1长度的最长回文计算得来, 很明显符合dp的使用场景
        // dp[i][j] 表示i~j之间字符的回文长度. dp[i][i+1]要么为1要么为2.
        // 如果i和j字符相同, dp[i][j]=dp[i][j],dp[i+1][j-1]+2
        // 如果i和j相邻且字符相同, dp[i][j]=2
        String s = word1 + word2;
        int[][] dp = new int[s.length()][s.length()];
        //从dp[0][0]到dp[n][n], 初始化下为1
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        //我们最终需要的是dp[0][n]的值,所以我们需要从dp[n][n]下手
        int max = 0;
        for (int i = dp.length; i >= 0; i--) {
            for (int j = i+1; j < dp.length; j++) {
                //先判断 i和j的元素是否一致
                if(s.charAt(i) == s.charAt(j)){
                    //如果i和j相邻,为2
                    if(j==i+1){
                        dp[i][j]=2;
                    }else{
                        //不相邻,我们既可以理解它中间[i+1][j-1]左右多了2个元素,也可以理解[i][j-1]右边多了个元素,或者[i+1][j]的左边多了个元素.
                        dp[i][j]=dp[i+1][j-1]+2;
                        //但是这个值不一定是最大值,比如 eabaee ,取中间的abae,回文是aa 2个数,但是取[i][j-1]的话是eabae 5个数
                        dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                        dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                    }

                    //由于我们必须要在两个字符串取值, 所以每次判断如果i和j在两个不同的字符串区间,则可以更新最大值
                    if(i < word1.length() && j >= word1.length()){
                        max = Math.max(dp[i][j], max);
                    }
                }else{
                    //i和j的元素不一致,那只能取内部左右两边最大的值了
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                }

            }
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}