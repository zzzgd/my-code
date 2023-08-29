//给你一个仅由数字（0 - 9）组成的字符串 num 。 
//
// 请你找出能够使用 num 中数字形成的 最大回文 整数，并以字符串形式返回。该整数不含 前导零 。 
//
// 注意： 
//
// 
// 你 无需 使用 num 中的所有数字，但你必须使用 至少 一个数字。 
// 数字可以重新排序。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：num = "444947137"  9 7.2 4.4 3.1 1.1
//输出："7449447"
//解释：
//从 "444947137" 中选用数字 "4449477"，可以形成回文整数 "7449447" 。
//可以证明 "7449447" 是能够形成的最大回文整数。
// 
//
// 示例 2： 
//
// 
//输入：num = "00009"
//输出："9"
//解释：
//可以证明 "9" 能够形成的最大回文整数。
//注意返回的整数不应含前导零。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= num.length <= 10⁵ 
// num 由数字（0 - 9）组成 
// 
//
// Related Topics 贪心 哈希表 字符串 👍 23 👎 0


  
package com.zgd.leetcode.editor.cn;

public class LargestPalindromicNumber{

  /**
  * 2384
  * 最大回文数字
  * 
  * 
  *
  * 2023-08-28 17:44:26
  */  
  public static void main(String[] args) {
    Solution solution = new LargestPalindromicNumber().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String largestPalindromic(String num) {
        //思路: 要拼成回文. 首先我们可以把这串数字整理起来, 得到每个数字有多少个
        //然后我们只需要按从大到小的顺序拼前一半,取出的时候数字-2,直到所有数字只剩1个或者都用完
        //然后我们取最大的一个数字放在最后作为回文中轴线(如果还剩下数字), 然后拼出后半部分
        int[] nums = new int[10];
        char[] chararr = num.toCharArray();
        for (int i = 0; i < num.length(); i++) {
            int n = chararr[i]-'0';
            nums[n]++;
        }
        StringBuilder sb = new StringBuilder();
        int maxSingleNum = -1;
        //从大到小拼
        for (int i = 9; i >=0 ; i--) {
            while(nums[i]>=2){
                //为了避免0前导的情况,如果i是0,且前面没有数,跳过
                if(sb.length() == 0 && i == 0){
                    break;
                }
                sb.append(i);
                nums[i]-=2;
            }
            //如果数字还剩下1个,记录下来
            if(maxSingleNum < 0 && nums[i] > 0){
                maxSingleNum = i;
            }
        }
        StringBuilder head = new StringBuilder(sb);
        StringBuilder tail = sb.reverse();
        if(maxSingleNum >= 0){
            head.append(maxSingleNum);
        }
        head.append(tail);
        return head.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}