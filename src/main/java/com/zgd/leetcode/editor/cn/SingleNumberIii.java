//给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。 
//
// 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,1,3,2,5]
//输出：[3,5]
//解释：[5, 3] 也是有效的答案。
// 
//
// 示例 2： 
//
// 
//输入：nums = [-1,0]
//输出：[-1,0]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,1]
//输出：[1,0]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 3 * 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 除两个只出现一次的整数外，nums 中的其他数字都出现两次 
// 
//
// Related Topics 位运算 数组 👍 852 👎 0


  
package com.zgd.leetcode.editor.cn;

public class SingleNumberIii{

  /**
  * 260
  * 只出现一次的数字 III
  * 
  * 
  *
  * 2023-11-30 19:20:33
  */  
  public static void main(String[] args) {
    Solution solution = new SingleNumberIii().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] singleNumber(int[] nums) {
        //注意审题, 重点提到了 两两相同(不需要的), 有一对不同(需要的), 且提示了位预算, 不难想到需要用 异或运算.
        //假如有数据 a,b,c,d,e,f  其中a=c,b=d, 则a^c=0, b^d=0, 由换算法则可知 a^b^c^d = 0. 最终6个一起异或的结果x=e^f, 比如 101 ^ 110 =  011, 同理可知x的0位说明两个数在此处相同,为1说明不同
        //那既然知道e和f异或=x, 说明在x每个1的位置, e和f都互补(1和0). 我们可以把这组数据一分为二, 其中在x为1的位是1的, 都放在A组, 是0的都放在B组, 可知e和f必然在不同组. 同时a和c,b和d相同的数肯定在同一组
        //然后我们在把这些组的数全部异或起来就是我们要的
        int x = 0;
        for (int num : nums) {
            x^=num;
        }
        //找到x中一个为1的位置
        int n = 1;
        while((n & x) == 0){
            n <<=1;
        }
        //分为两组来得到异或总值

        int a = 0,b=0;
        for (int num : nums) {
            if((n & num)==n){
                //如果在n的唯一1位上,num也是1, 分到这组
                a ^= num;
            }else{
                b ^= num;
            }
        }
        return new int[]{a,b};

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}