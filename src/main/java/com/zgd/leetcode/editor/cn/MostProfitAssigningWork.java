//你有 n 个工作和 m 个工人。给定三个数组： difficulty, profit 和 worker ，其中: 
//
// 
// difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。 
// worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。 
// 
//
// 每个工人 最多 只能安排 一个 工作，但是一个工作可以 完成多次 。 
//
// 
// 举个例子，如果 3 个工人都尝试完成一份报酬为 $1 的同样工作，那么总收益为 $3 。如果一个工人不能完成任何工作，他的收益为 $0 。 
// 
//
// 返回 在把工人分配到工作岗位后，我们所能获得的最大利润 。 
//
// 
//
// 示例 1： 
// 30,20,50,10,20,
// s[]
//输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
//输出: 100 
//解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。 
//
// 示例 2: 
//
// 47,57,85 66,99,24    10,20,40,50 -> n平方,
//输入: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
//输出: 0 
//
// 
//
// 提示: 
//
// 
// n == difficulty.length 
// n == profit.length 
// m == worker.length 
// 1 <= n, m <= 10⁴ 
// 1 <= difficulty[i], profit[i], worker[i] <= 10⁵ 
// 
//
// Related Topics 贪心 数组 双指针 二分查找 排序 👍 102 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.Arrays;

public class MostProfitAssigningWork{

  /**
  * 826
  * 安排工作以达到最大收益
  * 
  * 
  *
  * 2022-11-08 16:07:41
  */  
  public static void main(String[] args) {
    Solution solution = new MostProfitAssigningWork().new Solution();
    solution.maxProfitAssignment(new int[]{23,30,35,35,43,46,47,81,83,98},new int[]{8,11,11,20,33,37,60,72,87,95},new int[]{95,46,47,97,11,35,99,56,41,92});
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      /**
       * 使用了排序和二分查找, 但是代码写的比较复杂, 时间复杂度是 O(n2) + O(n) + m*O(log2n)
       * 有几个坑,1,给难度排序的时候,也得把收益数组调整位置,否则对应不是
       * 2.可能出现一种的难度有不同的收益,导致maxp数组获取的收益可能不是这个难度最大的. 于是我们需要在二分查找到最贴近的难度后, 再往后遍历看看直到确定是这个难度的最后一位
       */
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        //思路: 1. 先把难度排序,排序的同时需要把profit和difficulty对应的位置也要调整. 2,遍历一边难度,然后保存截止到这个难度的最大收益,即m[i]=max(m[i-1],i的难度)
        //3.二分查找,找到工人最能接受的难度,取得它的m值
        for (int i = 0; i < difficulty.length; i++) {
            boolean change =false;
            for (int j = 0; j < difficulty.length-i-1; j++) {
                if (difficulty[j] > difficulty[j+1]){
                    change = true;
                    int t = difficulty[j];
                    difficulty[j] = difficulty[j+1];
                    difficulty[j+1] = t;
                    int tt = profit[j];
                    profit[j] = profit[j+1];
                    profit[j+1] = tt;
                }
            }
            if (!change){
                break;
            }
        }
        //maxp[i]表示难度在i及以下能获得的最大收益,但是需要注意可能存在同样的难度,收益不一样的情况
        int[] maxp = new int[difficulty.length];
        for (int i = 0; i < maxp.length; i++) {
            if (i == 0){
                maxp[i] = profit[i];
            }else{
                maxp[i] = Math.max(maxp[i-1],profit[i]);
            }
        }
        //二分查找
        int total=0;
        for (int i = 0; i < worker.length; i++) {
            int nengli = worker[i];
            int j = erfenSearch(difficulty, nengli);
            //这里有个坑,即使我们找到了工人可胜任的难度的索引,但是可能并不是收益最大的. 比如[1,3,4,4,5], 能力是4,假如找到了j=2,但是maxp可能是[10,30,50,100,100],即有两份难度都是4的,但是
            //一个收益是50,一个是100. 我们要获取100而不是50
            while (j>=0&&j < maxp.length-1 && difficulty[j] == difficulty[j+1] ){
                j++;
            }
            total+=j <0 ?0:maxp[j];

        }
        return total;
    }

      /** 二分查找,返回最贴近t,不超过t的值在arr数组中的下标
       * @param arr
       * @param t
       * @return
       */
    private int erfenSearch(int[]arr,int t){
        int s = 0,e = arr.length-1;

        while (s < e && arr[s] <= t && arr[e] >= t){
            int mid = (s+e)/2;
            if (arr[mid] > t){
                e = mid-1;
            } else if (arr[mid] < t) {
                s = mid+1;
            }else{
                return mid;
            }
        }
        if (t < arr[s] ){
            //-1表示数组中没有比它还小的
            return s == 0? -1 : s-1;
        }
        if (t >= arr[e] ){
            return e;
        }
        return s;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}