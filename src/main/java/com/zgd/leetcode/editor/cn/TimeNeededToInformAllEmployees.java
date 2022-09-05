//公司里有 n 名员工，每个员工的 ID 都是独一无二的，编号从 0 到 n - 1。公司的总负责人通过 headID 进行标识。 
//
// 在 manager 数组中，每个员工都有一个直属负责人，其中 manager[i] 是第 i 名员工的直属负责人。对于总负责人，manager[
//headID] = -1。题目保证从属关系可以用树结构显示。 
//
// 公司总负责人想要向公司所有员工通告一条紧急消息。他将会首先通知他的直属下属们，然后由这些下属通知他们的下属，直到所有的员工都得知这条紧急消息。 
//
// 第 i 名员工需要 informTime[i] 分钟来通知它的所有直属下属（也就是说在 informTime[i] 分钟后，他的所有直属下属都可以开始传播
//这一消息）。 
//
// 返回通知所有员工这一紧急消息所需要的 分钟数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 1, headID = 0, manager = [-1], informTime = [0]
//输出：0
//解释：公司总负责人是该公司的唯一一名员工。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
//输出：1
//解释：id = 2 的员工是公司的总负责人，也是其他所有员工的直属负责人，他需要 1 分钟来通知所有员工。
//上图显示了公司员工的树结构。
// 
//
//
// 提示： 
//
// 
// 1 <= n <= 10^5 
// 0 <= headID < n 
// manager.length == n 
// 0 <= manager[i] < n 
// manager[headID] == -1 
// informTime.length == n 
// 0 <= informTime[i] <= 1000 
// 如果员工 i 没有下属，informTime[i] == 0 。 
// 题目 保证 所有员工都可以收到通知。 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 👍 104 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeNeededToInformAllEmployees{

  /**
  * 1376
  * 通知所有员工所需的时间
  * 
  * 
  *
  * 2022-09-05 18:04:09
  */  
  public static void main(String[] args) {
    Solution solution = new TimeNeededToInformAllEmployees().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // 思路,先将manage数组转为map,key是上司,value是下属集合. 再从根节点开始,深度优先遍历.累计每个节点的耗时,取不同分支的最大值
        // [2,0,-1,2,0,4]
        // -1 -- 2
        // 2 -- 0,3
        //0 -- 1,4
        //4 -- 5

        //1. 转为map
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            List<Integer> list = tree.getOrDefault(manager[i], new ArrayList<>());
            list.add(i);
            tree.put(manager[i],list);
        }
        //2. 深度遍历
        dfs(tree,informTime,headID,0);
        return maxTime;

    }

    private int maxTime = 0;

    public void dfs(Map<Integer, List<Integer>> tree,int[] informTime,int i,int time){
        List<Integer> list = tree.get(i);
        if (list == null){
            if (time > maxTime){
                maxTime = time;
            }
            return ;
        }
        //本轮耗时
        time += informTime[i];
        for (Integer n : list) {
            dfs(tree,informTime,n,time);

        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}