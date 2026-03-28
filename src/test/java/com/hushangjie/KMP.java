package com.hushangjie;

public class KMP {

    // 构建部分匹配表（即 lps 数组）
    public static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0; // 前一个最长前后缀长度
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // 回退
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    // KMP 主匹配算法
    public static int kmpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] lps = computeLPSArray(pattern);

        int i = 0; // text 指针
        int j = 0; // pattern 指针

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                return i - j; // 匹配成功，返回起始索引
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1; // 未匹配到
    }

    // 测试示例
    public static void main(String[] args) {
        String text = "abababcd";
        String pattern = "ababd";

        int index = kmpSearch(text, pattern);
        if (index != -1) {
            System.out.println("匹配成功，位置：" + index);
        } else {
            System.out.println("未找到匹配");
        }
    }
}
