//节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。 
//
// 示例1:  *     *        *
//    0,1 0,2   1,2 1,2
//
//  输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
// 输出：true
// 
//
// 示例2:   *   *    *    *    *
//  0,1 0,2 0,4 0,4  0,1
//  输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [
//1, 3], [2, 3], [3, 4]], start = 0, target = 4
// 输出 true
// 
//
// 提示： 
//
// 
// 节点数量n在[0, 1e5]范围内。 
// 节点编号大于等于 0 小于 n。 
// 图中可能存在自环和平行边。 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 👍 65 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.*;

public class RouteBetweenNodesLcci{

  /**
  * 面试题 04.01
  * 节点间通路
  * 
  * 
  *
  * 2022-08-30 17:45:39
  */  
  public static void main(String[] args) {
    Solution solution = new RouteBetweenNodesLcci().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        //按起始位置分成一个map,每个起始位置对应一个set,表示出去的箭头目标位置
        Map<Integer, Set<Integer>> map = new HashMap<>(n);
        for (int[] ints : graph) {
            Set<Integer> set = map.get(ints[0]);
            if (set == null){
                set = new HashSet<>();
            }
            set.add(ints[1]);
            map.put(ints[0],set);
        }
        //取出start
        Set<Integer> ints = map.get(start);
        if (ints == null ){
            return false;
        }
        //深度遍历,起始位置不变,判断target. 为了避免环形死循环,需要记录走过的路径
        List<Integer> path = new ArrayList<>();
        return dfs(ints,target, path,map);

    }

    private boolean dfs(Set<Integer> arr,int target,List<Integer> path,Map<Integer,Set<Integer>> map){
        if (arr == null){
            return false;
        }
        for (int i : arr) {
            if (path.contains(i)){
                //闭环了
                continue;
            }
            if (i == target){
                return true;
            }
            path.add(i);
            if (dfs(map.get(i),target,path,map)){
                return true;
            }
            //不符合条件,回退一步,继续遍历
            path.remove((Object)i);
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}