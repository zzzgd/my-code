////给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
////
//// 两个相邻元素间的距离为 1 。
////
////
////
//// 示例 1：
////
////
////
////
////输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
////输出：[[0,0,0],[0,1,0],[0,0,0]]
////
////
//// 示例 2：
////
////
////
////
////输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
////输出：[[0,0,0],[0,1,0],[1,2,1]]
////
////
////
////
//// 提示：
////
////
//// m == mat.length
//// n == mat[i].length
//// 1 <= m, n <= 10⁴
//// 1 <= m * n <= 10⁴
//// mat[i][j] is either 0 or 1.
//// mat 中至少有一个 0
////
////
////
////
//// 注意：本题与主站 542 题相同：https://leetcode-cn.com/problems/01-matrix/
//// Related Topics 广度优先搜索 数组 动态规划 矩阵 👍 22 👎 0
//


package com.zgd.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Queue;

public class TwoBCMpM {

  /**
   * 剑指 Offer II 107
   * 矩阵中的距离
   */
  public static void main(String[] args) {
    Solution solution = new TwoBCMpM().new Solution();
//    solution.updateMatrix(new int[][]{{0,0,0},{0,1,0},{0,0,0}});
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[][] updateMatrix(int[][] mat) {
      //1 采用广度优先的解法. 从0开始往外围一圈圈计算距离
      int[][] wasd = {{-1,0},{1,0},{0,1},{0,-1}};
      int[][] result = new int[mat.length][mat[0].length];
      boolean[][] boo = new boolean[mat.length][mat[0].length];
      //先遍历矩阵,然后把0的筛出来, 将其位置放到队列中
      Queue<int[]> queue = new ArrayDeque<>();
      for (int i = 0; i < mat.length; i++) {
        for (int j = 0; j < mat[i].length; j++) {
          if (mat[i][j]==0){
            boo[i][j] = true;
            //放入队列中,表示从这里开始计算距离
            queue.offer(new int[]{i,j});
          }
        }
      }


      //从队列中取出坐标,计算四周的距离
      while (queue.size() > 0){
        int[] zuobiao = queue.poll();
        //得到四周的距离 = 自身+1
        for (int i = 0; i < 4; i++) {
          //4个方向
          int x = wasd[i][0] + zuobiao[0];
          int y = wasd[i][1] + zuobiao[1];
          //如果目标位置没有处理过
          if ( x >=0 && y >=0 && x < mat.length && y < mat[0].length && !boo[x][y]){
            boo[x][y] = true;
            //新位置=当前位置+1
            result[x][y] = result[zuobiao[0]][zuobiao[1]] + 1;
            //放到队列等候处理
            queue.offer(new int[]{x,y});
          }
        }
      }
      return result;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
