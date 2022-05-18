//在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格
//是 (0,0) ，右下单元格是 (n - 1, n - 1) 。 
//
// 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。 
//
// 
//
// 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。 
//
// 骑士继续移动，直到它走了 k 步或离开了棋盘。 
//
// 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。 
//
// 
//
// 示例 1： 
//
// 
//输入: n = 3, k = 2, row = 0, column = 0
//输出: 0.0625
//解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
//在每一个位置上，也有两种移动可以让骑士留在棋盘上。
//骑士留在棋盘上的总概率是0.0625。
// 
//
// 示例 2： 
//
// 
//输入: n = 1, k = 0, row = 0, column = 0
//输出: 1.00000
// 
//
// 
//
// 提示: 
//
// 
// 1 <= n <= 25 
// 0 <= k <= 100 
// 0 <= row, column <= n 
// 
// Related Topics 动态规划 👍 288 👎 0


package com.zgd.leetcode.editor.cn;

public class KnightProbabilityInChessboard {

  /**
   * 688
   * 骑士在棋盘上的概率
   */
  public static void main(String[] args) {
    Solution solution = new KnightProbabilityInChessboard().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    //8个方向
    final int[][] wasd = {{1, 2}, {2, 1}, {-1, 2}, {2, -1}, {1, -2}, {-2, 1}, {-1, -2}, {-2, -1}};

    /**
     * 记忆搜索法
     * 这个方法是在 暴力-递归 方法上优化而来, 一层层递归每移动一步的概率, 如果在棋盘内则这一步的概率是1/8, 将8步概率之和加起来就是这一次的概率.
     * 然后除以8就是它的上一步, 移动到该坐标(x,y)有效在范围内的概率, 以此内推
     * 但是越到后面递归效率低, 是因为棋盘大了后, 计算概率会出现重复的情况.
     * 比如第k次移动,此时骑士可能出现在其中n个(n<=8)位置, 那k+1次移动的时候, 可能这n个位置的各自下一次移动就可能重复
     * 于是我们可以通过 缓存记忆 在k次,(x,y)位置上的概率, 减少重复工作
     */
    public double knightProbability2(int n, int k, int row, int column) {
      //因为最后k=0的时候不需要缓存.这里注意是k+1,因为后面有取[k]
      double[][][] cache = new double[k + 1][n][n];
      return dfs(n, k, row, column, cache);
    }


    public double dfs(int n, int k, int row, int column, double[][][] cache) {
      //递归跳出条件
      if (n <= 0) {
        return 0.0;
      }

      if (row >= 0 && row < n && column >= 0 && column < n) {
        if (k == 0) {
          return 1.0;
        }
      }
      //出界了
      if (row < 0 || row >= n || column < 0 || column >= n) {
        return 0.0;
      }
      //查缓存
      if (cache[k][row][column] != 0) {
        return cache[k][row][column];
      }
      //递归
      double per = 0;
      for (int[] arr : wasd) {
        //把8个方向的概率汇总,每个方向如果不出去的话是1/8
        per += dfs(n, k - 1, row + arr[0], column + arr[1], cache) / 8.0;
      }
      //缓存概率
      cache[k][row][column] = per;
      return per;
    }


    /**
     * 动态规划法
     * 动态规划, 规则就是数组保存每一步的数据, 根据上次的数据推导下次的数据
     * <p>
     * 和记忆搜索法不同的是,这次是倒推的, 记忆搜索法是知道起点(x,y), 推导从起点走k次还在棋盘的概率.动态规划是将起点看为终点,
     * 倒着走, 看走k步倒着走能到达起点(终点)的数量 / 8
     * <p>
     * 从k=0开始,0步的概率肯定是1, 然后依次推导k=1, k=2. 要这么做那肯定要全部把棋盘遍历一次
     * 定义一个数组[k][x][y], 表示的是 走了k步, 到达x,y的概率
     */
    public double knightProbability(int n, int k, int row, int column) {
      double[][][] map = new double[k + 1][n][n];
      for (int kk = 0; kk <= k; kk++) {
        for (int x = 0; x < n; x++) {
          for (int y = 0; y < n; y++) {
            if (kk == 0) {
              map[kk][x][y] = 1;
            } else {
              //移动k次到达(x,y)坐标的概率 = 移动了k-1次到达了它的8个方向的坐标,能到达的概率/8 之和
              for (int[] arr : wasd) {
                //上一个坐标可能位置
                int oldx = x - arr[0];
                int oldy = y - arr[1];
                //如果合法
                if (oldx >= 0 && oldx < n && oldy >= 0 && oldy < n) {
                  map[kk][x][y] += map[kk - 1][oldx][oldy] / 8;
                }
              }
            }
          }

        }
      }
      return map[k][row][column];
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
