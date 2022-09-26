//给你两个整数 n 和 maxValue ，用于描述一个 理想数组 。 
//
// 对于下标从 0 开始、长度为 n 的整数数组 arr ，如果满足以下条件，则认为该数组是一个 理想数组 ： 
//
// 
// 每个 arr[i] 都是从 1 到 maxValue 范围内的一个值，其中 0 <= i < n 。 
// 每个 arr[i] 都可以被 arr[i - 1] 整除，其中 0 < i < n 。 
// 
//
// 返回长度为 n 的 不同 理想数组的数目。由于答案可能很大，返回对 10⁹ + 7 取余的结果。
// 1,1  1,2  1,3  2,2 3,3
// 11 12 13 14 22 24 33 44
// 11 12 13 14 15 22 24 33 44 55
// 11 12 22\
// 11 12 13 22 33
// 111 112 122 222
// 111 112 122 222 333  113 133
// 1 2|  1,1 2,2 1,2|  1,1,1 1,1,2 1,2,2 2,2,2| 1111 1112 1122 1222 2222
// 11 12 13 22 33
// 11 12 13 14 15 16  17 18 19 110 111  22 33 44 55 66 77 88 99 1010 1111
// 1111 1112 1113 1122 1133 1222 1333 2222 3333
// 1111 1112 1113 1122 1133 1222 1333 2222 3333 1114 1124 1144 1224 1244 1444 2224 2244 2444 4444 >>> 9+4+ 1124,1224,1244,2224,2244,2444 >>1,2,4的排列组合 chushu+2的排列组合
// 11111 11112 11122 11222 12222 22222
// 11111 11112 11113 11122 11133 11222 11333 12222 13333 22222 33333
// 11 12 13 14 15 16 22 [24] [26] 33 [36] 44 55 66 -> 6+5+3

// 示例 1：
//
// 输入：n = 2, maxValue = 5
//输出：10
//解释：存在以下理想数组：
//- 以 1 开头的数组（5 个）：[1,1]、[1,2]、[1,3]、[1,4]、[1,5]
//- 以 2 开头的数组（2 个）：[2,2]、[2,4]
//- 以 3 开头的数组（1 个）：[3,3]
//- 以 4 开头的数组（1 个）：[4,4]
//- 以 5 开头的数组（1 个）：[5,5]
//共计 5 + 2 + 1 + 1 + 1 = 10 个不同理想数组。
// 
//
// 示例 2： 
//
// 输入：n = 5, maxValue = 3
//输出：11
//解释：存在以下理想数组：
//- 以 1 开头的数组（9 个）：
//   - 不含其他不同值（1 个）：[1,1,1,1,1] 
//   - 含一个不同值 2（4 个）：[1,1,1,1,2], [1,1,1,2,2], [1,1,2,2,2], [1,2,2,2,2]
//   - 含一个不同值 3（4 个）：[1,1,1,1,3], [1,1,1,3,3], [1,1,3,3,3], [1,3,3,3,3]
//- 以 2 开头的数组（1 个）：[2,2,2,2,2]
//- 以 3 开头的数组（1 个）：[3,3,3,3,3]
//共计 9 + 1 + 1 = 11 个不同理想数组。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= n <= 10⁴ 
// 1 <= maxValue <= 10⁴ 
// 
//
// Related Topics 数学 动态规划 组合数学 数论 👍 39 👎 0


