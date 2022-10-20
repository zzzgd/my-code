//给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。 
//
// 您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [3,6,9,1]
//输出: 3
//解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。 
//
// 示例 2: 
//
// 
//输入: nums = [10]
//输出: 0
//解释: 数组元素个数小于 2，因此返回 0。 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 10⁵ 
// 0 <= nums[i] <= 10⁹ 
// 
//
// Related Topics 数组 桶排序 基数排序 排序 👍 522 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.*;

public class MaximumGap{

  /**
  * 164
  * 最大间距
  * 
  * 
  *
  * 2022-10-17 18:03:51
  */  
  public static void main(String[] args) {
    Solution solution = new MaximumGap().new Solution();
    solution.maximumGap(new int[]{15252,16764,27963,7817,26155,20757,3478,22602,20404,6739,16790,10588,16521,6644,20880,15632,27078,25463,20124,15728,30042,16604,17223,4388,23646,32683,23688,12439,30630,3895,7926,22101,32406,21540,31799,3768,26679,21799,23740});
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      private static final int TONG_NUM = 1000;
      private static final int TONG_SIZE = 100;
      private static final int TONG_RANGE = 1000_000_000 / TONG_NUM;

      public int maximumGap(int[] nums) {
          //动态设置桶数吧,// 1,100,102,104,103| 101, 105,199|
          //1. 设置桶, 然后把数放到桶里
          //2. 放到桶里的时候,记录这个桶的最大值,最小值,
          //3. 比较每个桶的最大差值,
          /**
           * 这里最重要的一个点,就是如何设计桶的间距. 比如1,100,150,200. 如果设置间距为1,没啥效果. 间距为100,会出现一个桶内(1和100)的间距比桶与桶之间的还大
           * 如果不理解,先记住公式: 间距 = (max-min)/(num-1)
           * 这个公式得出来的,是num个数,最大值为max,最小值为min,平分间隔时,间隔大小.
           * 举例,2~8有4个数,根据公式得到平均间隔的话就是2. (2,4,6,8)
           * 这样的话,不管这 4个数实际是什么样的, 他们的最大相邻间隔,肯定>=2, 即如果用大小2来设置桶,不会出现在同一个桶里. [2,1|4,5|6,7|8,9]
           */
          int min = Integer.MAX_VALUE;
          int max = Integer.MIN_VALUE;
          for (int num : nums) {
              min = Math.min(min,num);
              max = Math.max(max,num);
          }
          if (min == max || nums.length == 1){
              //说明所有数都是一样的
              return 0;
          }
          //使用公式,得到平均间隔,这样不管数怎么分配,他们总有一个相邻的最大间隔会大于等于这个值
          int avggap = (max-min)/(nums.length-1);
          //如果avggap为0, 说明数比较多但是大小区别不大, 但是最小为1
          if (avggap ==0){
              avggap = 1;
          }
          //桶的数量 +1是为了处理int相除的问题
          int tong_len = (max-min  ) / avggap +1;
          int[] maxarr = new int[tong_len];
          int[] minarr = new int[tong_len];
          //因为数可以为0,要避免混淆,预设值为-1
          Arrays.fill(maxarr,-1);
          Arrays.fill(minarr,Integer.MAX_VALUE);
          //遍历,将每个桶最大值最小值确定
          for (int num : nums) {
              //因为是从min~max 所以要减去min,计算出属于哪个桶
              int tong_idx = (num-min)/avggap;
              maxarr[tong_idx] = Math.max(maxarr[tong_idx],num);
              minarr[tong_idx] = Math.min(minarr[tong_idx],num);
          }

          //遍历桶,然后比较这个桶的最小值与上一个的最大值. 这里不用担心这个0的索引没有数据,因为min和max注定了第0个和最后一个肯定是有数据的
          int last_tong_max = maxarr[0];
          int res = -1;
          for (int i = 1; i < tong_len; i++) {
              //如果值为-1,说明这个桶没有处理过没有任何值
              if (maxarr[i] == -1){
                  continue;
              }
              res = Math.max(res,minarr[i] - last_tong_max);
              last_tong_max = maxarr[i];
          }
          return res;

      }
      /**
       * 这种方式本质还是进行了排序,速度不是很快,不算很优的解法
       * @param nums
       * @return
       */
    public int maximumGap2(int[] nums) {

        //使用桶排序
        //因为数字取自0~10的9次方. 最多10的5次方个数, 10的3次方个桶比较合适,这样平均每个桶100个数
        List<Integer>[] tong = new List[TONG_NUM];
        //取值是10的9次方,这样 10^9 / 10^3 = 10^6, 即每10^6一个区间
        for (int i = 0; i < nums.length; i++) {
            //在这个桶的对应位置上加
            List list = tong[nums[i] / TONG_RANGE];
            if (list == null){
                list = new ArrayList();
                tong[nums[i] / TONG_RANGE] = list;
            }
            list.add(nums[i]);
        }
        //遍历桶,排序
        int last = -1;
        int maxgap = 0;
        for (int i = 0; i < tong.length; i++) {
            //这个桶里的数必定在一个 TONG_RANGE 区间
//            int[] rang = new int[TONG_RANGE];
//            for (int o : tong[i]) {
//                //这个位置标记出来
//                rang[o - i*TONG_RANGE]+=1;
//            }
            if (tong[i] == null || tong[i].isEmpty()){
                continue;
            }
            Collections.sort(tong[i]);
            for (Integer thisone : tong[i]) {
                    if (last == -1){
                        last = thisone;
                    }
                    maxgap =  Math.max(maxgap,thisone-last);
                    last=thisone;
                }
        }
        return maxgap;


    }
}
//leetcode submit region end(Prohibit modification and deletion)

}