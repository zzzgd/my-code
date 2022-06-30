//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 500] 内 
// -100 <= Node.val <= 100 
// 0 <= k <= 2 * 10⁹ 
// 
// Related Topics 链表 双指针 👍 793 👎 0



package com.zgd.leetcode.editor.cn;

import java.util.List;

public class RotateList{

  /**
  * 61
  * 旋转链表
  *
  *
  *
  * 2022-06-30 14:34:51
  */
  public static void main(String[] args) {
    Solution solution = new RotateList().new Solution();
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
    public ListNode rotateRight(ListNode head, int k) {
      //首先确定k的位置,根据移动后的新链表头部,倒推k可以知道,新的链表的尾部在旧链表(长度n)的第n-k%n 的节点,我们需要在这里切一刀
      //1. 确定链表长度
      int n = 0;
      ListNode node = head;
      ListNode tail = null;
      while (node != null){
        n++;
        //找到尾结点
        tail = node;
        node = node.next;
      }
      if(n ==0 || k == 0){
        return head;
      }
      int i = n - k%n ;
      if(i == n){
        //没动,直接返回
        return head;
      }
      ListNode newnode = null;
      node = head;
      //重新遍历
      int idx = 0;
      while (node != null){
        idx++;
        if (idx == i && newnode == null){
          //找到了这个新链表的尾结点,同样也是在这节点后需要切分的地方,比如1,2,3,4,5 在3后切分
          newnode = node.next;
          node.next = null;

          //将尾节点和原头结点连接起来
          tail.next = head;
          return newnode;
        }
        node = node.next;
      }
     return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}