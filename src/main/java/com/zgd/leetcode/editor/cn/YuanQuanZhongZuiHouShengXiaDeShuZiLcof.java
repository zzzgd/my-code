//0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
// 
//
// 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。 
// 5,3 01234 0134  134  13  3
// 5,4 01234 0124  014   01 0
//
// 示例 1： 
//
// 
//输入: n = 5, m = 3
//01234
//输出: 3
// 
//
// 示例 2： 
//
// 
//输入: n = 10, m = 17
//输出: 2
// 
//
// 
//
// 限制： 
//
// 
// 1 <= n <= 10^5 
// 1 <= m <= 10^6 
// 
//
// Related Topics 递归 数学 👍 808 👎 0


  
package com.zgd.leetcode.editor.cn;

public class YuanQuanZhongZuiHouShengXiaDeShuZiLcof{

  /**
  * 剑指 Offer 62
  * 圆圈中最后剩下的数字
  * 
  * 
  *
  * 2023-07-28 17:19:26
  */  
  public static void main(String[] args) {
    Solution solution = new YuanQuanZhongZuiHouShengXiaDeShuZiLcof().new Solution();
    solution.lastRemaining(5,3);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lastRemaining(int n, int m) {
        int[] arr= new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=i;
        }
        return digui(arr,m,0,arr.length);
    }



      private int digui2(int[] arr, int m,int i,int num){
          if(i == arr.length){
              i = 0;

          }
          while(arr[i]<0){
              i++;
          }
          if(arr.length-1 == num){
              return arr[i];
          }
          //每次要删的第n个数量 m, 从i开始
          int mm = 0;
          while(mm < m ){

              while(arr[i] < 0){
                  i++;
                  if(i == arr.length){
                      i = 0;
                  }
              }
              {
                  mm++;
                  i++;
                  if(i == arr.length){
                      i = 0;
                  }
              }

          }
          while(arr[i] < 0) {
              i++;
              if(i == arr.length){
                  i = 0;
              }
          }
          arr[i] = -1;

          return digui(arr,m,i+1,num+1);
      }


      /**
       * 这个方法不出所料超时了 Time Limit Exceeded
       * @param arr
       * @param m
       * @param i 记录下次递归从哪里开始
       * @param len 使用同一个数组不变 通过改变len长度来改变数组大小
       * @return
       */
    private int digui(int[] arr, int m,int i,int len){
        if(len == 1){
            return arr[0];
        }
            //找到要删的下标
            int idx =(m+i)   % len -1;
            if(idx < 0){
                //说明 m+1 = len, 则实际为len-1, 这种情况应该是正好删除的是最后一个
                idx = len-1;
            }
            if(idx < len-1){
                System.arraycopy(arr,idx+1,arr,idx,len-1-idx);
            }
            return digui(arr,m,idx,len-1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}