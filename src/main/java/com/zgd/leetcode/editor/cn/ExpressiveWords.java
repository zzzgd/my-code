//有时候人们会用重复写一些字母来表示额外的感受，比如 "hello" -> "heeellooo", "hi" -> "hiii"。我们将相邻字母都相同的一串
//字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。 
//
// 对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下
//：选择一个字母组（包含字母 c ），然后往其中添加相同的字母 c 使其长度达到 3 或以上。 
//
// 例如，以 "hello" 为例，我们可以对字母组 "o" 扩张得到 "hellooo"，但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 
//长度小于 3。此外，我们可以进行另一种扩张 "ll" -> "lllll" 以获得 "helllllooo"。如果 s = "helllllooo"，那么查询词
// "hello" 是可扩张的，因为可以对它执行这两种扩张操作使得 query = "hello" -> "hellooo" -> "helllllooo" = 
//s。 
//
// 输入一组查询单词，输出其中可扩张的单词数量。 
//
// 
//
// 示例： 
//
// 
//输入： 
//s = "heeellooo"
//words = ["hello", "hi", "helo"]
//输出：1
//解释：
//我们能通过扩张 "hello" 的 "e" 和 "o" 来得到 "heeellooo"。
//我们不能通过扩张 "helo" 来得到 "heeellooo" 因为 "ll" 的长度小于 3 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, words.length <= 100 
// 1 <= words[i].length <= 100 
// s 和所有在 words 中的单词都只由小写字母组成。 
// 
//
// Related Topics 数组 双指针 字符串 👍 126 👎 0


  
package com.zgd.leetcode.editor.cn;

public class ExpressiveWords{

  /**
  * 809
  * 情感丰富的文字
  * 
  * 
  *
  * 2023-08-30 11:33:25
  */  
  public static void main(String[] args) {
    Solution solution = new ExpressiveWords().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int expressiveWords(String s, String[] words) {
        //这个想到用双数组,[x]和[y], x表示s的字符,y表示字符个数 将字符串s记录下来
        char[] charr = s.toCharArray();
        //因为s长度最大是100,所以size设置100. 把字符串压缩, 比如aabbccc变成x[]: a,b,c y[]: 2,2,3
        char[] x = new char[100];
        int[] y = new int[100];
        int i = -1;
        for (char c : charr) {
            if( i < 0  || x[i] != c){
                //如果当前字符和上一个字符不一样,i+1.
                i++;
                x[i]=c;
            }
            y[i]++;
        }
        int num = 0;
        for (String word : words) {
            if(isExpress(word, x, y)) num++;

        }
        return num;

    }

      private boolean isExpress(String word, char[] x, int[] y) {
          char[] wwarr = word.toCharArray();
          int n = 0;
          int idx = 0;
          for (int j = 0; j < wwarr.length; j++) {
              if(x[idx] != wwarr[j]){
                  return false;
              }
              n++;
                  //如果j是最后一个元素,或者下个元素和j不一样,进行判断
              if(j == wwarr.length-1 || wwarr[j+1]!=wwarr[j] ){
                  //判断的内容是, 这个字母和原字符串的数量是否一样, 如果不一样且比原来的少, 那就需要扩充, 扩充的条件是原来的字母数量超过3个
                  if(n == y[idx] || (n < y[idx] && y[idx] >= 3)){
                    idx++;
                    n = 0;
                  }else{
                      return false;
                  }

              }
          }
          //如果x的下一个字符不是0,表示还有字符没匹配完,不符合
          if(idx == x.length || x[idx] == 0){
              return true;
          }
          return false;
      }
  }
//leetcode submit region end(Prohibit modification and deletion)

}