//存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。 
//
// 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 
//ui 和 vi 在 nums 中相邻。 
//
// 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i]
//, nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。 
//
// 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。 
//
// 
//
// 示例 1： 
// 2   1 3   4
// 1,3 2 4,2 3
//输入：adjacentPairs = [[2,1],[3,4],[3,2]]
//输出：[1,2,3,4]
//解释：数组的所有相邻元素对都在 adjacentPairs 中。
//特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
// 
//
// 示例 2： 
//
// 
//输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
//输出：[-2,4,1,-3]
//解释：数组中可能存在负数。
//另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
// 
//
// 示例 3： 
//
// 
//输入：adjacentPairs = [[100000,-100000]]
//输出：[100000,-100000]
// 
//
// 
//
// 提示： 
//
// 
// nums.length == n 
// adjacentPairs.length == n - 1 
// adjacentPairs[i].length == 2 
// 2 <= n <= 10⁵ 
// -10⁵ <= nums[i], ui, vi <= 10⁵ 
// 题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums 
// 
//
// Related Topics 数组 哈希表 👍 118 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestoreTheArrayFromAdjacentPairs{

  /**
  * 1743
  * 从相邻元素对还原数组
  * 
  * 
  *
  * 2023-06-08 16:28:08
  */  
  public static void main(String[] args) {
    Solution solution = new RestoreTheArrayFromAdjacentPairs().new Solution();
      int[][] param = {{2,1},{3,4},{3,2}};
      solution.restoreArray(param);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        //因为是不同元素组成的, 所以一个值最多只能有2个相邻元素, 并且头尾两个元素肯定是只有1个相邻元素
        //先把它放到map,比如<3-4> ,分别放入3-4,和4-3两个entry
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] ints : adjacentPairs) {
            putMap(map, ints[0],ints[1] );
            putMap(map, ints[1],ints[0] );
        }
        int[] res = new int[adjacentPairs.length+1];
        //然后找到map中value只有1个的,即头和尾
        for (Map.Entry<Integer, List<Integer>> en : map.entrySet()) {
            if (en.getValue().size() == 1){
                res[0] = en.getKey();
                res[1] = en.getValue().get(0);
                //放到res数组后直接从map移除
                map.remove(en.getKey());
                break;
            }
        }

        for (int i = 2; i < res.length; i++) {
            List<Integer> vals = map.get(res[i - 1]);
            for (Integer val : vals) {
                //如果map有这个key说明没用过,没有说明已经用过了
                if(map.containsKey(val)){
                    res[i] = val;
                }
            }
            //要从map里移除,避免这个key重复使用
            map.remove(res[i-1]);
        }
        return res;
    }
      private void putMap(Map<Integer, List<Integer>> map, int key,int val) {
          List<Integer> vals = map.get(key);
          if(vals == null){
              vals = new ArrayList<>(2);
          }
          vals.add(val);
          map.put(key,vals);
      }
  }
//leetcode submit region end(Prohibit modification and deletion)

}