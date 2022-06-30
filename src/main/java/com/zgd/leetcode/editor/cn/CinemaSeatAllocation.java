//
//
// 如上图所示，电影院的观影厅中有 n 行座位，行编号从 1 到 n ，且每一行内总共有 10 个座位，列编号从 1 到 10 。 
//
// 给你数组 reservedSeats ，包含所有已经被预约了的座位。比如说，researvedSeats[i]=[3,8] ，它表示第 3 行第 8 个座
//位被预约了。 
//
// 请你返回 最多能安排多少个 4 人家庭 。4 人家庭要占据 同一行内连续 的 4 个座位。隔着过道的座位（比方说 [3,3] 和 [3,4]）不是连续的座
//位，但是如果你可以将 4 人家庭拆成过道两边各坐 2 人，这样子是允许的。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
//输出：4
//解释：上图所示是最优的安排方案，总共可以安排 4 个家庭。蓝色的叉表示被预约的座位，橙色的连续座位表示一个 4 人家庭。
// 
//
// 示例 2： 
//
// 输入：n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
//输出：2
// 
//
// 示例 3： 
//
// 输入：n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10^9 
// 1 <= reservedSeats.length <= min(10*n, 10^4) 
// reservedSeats[i].length == 2 
// 1 <= reservedSeats[i][0] <= n 
// 1 <= reservedSeats[i][1] <= 10 
// 所有 reservedSeats[i] 都是互不相同的。 
// 
// Related Topics 贪心 位运算 数组 哈希表 👍 52 👎 0


package com.zgd.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class CinemaSeatAllocation {

  /**
   * 1386 安排电影院座位 采用位运算来标记已经占用的作为. 这里有几个坑, 1: 位运算 中, 加减的优先级 高于 位运算的, 所以要记得加括号
   * <p>
   * <p>
   * 2022-06-27 17:42:58
   */
  public static void main(String[] args) {
    Solution solution = new CinemaSeatAllocation().new Solution();
    solution.maxNumberOfFamilies(3, new int[][]{{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}});
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    int m_mark = 0b1110000111;
    int l_mark = 0b1000011111;
    int r_mark = 0b1111100001;

    /**
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
      //1. 思路,首先要知道只有四种排列方式 家庭座位只有[左,右],[左],[右],[中]四个情况
      //2. 只要占用的座位不在2~9之间, 就肯定是+2的家庭位

      //用map而不是直接用数组,是因为数组直接开辟一整块空间会内存不够
      int num = 0;
      Map<Integer, Integer> map = new HashMap<>();
      for (int[] reservedSeat : reservedSeats) {
        if (reservedSeat[1] == 1 || reservedSeat[1] == 10) {
          continue;
        }
        Integer mark = map.getOrDefault(reservedSeat[0], 0);
        //压缩状态, 吧每行的座位,以位运算 合并到一起
        mark |= 1 << (10 - reservedSeat[1]);
        map.put(reservedSeat[0], mark);
      }
      //map的数量都是在2~9会影响到家庭位的
      num = (n - map.size()) * 2;
      for (Map.Entry<Integer, Integer> en : map.entrySet()) {
        Integer seatmark = en.getValue();
        if (((seatmark | l_mark) == l_mark)
                ||
                ((seatmark | r_mark) == r_mark)
                ||
                ((seatmark | m_mark) == m_mark)
        ) {
          num++;
        }
      }
      return num;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)


  /**
   * 居然超过内存限制, 因为用n作为长度设置数组,当n很大的时候就会需要很大的内存空间
   *
   * @param n
   * @param reservedSeats
   * @return
   */
  public int maxNumberOfFamilies2(int n, int[][] reservedSeats) {

    //1. 思路 采用位运算, 家庭座位只有[左,右],[左],[右],[中]四个情况
    //2. 先将座位信息,遍历,以bit位的形式保存, 然后再和上面四种情况位运算比对

    //初始化一个数组,数组长度为座位行数. 数组保存的是一个整数,但是我们将其当做bitmap
    int[] arr = new int[n];
    for (int i = 0; i < reservedSeats.length; i++) {
      //遍历已占用的座位,转成位运算标记
      int[] seat = reservedSeats[i];
      //0位是行,1位是列
      //seat里是从1开始 所以要减去1从0开始
      int row = seat[0] - 1;
      arr[row] = arr[row] | 1 << (10 - seat[1]);
    }

    //遍历标记后,再遍历取数据
    int num = 0;
    for (int i : arr) {
//        System.out.println(Integer.toBinaryString(i));
      //1. 中间两个的情况,从2~9
      if (checkHasSeat(2, 9, i)) {
        //中间两个
        num += 2;
      } else if (checkHasSeat(2, 5, i) || checkHasSeat(6, 9, i)) {
        //2.第二种情况,2~5,或者6~9
        num += 1;
      } else if (checkHasSeat(4, 7, i)) {
        //3.第三种情况 4~7
        num += 1;
      }

    }
    return num;
  }

  private boolean checkHasSeat(int start, int end, int mark) {
    int t = mark;
    //形成 类似 011111111的结构                和标记0000010010 进行与运算
//      System.out.println(Integer.toBinaryString((1<<(end - start  + 1))-1));
//      System.out.println(Integer.toBinaryString(t >> (10-end)));
    if ((((1 << (end - start + 1)) - 1) & (t >> (10 - end))) == 0) {
      return true;
    }
    return false;
  }
}