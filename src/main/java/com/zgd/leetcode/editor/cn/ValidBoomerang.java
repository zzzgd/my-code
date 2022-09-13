//给定一个数组
// points ，其中
// points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。 
//
// 回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。 
//
// 
//
// 示例 1： 
//
// 
//输入：points = [[1,1],[2,3],[3,2]]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：points = [[1,1],[2,2],[3,3]]
//输出：false 
//
// 
//
// 提示： 
// 
//
// 
// points.length == 3 
// points[i].length == 2 
// 0 <= xi, yi <= 100 
// 
//
// Related Topics 几何 数组 数学 👍 96 👎 0


  
package com.zgd.leetcode.editor.cn;

public class ValidBoomerang{

  /**
  * 1037
  * 有效的回旋镖
  * 
  * 
  *
  * 2022-09-13 16:34:49
  */  
  public static void main(String[] args) {
    Solution solution = new ValidBoomerang().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isBoomerang(int[][] points) {
        //这个就是判断两点斜率是否一致,是则表示三点一线.就不是回旋镖 . a/b=c/d这种写法可能会有除0异常.换成ad=bc
        return ((points[0][0] - points[1][0]) * ((points[1][1] - points[2][1])) )!=
               ((points[1][0] - points[2][0]) * ((points[0][1] - points[1][1])) );
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}