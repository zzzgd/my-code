//给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。 
//
// 请你找到并返回这个整数 
//
// 
//
// 示例： 
//
// 
//输入：arr = [1,2,2,6,6,6,6,7,10]
//输出：6
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10^4 
// 0 <= arr[i] <= 10^5 
// 
// Related Topics 数组 👍 64 👎 0


  
package com.zgd.leetcode.editor.cn;

public class ElementAppearingMoreThan25InSortedArray{

  /**
  * 1287
  * 有序数组中出现次数超过25%的元素
  * 
  * 
  *
  * 2022-07-28 19:33:01
  */  
  public static void main(String[] args) {
    Solution solution = new ElementAppearingMoreThan25InSortedArray().new Solution();
    solution.findSpecialInteger(new int[]{15,15,21,21,32,32,33,33,33,34,35});
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      /**
       * <p>
       * 这题看起来很简单  但是有几个坑:
       * 1. 临时变量最好直接取[0]位置的元素, 然后第一次判断必然是相等, count++;
       * 2. 判断是否超过25%, 要用*1.0来将数据从int转为double类型, 否则会精度丢失
       * 3. 在遇到不再连续的元素后, 需要重置count, 记得是从1开始而不是从0
       *
       * </p>
       * @param arr
       * @return
       */
    public int findSpecialInteger(int[] arr) {
        int  len = arr.length;
        int last = arr[0];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == last){
                count++;
            }
            if (i == arr.length - 1 ||arr[i] != last){
                if (count > 0 && len* 1.0 / count  < 4.0){
                    break;
                }
                //重置计数
                last = arr[i];
                //重置要从1开始算
                count = 1 ;
            }
        }
        return last;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}