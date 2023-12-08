//给你一个正整数数组 arr（可能存在重复的元素），请你返回可在 一次交换（交换两数字 arr[i] 和 arr[j] 的位置）后得到的、按字典序排列小于 
//arr 的最大排列。 
//
// 如果无法这么操作，就请返回原数组。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [3,2,1]
//输出：[3,1,2]
//解释：交换 2 和 1
// 
//
// 示例 2： 
//
// 
//输入：arr = [1,1,5]
//输出：[1,1,5]
//解释：已经是最小排列
// 
//
// 示例 3： 
//
// 
//输入：arr = [1,9,4,6,7]
//输出：[1,7,4,6,9]
//解释：交换 9 和 7
// 
//

//
// 提示： 
//
// 
// 1 <= arr.length <= 10⁴ 
// 1 <= arr[i] <= 10⁴ 
// 
//
// Related Topics 贪心 数组 👍 144 👎 0


  
package com.zgd.leetcode.editor.cn;

public class PreviousPermutationWithOneSwap{

  /**
  * 1053
  * 交换一次的先前排列
  * 
  * 
  *
  * 2023-12-08 18:14:14
  */  
  public static void main(String[] args) {
    Solution solution = new PreviousPermutationWithOneSwap().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] prevPermOpt1(int[] arr) {
        //3,1,1,3
        //3,2,1,3
        //3,1,2,3
        // x: 9,6,13,8,9,5    9,6,13,8,5,9
        // 首先分析题目,需要调换一次后比现在排列要小,但是又是所有结果里最大的一个,
        // 首先排列比现在小,那必然是把一个前面大的数换成后面小的数. 又因为需要这个排列尽可能最大,即在小于原排列基础上极可能靠近原排列
        // ,所以这个调换必须从右边开始,因为右边变化对排列影响不大(理解为个位和十位的变化肯定比千位万位小)
        // 从右往左,找到第一个降序的,此时颠倒这个值和后面一个比他大的值就可以得到, 比如3,2,1,3, 发现2是第一个开始降序的,和后面1调换.3,1,2,3

        int left = -1;
        for (int i = arr.length-2; i >=0 ; i--) {
            //这里遍历的下表i,但是判断的是i+1和后面的最大值, 找到从后往前第一个降序的下表left
            if(arr[i] > arr[i+1]){
                left = i;
                break;
            }
        }
        if (left >= 0){
            int rightIdx = -1;
            for (int i = left; i < arr.length ; i++) {
                if (arr[i] < arr[left] && (rightIdx < 0|| arr[i] > arr[rightIdx])){
                    rightIdx = i;
                }
            }
            //找到了右边比left要小,但是最大值的下标
            //两两替换
            int t = arr[left];
            arr[left] = arr[rightIdx];
            arr[rightIdx] = t;
        }
        return arr;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}