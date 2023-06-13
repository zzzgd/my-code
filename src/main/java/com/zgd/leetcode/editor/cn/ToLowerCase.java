//给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "Hello"
//输出："hello"
// 
//
// 示例 2： 
//
// 
//输入：s = "here"
//输出："here"
// 
//
// 示例 3： 
//
// 
//输入：s = "LOVELY"
//输出："lovely"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 由 ASCII 字符集中的可打印字符组成 
// 
//
// Related Topics 字符串 👍 230 👎 0


  
package com.zgd.leetcode.editor.cn;

public class ToLowerCase{

  /**
  * 709
  * 转换成小写字母
  * 
  * 
  *
  * 2023-06-13 14:02:34
  */  
  public static void main(String[] args) {
    Solution solution = new ToLowerCase().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String toLowerCase(String s) {
        int diff = 'a' - 'A';
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] >= 'A' && arr[i] <= 'Z'){
                arr[i] = (char)(arr[i]+diff);
            }
        }
        return new String(arr);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}