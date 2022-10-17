//按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> 
//s1 -> s2 -> ... -> sk 这样的单词序列，并满足： 
//
// 
// 
// 
// 每对相邻的单词之间仅有单个字母不同。 
// 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单
//词。 
// sk == endWord 
// 
// 
// 
//
// 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的
// 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
//"log","cog"]
//输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
//解释：存在 2 种最短的转换序列：
//"hit" -> "hot" -> "dot" -> "dog" -> "cog"
//"hit" -> "hot" -> "lot" -> "log" -> "cog"
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
//"log"]
//输出：[]
//解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 5 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 500 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有单词 互不相同 
// 
//
// Related Topics 广度优先搜索 哈希表 字符串 回溯 👍 613 👎 0


  
package com.zgd.leetcode.editor.cn;


import java.util.*;
import java.util.stream.Collectors;

public class WordLadderIi{

  /**
  * 126
  * 单词接龙 II
  * 
  * 
  *
  * 2022-09-29 17:26:30
  */  
  public static void main(String[] args) {
    Solution3 solution3 = new WordLadderIi().new Solution3();
      List<List<String>> res = solution3.findLadders("aaaaa", "ggggg", Arrays.stream(new String[]{"aaaaa","caaaa","cbaaa","daaaa","dbaaa","eaaaa","ebaaa","faaaa","fbaaa","gaaaa","gbaaa","haaaa","hbaaa","iaaaa","ibaaa","jaaaa","jbaaa","kaaaa","kbaaa","laaaa","lbaaa","maaaa","mbaaa","naaaa","nbaaa","oaaaa","obaaa","paaaa","pbaaa","bbaaa","bbcaa","bbcba","bbdaa","bbdba","bbeaa","bbeba","bbfaa","bbfba","bbgaa","bbgba","bbhaa","bbhba","bbiaa","bbiba","bbjaa","bbjba","bbkaa","bbkba","bblaa","bblba","bbmaa","bbmba","bbnaa","bbnba","bboaa","bboba","bbpaa","bbpba","bbbba","abbba","acbba","dbbba","dcbba","ebbba","ecbba","fbbba","fcbba","gbbba","gcbba","hbbba","hcbba","ibbba","icbba","jbbba","jcbba","kbbba","kcbba","lbbba","lcbba","mbbba","mcbba","nbbba","ncbba","obbba","ocbba","pbbba","pcbba","ccbba","ccaba","ccaca","ccdba","ccdca","cceba","cceca","ccfba","ccfca","ccgba","ccgca","cchba","cchca","cciba","ccica","ccjba","ccjca","cckba","cckca","cclba","cclca","ccmba","ccmca","ccnba","ccnca","ccoba","ccoca","ccpba","ccpca","cccca","accca","adcca","bccca","bdcca","eccca","edcca","fccca","fdcca","gccca","gdcca","hccca","hdcca","iccca","idcca","jccca","jdcca","kccca","kdcca","lccca","ldcca","mccca","mdcca","nccca","ndcca","occca","odcca","pccca","pdcca","ddcca","ddaca","ddada","ddbca","ddbda","ddeca","ddeda","ddfca","ddfda","ddgca","ddgda","ddhca","ddhda","ddica","ddida","ddjca","ddjda","ddkca","ddkda","ddlca","ddlda","ddmca","ddmda","ddnca","ddnda","ddoca","ddoda","ddpca","ddpda","dddda","addda","aedda","bddda","bedda","cddda","cedda","fddda","fedda","gddda","gedda","hddda","hedda","iddda","iedda","jddda","jedda","kddda","kedda","lddda","ledda","mddda","medda","nddda","nedda","oddda","oedda","pddda","pedda","eedda","eeada","eeaea","eebda","eebea","eecda","eecea","eefda","eefea","eegda","eegea","eehda","eehea","eeida","eeiea","eejda","eejea","eekda","eekea","eelda","eelea","eemda","eemea","eenda","eenea","eeoda","eeoea","eepda","eepea","eeeea","ggggg","agggg","ahggg","bgggg","bhggg","cgggg","chggg","dgggg","dhggg","egggg","ehggg","fgggg","fhggg","igggg","ihggg","jgggg","jhggg","kgggg","khggg","lgggg","lhggg","mgggg","mhggg","ngggg","nhggg","ogggg","ohggg","pgggg","phggg","hhggg","hhagg","hhahg","hhbgg","hhbhg","hhcgg","hhchg","hhdgg","hhdhg","hhegg","hhehg","hhfgg","hhfhg","hhigg","hhihg","hhjgg","hhjhg","hhkgg","hhkhg","hhlgg","hhlhg","hhmgg","hhmhg","hhngg","hhnhg","hhogg","hhohg","hhpgg","hhphg","hhhhg","ahhhg","aihhg","bhhhg","bihhg","chhhg","cihhg","dhhhg","dihhg","ehhhg","eihhg","fhhhg","fihhg","ghhhg","gihhg","jhhhg","jihhg","khhhg","kihhg","lhhhg","lihhg","mhhhg","mihhg","nhhhg","nihhg","ohhhg","oihhg","phhhg","pihhg","iihhg","iiahg","iiaig","iibhg","iibig","iichg","iicig","iidhg","iidig","iiehg","iieig","iifhg","iifig","iighg","iigig","iijhg","iijig","iikhg","iikig","iilhg","iilig","iimhg","iimig","iinhg","iinig","iiohg","iioig","iiphg","iipig","iiiig","aiiig","ajiig","biiig","bjiig","ciiig","cjiig","diiig","djiig","eiiig","ejiig","fiiig","fjiig","giiig","gjiig","hiiig","hjiig","kiiig","kjiig","liiig","ljiig","miiig","mjiig","niiig","njiig","oiiig","ojiig","piiig","pjiig","jjiig","jjaig","jjajg","jjbig","jjbjg","jjcig","jjcjg","jjdig","jjdjg","jjeig","jjejg","jjfig","jjfjg","jjgig","jjgjg","jjhig","jjhjg","jjkig","jjkjg","jjlig","jjljg","jjmig","jjmjg","jjnig","jjnjg","jjoig","jjojg","jjpig","jjpjg","jjjjg","ajjjg","akjjg","bjjjg","bkjjg","cjjjg","ckjjg","djjjg","dkjjg","ejjjg","ekjjg","fjjjg","fkjjg","gjjjg","gkjjg","hjjjg","hkjjg","ijjjg","ikjjg","ljjjg","lkjjg","mjjjg","mkjjg","njjjg","nkjjg","ojjjg","okjjg","pjjjg","pkjjg","kkjjg","kkajg","kkakg","kkbjg","kkbkg","kkcjg","kkckg","kkdjg","kkdkg","kkejg","kkekg","kkfjg","kkfkg","kkgjg","kkgkg","kkhjg","kkhkg","kkijg","kkikg","kkljg","kklkg","kkmjg","kkmkg","kknjg","kknkg","kkojg","kkokg","kkpjg","kkpkg","kkkkg","ggggx","gggxx","ggxxx","gxxxx","xxxxx","xxxxy","xxxyy","xxyyy","xyyyy","yyyyy","yyyyw","yyyww","yywww","ywwww","wwwww","wwvww","wvvww","vvvww","vvvwz","avvwz","aavwz","aaawz","aaaaz"}).collect(Collectors.toList()));
      System.out.println("ans:"+res.size());
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      private int minDistance = Integer.MAX_VALUE;
      private List<List<String>> res = new ArrayList<>();
      public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //1. 这个题先将词典中每个词和开始词,能连接的下一个词的下标记录下来. 即判断两个词是不是只差了1个不同
        //2. 然后从开始词开始,遍历它的下一个可连接词, 并用boolean数组记录走过的下标路径
        //3. 用回溯法,横向遍历,终止条件: 没有可连接词. 成功条件,下一个连接词是目标词


        //0,如果结束词不在连接词里直接返回空
        if (!wordList.contains(endWord)){
            return res;
        }
        //1. 找连接词. 如果开始词在列表中,获取开始下标,否则把开始词放到列表中
          int startIdx = wordList.indexOf(beginWord);
        if (startIdx < 0){
          wordList.add(0,beginWord);
          startIdx = 0;
        }
          List<Integer>[] nextList = getNextList(wordList);
//        //2. 回溯
//          ArrayList<String> path = new ArrayList<>();
//          path.add(beginWord);
//          huisu(0,1,path,endWord,wordList,nextList);

          //2.bfs
          bfs(startIdx,endWord,wordList,nextList);
      return res;

    }

