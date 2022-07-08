//实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。 
//
// 注意：本题相对原题稍作改动 
//
// 示例： 
//
// 输入： 1->2->3->4->5 和 k = 2
//输出： 4 
//
// 说明： 
//
// 给定的 k 保证是有效的。 
// Related Topics 链表 双指针 👍 110 👎 0


  
package com.zgd.leetcode.editor.cn;

public class KthNodeFromEndOfListLcci{

  /**
  * 面试题 02.02
  * 返回倒数第 k 个节点
  * 
  * 
  *
  * 2022-07-08 17:36:40
  */  
  public static void main(String[] args) {
    Solution solution = new KthNodeFromEndOfListLcci().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int kthToLast(ListNode head, int k) {
        last(head,k);
        return ans;
    }
    private int ans = 0;

  /**
   * 递归, 先一层层找到尾节点,再一层层回来同时判断是否满足条件
   * @param head
   * @param k
   * @return
   */
    private int last(ListNode head, int k){
      if (head == null){
        return 1;
      }
      int n = last(head.next,k);
      if (n == k){
        ans =  head.val;
      }return n+1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

   public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }


}