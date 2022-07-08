//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。 
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 1 <= words.length <= 5000 
// 1 <= words[i].length <= 30 
// words[i] 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 781 👎 0


  
package com.zgd.leetcode.editor.cn;

import javafx.util.Pair;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.*;

public class SubstringWithConcatenationOfAllWords{

  /**
  * 30
  * 串联所有单词的子串
  * 
  * 超时了..
  *
  * 2022-07-07 17:53:08
  */  
  public static void main(String[] args) {
    Solution solution = new SubstringWithConcatenationOfAllWords().new Solution();
    solution.findSubstring("wordgoodgoodgoodbestword",new String[]{"word","good","best","word" });
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
      int wl = words[0].length();
      Map<String,int[]> map = new HashMap<>();
      int count = 0;
      for (String word : words) {
        int[] n = map.get(word);
        if (n == null){
          n = new int[2];
        }
        count++;
        n[0]++;
        n[1]++;
        map.put(word,n);
      }
      List<Integer>res = new ArrayList<>();
      for (int j = 0; j < s.length(); j++) {
        check(s,j, words,map,res);
      }
      return res;


    }


    private void check(String s, int from, String[] words, Map<String, int[]> map, List<Integer>res ){
      int wl = words[0].length();
      int ll = words.length * wl;
      if (from> s.length() - ll){
        return;
      }
      //总体符合的数量
      int n = 0;
      for (int i = 0; i <  words.length; i++) {
        String ss = s.substring(from + i * wl, from + i * wl + wl);
        if (map.containsKey(ss) && map.get(ss)[1]>0){
          //匹配中一个单词,单词的计数-1,总体符合的数量+1
          map.get(ss)[1]--;
          n++;
        }
      }
      if (n==words.length){
        //说明正好匹配
        res.add(from);
    }
      //恢复map
      for (int[] value : map.values()) {
        value[1]= value[0];
      }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}