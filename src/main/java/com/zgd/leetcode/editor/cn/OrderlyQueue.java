//给定一个字符串 s 和一个整数 k 。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。 
//
// 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "cba", k = 1
//输出："acb"
//解释：
//在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
//在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。
// 
//
// 示例 2： 
//
// 
//输入：s = "baaca", k = 3
//输出："aaabc"
//解释：
//在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
//在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= S.length <= 1000 
// s 只由小写字母组成。 
// 
// Related Topics 数学 字符串 排序 👍 60 👎 0


package com.zgd.leetcode.editor.cn;

public class OrderlyQueue {

  /**
   * 899
   * 有序队列
   * <p>
   * 这里其实被题目给蒙中了,取前k个字母中的一个放到末尾这个很迷惑性.
   * 实际上这个就只有两种情况,k=1,以及k>=2
   * <p>
   * 前者的话无法改变字母之间的顺序(除了首尾),即首位是最小,然后将它前面的放到后面, 再比较每次这种字符串是不是最小
   * 【可能会有多个相同的首字符串最小的情况，这里一定要比较每次拼出的新字符串是不是更小】(虽然可能首字母都是同一个最小的a), 比如abazdb 和 azdbab
   * 后者的话,能从前2个以上的字符中的取1个放到后面, 既然是2(多)选1移动, 就可以改变所有字符之间的顺序了
   * <p>
   * 可以想象成整理左手的一副扑克牌, 每次都是调整左边k张中的1张放到后面, 最后可以排成序
   * 复习下排序:
   * 名字   时间复杂度
   * 冒泡:
   * 2022-05-20 11:28:56
   */
  public static void main(String[] args) {
    Solution solution = new OrderlyQueue().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public String orderlyQueue(String s, int k) {
      char[] chars = s.toCharArray();
      if (k <= 1) {
        //找到最小的放前面
        int min = 0;
        String minStr = null;
        for (int i = 0; i < chars.length; i++) {
          //这里是小于等于,而不仅仅是小于,比如bazabd 如果只看小于,char同样是a,则min=1的a, 但是min=3的a后面跟着b更小
          if (chars[i] <= chars[min]) {
            min = i;
            //这里需要注意,每一轮都需要拼接,每一轮都比较新字符串大小
            String s1 = s.substring(min) + s.substring(0, min);
            if (minStr == null) {
              //初始化
              minStr = s1;
            } else {
              minStr = minStr.compareTo(s1) > 0 ? s1 : minStr;
            }
          }
        }
        //把最小的放前面 ** 这里不能直接简单的把这个字符提到前面, 因为这样就相当于打乱了字符顺序.
        //它后面的,不变,再把它前面的, 放到后面
        return minStr;
      }
      //k>1 冒泡吧
      for (int i = 0; i < chars.length - 1; i++) {
        boolean hasChange = false;
        for (int j = 0; j < chars.length - 1 - i; j++) {
          if (chars[j] > chars[j + 1]) {
            hasChange = true;
            char t = chars[j];
            chars[j] = chars[j + 1];
            chars[j + 1] = t;
          }
        }
        if (!hasChange) {
          break;
        }
      }
      return new String(chars);
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