    private class Node{
          private int idx;
          private Node last;

          private String word;

        public Node( String word,int idx,Node last) {
            this.last = last;
            this.idx = idx;
            this.word = word;
        }
    }

      /**广度优先搜寻最短路径
       * @param endWord
       * @param wordList
       * @param nextList
       */
    private void bfs(int startIdx,String endWord, List<String> wordList,List<Integer>[] nextList){
        Queue<Node> queue = new LinkedList<>();
        //找到endword在wordlist中的位置,避免后面每次都需要equal判断
        int endIdx = wordList.indexOf(endWord);
        //首先把索引为0的begin开始词放入
        List<Node> ends = new ArrayList<>();
        boolean[] used = new boolean[wordList.size()];
        queue.offer(new Node(wordList.get(startIdx),startIdx,null));
        used[startIdx] =true;
        long l1 = System.currentTimeMillis();
        boolean findend = false;
        while (!queue.isEmpty()){
            //只要找到了endword,就可以在这一轮高度结束
            /**优化点: 这里遍历这一高度的节点的单词保存, 然后添加下一高度的节点到队尾的时候,不把这个高度出现过的节点放进去,因为那样肯定长度更长.举例子:
             * 比如这个高度有 1,3,4. 然后1的接下来的连线是:3,5,6 这里就可以把3排除. 因为...->1->3... 肯定不如...->3->... **/
            int thisHighSize = queue.size();
            List<Node> thisHighNods = new ArrayList<>();
            for (int i = 0; i < thisHighSize; i++) {
                //一轮size表示同一个高度,在这个高度节点的长度路径是一样长的
                Node node = queue.poll();
                thisHighNods.add(node);
                //记录用过的
                used[node.idx] = true;
                if (node.idx == endIdx){
                    //找到结束词了
                    ends.add(node);
                    findend = true;
                }
            }
            if (findend){
                break;
            }
            //把下一高度的放入队列
            for (Node node : thisHighNods) {
                List<Integer> nextidxs = nextList[node.idx];
                if (nextidxs == null || nextidxs.isEmpty()){
                    continue;
                }
                for (Integer nextidx : nextidxs) {
                    //如果下个节点的单词,之前没遇到过,且不是本轮同高度的单词
                    if (!used[nextidx] ){
                        queue.offer(new Node(wordList.get(nextidx),nextidx,node));
                    }
                }
            }


        }
        System.out.println(System.currentTimeMillis() - l1);

        long l2 = System.currentTimeMillis();
        for (Node end : ends) {
            List<String> list = new ArrayList<>();
            do{
                list.add(end.word);
                end = end.last;
            }while (end != null);
            Collections.reverse(list);
            if (minDistance > list.size()){
                res.clear();
                res.add(list);
                minDistance = list.size();
            }else if (minDistance == list.size()){
                res.add(list);
            }
        }
        System.out.println(System.currentTimeMillis() - l2);
    }

