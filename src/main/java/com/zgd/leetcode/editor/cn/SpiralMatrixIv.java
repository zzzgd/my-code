//给你两个整数：m 和 n ，表示矩阵的维数。 
//
// 另给你一个整数链表的头节点 head 。 
//
// 请你生成一个大小为 m x n 的螺旋矩阵，矩阵包含链表中的所有整数。链表中的整数从矩阵 左上角 开始、顺时针 按 螺旋 顺序填充。如果还存在剩余的空格，
//则用 -1 填充。 
//
// 返回生成的矩阵。 
//
// 
//
// 示例 1： 
// 输入：m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
//输出：[[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
//解释：上图展示了链表中的整数在矩阵中是如何排布的。
//注意，矩阵中剩下的空格用 -1 填充。
// 
//
// 示例 2： 
// 输入：m = 1, n = 4, head = [0,1,2]
//输出：[[0,1,2,-1]]
//解释：上图展示了链表中的整数在矩阵中是如何从左到右排布的。 
//注意，矩阵中剩下的空格用 -1 填充。 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 10⁵ 
// 1 <= m * n <= 10⁵ 
// 链表中节点数目在范围 [1, m * n] 内 
// 0 <= Node.val <= 1000 
// 
//
// Related Topics 数组 链表 矩阵 模拟 👍 23 👎 0


  
package com.zgd.leetcode.editor.cn;

public class SpiralMatrixIv{

  /**
  * 2326
  * 螺旋矩阵 IV
  * 
  * 
  *
  * 2023-07-31 16:34:25
  */  
  public static void main(String[] args) {
    Solution solution = new SpiralMatrixIv().new Solution();
    ListNode head = new ListNode(-1,
            new ListNode(3,
            new ListNode(0,
            new ListNode(2,
            new ListNode(6,
            new ListNode(8,
            new ListNode(1,
            new ListNode(7,
            new ListNode(9,
            new ListNode(4,
            new ListNode(2,
            new ListNode(5,
            new ListNode(5,
            new ListNode(0,
                         null
                    ))))))))))))));
    solution.spiralMatrix(3,5,head);
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * 不能想象得到,填充矩阵4个边的时候, 从上开始分别填充,第一(下标0)圈 n,m-1,n-1,m-2. 然后第二(下标1)圈n-2,m-3,n-3,m-4, 第n圈就是n-2*(q), m-2*q-1, n-2*q-1, m-2*q-2
     * 了解了四个边的填充数量以后, 就是一个圈一个循环,一个循环4次计算, 将队列的后面一个元素设置为死循环方便取数
     * @param m
     * @param n
     * @param head
     * @return
     */
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        //对链表最后处理下,设置一个虚拟节点-1然后闭环
        ListNode tail = head;
        while(tail.next != null){
            tail = tail.next;
        }
        ListNode node1 = new ListNode(-1, null);
        ListNode node2 = new ListNode(-1, node1);
        node1.next=node2;
        tail.next = node1;

        //第n圈
        int quan = 0;
        //总个数
        int total = m*n;
        //坐标x,y
        int x = 0,y = 0;
        int[][] res = new int[m][n];
        //虚假的头结点,方便取数
        ListNode node = new ListNode(-1,head);
        int num = 0;
        while(num < total){
            //上
            int a=x,b=y;
            for (int i = 0; i < n-quan*2 && num <total; i++) {
                node = node.next;
                res[y][x=a+i] = node.val;
                num++;
            }
            //右
            for (int i = 1; i <= m-quan*2-1 && num <total; i++) {
                node = node.next;
                res[y=b+i][x]=node.val;
                num++;
            }
            a=x;b=y;
            //下
            for (int i = 1; i <= n-quan*2-1 && num <total; i++) {
                node = node.next;
                res[y][x=a-i]=node.val;
                num++;
            }
            //左
            for (int i = 1; i <= m-quan*2-2 && num <total; i++) {
                node = node.next;
                res[y=b-i][x]=node.val;
                num++;
            }
            //这里x加1,因为一圈过后,如果还有第二圈, 第二圈肯定也是回到左上角, 起点是从x开始的, 右移1位
            x++;
            quan++;
        }
        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
  public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}