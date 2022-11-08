//行程长度编码 是一种常用的字符串压缩方法，它将连续的相同字符（重复 2 次或更多次）替换为字符和表示字符计数的数字（行程长度）。例如，用此方法压缩字符串 
//"aabccc" ，将 "aa" 替换为 "a2" ，"ccc" 替换为` "c3" 。因此压缩后的字符串变为 "a2bc3" 。 
//
// 注意，本问题中，压缩时没有在单个字符后附加计数 '1' 。 
//
// 给你一个字符串 s 和一个整数 k 。你需要从字符串 s 中删除最多 k 个字符，以使 s 的行程长度编码长度最小。 
//
// 请你返回删除最多 k 个字符后，s 行程长度编码的最小长度 。 
//
// 
//
// 示例 1： a100ba
//
// 输入：s = "aaabcccd", k = 2    >>> aaaccc
//输出：4
//解释：在不删除任何内容的情况下，压缩后的字符串是 "a3bc3d" ，长度为 6 。最优的方案是删除 'b' 和 'd'，这样一来，压缩后的字符串为 "a3
//c3" ，长度是 4 。 
//
// 示例 2： 
//
// 输入：s = "aabbaa", k = 2   a2b2a2
//输出：2
//解释：如果删去两个 'b' 字符，那么压缩后的字符串是长度为 2 的 "a4" 。
// 
//
// 示例 3： 
//
// 输入：s = "aaaaaaaaaaa", k = 0
//输出：3
//解释：由于 k 等于 0 ，不能删去任何字符。压缩后的字符串是 "a11" ，长度为 3 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// 0 <= k <= s.length 
// s 仅包含小写英文字母 
// 
//
// Related Topics 字符串 动态规划 👍 91 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringCompressionIi{

  /**
  * 1531
  * 压缩字符串 II
  * 
  * 
  *
  * 2022-11-07 16:19:35
  */  
  public static void main(String[] args) {
    Solution solution = new StringCompressionIi().new Solution();
    solution.getLengthOfOptimalCompression("llllllllllttttttttt",1);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


      /**
       * 老老实实使用dp
       */
      public int getLengthOfOptimalCompression(String s, int k) {
          if (s.length() <= k){
              return 0;
          }
          //既然是dp,首要确定的是二维分别是什么, 这里很明显,一个是从左到右的位置i,一个是当前已使用几次删除j
          //dp[i][j] 表示到i位置使用了j次删除,最小的长度
          int[][] dp = new int[s.length()][k+1];
          //初始化
          for (int[] ints : dp) {
              Arrays.fill(ints,Integer.MAX_VALUE);
          }

          //主要思路: 我们已经把dp初始化为最大的数字了,然后针对k次删除机会和长度n的字符串,我们将其排列组合进行二次遍历
          //然后遍历到第i位值,使用j次删除的时候, 我们可以选择删除, 此时相当于字符串没变,dp[i][j]=dp[i-1][j-1]
          //我们也可选择不删,保留,保留的话就需要把相同的字符串合并,比如abccdec, 这个时候可以看到,如果剩余删除次数有2以上的话,可以把de删除,把c合并.
          //即dp[i][j]=dp[k][j-diff] + 数量 ,k=2,diff即k到i之间有多少不是c 的,所以diff=2, 再把c的数量补上就够了

          int n = s.length();
          for (int i = 0; i < n; i++) {
              for (int j =  0; j <= k ; j++) {
                  //1. 先把删除的情况保存到dp
                  if (j > 0 ){
                      //这里i是从0开始的,i+1表示第几个字符,当j>i+1表示它完全有能力删除这些字符
                      if (j >= i+1){
                          //可全部删除
                          dp[i][j]= 0;
                          continue;
                      }else{
                          //如果选择删除该处字符,它的最小自然就是和前面字符一样
                          dp[i][j] = dp[i-1][j-1];
                      }
                  }
                //2. 再考虑不删除的情况,这就要计算和合并了
                  //从后往前,把和这个字符相同的进行合并,同时需要考虑要删除几个字符
                  int diff=0,same=0;
                  for (int l = i; l >=0 && diff<= j ; l--) {
                      if (s.charAt(l) == s.charAt(i)){
                          same++;
                          if (l == 0){
                              //表示找相同的元素已经找到了头都可以合起来
                              dp[i][j] = Math.min(dp[i][j], cal(same));
                          }else{
                            dp[i][j] = Math.min(dp[i][j], dp[l-1][j-diff]+cal(same));
                          }
                      }else{
                          diff++;
                      }

                  }
              }
          }
          return dp[s.length()-1][k];

      }

      private int cal(int n){
          if (n == 1){
              //a
              return 1;
          }else if (n < 10){
              //aaaa -> a4
              return 2;
          }else if (n < 100){
              return 3;
          }else if (n < 1000){
              return 4;
          }
          return -1;
      }

      /**
       * 超时了..
       * 这个题一度无法下笔,思路都没有, 看了几个解析用dp,感觉云里雾里,看了一个用记忆搜索的,感觉更容易理解.(但是超时了T_T)
       * 我们假设从左到右,对每个字符判断,每个字符都可以选择是删除,还是保留.如 babcd,k表示可删除次数,i表示当前位置.
       *  k=3        [b]abcd ->(保留,i++) b[a]bcd
       *            ↙(删除b)
       *  k=2     (b)[a]bcd ->(保留) (b)a[b]cd -> ...
       *        ↙(删除a)              ↙(删除b)
       *     ....
       * 每个位置都可以选择删和不删,最后层层获取结果,将删和不删的相比较. 为了提高效率,使用map缓存记录结果
       */
    public int getLengthOfOptimalCompression2(String s, int k) {
        //表示当前到哪个位置
        int i = 0;
        //表示上一个相同字符
        char pre = ' ';
        //表示相同的个数
        int same = 0;

        return ji_yi_sou_suo(s,i,k,pre,same);
    }
    private Map<String,Integer> cache = new HashMap<>();

    private int ji_yi_sou_suo(String s,int i,int k,char pre,int same){
        //全删了
        if (k >= s.length()){
            return 0;
        }

        if ( cache.containsKey(i+"_"+k+"_"+pre+"_"+same)){
            return cache.get(i+"_"+k+"_"+pre+"_"+same);
        }
        if (i == s.length()){
            return 0;
        }
        //分别记录删,和不删两种情况的结果,看选择哪个好
        int delete = Integer.MAX_VALUE,skip = Integer.MAX_VALUE;
        if (k > 0){
            //删,其实就是跳过
            delete = ji_yi_sou_suo(s,i+1,k-1,pre,same);
        }

        //是否需要加长度,出现则为1. 这里必须要用一个新变量来接受,因为后面缓存的时候需要原来的入参
        int addLen = 0,newsame = same;
        char newpre = pre;
        //保留的情况
        if (s.charAt(i) == pre){
            //如果和之前相同的一样,有两种情况,比如之前是a9,或a99, 再加一就进位长度+1
            // 或者之前是单独一个a,再来一个a变成a2也要加长度
            if (same == 1 ||((same + 1)+"").length() > (same+"").length()){
                addLen = 1;
            }
            newsame = same+1;
        }else{
            //和前一位不相同
            newpre = s.charAt(i);
            newsame = 1;
            addLen=1;
        }
        skip = addLen + ji_yi_sou_suo(s,i+1,k,newpre,newsame);
        //比较两个哪个大,取小的
        int min = Math.min(delete ,skip);
        //缓存
        cache.put(i+"_"+k+"_"+pre+"_"+same,min);
        return min;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}