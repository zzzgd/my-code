//给你一个 严格递增 的整数数组 rungs ，用于表示梯子上每一台阶的 高度 。当前你正站在高度为 0 的地板上，并打算爬到最后一个台阶。 
//
// 另给你一个整数 dist 。每次移动中，你可以到达下一个距离你当前位置（地板或台阶）不超过 dist 高度的台阶。当然，你也可以在任何正 整数 高度处插入
//尚不存在的新台阶。 
//
// 返回爬到最后一阶时必须添加到梯子上的 最少 台阶数。 
//
// 
//
// 示例 1： 
//
// 
//输入：rungs = [1,3,5,10], dist = 2
//输出：2
//解释：
//现在无法到达最后一阶。
//在高度为 7 和 8 的位置增设新的台阶，以爬上梯子。 
//梯子在高度为 [1,3,5,7,8,10] 的位置上有台阶。
// 
//
// 示例 2： 
//
// 
//输入：rungs = [3,6,8,10], dist = 3
//输出：0
//解释：
//这个梯子无需增设新台阶也可以爬上去。
// 
//
// 示例 3： 
//
// 
//输入：rungs = [3,4,6,7], dist = 2
//输出：1
//解释：
//现在无法从地板到达梯子的第一阶。 
//在高度为 1 的位置增设新的台阶，以爬上梯子。 
//梯子在高度为 [1,3,4,6,7] 的位置上有台阶。
// 
//
// 示例 4： 
//
// 
//输入：rungs = [5], dist = 10
//输出：0
//解释：这个梯子无需增设新台阶也可以爬上去。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= rungs.length <= 10⁵ 
// 1 <= rungs[i] <= 10⁹ 
// 1 <= dist <= 10⁹ 
// rungs 严格递增 
// 
// Related Topics 贪心 数组 👍 9 👎 0


  
package com.zgd.leetcode.editor.cn;

public class AddMinimumNumberOfRungs{

  /**
  * 1936
  * 新增的最少台阶数
  */  
  public static void main(String[] args) {
    Solution solution = new AddMinimumNumberOfRungs().new Solution();
    solution.addRungs(new int[]{1,3,5,10},2);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int addRungs(int[] rungs, int dist) {
      //需要插入的台阶数
      int newRungs = 0;
      //遍历数组, 第一个高度是0
      //上一次所在的高度
      int lastHigh = 0;
      //当前的台阶
      int curRungIndex = 0;
      //要满足场景的结束,则是当前台阶到了最后一个台阶
      while(curRungIndex < rungs.length){
        //在固定高度内,能达到,直接下一个台阶
        if (rungs[curRungIndex] - lastHigh <= dist){
          lastHigh = rungs[curRungIndex];
          curRungIndex++;
          //下次
        }else{
          //如果台阶迈不上去,增加台阶,台阶数=差值/固定值 (为什么这里要-1,举个例子,离下一个台阶高度是3,固定值也是3,如果不减1那结果就是3/3=1,还需要插入一节台阶,但其实是不需要的
          int i = (rungs[curRungIndex] - lastHigh-1) / dist;
          newRungs += i;
          //其实就是直接插入一个固定高度
          lastHigh = lastHigh + dist * i;
        }
      }
      System.out.println(newRungs);
      return newRungs;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
