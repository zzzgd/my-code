//给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// [
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
//
// Related Topics 数组 二分查找 分治 矩阵 👍 58 👎 0

/*
 1 2 3 4 5
 6 7 8 9 10

 1 4 7
 2 5 8
 3 6 9
 */

  
package com.zgd.leetcode.editor.cn;

public class SortedMatrixSearchLcci{

  /**
  * 面试题 10.09
  * 排序矩阵查找
  * 
  * 
  *
  * 2023-06-19 18:35:17
  */  
  public static void main(String[] args) {
    Solution solution = new SortedMatrixSearchLcci().new Solution();
//    solution.searchMatrix(new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}},19);
    solution.searchMatrix(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}},5);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //两次使用二分法
        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        //第一步,确定在哪一行
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] <= target && matrix[i][matrix[0].length-1] >= target){
                int l = 0, r = matrix[0].length-1;
                while(l < r){
                    int mid = (l+r)/2;
                    if(matrix[i][mid] == target){
                        return true;
                    }else if(matrix[i][mid] > target){
                        r = mid-1;
                    }else{
                        l = mid+1;
                    }
                }
                if(matrix[i][l] == target){
                    return true;
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}