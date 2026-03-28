package com.hushangjie;

import java.util.*;

public class SegmentTreeTest {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9, 11};

        SegmentTree segTree = new SegmentTree(nums);

        // 查询区间和 [1, 3]：3 + 5 + 7 = 15
        System.out.println("Query [1, 3] = " + segTree.query(1, 3));

        // 更新：将 nums[2] 的值从 5 改为 10
        segTree.update(2, 10);

        // 再次查询区间和 [1, 3]：3 + 10 + 7 = 20
        System.out.println("After update, Query [1, 3] = " + segTree.query(1, 3));
    }
}


class SegmentTree {
    private int[] tree;  // 存储线段树的数组
    private int n;       // 原始数组的长度

    /**
     * 构造函数，初始化线段树
     * @param nums 原始数组
     */
    public SegmentTree(int[] nums) {
        n = nums.length;
        // 通常开4倍大小，足以存储完整树结构
        tree = new int[4 * n];
        build(nums, 0, n - 1, 1); // 构建线段树，从根节点编号1开始
    }

    /**
     * 构建线段树
     * @param nums 原始数组
     * @param l 当前区间左边界
     * @param r 当前区间右边界
     * @param node 当前树节点编号
     */
    private void build(int[] nums, int l, int r, int node) {
        if (l == r) {
            // 叶子节点直接存原数组值
            tree[node] = nums[l];
            return;
        }

        int mid = (l + r) / 2;
        // 递归构建左子树和右子树
        build(nums, l, mid, 2 * node);       // 左子节点编号 2 * node
        build(nums, mid + 1, r, 2 * node + 1); // 右子节点编号 2 * node + 1

        // 当前节点保存左右区间的和
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    /**
     * 对外提供的查询接口，查询区间 [L, R] 的和
     */
    public int query(int L, int R) {
        return query(1, 0, n - 1, L, R);
    }

    /**
     * 查询函数
     * @param node 当前树节点编号
     * @param l 当前节点表示的区间左边界
     * @param r 当前节点表示的区间右边界
     * @param L 查询区间左边界
     * @param R 查询区间右边界
     * @return 区间和
     */
    private int query(int node, int l, int r, int L, int R) {
        if (R < l || L > r) {
            // 查询区间和当前区间无交集，返回 0
            return 0;
        }
        if (L <= l && r <= R) {
            // 当前节点区间完全包含在查询区间内，直接返回
            return tree[node];
        }

        // 否则部分重叠，递归左右子树
        int mid = (l + r) / 2;
        int leftSum = query(2 * node, l, mid, L, R);
        int rightSum = query(2 * node + 1, mid + 1, r, L, R);
        return leftSum + rightSum;
    }

    /**
     * 对外提供的更新接口，将 index 位置的值改为 val
     */
    public void update(int index, int val) {
        update(1, 0, n - 1, index, val);
    }

    /**
     * 更新函数
     * @param node 当前节点编号
     * @param l 当前节点区间左边界
     * @param r 当前节点区间右边界
     * @param index 要更新的索引
     * @param val 新值
     */
    private void update(int node, int l, int r, int index, int val) {
        if (l == r) {
            // 找到要更新的叶子节点
            tree[node] = val;
            return;
        }

        int mid = (l + r) / 2;
        if (index <= mid) {
            // 更新左子树
            update(2 * node, l, mid, index, val);
        } else {
            // 更新右子树
            update(2 * node + 1, mid + 1, r, index, val);
        }

        // 更新当前节点的值
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }
}
