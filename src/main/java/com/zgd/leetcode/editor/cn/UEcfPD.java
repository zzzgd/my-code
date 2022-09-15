//欢迎各位勇者来到力扣城，城内设有烹饪锅供勇者制作料理，为自己恢复状态。
//
//勇者背包内共有编号为 `0 ~ 4` 的五种食材，其中 `materials[j]` 表示第 `j` 种食材的数量。通过这些食材可以制作若干料理，`
//cookbooks[i][j]` 表示制作第 `i` 种料理需要第 `j` 种食材的数量，而 `attribute[i] = [x,y]` 表示第 `i` 道料理的美味
//度 `x` 和饱腹感 `y`。
//
//在饱腹感不小于 `limit` 的情况下，请返回勇者可获得的最大美味度。如果无法满足饱腹感要求，则返回 `-1`。
//
//**注意：**
//- 每种料理只能制作一次。
//
//**示例 1：**
//
//> 输入：`materials = [3,2,4,1,2]`
//> `cookbooks = [[1,1,0,1,2],[2,1,4,0,0],[3,2,4,1,0]]`
//> `attribute = [[3,2],[2,4],[7,6]]`
//> `limit = 5`
//>
//> 输出：`7`
//>
//> 解释：
//> 食材数量可以满足以下两种方案：
//> 方案一：制作料理 0 和料理 1，可获得饱腹感 2+4、美味度 3+2
//> 方案二：仅制作料理 2， 可饱腹感为 6、美味度为 7
//> 因此在满足饱腹感的要求下，可获得最高美味度 7
//
//**示例 2：**
//
//> 输入：`materials = [10,10,10,10,10]`
//> `cookbooks = [[1,1,1,1,1],[3,3,3,3,3],[10,10,10,10,10]]`
//> `attribute = [[5,5],[6,6],[10,10]]`
//> `limit = 1`
//>
//> 输出：`11`
//>
//> 解释：通过制作料理 0 和 1，可满足饱腹感，并获得最高美味度 11
//
//**提示：**
//+ `materials.length == 5`
//+ `1 <= cookbooks.length == attribute.length <= 8`
//+ `cookbooks[i].length == 5`
//+ `attribute[i].length == 2`
//+ `0 <= materials[i], cookbooks[i][j], attribute[i][j] <= 20`
//+ `1 <= limit <= 100`
//
// Related Topics 位运算 数组 回溯 枚举 👍 10 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.Arrays;

public class UEcfPD{

  /**
  * LCP 51
  * 烹饪料理
  * 
  * 
  *
  * 2022-09-15 16:55:29
  */  
  public static void main(String[] args) {
    Solution solution = new UEcfPD().new Solution();
    solution.perfectMenu(new int[]{10,10,10,10,10},new int[][]{{1,1,1,1,1},{3,3,3,3,3},{10,10,10,10,10}},new int[][]{{5,5},{6,6},{10,10}},1);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        //回溯法
        huisu(new int[cookbooks.length],materials,cookbooks,attribute,limit,0);
        return maxkougan;
    }

    private int maxkougan = -1;

      /**
       * @param num 每个料理做的数量
       * @param sucai
       * @param cookbooks
       * @param attribute
       * @param limit
       * @param i 当前做到第几个料理
       */
    private void huisu(int[] num,int[] sucai,int[][] cookbooks, int[][] attribute, int limit,int i){
        if (i == cookbooks.length) {
            //已经判断完了所有料理,检查结果
            //判断是否满足limit
            int baofu = 0;
            int kougan = 0;
            for (int ii = 0; ii < num.length; ii++) {
                int[] att = attribute[ii];
                int numii = num[ii];
                kougan += att[0]*numii;
                baofu+= att[1]*numii;
            }
            if (baofu >= limit){
                if (kougan > 0 && kougan > maxkougan){
                    System.out.println(Arrays.toString(num) + "  "+maxkougan);
                    maxkougan = kougan;
                }
            }
            return;
        }

        for (int j = i; j < cookbooks.length; j++) {
            //遍历剩下的料理
            //本次料理做多少份 // 题目写着每种料理只能做1次. 则可做可不做. 遍历0和1,0表示不作,1表示做
            for (int k = 0; k < 2; k++) {
                //一份一份增加,直到素材不够

                boolean b = (k == 0) || hasEnoughSucai(sucai, k, cookbooks[j]);
                if (b){
                    //素材够,减去素材,判断下一份料理
                    useSucai(sucai,-k,cookbooks[j]);
                    num[j]=k;
                    //判断下一份料理
                    huisu(num,sucai,cookbooks,attribute,limit,j+1);
                    //回溯,恢复素材
                    useSucai(sucai,k,cookbooks[j]);
                    num[j]=0;

                }
            }

        }
    }

      /**判断素材是否足够这次料理
       * @param sucai 剩余素材
       * @param thisNum 这次做几个
       * @param cookbooks 这次料理的料理材料要求
       * @return
       */
      private boolean hasEnoughSucai(int[] sucai, int thisNum, int[] cookbooks) {
          for (int i = 0; i < cookbooks.length; i++) {
              if (cookbooks[i] * thisNum > sucai[i]){
                  return false;
              }
          }
          return true;
      }

      private void useSucai(int[] sucai, int thisNum, int[] cookbooks) {
          for (int i = 0; i < cookbooks.length; i++) {
              sucai[i] = sucai[i] + thisNum * cookbooks[i];
          }
      }
  }
//leetcode submit region end(Prohibit modification and deletion)

}