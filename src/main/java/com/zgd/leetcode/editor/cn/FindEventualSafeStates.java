//有一个有 n 个节点的有向图，节点按 0 到 n - 1 编号。图由一个 索引从 0 开始 的 2D 整数数组 graph表示， graph[i]是与节点 
//i 相邻的节点的整数数组，这意味着从节点 i 到 graph[i]中的每个节点都有一条边。 
//
// 如果一个节点没有连出的有向边，则该节点是 终端节点 。如果从该节点开始的所有可能路径都通向 终端节点 ，则该节点为 安全节点 。 
//
// 返回一个由图中所有 安全节点 组成的数组作为答案。答案数组中的元素应当按 升序 排列。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//输出：[2,4,5,6]
//解释：示意图如上。
//节点 5 和节点 6 是终端节点，因为它们都没有出边。
//从节点 2、4、5 和 6 开始的所有路径都指向节点 5 或 6 。
// 
//
// 示例 2： 
//
// 
//输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//输出：[4]
//解释:
//只有节点 4 是终端节点，从节点 4 开始的所有路径都通向节点 4 。
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 1 <= n <= 10⁴ 
// 0 <= graph[i].length <= n 
// 0 <= graph[i][j] <= n - 1 
// graph[i] 按严格递增顺序排列。 
// 图中可能包含自环。 
// 图中边的数目在范围 [1, 4 * 10⁴] 内。 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 424 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindEventualSafeStates{

  /**
  * 802
  * 找到最终的安全状态
  * 
  * 
  *
  * 2023-11-10 16:05:55
  */  
  public static void main(String[] args) {
    Solution solution = new FindEventualSafeStates().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        //用一个数组记录每个位置是不是安全点.如果他不是,则经过这个路径的所有开端都不是
        Boolean[] isSave = new Boolean[graph.length];
        //再用一个数组记录路径,是不是走过
        boolean[] path = new boolean[graph.length];
        for (int i = 0; i < isSave.length; i++) {
            boolean b = isSave(graph, isSave, i, path);
            isSave[i] = b;
        }
        List<Integer> list = new ArrayList<>();
        int i = 0;
        for (Boolean b : isSave) {
            if(b){
                list.add(i);
            }
            i++;
        }
        return list;

    }

      /**
       * 深度优先进行遍历
       * @param graph 图
       * @param issave 数组,表示这个位置是否为安全点.
       * @param to 目标位置
       * @param path 路径,走过的标记为true
       * @return
       */
    private boolean isSave(int[][]graph,Boolean[] issave, int to,boolean[] path){
        if (path[to] || (issave[to] != null && !issave[to])){
            //如果目标位置,已经是之前的路径,说明开始循环,不是安全点. 或者已经知道目标点不是安全点了,直接false
            return false;
        }
        if (graph[to].length == 0 || (issave[to] != null && issave[to])){
            //如果目标位置是结果终端的,标记为true, 或者目标位置本身就是安全点,说明这条路是可以的,返回true
            return true;
        }

        //记录路径
        for (int i = 0; i < graph[to].length; i++) {
            //找到目标点的下一波位置
            path[to] = true;
            boolean b = isSave(graph,issave,graph[to][i],path);
            //得到这点是否为安全点,记录下来
            issave[to] = b;
            //移除路径
            path[to] = false;
            if (!b){
                //如果这条路走不通,这个节点不是安全点
                issave[to] = false;
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}