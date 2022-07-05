//「力扣挑战赛」 的入场仪式马上就要开始了，由于安保工作的需要，设置了可容纳人数总和为 `M` 的 `N` 个安检室，`capacities[i]` 记录第 
//`i` 个安检室可容纳人数。安检室拥有两种类型：
//- 先进先出：在安检室中的所有观众中，最早进入安检室的观众最先离开
//- 后进先出：在安检室中的所有观众中，最晚进入安检室的观众最先离开
//
//![c24754f1a5ff56989340ba5004dc5eda.gif](https://pic.leetcode-cn.com/1628843202
//-cdFPSt-c24754f1a5ff56989340ba5004dc5eda.gif)
//
//
//
//恰好 `M+1` 位入场的观众（编号从 0 开始）需要排队**依次**入场安检， 入场安检的规则如下：
//- 观众需要先进入编号 `0` 的安检室
//- 当观众将进入编号 `i` 的安检室时（`0 <= i < N`)，
// - 若安检室未到达可容纳人数上限，该观众可直接进入；
// - 若安检室已到达可容纳人数上限，在该观众进入安检室之前需根据当前安检室类型选择一位观众离开后才能进入；
//- 当观众离开编号 `i` 的安检室时 （`0 <= i < N-1`)，将进入编号 `i+1` 的安检室接受安检。
//
//若可以任意设定每个安检室的类型，请问有多少种设定安检室类型的方案可以使得编号 `k` 的观众第一个通过最后一个安检室入场。
//
//
//**注意：** 
//- 观众不可主动离开安检室，只有当安检室容纳人数达到上限，且又有新观众需要进入时，才可根据安检室的类型选择一位观众离开；
//- 由于方案数可能过大，请将答案对 `1000000007` 取模后返回。
//
//
//**示例 1：**
//> 输入：`capacities = [2,2,3], k = 2`    n%2 2-1-n%2
//>
//> 输出：`2`
//> 解释：
//> 存在两种设定的 `2` 种方案：
//> - 方案 1：将编号为 `0` 、`1` 的实验室设置为 **后进先出** 的类型，编号为 `2` 的实验室设置为 **先进先出** 的类型；
//> - 方案 2：将编号为 `0` 、`1` 的实验室设置为 **先进先出** 的类型，编号为 `2` 的实验室设置为 **后进先出** 的类型。
//>
//> 以下是方案 1 的示意图：
//>![c60e38199a225ad62f13b954872edf9b.gif](https://pic.leetcode-cn.com/162884161
//8-bFKsnt-c60e38199a225ad62f13b954872edf9b.gif)
//
//
//
//**示例 2：**
//> 输入：`capacities = [3,3], k = 3`    1,2,  -1,
//>
//> 输出：`0`
//
//**示例 3：**
//> 输入：`capacities = [4,3,2,2], k = 6`    2,1  7-4 4-(7-4)+1 3,2   1
// 6,3  6,3
//>
//> 输出：`2`
//
//**提示:**
//+ `1 <= capacities.length <= 200`
//+ `1 <= capacities[i] <= 200`
//+ `0 <= k <= sum(capacities)`
// Related Topics 数组 动态规划 👍 10 👎 0


  
package com.zgd.leetcode.editor.cn;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

import java.util.Arrays;

public class OPs9Bm{

  /**
  * LCP 47
  * 入场安检
  * 
  * 等同于一个排队插队问题. 先进先出,相当于k的位置没有变. 先进后出,相当于房间滞留了(c-1)个人,也就是相当于可以插队(c-1)人
   * 判断成功的条件,就是它最后的位置是第0位
  *
  * 2022-07-05 15:06:34
  */  
  public static void main(String[] args) {
    Solution solution = new OPs9Bm().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //3,3  3  -- 1 3
    final int MOD = 1000000007;
    private int ans = 0;
    public int securityCheck(int[] capacities, int k) {
      //记忆搜索 缓存
      int[][] mark = new int[201][k+1];
      for (int i = 0; i < 201; i++) {
        Arrays.fill(mark[i],-1);
      }
      return jisuan(capacities,0,k,mark) % MOD;
    }

    private int jisuan(int[]cap,  int n, int num,int[][] mark){
      if (n == cap.length && num == 0 ){
        return 1;
      }
      if (n >= cap.length||num < 0){
        return 0;
      }
      //使用缓存
      if (mark[n][num] != -1){
        return mark[n][num];
      }
      //这里要加取模,否则数据溢出变成负数
      int ans = (jisuan(cap,n+1,num,mark) +
              jisuan(cap,n+1,num -(cap[n]-1),mark)) %MOD;
      mark[n][num] = ans;
      return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}