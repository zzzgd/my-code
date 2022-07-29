//给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 
//输入: a = "11", b = "10"
//输出: "101" 
//
// 示例 2: 
//
// 
//输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
//
// 
//
// 注意：本题与主站 67 题相同：https://leetcode-cn.com/problems/add-binary/ 
// Related Topics 位运算 数学 字符串 模拟 👍 42 👎 0


  
package com.zgd.leetcode.editor.cn;

public class JFETK5{

  /**
  * 剑指 Offer II 002
  * 二进制加法
  * 
  * 
  *
  * 2022-07-29 14:33:00
  */  
  public static void main(String[] args) {
    Solution solution = new JFETK5().new Solution();
    solution.addBinary("11","10");
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addBinary(String a, String b) {
        //1. 先将其转化位两个0,1的int数组
        int[] aa = new int[a.length()];
        int[] bb = new int[b.length()];
        for (int i = 0; i < aa.length; i++) {
            aa[i] = a.charAt(i) - '0';
        }
        for (int i = 0; i < bb.length; i++) {
            bb[i] = b.charAt(i) - '0';
        }
        //两数相加,长度最大只能是Math.max(a长度,b长度)+1   11 + 11 =  110   10 + 10 = 100
        int[] res = new int[Math.max(a.length(),b.length())+1];
        //从后往前,两两相加,进位就为0,并标记下一位进位
        int jingwei = 0;
        int ae = aa.length -1, be = bb.length-1, re = res.length - 1;
        for (int i = 0; i < res.length; i++) {
            //虽然遍历的i是 从0开始, 但是元素下标的处理都是从后往前.
            int x = i <= ae ? aa[ae - i] : 0;
            int y = i <= be ? bb[be - i] : 0;
            int t = x+y+jingwei;
            //得到的数除以2, 余数为当前位的值, 除数是下一个进位
            res[re - i] = t % 2;
            jingwei = t/2;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[0] == 0){
                continue;
            }
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}