//给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。 
//
// 给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ： 
//
// 
// 覆盖所有 空 格子。 
// 不覆盖任何 被占据 的格子。 
// 我们可以放入任意数目的邮票。 
// 邮票可以相互有 重叠 部分。 
// 邮票不允许 旋转 。 
// 邮票必须完全在矩阵 内 。 
// 
//
// 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。 
//
// 
//
// 示例 1： 
// 1000
// 1000
// 1000
// 1000
// 1000
// 输入：grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 
//4, stampWidth = 3
//输出：true
//解释：我们放入两个有重叠部分的邮票（图中标号为 1 和 2），它们能覆盖所有与空格子。
// 
//
// 示例 2： 
//
// 1000
// 0100
// 0010
// 0001
// 输入：grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, 
//stampWidth = 2 
//输出：false 
//解释：没办法放入邮票覆盖所有的空格子，且邮票不超出网格图以外。
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[r].length 
// 1 <= m, n <= 10⁵ 
// 1 <= m * n <= 2 * 10⁵ 
// grid[r][c] 要么是 0 ，要么是 1 。 
// 1 <= stampHeight, stampWidth <= 10⁵ 
// 
//
// Related Topics 贪心 数组 矩阵 前缀和 👍 51 👎 0


package com.zgd.leetcode.editor.cn;

public class StampingTheGrid {

    /**
     * 2132 用邮票贴满网格图 11000 00000 00100 00001 00011 2023-06-16 15:30:54
     */
    public static void main(String[] args) {
        Solution solution = new StampingTheGrid().new Solution();
        //1000
        //1000
        //1000
        //1000
        //1000
        solution.possibleToStamp(new int[][]{{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,0,0}},4,3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //二维差分, 一个很好理解又不太好理解的问题, 如果对着图应该很好理解. 大概就是把一个图想象成一块蛋糕,如果我们需要中间矩形, 怎么切呢?
        //首先以矩形右下角切一个大矩形, 再从右上角和左下角把多余部分切掉. 比如,下面的矩阵
        // 1111  111
        // 1001  100  100  00
        // 1001  100  100  00
        // 1001  100  100  00
        // 1111
        public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
            //思路,1. 先用前缀和,把出现了1的都标出来. mark1[i][j] 表示一个矩形的右下角,即 0,0 到 i,j 之间的矩形有多少个1
            int r = grid.length;
            int l = grid[0].length;
            int[][] mark1 = new int[r + 1][l + 1];
            for (int i = 1; i <= grid.length; i++) {
                for (int j = 1; j <= grid[0].length; j++) {
                    mark1[i][j] =
                            mark1[i - 1][j] //上面部分
                                    + mark1[i][j - 1] //左边部分
                                    + grid[i - 1][j - 1] //自身
                                    - mark1[i - 1][j - 1]; //左上角部分(因为上面部分+左边部分把这部分重叠了)
                }
            }

            //2. 第二步,以邮票左上角为点, 判断该点这个邮票能不能贴的下,判断的方式,横,竖不能超过边界, 最重要的是mark1的这部分矩形里没有被标记(需要用二维差分)
            int[][] mygrid = new int[r+2][l+2];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    boolean height = i + stampHeight <= r;
                    boolean width = j + stampWidth <= l;
                    int er = i + stampHeight - 1;
                    int ec = j + stampWidth - 1;
                    if(height && width){
                        boolean noMark =
                                //二维差分法, 蛋糕先切大块, 然后切掉两个边, 但是因为左上角被重复切了,所以需要补,另外mark1是从1开始的,所以需要+1
                                mark1[er + 1][ec + 1]
                                        //减去左边
                                        - mark1[er + 1][(j-1)+1]
                                        //减去上面
                                        - mark1[(i-1)+1][ec + 1]
                                        //补上重复减的
                                        + mark1[(i-1)+1][(j-1)+1] == 0;
                        if (noMark) {
                            //标记贴上一张邮票,grid标记2表示
                            //如果直接遍历标记这个矩形, 会超时 , 取巧的方法, 先对四个角进行标记, 左上和右下+1, 左下+1和右上+1(注意除了左上在矩形内,其他都不是矩形内的点) 要-1
                            //因为mygrid也要计算前缀和,要预留出0行0列出来留空,所以都要+1
                            mygrid[i+1][j+1]++;
                            mygrid[i+stampHeight+1][j+stampWidth+1]++;
                            mygrid[i+1][j+stampWidth+1]--;
                            mygrid[i+stampHeight+1][j+1]--;
//                            markStamp(grid,i,j,er,ec);
                        }
                    }
                }
            }

            //从标记好的四个角,还原出矩形来
            // 一行一行的从左到右处理, 因为左上和右下+1, 左下和右上-1, 所以在按前缀和的方式就可以还原
            // 效果就是 1 0 0 0 -1 0 0  变成 1 1 1 1 0 0 0
            for (int i = 1; i <= r; i++) {
                for (int j = 1; j <= l; j++) {
                    mygrid[i][j] = mygrid[i][j-1] + mygrid[i-1][j] + mygrid[i][j] - mygrid[i-1][j-1];
                    if(mygrid[i][j] == 0 && grid[i-1][j-1] == 0){
                        //如果经过计算这里没有贴邮票,且这里原矩阵也没有东西,false
                        return false;
                    }
                }
            }
            return true;
        }

        private void markStamp(int[][] grid, int sr, int sc, int er, int ec) {
            for (int i = sr; i <= er; i++) {
                for (int j = sc; j <= ec; j++) {
                    grid[i][j] = 2;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}