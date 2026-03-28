package com.hushangjie;

public class BITree {
    private int[] tree; // 树状数组
    private int n;

    // 构造函数，初始化树状数组
    public BITree(int size) {
        n = size;
        tree = new int[n + 1]; // 从 1 开始使用，0 不用
    }

    // 工具函数：返回 x 的最低位 1 对应的值
    private int lowbit(int x) {
        return x & -x;
    }

    // 单点更新：给下标 i 处的元素加上 delta
    public void update(int i, int delta) {
        while (i <= n) {
            tree[i] += delta;
            i += lowbit(i); // 跳到下一个受影响的节点
        }
    }

    // 查询前缀和：nums[1] + ... + nums[i]
    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= lowbit(i); // 跳到父节点
        }
        return sum;
    }

    // 查询区间和：nums[l] + ... + nums[r]
    public int queryRange(int l, int r) {
        return query(r) - query(l - 1);
    }

    public static void main(String[] args) {
        // 初始化原数组，注意第 0 位不用（占位）
        int[] nums = {0, 1, 3, 5, 7, 9, 11}; // 实际数据从索引 1 开始，共 6 个元素

        // 创建树状数组实例
        BITree bit = new BITree(nums.length - 1);

        // 初始化树状数组：把每个值加进去
        for (int i = 1; i < nums.length; i++) {
            bit.update(i, nums[i]);
        }

        // 查询前缀和
        System.out.println("前缀和 nums[1..4] = " + bit.query(4));       // 1+3+5+7=16
        System.out.println("区间和 nums[3..5] = " + bit.queryRange(3, 5)); // 5+7+9=21

        // 更新：将 nums[3] 加 2 → 5→7
        bit.update(3, 2);

        // 更新后的查询
        System.out.println("更新后 nums[1..4] 的前缀和 = " + bit.query(4)); // 1+3+7+7=18
        System.out.println("更新后 nums[3..5] 的区间和 = " + bit.queryRange(3, 5)); // 7+7+9=23
    }
}
