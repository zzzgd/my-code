//给定一个整数 num，计算所有小于等于 num 的非负整数中数字 1 出现的个数。 
//
// 
//
// 示例 1： 
//
// 
//输入：num = 0
//输出：0
// 
//
// 示例 2： 
//
// 
//输入：num = 13
//输出：6 
//232 200+32  32:1+11+1+1
//
//
// 提示： 
//
// 
// 1 <= n < 2³¹ 
// 
//
// 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/ 
//
// 
//
// Related Topics 递归 数学 动态规划 👍 459 👎 0


  
package com.zgd.leetcode.editor.cn;

public class OneNzhengShuZhong1chuXianDeCiShuLcof{

  /**
  * LCR 162
  * 数字 1 的个数
  * 
  * 
  *
  * 2023-10-09 14:12:17
  */  
  public static void main(String[] args) {
    Solution solution = new OneNzhengShuZhong1chuXianDeCiShuLcof().new Solution();
    solution.digitOneInNumber(1410065408);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int digitOneInNumber(int num) {
        //这个题,不写思路了,直接百度去吧
        //相当复杂,要从低位到高位,分别判断他们左右的数,以及自身的情况
        //当前位x的数字分三种情况,0,1,和大于1
        //当x是0时,它取决于前面的数. 比如2024, 这个x位为1的情况是1xx,11xx,因为x后面有2位数(100个数)所以两者一组合就是200
        //当x>1时,它左边的数和右边的数均能组合,比如30241的2,当把2变成1时,左边有30种数,配合右边的2位数,所以是30*100,但是还有一种情况是前面都是0,1xx,这里还有100,所以是3100
        //当x=1时,这里左右两边就不能全部组合了,比如3124,即31xx只有1xx,11xx,21xx,3种情况是对应右边的数分别有100种的,其他只有24种,还有1种是0100
        String nums = num+"";
        //倒数位数
        long wei = 0;
        long res = 0;
        for (int i = nums.length()-1; i >=0; i--) {
            wei++;
            long left = left(num, wei);
            long right = right(num, wei);
            //右边的位数
            long rightwei = 1;
            for (int j = 1; j < wei; j++) {
                rightwei *= 10l;
            }
            if(nums.charAt(i)-'0' == 0){
                //如果当前位是0,取决于前面的数比如3024,是21xx,11xx,1xx
                res += (left *rightwei) ;
            }
            else if(nums.charAt(i) - '0' == 1){
                //如果是为1,别忘了还有1种 0010..0
                res += ((left *rightwei)+right+1l) ;
            }else if(nums.charAt(i) - '0' > 1){
                //如果大于1,完全取决于左边
                res += ((left+1l) *rightwei) ;
            }
        }
        return (int) res;

    }

    private long right(int num,long x){
        long n = 1l;
        for (int i = 1; i < x; i++) {
            n*=10l;
        }
        return num-(num*1l)/n*n;
    }

      private long left(int num,long x){
          long n = 1;
          for (int i = 0; i < x; i++) {
              n*=10;
          }
          return num/n;
      }
}
//leetcode submit region end(Prohibit modification and deletion)

}