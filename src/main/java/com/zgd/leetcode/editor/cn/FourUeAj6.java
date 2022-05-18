//给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。 
//
// 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。 
//
// 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。 
//
// 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。 
//
// 
//
// 示例 1： 
//
// 
// 
//
// 
//输入：head = [3,4,1], insertVal = 2
//输出：[3,4,1,2]
//解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后
//，整个列表如上图所示，最后返回节点 3 。
//
//
// 
//
// 示例 2： 
//
// 
//输入：head = [], insertVal = 1
//输出：[1]
//解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
// 
//
// 示例 3： 
//
// 
//输入：head = [1], insertVal = 0
//输出：[1,0]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= Number of Nodes <= 5 * 10^4 
// -10^6 <= Node.val <= 10^6 
// -10^6 <= insertVal <= 10^6 
// 
//
// 
//
// 注意：本题与主站 708 题相同： https://leetcode-cn.com/problems/insert-into-a-sorted-
//circular-linked-list/ 
// Related Topics 链表 👍 44 👎 0


  
package com.zgd.leetcode.editor.cn;

import org.w3c.dom.Node;

public class FourUeAj6{

  /**
  * 剑指 Offer II 029
  * 排序的循环链表
   * 2,4,6 插入1或7 找不到前<x<后的, 只能插入这个循环的6->2断开点
   * 4,6,2 插入3或5, 找得到前<x<后, 插入两者之间
   * 3,3,3 插入1或4  【最容易忽略第三种情况】
  */  
  public static void main(String[] args) {
    Solution solution = new FourUeAj6().new Solution();
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
      //三种情况:
      Node node = new Node(insertVal);
      if(head == null){
        //这里要注意, 要保证循环
        node.next = node;
        return node;
      }

      if (head.next ==null){
        node.next = head;
        head.next = node;
        return head;
      }
      //遍历
      Node cur = null;
      Node next = head.next;
      while (cur != head){
        if (cur == null){
          cur = head;
        }
        //第一种情况, 比最大值更大,或比最小值更小    2,4,6 插入1或7 找不到前<x<后的, 只能插入这个循环的6->2断开点
        if (cur.val > next.val){
          //此为断点,cur是最大值,next是最小值
          if (insertVal > cur.val || insertVal < next.val){
            //插入
            cur.next = node;
            node.next = next;
            return head;
          }
        }
        //第二种情况  正好有合适的 4,6,2 插入3或5, 找得到前<x<后, 插入两者之间
        if (cur.val <= insertVal && next.val >= insertVal){
          //插入
          cur.next = node;
          node.next = next;
          return head;
        }
        //忘记考虑第三种情况, 重复的值构成的链表 3,3,3 插入1或4

        cur = next;
        next = cur.next;
      }
      //如果循环的两种情况都不满足,那就是重复的值构成的
      //直接插入head后
      cur.next = node;
      node.next = next;
      return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _next) {
      val = _val;
      next = _next;
    }
  }
}
