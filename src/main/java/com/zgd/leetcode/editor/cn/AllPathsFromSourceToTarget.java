//给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序） 
//
// 
// graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。 
//
// 
//
// 示例 1： 
//
//
//
// 
//输入：graph = [[1,2],[3],[3],[]]
//输出：[[0,1,3],[0,2,3]]
//解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
// 
//
// 示例 2： 
//
// 
//
// 
//输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
//输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 2 <= n <= 15 
// 0 <= graph[i][j] < n 
// graph[i][j] != i（即不存在自环） 
// graph[i] 中的所有元素 互不相同 
// 保证输入为 有向无环图（DAG） 
// 
//
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 回溯 👍 407 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget{

  /**
  * 797
  * 所有可能的路径
  * 
  * 
  *
  * 2023-06-12 15:33:53
  */  
  public static void main(String[] args) {
    Solution solution = new AllPathsFromSourceToTarget().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        //初始化起点
        path.add(0);
        huisu(res,path,-1,0,graph);
        return res;
    }


    private void huisu(List<List<Integer>> res, List<Integer> path,int from,int curr,int[][] graph ){
        if(graph.length - 1 == curr){
            //这里要用一个new对象接收,因为后面这个list会被回溯remove
            res.add(new ArrayList<>(path));
            return ;
        }
        for (int i = 0; i < graph[curr].length; i++) {
            //target
            int target = graph[curr][i];
            if(target == from){
                //不走回头路
                continue;
            }
            path.add(target);
            huisu(res,path,curr,target,graph);
            //回溯
            path.remove(path.size()-1);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}