//给你一个方程，左边用 words 表示，右边用 result 表示。 
//
// 你需要根据以下规则检查方程是否可解： 
//
// 
// 每个字符都会被解码成一位数字（0 - 9）。 
// 每对不同的字符必须映射到不同的数字。 
// 每个 words[i] 和 result 都会被解码成一个没有前导零的数字。 
// 左侧数字之和（words）等于右侧数字（result）。 
// 
//
// 如果方程可解，返回 True，否则返回 False。 
//
// 
//
// 示例 1： 
//
// 输入：words = ["SEND","MORE"], result = "MONEY"
//输出：true
//解释：映射 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
//所以 "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652 
//
// 示例 2： 
//
// 输入：words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
//输出：true
//解释：映射 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->
//4
//所以 "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214 
//
// 示例 3： 
//
// 输入：words = ["THIS","IS","TOO"], result = "FUNNY"
//输出：true
// 
//
// 示例 4： 
//
// 输入：words = ["LEET","CODE"], result = "POINT"   LETCODPIN
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 2 <= words.length <= 5 
// 1 <= words[i].length, results.length <= 7 
// words[i], result 只含有大写英文字母 
// 表达式中使用的不同字符数最大为 10 
// 
//
// Related Topics 数组 数学 字符串 回溯 👍 69 👎 0


package com.zgd.leetcode.editor.cn;

import java.util.*;

public class VerbalArithmeticPuzzle {

    /**
     * 1307 口算难题 这个题的思路,想到了回溯法,还是比较复杂的
     * 1. 首先把出现过的字符都去重的列出来放到数组A,并用listB记录下标
     * 2. 然后可分配的数字0-9共十个,用一个数组记录用过的数字, 然后回溯法分配
     * 3. 如果数组A都分配完了,计算看看想不想等,相等则结束,不相等,回溯分配的值
     * <p>
     * 还有一种优化方法,把字符看成是方程式的参数x,y,z,把每个字符看成10进制,这样每次回溯的时候,不用完全的计算一遍, 只用加,减变动的值就可以 如下:
     * 以 "SEND"+"MORE"="MONEY" 為例:
     * SEND=1000*S+100*E+10*N+D MORE=1000*M+100*O+10*R+E
     * MONEY=10000*M+1000*O+100*N+10*E+Y 而 SEND+MORE=MONEY 可轉換成 SEND+MORE-MONEY=0 將每個數字轉換成十進位後, 再整理後可得
     * 1000*S+91*E-90*N+D-9000*M-900*O+10*R+Y=0
     *
     * <p>
     * <p>
     * <p>
     * 2023-06-13 16:03:30
     */
    public static void main(String[] args) {
        Solution solution = new VerbalArithmeticPuzzle().new Solution();
        //17053
        //071  355
        solution.isSolvable(new String[]{"A", "B"}, "A");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isSolvable(String[] words, String result) {
            //先把出现过的字符去重集中起来, 一共26个字母
            Integer[] charArr = new Integer[27];
            //记录用过的数字
            byte[] usernum = new byte[10];
            //记录出现的字符的下标
            Set<Integer> charIdx = new HashSet<>();
            int maxword = 0;
            //先把出现过的字符都放到数组里标记起来
            for (int i = 0; i < words.length; i++) {
                maxword = words[i].length() > maxword ? words[i].length() : maxword;
                markChar(charArr, charIdx, words[i]);
            }
            if (maxword > result.length()) {
                //如果word里面的位数比结果还大,肯定false
                return false;
            }
            markChar(charArr, charIdx, result);

            //针对出现过的字符, 一个个试,错误就回溯
            return huisu2(charArr, usernum, new ArrayList<>(charIdx), 0, words, result);

        }

        private void markChar(Integer[] charArr, Set<Integer> charIdx, String words) {
            char[] charArray = words.toCharArray();
            for (int n = 0; n < charArray.length; n++) {
                int idx = charArray[n] - 'A';
                charArr[idx] = -1;
                charIdx.add(idx);
            }
        }


        /**
         * 这个方法,正确是正确 但是差点超时
         *
         * @param charArr
         * @param usernum
         * @param i
         * @param words
         * @param result
         * @return
         */
        private boolean huisu2(Integer[] charArr, byte[] usernum, List<Integer> charIdx, int i, String[] words, String result) {
            if (i >= charIdx.size()) {
                //数据都填满了,开始计算
                int res = getNum(result, charArr);

                for (String word : words) {
                    int num = getNum(word, charArr);
                    res -= num;
                    if (res < 0 || num < 0) {
                        return false;
                    }
                }
                return res == 0;
            }

            for (int j = 0; j < 10; j++) {
                //如果为1说明已经出现过
                if (usernum[j] == 1) {
                    continue;
                }
                int idx = charIdx.get(i);
                charArr[idx] = j;
                usernum[j] = 1;
                boolean b = huisu2(charArr, usernum, charIdx, i + 1, words, result);
                if (b) {
                    return true;
                } else {
                    //回溯
                    usernum[j] = 0;
                }
            }
            return false;
        }

        private int getNum(String word, Integer[] charArr) {
            char[] war = word.toCharArray();
            //因为题目规定了是 非零前导的数字,所以如果解析出来长度>1.且前面是0,直接返回-1
            if (word.length() > 1 && charArr[(war[0] - 'A')] == 0) {
                return -1;
            }
            int n = 1;
            int sum = 0;
            for (int i = word.length() - 1; i >= 0; i--) {
                sum += charArr[(war[i] - 'A')] * n;
                n *= 10;
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    boolean fndAns(Map<Character, Integer> hm, List<Character> chAL, Set<Character> hS, boolean[] vstd, int sum) {
        if (chAL.isEmpty()) return sum == 0;
        //若所有字母皆有對應,則比對總和是否為0
        Character ch = chAL.remove(0);
        //回溯取出
        for (int ix = 0; ix < 10; ix++) {
            if (vstd[ix]) continue;
            if (ix == 0 && hS.contains(ch)) continue;
            //開頭字母避開0
            vstd[ix] = true;
            //回溯取出
            sum += hm.get(ch) * ix;
            //回溯加上
            if (fndAns(hm, chAL, hS, vstd, sum)) return true;
            sum -= hm.get(ch) * ix;
            //回溯減回
            vstd[ix] = false;
            //回溯存回
        }
        chAL.add(0, ch);
        //回溯存回
        return false;
    }

    public boolean isSolvable(String[] words, String result) {
        Map<Character, Integer> hm = new HashMap<>();
        //使用到的字母,和其對應的十進位數目
        Set<Character> hS = new HashSet<>();
        //存放開頭字母, 以避開0
        for (String wd : words) {
            char[] chs = wd.toCharArray();
            for (int ix = 0; ix < chs.length; ix++) {
                char ch = chs[ix];
                hm.put(ch, hm.getOrDefault(ch, 0) + (int) Math.pow(10, chs.length - 1 - ix));
                //將字母轉成十進位
            }
        }
        char[] chs = result.toCharArray();
        for (int ix = 0; ix < chs.length; ix++) {
            char ch = chs[ix];
            hm.put(ch, hm.getOrDefault(ch, 0) - (int) Math.pow(10, chs.length - 1 - ix));
            //wd為正, result 為負
        }
        List<Character> chAL = new ArrayList<>(hm.keySet());
        //列出此次用到的字母
        return fndAns(hm, chAL, hS, new boolean[10], 0);
    }
}