      /**
       * 回溯法 超时了
       * @param begin
       * @param path
       * @param pathStr
       * @param endWord
       * @param wordList
       * @param nextList
       */
    private void huisu( int begin,int path,List<String> pathStr,String endWord, List<String> wordList,List<Integer>[] nextList){
        //如果当前开始的就是结束词,表示已经找到了一条路,结束
        if (wordList.get(begin).equals(endWord)){
            if (pathStr.size() <= minDistance) {
                if (pathStr.size() < minDistance) { //0,1,2,3,6
                    //如果小的话,清空原来的结果
                    res.clear();
                }
                minDistance = pathStr.size();
                //这里一定要用新的list,否则会在外层被remove调
                res.add(new ArrayList<>(pathStr));
            }
            return;
        }

        //找到它的下个节点
        List<Integer> next = nextList[begin];
        if (next== null ||next.isEmpty()){
            //如果从begin这个点没有下一个连接点了,结束
            return;
        }
        for (Integer np : next) {
            if (1 << np  ==  (1 << np & path)){
                //说明已经走过,跳过
                continue;
            }
            //记录path
            path |= (1 << np);

            String nextword = wordList.get(np);
            pathStr.add(nextword);
            huisu(np,path,pathStr,endWord,wordList,nextList);
            //回溯
            path &= (~(1 << np));

            pathStr.remove(pathStr.size()-1);
        }

    }

      private List<Integer>[] getNextList(List<String> wordList) {
          List<Integer>[] nextlist = new List[wordList.size()];
          for (int i = 0; i < wordList.size(); i++) {
              String w = wordList.get(i);
              for (int j = 0; j < wordList.size(); j++) {
                  String w2 = wordList.get(j);
                  char[] chars = w.toCharArray();
                  char[] chars2 = w2.toCharArray();
                  int diff=0;
                  for (int k = 0; k < chars.length; k++) {
                      if (chars[k] != chars2[k]){
                          diff++;
                      }
                  }
                  if (diff == 1){
                      List<Integer>  next= nextlist[i];
                      if (next == null){
                          next = new ArrayList<>();
                      }
                      //如果相差只有1,则他就是可以作为下一个连接点
                      next.add(j);
                      nextlist[i] = next;
                  }
              }
          }
          return nextlist;
      }
  }
