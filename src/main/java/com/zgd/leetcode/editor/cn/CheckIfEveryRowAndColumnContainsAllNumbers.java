//对一个大小为 n x n 的矩阵而言，如果其每一行和每一列都包含从 1 到 n 的 全部 整数（含 1 和 n），则认为该矩阵是一个 有效 矩阵。 
//
// 给你一个大小为 n x n 的整数矩阵 matrix ，请你判断矩阵是否为一个有效矩阵：如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：matrix = [[1,2,3],[3,1,2],[2,3,1]]
//输出：true
//解释：在此例中，n = 3 ，每一行和每一列都包含数字 1、2、3 。
//因此，返回 true 。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：matrix = [[1,1,1],[1,2,3],[1,2,3]]
//输出：false
//解释：在此例中，n = 3 ，但第一行和第一列不包含数字 2 和 3 。
//因此，返回 false 。
// 
//
// 
//
// 提示： 
//
// 
// n == matrix.length == matrix[i].length 
// 1 <= n <= 100 
// 1 <= matrix[i][j] <= n 
// 
// Related Topics 数组 哈希表 矩阵 👍 8 👎 0


  
package com.zgd.leetcode.editor.cn;

public class CheckIfEveryRowAndColumnContainsAllNumbers{

  /**
  * 2133
  * 检查是否每一行每一列都包含全部整数
  *
  * 思路,采用一个长度为n的boolean数组,来判断数是否出现过.  这里需要注意的是每一列也需要满足
  *
  * 2022-06-27 17:00:53
  */  
  public static void main(String[] args) {
    Solution solution = new CheckIfEveryRowAndColumnContainsAllNumbers().new Solution();
    int[][]a = new int[][]{{15,7,18,11,19,10,14,16,8,2,3,6,5,1,17,12,9,4,13},{17,15,9,8,11,13,7,6,5,1,3,16,12,19,10,2,4,14,18},{19,14,12,10,8,9,17,16,4,3,13,18,1,5,7,11,2,15,6},{4,2,10,15,19,16,8,9,5,3,1,11,13,14,6,18,12,17,7},{13,19,9,16,5,8,6,12,14,11,18,10,7,2,3,4,15,17,1},{4,7,18,11,17,16,5,12,10,1,15,13,14,6,19,2,3,9,8},{14,5,15,1,18,6,12,7,8,9,3,13,2,10,19,4,11,16,17},{10,3,1,8,14,19,11,18,15,13,9,12,16,17,7,4,5,2,6},{14,13,19,18,7,2,4,8,10,17,12,5,15,1,6,9,11,3,16},{19,8,10,18,16,12,11,17,4,9,7,2,5,13,15,3,6,1,14},{1,10,6,14,7,18,3,9,4,16,5,11,13,17,15,8,19,2,12},{13,10,5,16,1,19,17,3,9,11,7,8,12,6,4,2,14,15,18},{17,2,1,6,9,19,18,14,4,11,12,13,16,5,8,7,3,10,15},{1,4,10,5,13,6,18,11,3,2,15,14,16,12,17,19,8,9,7},{2,14,3,12,16,17,11,9,1,6,5,19,10,13,4,18,7,15,8},{15,9,8,18,14,13,4,12,5,17,6,1,11,16,19,3,7,2,10},{15,8,12,16,13,2,6,19,18,14,10,5,11,9,7,1,3,17,4},{15,6,17,7,5,3,1,9,19,12,10,11,16,14,18,8,2,13,4},{6,11,10,14,2,13,16,1,9,15,8,19,17,3,5,18,7,4,12}};
    solution.checkValid(a);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean checkValid(int[][] matrix) {
      //设置一个长度为n+1的数组来进行判断
      boolean[] arr = new boolean[matrix.length+1];
      //每一行判断
      for (int i = 0; i < matrix.length; i++) {
        int[] row = matrix[i];
        for (int j = 0; j < row.length; j++) {
          arr[row[j]] = true;
        }

        //然后再遍历一次arr,一是判断是否符合要求.而是重新初始化
        if (!checkArr(arr)) {
          return false;
        }
      }

      //每一列判断
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
          int[] row = matrix[j];
          arr[row[i]] = true;
        }

        if (!checkArr(arr)) {
          return false;
        }
      }
      return true;
    }

    private boolean checkArr(boolean[] arr) {
      //然后再遍历一次arr,一是判断是否符合要求.而是重新初始化
      for (int i1 = 1; i1 < arr.length; i1++) {
        if (!arr[i1]){
          return false;
        }
        arr[i1] = false;
      }
      return true;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}