//车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向） 
//
// 给定整数 capacity 和一个数组 trips , trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有
// numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。 
//
// 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。 
//
// 
//
// 示例 1： 
// 02222200
// 
//输入：trips = [[2,1,5],[3,3,7]], capacity = 4
//输出：false
// 
//
// 示例 2： 
//
// 0227770
//输入：trips = [[2,1,5],[3,3,7]], capacity = 5
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= trips.length <= 1000 
// trips[i].length == 3 
// 1 <= numPassengersi <= 100 
// 0 <= fromi < toi <= 1000 
// 1 <= capacity <= 10⁵ 
// 
//
// Related Topics 数组 前缀和 排序 模拟 堆（优先队列） 👍 265 👎 0


  
package com.zgd.leetcode.editor.cn;

public class CarPooling{

  /**
  * 1094
  * 拼车
  * 
  * 
  *
  * 2023-07-28 16:48:15
  */  
  public static void main(String[] args) {
    Solution solution = new CarPooling().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        //直接将上车下车的点看成一个个坐标, 坐标的值是这个时候几个人, 因为0 <= fromi < toi <= 1000 ,所以设置1000
        int[] bucket = new int[1000];
        for (int[] trip : trips) {
            for (int i = trip[1]; i < trip[2] ; i++) {
                bucket[i]+=trip[0];
                if(bucket[i] > capacity){
                    return false;
                }
            }
        }
        return true;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}