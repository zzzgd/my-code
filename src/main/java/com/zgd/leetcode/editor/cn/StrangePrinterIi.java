//给你一个奇怪的打印机，它有如下两个特殊的打印规则： 
//
// 
// 每一次操作时，打印机会用同一种颜色打印一个矩形的形状，每次打印会覆盖矩形对应格子里原本的颜色。 
// 一旦矩形根据上面的规则使用了一种颜色，那么 相同的颜色不能再被使用 。 
// 
//
// 给你一个初始没有颜色的 m x n 的矩形 targetGrid ，其中 targetGrid[row][col] 是位置 (row, col) 的颜色。
// 
//
// 如果你能按照上述规则打印出矩形 targetGrid ，请你返回 true ，否则返回 false 。 
//
// 
//
// 示例 1： 
//  1 1 1 1
//  1 2 2 1
//  1 2 2 1
//  1 1 1 1
//
// 输入：targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
//输出：true
// 
//
// 示例 2：  1: (0,3) (0,3). 3:(1,2)(2,3) 4:(2,3) (3,3) 5(3,3)(0,1)
// 1 1 1 1
// 1 1 3 3
// 1 1 3 4
// 5 5 1 4
// 输入：targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
//输出：true
// 1 2 1
// 2 1 2
// 1 2 1
// 示例 3： 
//
// 输入：targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
//输出：false
//解释：没有办法得到 targetGrid ，因为每一轮操作使用的颜色互不相同。 
//
// 示例 4：
// 1 1 1
// 3 1 3
// 输入：targetGrid = [[1,1,1],[3,1,3]]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == targetGrid.length 
// n == targetGrid[i].length 
// 1 <= m, n <= 60 
// 1 <= targetGrid[row][col] <= 60 
// 
//
// Related Topics 图 拓扑排序 数组 矩阵 👍 39 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.*;

public class StrangePrinterIi{

