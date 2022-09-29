//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// ccbacdb  c3b2d1  cbad
//输入：s = "cbacdcbc"  c4 b2 a1 d1 --> cbad  -> c3b2a1d1: bacd  ->c3b1a1d1:acdb -> c3b1a0d1
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 
//
// 
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-
//distinct-characters 相同 
//
// Related Topics 栈 贪心 字符串 单调栈 👍 824 👎 0


  
package com.zgd.leetcode.editor.cn;

import com.sun.deploy.security.MSCryptoDSASignature;

import java.util.*;

public class RemoveDuplicateLetters{

  /**
  * 316
  * 去除重复字母
  * 
  * 
  *
  * 2022-09-29 11:06:15
  */  
  public static void main(String[] args) {
    Solution solution = new RemoveDuplicateLetters().new Solution();
    solution.removeDuplicateLetters("ececbaceba"); //e:0,5 c:1,4 b:2,6 a:3,7
      //e
      // ec
      // ec ce
      // ec ce ec
      // ecb ceb
      // ecba ceba
      // ecba ebac ceba ebac
      // ecba cbae ebac bace ceba cbae ebac bace
      // ce ec
      // ce ec
      //
      //ec ce
      //ec
      //cbae bace
      //edab eacb caeb aceb
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      public String removeDuplicateLetters(String s) {
          //其实这个题在前面的2个错误方法中摸到了一点门道
          //一个就是找出所有不重复的元素,和他们的次数,遍历一个元素它的剩余个数就减一
          //一个就是维护一个队列,遍历的时候更新队列成有序,然后如果队尾的元素比当前的大,判断剩余个数是否还有,有的话就可以弹出
          int[] count = new int[26];
          for (char c : s.toCharArray()) {
              count[c-'a']+=1;
          }
          //双向链表
          LinkedList<Character> ll = new LinkedList<>();
          //标记是否已经放到链表
          boolean[] mk = new boolean[26];
          for (char c : s.toCharArray()) {
              //遍历的剩余个数减一
              count[c-'a']-=1;
              if (mk[c-'a']){
                  //如果出现过就跳过
                  continue;
              }
              mk[c-'a'] = true;
              //从后往前找,需要弹出后面的元素. 1: 新的比后面的小,且弹出的剩余元素数目>=1, 否则放在后面
              char last;
              while (!ll.isEmpty() && (last = ll.peekLast())>c && count[last-'a'] >= 1) {
                  Character m = ll.pollLast();
                  //移除的时候别忘了标记为不存在
                  mk[m-'a']=false;
              }

              //放入队列
              ll.offer(c);

          }
          StringBuilder sb = new StringBuilder();
          Character last;
          while ((last = ll.poll())!=null){
              sb.append(last);
          }
          return sb.toString();

      }

}
//leetcode submit region end(Prohibit modification and deletion)


    /**
     * 看错题了
     * @param s
     * @return
     */
    public String removeDuplicateLetters2(String s) {
    //这个直接转为数组就行, 因为不能重复,一共就是26个字母, 设置一个26大小的数组,出现过就1,没出现就0.在输出
    int[] mark = new int[26];
    for (char c : s.toCharArray()) {
        mark[c-'a']=1;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < mark.length; i++) {

        if (mark[i] > 0){
            sb.append((char)(i+'a'));
        }
    }
    return sb.toString();
}

    /**
     * 这个到没看错题,超时了
     * @param s
     * @return
     */
    public String removeDuplicateLetters3(String s) {
        Set<String> ss = new HashSet<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (ss.isEmpty()){
                ss.add(String.valueOf(c));
            }
            Iterator<String> iterator = ss.iterator();
            List<String> temp = new ArrayList<>();
            while (iterator.hasNext()){
                String s1 = iterator.next();
                //移除原来的c,把新的c放到最后然后放到set里
                int i = s1.indexOf(String.valueOf(c));
                if (i>=0){
                    StringBuilder copyss = new StringBuilder(s1);
                    copyss.deleteCharAt(i).append(c);
                    temp.add(copyss.toString());
                }else{
                    iterator.remove();
                    //临时放到新容器,遍历完再丢进去
                    temp.add(s1+c);
                }
            }
            ss.addAll(temp);
        }
        String min = null;
        for (String sb : ss) {
            min = min == null || min.compareTo(sb.toString())>0?sb.toString():min;
        }
        return min;
    }
}