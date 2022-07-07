//有 n 位乘客即将登机，飞机正好有 n 个座位。第一位乘客的票丢了，他随便选了一个座位坐下。 
//
// 剩下的乘客将会： 
//
// 
// 
// 如果他们自己的座位还空着，就坐到自己的座位上， 
// 
// 当他们自己的座位被占用时，随机选择其他座位 
// 
//
// 第 n 位乘客坐在自己的座位上的概率是多少？

//2  1/2 +
//3  1/3 + (3-2)/3*1/2
// 
//
// 示例 1： 
//
// 
//输入：n = 1
//输出：1.00000
//解释：第一个人只会坐在自己的位置上。 
//
// 示例 2： 
//
// 
//输入: n = 2
//输出: 0.50000
//解释：在第一个人选好座位坐下后，第二个人坐在自己的座位上的概率是 0.5。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10^5 
// 
// Related Topics 脑筋急转弯 数学 动态规划 概率与统计 👍 103 👎 0


  
package com.zgd.leetcode.editor.cn;

public class AirplaneSeatAssignmentProbability{

  /**
  * 1227
  * 飞机座位分配概率
  * 
  * 
  *
  * 2022-07-05 18:29:47
  */  
  public static void main(String[] args) {
    Solution solution = new AirplaneSeatAssignmentProbability().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double nthPersonGetsNthSeat(int n) {
      //(1+(n-2)*x(n-1))/n
      //居然解答出来了.. 解答下,首先要n坐到他自己的位子,首先分子的1,表示第一个人没有乱坐,那后面都不会乱坐. 其次是 (n-2),表示其他情况,第一个需要他既不坐到自己的位子,也不坐到
      //n的位子,这样才可能最后n坐自己的位子. 接着是乘以x(n-1), 对于第一个人来说,他选了(n-2)中某个位子,接着第二个人在剩下来(n-1)个位子中同样面临和第一个人的选择,所以是递归
      return jisuan(n);
    }

    private double jisuan(int n){
      if(n == 1){
        return 1;
      }
      return (1 + (n-2)*jisuan(n-1))/n;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}