  /**
  * 1591
  * 奇怪的打印机 II
  * 
  * 
  *
  * 2022-11-10 15:14:26
  */  
  public static void main(String[] args) {
    Solution solution = new StrangePrinterIi().new Solution();
    solution.isPrintable(new int[][]{{1,1,1,1},{1,2,2,1},{1,2,2,1},{1,1,1,1}});
    //  1 1 1 1
  //    1 2 2 1
     // 1 2 2 1
     // 1 1 1 1
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

      /**
       * 思路, 和一开始错误的思路方法不一样
       * 这次的思路是从上往下,消除. 把一个颜色区域里没有其他颜色,或者包含了白色区域的, 涂白. 最后看看是不是都涂白了.
       * 即找出各个颜色边界, 然后判断边界里是否都是这个颜色或0, 如果是,区域里改为0.以此类推
       *
       * 具体放到代码里,把颜色,和对应的区域放到map里.在一个死循环中,一直判断这个区域是否满足条件,满足则涂色,然后从map里移除这个颜色.
       * 最后看看,如果map是empty了,返回true. 如果这一轮循环没有任何颜色被涂白,返回false
       *
       * 解答成功:
       * 	执行耗时:9 ms,击败了80.28% 的Java用户
       * 	内存消耗:41.8 MB,击败了42.25% 的Java用户
       */
      public boolean isPrintable(int[][] targetGrid){
          Map<Integer,int[]> yanses = new HashMap<>();
          //yanses[0] 中0表示颜色,1,2表示最小和最大横坐标,3,4表示最小和最大纵坐标
          int y = targetGrid.length;
          int x = targetGrid[0].length;
          for (int i = 0; i < y; i++) { //纵坐标
              for (int j = 0; j < x; j++) { //横坐标
                  //遍历,记录每个颜色区域的最大,最小坐标,以此框定区域
                  int ys = targetGrid[i][j];
                  int[] quyu =yanses.get(ys);
                  if (quyu == null){
                      quyu = new int[5];
                      quyu[0] = ys;
                      quyu[1] = quyu[2] = j;
                      quyu[3] = quyu[4] = i;
                  }
                  quyu[1] = Math.min(quyu[1],j); //横坐标
                  quyu[2] = Math.max(quyu[2],j);
                  quyu[3] = Math.min(quyu[3],i); //纵坐标
                  quyu[4] = Math.max(quyu[4],i);
                  yanses.put(ys,quyu);
              }
          }
          /*这里是一个坑,直接用map的remove,会因为边遍历边修改报并发修改异常, 同时如果直接用keyset也是,因为返回的是keys的视图,修改set也会直接修改map*/
          Set<Integer> keys = new HashSet<>(yanses.keySet());
          while(!keys.isEmpty()){
              int size = keys.size();
              //判断每个颜色区域里,是否都是自身颜色或白色
              out:for (int[] yanse : yanses.values()) {
                  for (int i = yanse[1]; i <= yanse[2] ; i++) { //横坐标
                      for (int j = yanse[3]; j <= yanse[4]; j++) { //纵坐标
                          if (targetGrid[j][i] != 0 &&
                                  targetGrid[j][i] != yanse[0]) {
                              //即既是白色,也不是自身颜色,跳过
                              continue out;
                          }
                      }
                  }
                  //边界内是既是白色,或自身颜色,涂白
                  for (int i = yanse[1]; i <= yanse[2] ; i++) { //横坐标
                      for (int j = yanse[3]; j <= yanse[4]; j++) { //纵坐标
                          targetGrid[j][i] = 0;
                      }
                  }
                  keys.remove(yanse[0]);
              }
              if (keys.size() == size){
                  //size没变,说明没满足条件的
                  return false;
              }
          }
        return true;
      }
  }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 这个思路失败了.
     * 简单来说, 是把这个一个个相互叠加的颜色块当成一条条等高线. 然后从下往上把颜色排序. 再从下往上在一个等大的区域涂色,看看最后结果是否一样.
     * 结果是在给出的几个例子中, 还是解答成功的. 但是在测试用例48*48的格子失败了.说明这个思路不严谨
     * @param targetGrid
     * @return
     */
    public boolean isPrintable(int[][] targetGrid) {
        //思路: 首先题目很好理解,但是怎么判断结果是否为true呢?
        // 1.转为等高线地图来看,我们把每个颜色对应的最大矩形区域计算出来
        // 2.我们对区域高度进行排序. 如果A包裹B,B>A,如果A和B有交集,判断交集是A颜色还是B颜色,如果是A,则A>B. 如果A和B不相交,两者相等
        // 3. 然后我们根据排好序,对矩阵遍历,填充
        // 4. 将填充后的图和原图比较
        Map<Integer,int[]> yanses = new HashMap<>();
        //yanses[0] 中0表示颜色,1,2表示最小和最大横坐标,3,4表示最小和最大纵坐标
        int y = targetGrid.length;
        int x = targetGrid[0].length;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                //遍历,记录每个颜色区域的最大,最小坐标,以此框定区域
                int ys = targetGrid[i][j];
                int[] quyu =yanses.get(ys);
                if (quyu == null){
                    quyu = new int[5];
                    quyu[0] = ys;
                    quyu[1] = quyu[2] = j;
                    quyu[3] = quyu[4] = i;
                }
                quyu[1] = Math.min(quyu[1],j);
                quyu[2] = Math.max(quyu[2],j);
                quyu[3] = Math.min(quyu[3],i);
                quyu[4] = Math.max(quyu[4],i);
                yanses.put(ys,quyu);
            }
        }
        //排除没有颜色的,放到list排序
        List<int[]> yanselist = new ArrayList<>();
        for (int[] yans : yanses.values()) {
            if(yans != null){
                yanselist.add(yans);
            }
        }
        yanselist.sort((a,b) -> {
            //复写排序规则,
            if (a[1] <= b[1]
                    && a[2] >= b[2]
                    && a[3] <= b[3]
                    && a[4] >= b[4]
            ){
                //此为a区域包括了b区域,按等高线理解,b>a
                return  -1;
            }else if (b[1] <= a[1]
                    && b[2] >= a[2]
                    && b[3] <= a[3]
                    && b[4] >= a[4]
            ){
                //此为b区域包括了a区域,按等高线理解,a>b
                return  1;
            }
            else if (a[1] > b[2] || a[2] < b[1]
                    || a[3] > b[4] || a[4] < b[3]
            ) {
                //不相交,相等
                return 0;
            }else{
                //此为相交的时候,看哪个在上面就看相交的那个角是不是a颜色,是则认为a在上
                // 1 1 1 0
                // 1 2 2 2
                // 1 2 2 2
                for (int i = 1; i <= 2; i++) { //纵坐标左右 x
                    for (int j = 3; j <= 4; j++) { //横坐标的上下限 y
                        if (b[i] >= a[1] && b[i] <= a[2]
                                && b[j] >= a[3] && b[j] <= a[4]
                        ){
                            //表示找到了这个在a区域相交的b的角,如果这个位置不等于它本身的颜色,我们就认为b被a盖住,即a>b
                            return b[0] != targetGrid[b[j]][b[i]] ? 1:-1;
                        }
                    }
                }
                return 1;
            }
        });
        //这个时候颜色区域已经排好序了,按这个序号填充
        int[][] newquyu = new int[targetGrid.length][targetGrid[0].length];
        for (int[] ints : yanselist) {
            int yanse = ints[0];
            int minx = ints[1];
            int maxx = ints[2];
            int miny = ints[3];
            int maxy = ints[4];
            for (int xx = minx; xx <= maxx; xx++) {
                for (int yy = miny; yy <= maxy; yy++) {
                    newquyu[yy][xx]=yanse;
                }
            }
        }
        //再比对
        for (int xx = 0; xx < targetGrid[0].length; xx++) {
            for (int yy = 0; yy < targetGrid.length; yy++) {
                if (targetGrid[yy][xx] != newquyu[yy][xx]){
                    return false;
                }
            }
        }
        return true;
    }

}