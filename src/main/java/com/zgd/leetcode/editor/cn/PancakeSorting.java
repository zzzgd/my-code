//给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。 
//
// 一次煎饼翻转的执行过程如下： 
//
// 
// 选择一个整数 k ，1 <= k <= arr.length 
// 反转子数组 arr[0...k-1]（下标从 0 开始） 
// 
//
// 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。 
//
// 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断
//为正确。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,4,1]
//输出：[4,2,4,3]
//解释：
//我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
//初始状态 arr = [3, 2, 4, 1]
//第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
//第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
//第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
//第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。 
// 
//
// 示例 2： 
//
// 
//输入：[1,2,3]
//输出：[]
//解释：
//输入已经排序，因此不需要翻转任何内容。
//请注意，其他可能的答案，如 [3，3] ，也将被判断为正确。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 100 
// 1 <= arr[i] <= arr.length 
// arr 中的所有整数互不相同（即，arr 是从 1 到 arr.length 整数的一个排列） 
// 
// Related Topics 贪心 数组 双指针 排序 👍 267 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class PancakeSorting{

  /**
  * 969
  * 煎饼排序
  * 
  * 思路:  煎饼翻转每次都只是影响0~i之间的数, 可以考虑从大到小,每次通过翻转2次将其放到最后. 果然是脑筋急转弯
  *
  * 2022-07-07 16:00:07
  */  
  public static void main(String[] args) {
    Solution solution = new PancakeSorting().new Solution();
    solution.pancakeSort(new int[]{3,2,4,1});
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //思路:  煎饼翻转每次都只是影响0~i之间的数, 可以考虑从大到小,每次通过翻转2次将其放到最后
    //3,5,1,4,2  5,3,1,4,2  4,1,3,5,2  3,1,4,5,2  1,3,4,5,2   2,5,4,3,1  4,5,2,3,1  5,4,2,3,1  3,2,4,5,1  2,3,4,5,1
    public List<Integer> pancakeSort(int[] arr) {
      //2,5,3,1,4 -> 3,5,2,1,4 -> 5,3,2,1,4 -> 4,1,2,3,5 -> 2,1,4,3,5 1,2,4,3,5   3,4,2,1,5 4,3,2,1,5
      List<Integer> res = new ArrayList<>();
      if(arr.length < 2){
        return res;
      }
      int maxI = 0;
      //只需要确定 length-1 个最大值
      for (int i = 0; i < arr.length-1; i++) {
        //遍历找到最大值
        int lastI = arr.length - i -1;
        for (int j = 0; j <= lastI; j++) {
          if (arr[maxI] < arr[j]){
            //最大值
            maxI = j;
          }
        }
        //将最大值通过两次煎饼翻转换到最后
        reverse(arr,maxI); //将其转到第一位
        res.add(maxI+1);
        reverse(arr,lastI); //将第一位转到最后
        res.add(lastI+1);
        //maxi重置
        maxI = 0;
      }

    return res;
    }

    private void reverse(int[] arr,int k){
      for (int i = 0; i <= k / 2 && k > 0; i++) {
        int t = arr[i];
        arr[i] = arr[k-i];
        arr[k-i] = t;
      }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}