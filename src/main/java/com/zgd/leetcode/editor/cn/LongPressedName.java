//你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。 
//
// 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。 
//
// 
//
// 示例 1： 
//
// 
//输入：name = "alex", typed = "aaleex"
//输出：true
//解释：'alex' 中的 'a' 和 'e' 被长按。
// 
//
// 示例 2： 
//
// 
//输入：name = "saeed", typed = "ssaaedd"
//输出：false
//解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= name.length, typed.length <= 1000 
// name 和 typed 的字符都是小写字母 
// 
//
// Related Topics 双指针 字符串 👍 275 👎 0


  
package com.zgd.leetcode.editor.cn;

public class LongPressedName{

  /**
  * 925
  * 长按键入
  * 
  * 
  *
  * 2023-06-08 15:33:26
  */  
  public static void main(String[] args) {
    Solution solution = new LongPressedName().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        if(name.length() > typed.length()){
            return false;
        }
        if(name.length() ==0 && typed.length() == 0){
            return true;
        }
        //双指针
        int ni = 0,ti = 0;
        int matchedIdx = 0;

        //aabcd  //aaabbcd
        while( ti < typed.length()){
            if(ni < name.length() && name.charAt(ni) == typed.charAt(ti)){
                //名字ni处和当前输入字符ti处匹配, 记录匹配成功的位置matchIdx, 两者一起右移
                matchedIdx = ni;
                //ni右移
                ni++; //ni可能超出name的length,但是matchIdx不会
            }else{
                //如果不匹配,和之前匹配过的进行匹配
                if(name.charAt(matchedIdx) == typed.charAt(ti)){
                    //如果和前面的匹配, 则名字的游标不右移
                }else{
                    //否则返回false
                    return false;
                }
            }
            //不管匹不匹配,输入字符右移
            ti++;
        }
        //跳出循环,判断ni是否匹配完了,以及ti是否匹配完了
        return matchedIdx == name.length()-1 && ti == typed.length();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}