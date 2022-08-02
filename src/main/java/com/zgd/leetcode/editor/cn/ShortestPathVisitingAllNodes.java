//存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。 
//
// 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。 
//
// 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。 
//
// 
//
// 
// 
//
// 示例 1： 
//[][]
// 
//输入：graph = [[1,2,3],[0],[0],[0]]
//输出：4
//解释：一种可能的路径为 [1,0,2,0,3] 
//
// 示例 2： 
//
// 
//
// 
//输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
//输出：4
//解释：一种可能的路径为 [0,1,4,2,3]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 1 <= n <= 12 
// 0 <= graph[i].length < n 
// graph[i] 不包含 i 
// 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a 
// 输入的图总是连通图 
// 
// Related Topics 位运算 广度优先搜索 图 动态规划 状态压缩 👍 319 👎 0


  
package com.zgd.leetcode.editor.cn;

import javafx.util.Pair;

import java.util.*;

public class ShortestPathVisitingAllNodes{

  /**
  * 847
  * 访问所有节点的最短路径
  * 
  * 
  *
  * 2022-08-01 14:37:05
  */
  public static void main(String[] args) {
    Solution solution = new ShortestPathVisitingAllNodes().new Solution();
    solution.shortestPathLength(new int[][]{{1},{0,2,4},{1,3},{2},{1,5},{4}});
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int shortestPathLength(int[][] graph) {
        /**
         * 动态规划 和 图搜索, 永远的痛
         * 思路:
         * 这个题是一个bfs宽度优先搜素, 宽度优先bfs,则考虑队列.
         * bfs把一排的节点先放到队列,再从队列取出遍历每个节点,同时把节点的子节点放到队列尾部
         */

        //定义一个队列来bfs
        Queue<Node> queue = new LinkedList<>();
        //在定义一个Set,key是一个二元组,表示接下来经过的v节点得到的path. value表示是否经过. 如果value是true,表示这个n位置出发,到接下来的v得到的新path和原来的一样,已经跑过了
        //举个例子,比如有个循环的线0->1>0->1.
        // 1.那一开始在0的时候, n=0,path=01(二进制).
        // 2.接着连到1, n=1,path=11.
        // 3.从1->0时, n=0,path=11
        // 4.从0->1时, n=1,path=11(重复)
        Set<Pair<Integer,Integer>> haveGo = new HashSet<>();

        //1. bfs 首先把第一排的节点放到队列
        for (int i = 0; i < graph.length; i++) {
            queue.offer(new Node(i,1<<i,0));
            haveGo.add(new Pair<>(i,1<<i));
        }
        //2. bfs 再把队列节点依次去除,再找到它的子节点放到队列  //0011 0110
        int fullpath = (1 << graph.length) - 1;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            //如果二进制位运算,走过的路径和fullpath与运算,仍然等于fullpath,说明每个位置都走过了
            if ((fullpath & node.path) == fullpath){
                return node.dist;
            }
            //遍历该节点的下个目标
            int from = node.from;
            for (int to : graph[from]) {
                //看看path是否已经走过目标i,避免一个圈死循环, 死循环触发的情况,就是path来回把a,b两个位置二进制设为1
                //计算新path
                int path = node.path | (1<<to);
                /**
                 * 注意,这里有个坑, 是new Pair<>(to,path),而不是new Pair<>(from,path),
                 * 举个例子, 下面的 1-4-5连线, 如果用from记录,1-4-5的时候from是4,会往set里存 "4=1-4-5", 然后,从5-4-1的时候, 也会判断存在"4=5-4-1",就不会走到if里
                 * 从而失去正确答案5-4-1-0-1-2-3.
                 * 而用to来判断就不会有这个问题, 1-4-5是"5=1-4-5", 5-4-1是"1=5-4-1"
                 * <p>
                 *       ----------------
                 *      |               |
                 * 0 -- 1 -- 2 -- 3    4 -- 5
                 * </p>
                 *
                 */
                if (!haveGo.contains(new Pair<>(to,path))){
                    haveGo.add(new Pair<>(to,path));
                    queue.offer(new Node(to,path, node.dist+1));
                }else{
                    System.out.println(Integer.toBinaryString(path)+", "+to);
                }
            }

        }
        return 0;

    }

      /**
       * 节点对象
       */
    private class Node{
          /**
           * 来源,开始于哪个位置
           */
        public int from;
        /**
        * 经历过的节点,二进制计算
        */
        public int path;
          /**
           * 走过的距离
           */
        public int dist;

          public Node(int from, int path, int dist) {
              this.from = from;
              this.path = path;
              this.dist = dist;
          }
      }
}
//leetcode submit region end(Prohibit modification and deletion)

}