//请你在设计一个迭代器，在集成现有迭代器拥有的 hasNext 和 next 操作的基础上，还额外支持 peek 操作。 
//
// 实现 PeekingIterator 类： 
//
// 
// PeekingIterator(Iterator<int> nums) 使用指定整数迭代器 nums 初始化迭代器。 
// int next() 返回数组中的下一个元素，并将指针移动到下个元素处。 
// bool hasNext() 如果数组中存在下一个元素，返回 true ；否则，返回 false 。 
// int peek() 返回数组中的下一个元素，但 不 移动指针。 
// 
//
// 注意：每种语言可能有不同的构造函数和迭代器 Iterator，但均支持 int next() 和 boolean hasNext() 函数。 
//
// 
//
// 示例 1： 
//
// 
//输入：
//["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
//[[[1, 2, 3]], [], [], [], [], []]
//输出：
//[null, 1, 2, 2, 3, false]
//
//解释：
//PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
//peekingIterator.next();    // 返回 1 ，指针移动到下一个元素 [1,2,3]
//peekingIterator.peek();    // 返回 2 ，指针未发生移动 [1,2,3]
//peekingIterator.next();    // 返回 2 ，指针移动到下一个元素 [1,2,3]
//peekingIterator.next();    // 返回 3 ，指针移动到下一个元素 [1,2,3]
//peekingIterator.hasNext(); // 返回 False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 1 <= nums[i] <= 1000 
// 对 next 和 peek 的调用均有效 
// next、hasNext 和 peek 最多调用 1000 次 
// 
//
// 
//
// 进阶：你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？ 
//
// Related Topics 设计 数组 迭代器 👍 174 👎 0


  
package com.zgd.leetcode.editor.cn;

import java.util.Iterator;

public class PeekingIterator {

  /**
  * 284
  * 顶端迭代器
  * 
  * 
  *
  * 2022-10-31 16:07:16
  */  
  public static void main(String[] args) {

  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class Node<T>{
	  T t;
	  Node<T> next;
  }

	/**
	 * 提交测试的时候记得把2去掉,因为类名重复了所以需要加个2
	 */
class PeekingIterator2 implements Iterator<Integer> {
	//使用单向链表来实现
	Node<Integer> head = null;
	Node<Integer> cursor ;
	public PeekingIterator2(Iterator<Integer> iterator) {
	    // initialize any member here.
		Node<Integer> cur ;
		Node<Integer> pre = null;
		while(iterator.hasNext()){
			cur = new Node<>();
			if (head == null){
				head = cur;
				cursor = head;
			}
			cur.t = iterator.next();
			if (pre != null){
				pre.next = cur;
			}
			pre = cur;
		}
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return cursor == null ? null :cursor.t;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		if (cursor == null){
			return null;
		}
	    Integer r = cursor.t;
		cursor = cursor.next;
		return r;
	}
	
	@Override
	public boolean hasNext() {
	    return cursor != null;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}