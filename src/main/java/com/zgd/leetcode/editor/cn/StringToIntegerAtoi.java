//请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。 
//
// 函数 myAtoi(string s) 的算法如下： 
//
// 
// 读入字符串并丢弃无用的前导空格 
// 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。 
// 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。 
// 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 
//2 开始）。 
// 如果整数数超过 32 位有符号整数范围 [−2³¹, 231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2³¹ 的整数应该被固
//定为 −2³¹ ，大于 231 − 1 的整数应该被固定为 231 − 1 。 
// 返回整数作为最终结果。 
// 
//
// 注意： 
//
// 
// 本题中的空白字符只包括空格字符 ' ' 。 
// 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "42"
//输出：42
//解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
//第 1 步："42"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
//         ^
//第 3 步："42"（读入 "42"）
//           ^
//解析得到整数 42 。
//由于 "42" 在范围 [-2³¹, 2³¹ - 1] 内，最终结果为 42 。 
//
// 示例 2： 
//
// 
//输入：s = "   -42"
//输出：-42
//解释：
//第 1 步："   -42"（读入前导空格，但忽视掉）
//            ^
//第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
//             ^
//第 3 步："   -42"（读入 "42"）
//               ^
//解析得到整数 -42 。
//由于 "-42" 在范围 [-2³¹, 2³¹ - 1] 内，最终结果为 -42 。
// 
//
// 示例 3： 
//
// 
//输入：s = "4193 with words"
//输出：4193
//解释：
//第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
//         ^
//第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
//             ^
//解析得到整数 4193 。
//由于 "4193" 在范围 [-2³¹, 2³¹ - 1] 内，最终结果为 4193 。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 200 
// s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成 
// 
//
// Related Topics 字符串 👍 1677 👎 0


  
package com.zgd.leetcode.editor.cn;

public class StringToIntegerAtoi{

  /**
  * 8
  * 字符串转换整数 (atoi)
  *
  * 这道题最大的问题是数字溢出的问题, int和long溢出要如何进行判断
   * 对字符串的解析思路,首先处理前面的空格, 然后判断正负号, 然后处理多余的0, 最后解析连续的数字
   * 对溢出的解决思路, 就是加,或乘以后, 是否为负数, 然后是否能逆向运算回去.
  * 
  *
  * 2023-06-07 18:17:26
  */  
  public static void main(String[] args) {
    Solution solution = new StringToIntegerAtoi().new Solution();
      solution.myAtoi("2147483648");
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int myAtoi(String s) {
        int maxInt = Integer.MAX_VALUE;
        int minInt = Integer.MIN_VALUE;
        boolean fushu = false;

        //分三步来处理, 第一步处理前面的空格
        StringBuffer sb = new StringBuffer();
        if(s == null || s.length() == 0){
            return 0;
        }
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ' && sb.length() == 0){
                continue;
            }else{
                sb.append(s.charAt(i));
            }
        }
        if(sb.length() == 0){
            return 0;
        }
        //第二步判断正负
        int startIdx = 0;
        if (sb.charAt(0) == '-'){
            startIdx = 1;
            fushu = true;
        }else if(sb.charAt(0) == '+'){
            startIdx = 1;
        }
        int endIdx = sb.length();//12345
        //第三步,从头开始解析数字, 同时去掉前面的很多个0比如 00000123
        boolean has0 = true;
        for (int i = startIdx; i < sb.length(); i++) {
            if(has0 ){
                if( sb.charAt(i) == '0'){
                    if(i ==sb.length()-1){ //这里不要忘了加 i ==sb.length()-1 这个条件
                        has0 =false;
                        startIdx = i;
                    }
                    continue;
                }else if (sb.charAt(i) != '0' ){
                    has0 =false;
                    startIdx = i;
                }
            }
            if(sb.charAt(i) >= '0' && sb.charAt(i) <= '9'){
                continue;
            }else{
                endIdx = i;
                break;
            }
        }
        String number = sb.substring(startIdx,endIdx);
        if(number.length() == 0){
            return 0;
        }
        //得到数字
        int weishu = 1;
        boolean gewei = true;
        int num = 0;
        for (int i = number.length()-1; i >=0; i--) {
            int n = number.charAt(i) - '0';
            if(gewei){
                num += (n);
                gewei = false;
            }else{
                //要判断是否超出范围,因为如果碰到100000000000001321 这样的, 也会导致突然超过int的范围, weishu一直*10, 溢出的话变成负数

                //下面几排计算可能溢出
                int new_weishu = weishu * 10;
                int n1 = n * new_weishu;
                //相加也可能变成负数
                int new_num = num+ n1;
                    //判断是否溢出,最简单的是逆向再算一次看看对不对
                if(new_weishu < 0 || new_weishu /10 != weishu ||  new_num<0|| n1 <0|| new_num - num != n1 || n1 / new_weishu != n){
                    //判断是否超过最大最小值
                    if (fushu ){
                        return minInt;
                    }else {
                        return maxInt;
                    }
                }
                weishu = new_weishu;
                num = new_num;
            }
        }
        return  (int)( fushu ?-num:num);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}