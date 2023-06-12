//给你一个偶数 n ，已知存在一个长度为 n 的排列 perm ，其中 perm[i] == i（下标 从 0 开始 计数）。 
//
// 一步操作中，你将创建一个新数组 arr ，对于每个 i ： 
//
// 
// 如果 i % 2 == 0 ，那么 arr[i] = perm[i / 2] 
// 如果 i % 2 == 1 ，那么 arr[i] = perm[n / 2 + (i - 1) / 2] 
// 
//
// 然后将 arr 赋值给 perm 。 
//
// 要想使 perm 回到排列初始值，至少需要执行多少步操作？返回最小的 非零 操作步数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：1
//解释：最初，perm = [0,1]
//第 1 步操作后，perm = [0,1]
//所以，仅需执行 1 步操作 
//
// 示例 2： 
//
// 
//输入：n = 4
//输出：2
//解释：最初，perm = [0,1,2,3]
//第 1 步操作后，perm = [0,2,1,3]
//第 2 步操作后，perm = [0,1,2,3]
//所以，仅需执行 2 步操作 
//
// 示例 3： 
//
// 
//输入：n = 6  [0,1,2,3,4,5]  ->  [0,3,1,4,2,5]  -> [0,4,3,2,1,5] -> [0,2,4,1,3,5] -> [0,1,2,3,4,5]
//输出：4
// 
// n =8 [0,1,2,3,4,5,6,7] -> [0,4,1,5,2,6,3,7] -> [0,2,4,6,1,3,5,7] -> [0,1,2,3,4,5,6,7]
// 
//
// 提示： 
//
// 
// 2 <= n <= 1000 
// n 是一个偶数 
// 
//
// Related Topics 数组 数学 模拟 👍 102 👎 0


  
package com.zgd.leetcode.editor.cn;

public class MinimumNumberOfOperationsToReinitializeAPermutation{

  /**
  * 1806
  * 还原排列的最少操作步数
  * 
  * 还真是我这样解的,其实不用关注每一个元素,找出其中一个比如1,然后倒推,看多少次变成1就行.
  *
  * 2023-06-12 14:11:27
  */  
  public static void main(String[] args) {
    Solution solution = new MinimumNumberOfOperationsToReinitializeAPermutation().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reinitializePermutation(int n) {
        //根据上面的推算,0和最后一个位置是一直不变的, 要实现1重新回到1的位置, 按照公式, 1是上一位必定是 n / 2 + (i - 1) / 2 即 n/2+1的位置, 再根据这个位置往前推
        int i = 1;
        int num = 0;
        while(num == 0 || i != 1){
            if(i % 2 == 0){
                i = i/2;
            }else{
                i = n / 2 + (i - 1) / 2;
            }
            num++;
        }
        return num;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}