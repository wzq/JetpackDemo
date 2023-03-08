package com.wzq.sample;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by wzq on 2023/2/24
 * <p>
 * top 100
 */
public class Test {

    //01
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] rt = new int[2];
        for (int i = 0; i < nums.length; i++) {
            Integer s = map.get(target - nums[i]);
            if (s == null) {
                map.put(nums[i], i);
            } else {
                rt[0] = i;
                rt[1] = s;
                break;
            }
        }
        return rt;
    }

    //02
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n = new ListNode(-1);
        ListNode r = n;

        int last = 0;
        do {
            int a;
            if (l1 == null) {
                a = 0;
            } else {
                a = l1.val;
                l1 = l1.next;
            }

            int b;
            if (l2 == null) {
                b = 0;
            } else {
                b = l2.val;
                l2 = l2.next;
            }

            int s = a + b + last;
            last = s > 9 ? 1 : 0;
            s = s % 10;

            r.next = l1 == null ? l2 : l1;
        } while (l1 != null || l2 != null);


        if (last > 0) r.next = new ListNode(last);

        return n.next;
    }

    //19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fake = new ListNode(-1, head);
        ListNode first = head;
        ListNode second = fake;

        for (int i = 0; i < n; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return fake.next;
    }

    //20
    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        char[] ss = s.toCharArray();

        Map<Character, Character> pair = new HashMap<>();
        pair.put('(', ')');
        pair.put('[', ']');
        pair.put('{', '}');


        for (char c : ss) {
            Character check = pair.get(stack.peek());
            if (check != null && check == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    //daily
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        List<List<Integer>> ret = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] n : items1) {
            map.put(n[0], n[1]);
        }
        for (int[] n : items2) {
            int v = map.getOrDefault(n[0], 0) + n[1];
            map.put(n[0], v);
        }

        for (Map.Entry<Integer, Integer> key : map.entrySet()) {
            List<Integer> l = new ArrayList<>(2);
            l.add(key.getKey());
            l.add(key.getValue());
            ret.add(l);
        }

        Collections.sort(ret, Comparator.comparingInt(o -> o.get(0)));
        return ret;
    }


    //全排列 无重复 回溯
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<>();
        boolean[] mark = new boolean[nums.length];
        backtrack(nums.length, nums, output, mark, res, 0);

        return res;
    }

    public void backtrack(int n, int[] nums, List<Integer> output, boolean[] mark, List<List<Integer>> res, int first) {
        if (first == n) {
            //出口
            res.add(new ArrayList<>(output));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!mark[i]) {
                output.add(nums[i]);
                mark[i] = true;
                backtrack(n, nums, output, mark, res, first + 1);
                mark[i] = false;
                output.remove(output.size() - 1);
            }
        }
    }

    //全排列 有重复 回溯
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;

        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        List<Integer> path = new ArrayList<>();

        dfs(len, res, path, 0, nums, new boolean[len]);

        return res;
    }

    private void dfs(int len, List<List<Integer>> res, List<Integer> path, int depth, int[] nums, boolean[] mark) {
        if (len == depth) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            //去重复： 不等于前面的元素，且前面的元素没有使用过
            if (mark[i] || (i > 0 && !mark[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            mark[i] = true;
            path.add(nums[i]);
            dfs(len, res, path, depth + 1, nums, mark);
            path.remove(path.size() - 1);
            mark[i] = false;
        }

    }

    //接雨水
    public int trap(int[] height) {
        int s1 = 0, s2 = 0, high = 0, sum = 0;

        int left = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] >= left) {
                left = height[i];
            }
            s1 += left - height[i];

            sum += height[i];
            high = Math.max(height[i], high);
        }


        int right = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] >= right) {
                right = height[i];
            }
            s2 += right - height[i];
        }


        return s1 + s2 + sum - high * height.length;
    }


    @org.junit.Test
    public void test() {
//        System.out.println(permuteUnique(new int[]{1, 1, 3}));

//        int r = trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
//        System.out.println(r);

        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public int maxArea(int[] height) {
        int width = height.length - 1;
        int area = 0, l = 0, r = height.length - 1;

        while (l < r) {
            int s;
            if (height[l] > height[r]) {
                s = height[r] * width;
                r--;
            } else {
                s = height[l] * width;
                l++;
            }
            area = Math.max(area, s);

            width--;
        }

        return area;
    }
}
