//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。 
//
// 返回符合要求的 最少分割次数 。 
//
// 
// 
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：1
//解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：s = "ab"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2000 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 700 👎 0


  
package com.zgd.leetcode.editor.cn;

import javax.print.attribute.standard.JobKOctets;

public class PalindromePartitioningIi{

  /**
  * 132
  * 分割回文串 II
  * 
  * 
  *
  * 2023-08-29 15:49:12
  */  
  public static void main(String[] args) {
    Solution solution = new PalindromePartitioningIi().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCut(String s) {
        //这个题抛开回文的问题, 其实可以简单化为, 求 0~m的最小值, 并可通过0~k的最小值x(k), x[0,m] = x(k) + x[k+1,m]
        //再细化一下, 就是 if(是回文(i,j)) then  x(j) = x(i-1)+1;

        char[] arr = s.toCharArray();
        //先记录哪些是回文
        boolean[][] huiwen = new boolean[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length ; j++) {
                huiwen[i][j] = ishuiwen(arr,i,j);
            }
        }
        //mini[i] 指的是第i个元素之间最少的回文数. 从1开始
        int[] mini = new int[arr.length+1];
        //初始化 mini[0]=0;
        //从左到右二次循环, [0,0], 到[0,1],[1,1],[1,2],[2,2], 然后分别求得x(i)从0到结尾的最小回文数
        for (int i = 0; i < arr.length; i++) {
            mini[i+1] = Integer.MAX_VALUE;
            for (int j = 0; j <= i; j++) {
                if(huiwen[j][i]){
                    mini[i+1] = Math.min(mini[i+1],mini[j]+1);
                }
            }
        }
        //需要减一,因为需要的是分割数而不是回文数量
        return mini[arr.length]-1;
    }


    private boolean ishuiwen(char[] arr, int i,int j){
        while(i <= j && arr[i] == arr[j]){
            if(i == j || i == j-1){
                return true;
            }
            i++;
            j--;
        }

        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}