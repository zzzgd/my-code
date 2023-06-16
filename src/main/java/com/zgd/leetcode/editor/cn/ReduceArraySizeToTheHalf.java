//给你一个整数数组 arr。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。 
//
// 返回 至少 能删除数组中的一半整数的整数集合的最小大小。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [3,3,3,3,5,5,5,2,2,7]
//输出：2
//解释：选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。
//大小为 2 的可行集合有 {3,5},{3,2},{5,2}。
//选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。
// 
//
// 示例 2： 
//
// 
//输入：arr = [7,7,7,7,7,7]
//输出：1
//解释：我们只能选择集合 {7}，结果数组为空。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10⁵ 
// arr.length 为偶数 
// 1 <= arr[i] <= 10⁵ 
// 
//
// Related Topics 贪心 数组 哈希表 排序 堆（优先队列） 👍 47 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class ReduceArraySizeToTheHalf{

  /**
  * 1338
  * 数组大小减半
  * 
  * 
  *
  * 2023-06-16 14:27:07
  */  
  public static void main(String[] args) {
    Solution solution = new ReduceArraySizeToTheHalf().new Solution();
    solution.minSetSize(new int[]{61560,82394,79991,94425,51367,15585,70806,62035,92691,19898,92805,87090,62437,1541,44877,1806,87165,48673,69964,81681,50603,52633,36450,20605,43939,69398,90540,72594,71583,38562,32649,90011,14125,23805,40535,29821,3141,9688,30729,90746,6106,72088,68317,76565,79240,21682,1725,21595,82484,21702,3979,47020,7870,69974,18820,37987,33045,75205,4426,87377,58791,55352,94064,82306,9666,64229,3530,18729,65631,21362,98319,53945,25853,57521,27345,12898,73957,73394,25872,66448,61867,1172,3566,91448,59578,43360,63326,57630,72646,54078,52312,36425,8945,68573,97785,83927,53190,34535,50730,23437,57367,52302,62327,98877,34108,92019,34669,40364,95783,89771,96017,42405,54421,76224,38673,86701,65829,14171,61058,51345,99360,17076,23607,29336,34288,92214,1820,99798,45268,83921,51020,93354,88596,17949,56103,70100,68536,9525,69154,78172,88781});
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

      /**
       * 方案2 超时了, 其实也能想到用堆排序的方式, 因为每次都需要取出一个次数最大的值, 累加后, 再取一个第二大的值,这不就是堆排序吗
       * @param arr
       * @return
       */
      public int minSetSize(int[] arr) {
          //用一个map接收, key是值,value是出现次数
          //然后遍历map,无序也没关系,找到value最大的一个,并移除,然后累加value, 直到value > arr.length/2
          Map<Integer,Integer> map = new HashMap<>();
          for (int i : arr) {
              Integer v = map.get(i);
              map.put(i,v==null? 1: v+1);
          }
          Integer[] vals = map.values().toArray(new Integer[0]);
          //构建最大顶堆,累计数量

          int num = 0;
          int cnt = 0;
          int end = vals.length - 1;
          while(num < arr.length /2){
              maxTopHeap(vals,end);
              //顶堆最大值
              int maxval = vals[0];
              //如果最大值是1,那后面都不用看了
              if(maxval == 1){
                  return cnt + arr.length /2 - num;
              }
              //调换到尾部
              a2b(vals,0,end--);
              num+=maxval;
              cnt++;
          }
          return cnt;
      }

      private void maxTopHeap(Integer[] arr,int end){
          if(end <= 0){
              return;
          }
          //从0-end 重建最大顶堆, 比如下面的堆, 我们需要从2((end-1)/2)开始,和5,6比较对调,再到1,再到0...
          //    0
          //  1  2
          //3 4  5 6
          int n = (end-1)/2;
          while(n >= 0){
              //和子节点比较,把大的放上来
              if(arr[n] < arr[2*n+1]){
                  a2b(arr,n,2*n+1);
              }
              if(2*n+2 <= end && arr[n] < arr[2*n+2]){
                  a2b(arr,n,2*n+2);
              }
              n--;
          }
      }

      private void a2b(Integer[]arr,int a,int b){
          int t = arr[a];
          arr[a] = arr[b];
          arr[b] = t;
      }
      private void a2b(int[]arr,int a,int b){
          int t = arr[a];
          arr[a] = arr[b];
          arr[b] = t;
      }
      /**
       * 前面的思路应该都没问题, 后面超时了
       * @param arr
       * @return
       */
    public int minSetSize2(int[] arr) {
        //用一个map接收, key是值,value是出现次数
        //然后遍历map,无序也没关系,找到value最大的一个,并移除,然后累加value, 直到value > arr.length/2
        Map<Integer,Integer> map = new HashMap<>();
        for (int i : arr) {
            Integer v = map.get(i);
            map.put(i,v==null? 1: v+1);
        }

        int num = 0;
        int cnt = 0;
        while(num < arr.length /2){
            int maxval = 0;int maxkey = 0;
            for (Map.Entry<Integer, Integer> en : map.entrySet()) {
                if(en.getValue() > maxval){
                    maxkey = en.getKey();
                    maxval = en.getValue();
                }
            }
            map.remove(maxkey);
            num+=maxval;
            cnt++;
        }
        return cnt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}