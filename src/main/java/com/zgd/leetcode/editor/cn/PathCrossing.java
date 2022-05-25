//给你一个字符串 path，其中 path[i] 的值可以是 'N'、'S'、'E' 或者 'W'，分别表示向北、向南、向东、向西移动一个单位。 
//
// 你从二维平面上的原点 (0, 0) 处开始出发，按 path 所指示的路径行走。 
//
// 如果路径在任何位置上与自身相交，也就是走到之前已经走过的位置，请返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：path = "NES"
//输出：false 
//解释：该路径没有在任何位置相交。 
//
// 示例 2： 
//
// 
//
// 
//输入：path = "NESWW"
//输出：true
//解释：该路径经过原点两次。 
//
// 
//
// 提示： 
//
// 
// 1 <= path.length <= 10⁴ 
// path[i] 为 'N'、'S'、'E' 或 'W' 
// 
// Related Topics 哈希表 字符串 👍 36 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathCrossing{

  /**
  * 1496
  * 判断路径是否相交
  * 
  * 
  *
  * 2022-05-25 17:27:19
  */  
  public static void main(String[] args) {
    Solution solution = new PathCrossing().new Solution();
  }

  //四个路径


  //leetcode submit region begin(Prohibit modification and deletion)


  class Solution {
    public boolean isPathCrossing(String path) {
      Map<Character,int[]> map = new HashMap<>();
        map.put('E',new int[]{1,0});
        map.put('S',new int[]{0,-1});
        map.put('W',new int[]{-1,0});
        map.put('N',new int[]{0,1});

        //用一个List记录走过的路径
      int[] start = new int[]{0,0};
      List<String> route = new ArrayList<>();
      route.add("0:0");
      for (char c : path.toCharArray()) {
        int[] wasd = map.get(c);
        //路径相加
        String s = (start[0] = (start[0] + wasd[0])) + ":" + (start[1] = (start[1] + wasd[1]));
        if (route.contains(s)){
          return true;
        }
        route.add(s);
      }
      return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
