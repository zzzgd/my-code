笔记:
这个题的主要思路是 两两台阶之间的差值, 和固定值之间的关系, 以及跳出循环的判断条件.

首先跳出循环的条件, 不是遍历台阶(一开始理想当然的这样做了), 而是判断当前台阶数不大于最大台阶数.  
当前台阶数i, 当满足下个台阶能迈上去(小于固定值x)的时候才+1  
如果不满足, 需要 `(下个台阶高度 - 当前台阶高度 - 1)/固定值` 来计算需要插入的台阶数. 注意这里要-1