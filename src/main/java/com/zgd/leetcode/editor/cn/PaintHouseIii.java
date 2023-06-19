//在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n ）。有的房子去年夏天已经涂过颜色了，所以这些房子不可以
//被重新涂色。 
//
// 我们将连续相同颜色尽可能多的房子称为一个街区。（比方说 houses = [1,2,2,3,3,2,1,1] ，它包含 5 个街区 [{1}, {2,2}
//, {3,3}, {2}, {1,1}] 。） 
//
// 给你一个数组 houses ，一个 m * n 的矩阵 cost 和一个整数 target ，其中： 
//
// 
// houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色。 
// cost[i][j]：是将第 i 个房子涂成颜色 j+1 的花费。 
// 
//
// 请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target 个街区。如果没有可用的涂色方案，请返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n 
//= 2, target = 3
//输出：9
//解释：房子涂色方案为 [1,2,2,1,1]
//此方案包含 target = 3 个街区，分别是 [{1}, {2,2}, {1,1}]。
//涂色的总花费为 (1 + 1 + 1 + 1 + 5) = 9。
// 
//
// 示例 2： 
//
// 
//输入：houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n 
//= 2, target = 3
//输出：11
//解释：有的房子已经被涂色了，在此基础上涂色方案为 [2,2,1,2,2]
//此方案包含 target = 3 个街区，分别是 [{2,2}, {1}, {2,2}]。
//给第一个和最后一个房子涂色的花费为 (10 + 1) = 11。
// 
//
// 示例 3： 
//
// 
//输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, 
//n = 2, target = 5
//输出：5
// 
//
// 示例 4： 
//
// 
//输入：houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3,
// target = 3
//输出：-1
//解释：房子已经被涂色并组成了 4 个街区，分别是 [{3},{1},{2},{3}] ，无法形成 target = 3 个街区。
// 
//
// 
//
// 提示： 
//
// 
// m == houses.length == cost.length 
// n == cost[i].length 
// 1 <= m <= 100 
// 1 <= n <= 20 
// 1 <= target <= m 
// 0 <= houses[i] <= n 
// 1 <= cost[i][j] <= 10^4 
// 
//
// Related Topics 数组 动态规划 👍 194 👎 0


package com.zgd.leetcode.editor.cn;

public class PaintHouseIii {

    /**
     * 1473 粉刷房子 III
     * <p>
     * <p>
     * <p>
     * 2023-06-16 18:42:03
     */
    public static void main(String[] args) {
        Solution solution = new PaintHouseIii().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * <div>
         * 1. 动态规划-使用范围:若干的变量能确定一个固定的值, 根据上一个值确定下一个值,使用数组来表示.从0下标开始推导,最后直接取最后结果
         * 2. 此题有三个变量, 第几个房子,第几个分区,刷的什么颜色. 值就是这一点累计的最小cost
         * </div>
         *
         * @param houses
         * @param cost
         * @param m      m == houses.length == cost.length
         * @param n      颜色的数量
         * @param target 分区数
         * @return
         */
        public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
            //定义一个比较大的默认值,除以2是为了避免+cost的时候溢出
            int INF = Integer.MAX_VALUE/2;
            int[][][] arr = new int[m + 1][target + 1][n + 1];
            //初始化, 默认值Integer.maxValue, 0号房子(不是指下标为0的,这个是虚拟的)都是0
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    for (int k = 0; k <= target; k++) {
                        if (i == 0) {
                            arr[i][k][j] = 0;
                        } else {
                            arr[i][k][j] = INF;
                        }
                    }

                }
            }

            //从0号房子推导
            for (int i = 1; i <= m; i++) {
                //房子有两种情况,houses[i]=0表示没刷过,需要刷,即颜色可选.house[i]=1则不可选颜色
                int nn = i - 1;
                boolean color = houses[nn] == 0;

                //分区
                for (int j = 1; j <= i && j <= target; j++) {
                    //颜色
                    for (int k = 1; k <= n; k++) {
                        //2种可能,1是和前面颜色一样,即分区也和前面一样,cost不变
                        int tmp1 = arr[i - 1][j][k];

                        //2是和前面颜色不一样,所以前一个分区数比当前分区数小1
                        // 即，从「上一分区」「不同颜色」房子中找「花费最少」的情况
                        int tmp2 = INF;

                        for (int p = 1; p <= n; p++) {
                            if (p != k) {
                                tmp2 = Math.min(tmp2, arr[i - 1][j - 1][p]);
                            }
                        }

                        //如果已经刷了色
                        if (!color) {
                            if (k != houses[nn]) {
                                arr[i][j][k] = INF;
                            } else {
                                //两者取最小的
                                arr[i][j][k] = Math.min(tmp1, tmp2);
                            }
                        } else {
                            //如果没刷颜色,还得加上这次的cost
                            arr[i][j][k] = Math.min(tmp1, tmp2) + cost[i - 1][k - 1];
                        }

                    }
                }
            }
            //取最后一个房子, 分区是target的最小的
            int res = INF;
            for (int a = 1; a <= n; a++) {
                res = Math.min(res, arr[m][target][a]);
            }
            return res == INF ? -1 : res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}