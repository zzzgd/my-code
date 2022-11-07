### 公式统计
#### 1.背包问题(dp)
- arr[]: 每个物品占用的大小
- dp[i][n]: 第i中物品(包括i之前的), 总量为n, 一共有多少种组合方式
```
dp[i][n] = dp[i-1][n] + dp[i][n-arr[i]]; 
```
#### 2. 0和1子序列(dp)
- end0: 以0结尾的序列数
- end1: 以1结尾的序列数
- has0: 字符是否有0
```
end1 += end0 + 1
end0 += end1
if 有0 then has0 = 1
```