//leetcode submit region end(Prohibit modification and deletion)


    class Solution2 {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            HashMap<String,Integer>word_index=new HashMap<>();
            for (int i = 0; i <wordList.size() ; i++) {
                word_index.put(wordList.get(i),i);
            }
            List<String>newWordList=new ArrayList<>(wordList);
            if(!word_index.containsKey(beginWord)){
                word_index.put(beginWord,wordList.size());
                newWordList.add(beginWord);
            }
            List<List<Integer>>graph=new LinkedList<>();
            for (int i = 0; i <newWordList.size() ; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i <newWordList.size() ; i++) {
                for (int j = 0; j < newWordList.size(); j++) {
                    if(i!=j&&onlyOneDiff(newWordList.get(i),newWordList.get(j))){
                        graph.get(i).add(j);
                    }
                }
            }
            List<List<String>>paths=new ArrayList<>();
            List<Node>ends=new ArrayList<>();
            //bfs找到最短路径及最短路径的长度
            Queue<Node>queue=new LinkedList<>();
            boolean visited[]=new boolean[newWordList.size()];
            int start=word_index.get(beginWord);
            queue.add(new Node(beginWord,start,null));
            int miniumStep=Integer.MAX_VALUE;
            int step=1;
            while (!queue.isEmpty()){
                int size=queue.size();
                for (int i = 0; i <size ; i++) {
                    Node cur=queue.poll();
                    visited[cur.index]=true;
                    if(cur.word.equals(endWord)){
                        ends.add(cur);
                        miniumStep=Math.min(step,miniumStep);
                    }
                    for(int ele:graph.get(cur.index)){
                        if(!visited[ele]&&onlyOneDiff(cur.word,newWordList.get(ele))){
                            queue.add(new Node(newWordList.get(ele),ele,cur));
                        }
                    }
                }
                step++;
            }
            for(Node node:ends){
                List<String>tmp=new ArrayList<>();
                while (node.prev!=null){
                    tmp.add(0,node.word);
                    node=node.prev;
                }
                tmp.add(0,beginWord);
                if(tmp.size()==miniumStep){
                    paths.add(tmp);
                }
            }
            return paths;
        }
        private boolean onlyOneDiff(String word1,String word2){
            int count=0;
            for (int i = 0; i <word1.length() ; i++) {
                if(word1.charAt(i)!=word2.charAt(i)){
                    count++;
                }
                if(count>=2){
                    return false;
                }
            }
            return true;
        }

        class Node{
            String word;
            int index;
            Node prev;
            public Node(String word,int index,Node prev){
                this.prev=prev;
                this.index=index;
                this.word=word;
            }
        }
    }


    class Solution3 {

         class Node {
            String word;
            Node prev;
            public Node(String word, Node prev) {
                this.word = word;
                this.prev = prev;
            }
        }

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> dict = new HashSet<String>(wordList);


            List<List<String>> res = new ArrayList<>();
            if (!dict.contains(endWord)) return res;

            Deque<Node> queue = new LinkedList<>(); // BFS辅助队列
            List<Node> ends = new ArrayList<>(); // 存储所有终点
            Set<String> visited = new HashSet<>(); // 防止走回头路
            queue.add(new Node(beginWord, null));

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node cur = queue.removeFirst();
                    String w = cur.word;
                    visited.add(w);
                    if (w.equals(endWord)) {
                        ends.add(cur);
                    }
                    for (String s : wordList) {
                        if (!visited.contains(s) && checkSimilar(w, s)) {
                            queue.addLast(new Node(s, cur));
                        }
                    }
                }
                if (ends.size() > 0) break;
            }

            for (Node node : ends) {
                List<String> temp = new ArrayList<>();
                while (node.prev != null) {
                    temp.add(0, node.word);
                    node = node.prev;
                }
                temp.add(0, beginWord);
                res.add(temp);
            }
            return res;
        }

        private boolean checkSimilar(String s1, String s2) {
            if (s1.length() != s2.length()) return false;
            int diff = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == s2.charAt(i)) continue;
                diff++;
            }
            return diff == 1;
        }
    }
}