package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class CountTheNumberOfIdealArrays {

    /**
     * 2338 统计理想数组的数目
     * <p>
     * <p>
     * <p>
     * 2022-09-22 17:48:48
     */
    public static void main(String[] args) {
        Solution solution = new CountTheNumberOfIdealArrays().new Solution();
        int i = solution.idealArrays(37, 71);
        System.out.println("i = " + i);
    }

    /**
     * <div>
     * 困扰了我足足一天之久,甚至看解答都看了好几个小时. 没办法 高中的排列组合都忘记了
     * 在做这个题之前,复习下排列组合A和C,主要讲C:
     *   1.如果不考虑顺序,则用C
     *   2.比如从10个球中抽出2个球的组合数是 C(10,2) = 10*9/2*1 = 45
     *   3.C(n,m) = C(n-1,m)+C(n-1,m-1). 如果要理解的话,10个球抽3个球,可以理解为,先放9个球(不放1号球),摸3个球(自然始终摸不到1球),
     * 即C(9,3), 接着手里直接拿1号球,从9个球摸2个球,即C(9,2),加起来的组合方式就和10个球摸3个一样了
     *   4.C(n,m)  等同于 C(n,n-m)
     * 好了,复习完高中课程,再看这个题.
     * a.首先可知它的排列是一个倍数递增的, 1,2,4,4,8,18. 那这个序列如果要<maxValue内尽可能不重复的话,应该按最小的倍率即2来,
     * 即 1,2,4,8...2^13(8000多) 到2^14就超过题目的10000了, 即这个序列不重复的数最多14个.即最多翻倍的次数不会超过14此
     * b.这个序列的写法,可以通过倍数的方法表示,比如 1,2,4,4,8 即 -> _,*2,*2,_,*2 (下划线表示不翻倍,*2表示翻两倍)
     * c.但是序列的写法实在太多了,我们可以根据最后一位来拆成不同部分,最后累加. 比如最后一位m=3,n=4,可以分别拆成 xxx1,xxx2,xxx3,xxx4 4种情况来分别考虑
     * d.假设最后一位是m,因此这个题目大概可以转换成, 找出m的因数,在n个位置里排列组合的方式. 比如m=12.它的因数有2*2*3, 即有两个2,一个3
     * 在n=4,它可以这么排: _,*2,*2,# 即 1,2,4,12 (最后一位我们要固定设置为m,不然这个情况就会和其他m后缀的情况重合).对于因数2,可以两个都出现,可以都不出现
     * e.m可能有x个因数a,y个因数b... 我们同样分开考虑, 先考虑a的情况, 在考虑b的情况, 在考虑两者都出现的情况(两者互不干扰,所以结果是相乘)
     * f.那怎么计算m的k个因数a在n个位置中的组合数量呢(因为最后一个必须是m,所以k个因数都必须出现,比如12的2*2*3, 即a=2时2个*2都必须出现,a=3时*3也必须出现).
     * 我们可以想象有n个盒子,k个球,因为允许盒子为空,我们假定先借来n个球,总共n+k个球,n+k-1个间隙,我们插入n-1个板子隔开,把板子之间的球放到盒子里,再把原来借来的球还回去,
     * 则结果是C(n-1+k,n-1),根据上面的4公式,可以换成C(n-1+k,k),这是因为k大概率比n要小,好算一些. 同样我们可以把10000+14 以内的C排列数量计算都初始化
     * g.那如果m有因数如下: k1个a,k2个b..., 则m的总排列方式就是 C(n-1+k1,k1) * C(n-1+k2,k2) * ...
     * h.最后的结果就是 1~m 的g的结果相加
     * </div>
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        final  static int MOD = 1_000_000_007;
        //最大的m
        final static int MAX_M = 10_000;
        //序列最多翻倍的次数,也可以理解为这个数组序列不重复的数出现的最大次数
        final static int MAX_K = 14;
        //zuhe[n][k]表示C(n-1+k,k)
        int[][] zuhe = new int[MAX_M + MAX_K][MAX_K + 1];
        List<Integer>[] yinshu = new List[MAX_M+1];
         {
            initZuhe();
            getYinshu(MAX_M);
        }
        public int idealArrays(int n, int maxValue) {
            //初始化组合结果
            initZuhe();

            //获取因数
         getYinshu(maxValue);

            //最后一步,合并
            long res = 0;
            for (int i = 1; i <= maxValue; i++) {
                //m的不同因数的组合结果,再相乘
                long mm = 1;
                for (Integer k : yinshu[i]) {
                    //k表示i的其中一个因数的个数,如果i是24,k可能是3,即3个因数2. 因为2*2*2*3
                    //zuhe[n][k]表示C(n-1+k,k)
                    //把不同因数之间的排列数相乘
                    mm = (mm * zuhe[n-1+k][k]) % MOD;
                }
                res=(res+mm)% MOD;
            }
            return (int) (res%MOD);
        }

        private void getYinshu(int maxValue) {
            //获取1~maxValue的每个m的因数

            //4,4:  1+ C4+1-1,1 + C4+1-1,1  + C4+2-1,2
            for (int i = 1; i <= maxValue; i++) {
                //获取因数
                List<Integer> list = new ArrayList<>();
                int t = i;
                for (int k = 2;k*k<=i;k++){
                    int x = 0;
                    while(t % k == 0){
                        t = t/k;
                        //i能被k整除,继续除,看有几个
                        x++;
                    }
                    //记录下来这个因数的个数, 如12的因数2就有2个 2*2*3
                    if (x > 0){
                        list.add(x);
                    }
                }

                if (t > 1){
                    //如果没有找到因数,add1,表示质数
                    list.add(1);
                }
                yinshu[i]=list;
            }

        }

        private void initZuhe() {
            //根据上面的f点, 我们可以先把10000+14以内的组合数都计算出来
            //利用C<n,m> = C<n-1,m> + C<n-1,m-1>
            zuhe[0][0]=1;
            for (int i = 1; i < zuhe.length; i++) {
                //C<i,j> j大于i的话无意义
                zuhe[i][0]=1;
                for (int j = 1; j <= i && j<zuhe[0].length; j++) {
                    if (j == 1) {
                        //C<i,j>,如果j是1,表示i个里选1个,组合数是i个
                        zuhe[i][j] = i;
                    }else{
                        zuhe[i][j] = zuhe[i-1][j]% MOD + zuhe[i-1][j-1]% MOD;
                    }

                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}