<p>给你一个字符串&nbsp;<code>num</code> 和一个整数&nbsp;<code>k</code> 。其中，<code>num</code> 表示一个很大的整数，字符串中的每个字符依次对应整数上的各个 <strong>数位</strong> 。</p>

<p>你可以交换这个整数相邻数位的数字 <strong>最多</strong>&nbsp;<code>k</code>&nbsp;次。</p>

<p>请你返回你能得到的最小整数，并以字符串形式返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/06/17/q4_1.jpg" style="height:40px; width:500px" /></p>

<pre>
<strong>输入：</strong>num = "4321", k = 4
<strong>输出：</strong>"1342"
<strong>解释：</strong>4321 通过 4 次交换相邻数位得到最小整数的步骤如上图所示。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = "100", k = 1
<strong>输出：</strong>"010"
<strong>解释：</strong>输出可以包含前导 0 ，但输入保证不会有前导 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>num = "36789", k = 1000
<strong>输出：</strong>"36789"
<strong>解释：</strong>不需要做任何交换。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>num = "22", k = 22
<strong>输出：</strong>"22"
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>num = "9438957234785635408", k = 23
<strong>输出：</strong>"0345989723478563548"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= num.length &lt;= 30000</code></li> 
 <li><code>num</code>&nbsp;只包含&nbsp;<strong>数字</strong>&nbsp;且不含有<strong>&nbsp;前导 0&nbsp;</strong>。</li> 
 <li><code>1 &lt;= k &lt;= 10^9</code></li> 
</ul>

<div><div>Related Topics</div><div><li>贪心</li><li>树状数组</li><li>线段树</li><li>字符串</li></div></div><br><div><li>👍 81</li><li>👎 0</li></div>