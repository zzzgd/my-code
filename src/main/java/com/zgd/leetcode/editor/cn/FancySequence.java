//请你实现三个 API append，addAll 和 multAll 来实现奇妙序列。 
//
// 请实现 Fancy 类 ： 
//
// 
// Fancy() 初始化一个空序列对象。 
// void append(val) 将整数 val 添加在序列末尾。 
// void addAll(inc) 将所有序列中的现有数值都增加 inc 。 
// void multAll(m) 将序列中的所有现有数值都乘以整数 m 。 
// int getIndex(idx) 得到下标为 idx 处的数值（下标从 0 开始），并将结果对 10⁹ + 7 取余。如果下标大于等于序列的长度，请返回
// -1 。 
// 
// 010010
// 010001
//
// 示例： 
//
// 
//输入：
//["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", 
//"append", "multAll", "getIndex", "getIndex", "getIndex"]
//[[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
//输出：
//[null, null, null, null, null, 10, null, null, null, 26, 34, 20]
//
//解释：
//Fancy fancy = new Fancy();
//fancy.append(2);   // 奇妙序列：[2]
//fancy.addAll(3);   // 奇妙序列：[2+3] -> [5]
//fancy.append(7);   // 奇妙序列：[5, 7]
//fancy.multAll(2);  // 奇妙序列：[5*2, 7*2] -> [10, 14]
//fancy.getIndex(0); // 返回 10
//fancy.addAll(3);   // 奇妙序列：[10+3, 14+3] -> [13, 17]
//fancy.append(10);  // 奇妙序列：[13, 17, 10]
//fancy.multAll(2);  // 奇妙序列：[13*2, 17*2, 10*2] -> [26, 34, 20]
//fancy.getIndex(0); // 返回 26
//fancy.getIndex(1); // 返回 34
//fancy.getIndex(2); // 返回 20
// 
//
// 
//
// 提示： 
//
// 
// 1 <= val, inc, m <= 100 
// 0 <= idx <= 10⁵ 
// 总共最多会有 10⁵ 次对 append，addAll，multAll 和 getIndex 的调用。 
// 
//
// Related Topics 设计 线段树 数学 👍 58 👎 0


  
package com.zgd.leetcode.editor.cn;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.HashMap;
import java.util.Map;

public class FancySequence{

  /**
  * 1622
  * 奇妙序列
  * 
  * 
  *
  * 2022-09-13 17:20:56
  */  
  public static void main(String[] args) {

  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Fancy {

      /**
       * 搞不懂乘法逆元,老老实实按想得到的思路来.
       * 1. 依次保存append的值
       * 2. 乘和加都先不操作,记录起来
       * 3. 考虑超出int溢出的情况,随时求模
       * 4. 做好缓存
       */
    public Fancy() {

    }
    //数据组,因为都是小于100 所以用byte足够
    byte[] arr = new byte[100001];
    //操作组
    byte[] opt = new byte[100000];
    //记录idx位置的元素计算到了第几步
    Map<Integer,Integer> optMap = new HashMap<Integer,Integer>();
    //做一个缓存,缓存索引,key是idx,value是一个数组,[0]表示计算到第几步,[1]表示计算到这一步的值
    Map<Integer,Integer> cache = new HashMap<Integer,Integer>();
    int MOD = 1000_000_007;
    int optOffset = 0;
    int arrOffset = 0;
    //0 <= idx <= 10⁵
    public void append(int val) {
        arr[arrOffset++] = (byte)val;
        //为了知道该索引后面做了哪些操作,操作组也需要后移. 0表示添加,正数表示乘,负数表示加
        opt[optOffset++] = 0;
        //记录idx 计算到了 opt步
        optMap.put(arrOffset-1, optOffset-1);
    }
    
    public void addAll(int inc) {
        opt[optOffset++] = (byte)-inc;
        //缓存清空 但是这样的话缓存就没太大意义了. 更好的方式是不清空,但是记录idx索引处的x在计算到n步骤以后的结果值res
//        cache.clear();
    }
    
    public void multAll(int m) {
        opt[optOffset++] = (byte)m;
        //缓存清空
//        cache.clear();
    }
    
    public int getIndex(int idx) {
        //重头戏.
        //根据idx得到val
        if (idx >= arrOffset){
            return -1;
        }
        Integer val = cache.get(idx);
        if (val  == null){
            //初始化
            val = (int) arr[idx];
        }
        //得到idx的元素计算到第几步
        int startSetp = optMap.get(idx)+1;

        //开始遍历后面的操作
        for (int i = startSetp; i < optOffset; i++) {
            if (opt[i] == 0){
                continue;
            }
            //如果val*100 就要打过int最大值了,此时乘法改成每加一次mod一次
            boolean tomax = val >= Integer.MAX_VALUE /100;
            byte m = opt[i];
            if (m > 0){
                if (tomax){
                    val = addReplaceMult(val,m);
                }else {
                    val *= m;
                }
            }else{
                //因为存的是负数,所以用减法
                val -= m;
                if (tomax){
                    val %= MOD;
                }
            }

        }
        //更新缓存
        cache.put(idx,val);
        optMap.put(idx,optOffset-1);
        return val;

    }

    private int addReplaceMult(int x,int y){
        long lx = x;
        for (int i = 1; i < y; i++) {
            lx+=x;
        }
        lx%=MOD;
        return (int)lx;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
//leetcode submit region end(Prohibit modification and deletion)

}