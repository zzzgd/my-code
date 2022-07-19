//用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。 
//
// 箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。 
//
// 
// 将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。 
// 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。 
// 
//
// 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就
//会卡住。 
//
// 返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 
//-1 。 
//
// 
//
// 示例 1： 
//  |  \  \  \  /  /
//  |  \  \  \  /  /
//  |  /  /  /  \  \
//  |  \  \  \  \  /
//  |  /  /  /  /  /
//输入：grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-
//1,-1]]
//输出：[1,-1,-1,-1,-1]
//解释：示例如图：
//b0 球开始放在第 0 列上，最终从箱子底部第 1 列掉出。
//b1 球开始放在第 1 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
//b2 球开始放在第 2 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
//b3 球开始放在第 3 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
//b4 球开始放在第 4 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
// 
//
// 示例 2： 
// | /
//
//输入：grid = [[-1]]
//输出：[-1]
//解释：球被卡在箱子左侧边上。
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]
//]
//输出：[0,1,2,3,4,-1]
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 100 
// grid[i][j] 为 1 或 -1 
// 
// Related Topics 深度优先搜索 数组 动态规划 矩阵 模拟 👍 145 👎 0


  
package com.zgd.leetcode.editor.cn;

public class WhereWillTheBallFall{

  /**
  * 1706
  * 球会落何处
  * 
  * 
  *
  * 2022-07-19 14:35:33
  */  
  public static void main(String[] args) {
    Solution solution = new WhereWillTheBallFall().new Solution();
    solution.findBall(new int[][]{{1,1,1,-1,-1},{1,1,1,-1,-1},{-1,-1,-1,1,1},{1,1,1,1,-1},{-1,-1,-1,-1,-1}});
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] findBall(int[][] grid) {
      //尝试动态规划
      int cl = grid[0].length;
      //1. 定义数组,表示小球在各行的状态
      /**
       * ******************** 这里注意,为什么是grid.length+1,因为第0行的位置是确定的,第1行则跟根据第0行的隔板来计算位置,
       * 因此球出来的位置, 是第length行的位置, 我们需要第length-1行的隔板
       */
      int[][] res = new int[grid.length+1][cl];
      //2. 初始化
      for (int i = 0; i < cl; i++) {
        //一开始都在挡板上方所以是正数
        res[0][i] = i;
      }

      /**
       * 注意这里是 <= grid.length,  因为球出来的位置, 其实是第length行, 而不是length-1行. 比如有5行, 我们应该计算5次而不是4次
       */
      for (int i = 1; i <= grid.length; i++) {
        for (int j = 0; j < cl; j++) {
          res[i][j] = nextCol(grid,i-1,res[i-1][j] );
        }
      }
//      int[] rr = res[grid.length-1];
//      for (int i = 0; i < rr.length; i++) {
//        rr[i]--;
//      }
      return res[grid.length];
    }

    private int nextCol(int[][]grid,int row,int col){
      // 用正负数表示球是在挡板上方还是下方,0表示卡住
      if (col == -1){
        return -1;
      }
      //判断是否卡住
      //1. \ 型, 正数, 右边是边界或是 /卡住, 其他情况 列数+1
      //2. /型, 正数,  左边是边界或是  \卡住, 其他情况 列数-1

      int[] rr = grid[row];
//      int mcol = Math.abs(col);
      // "\"型
      if (rr[col] == 1){
        if ( (col+1  == rr.length || rr[col+1] == -1)){
          return -1;
        }
        return   (col+1) ;
      }else{
        // "/" 型
        if ( (col  == 0 || rr[col-1] == 1)){
          return -1;
        }
        return  col-1;
      }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}