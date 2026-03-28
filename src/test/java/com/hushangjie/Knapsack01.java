package com.hushangjie;

public class Knapsack01 {

    /* 0-1 背包：动态规划 */
    static int knapsackDP(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        // 初始化 dp 表
        int[][] dp = new int[n + 1][cap + 1];
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= cap; c++) {
                if (wgt[i - 1] > c) {
                    // 若超过背包容量，则不选物品 i
                    dp[i][c] = dp[i - 1][c];
                } else {
                    // 不选和选物品 i 这两种方案的较大值
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i - 1][c - wgt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[n][cap];
    }

    public static void main(String[] args) {
        int[] w = {2, 1, 3};
        int[] v = {4, 2, 3};
        int W = 4;

        int maxVal = knapsackDP(w, v, W);
        System.out.println("最大价值: " + maxVal);  // 输出最大价值: 6
    }
}
