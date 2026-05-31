package com.sarvan.userservice;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test1 {

    public static void main(String[] args) {

        //System.out.println(kClosest(new int[]{1,2,3,4,5}, 4, 3));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
        ArrayBlockingQueue<String> st = null;

    }

    public static List<Integer> kClosest(int[] nums, Integer k, Integer target) {
        PriorityQueue<int[]> que = new PriorityQueue<>((a,b) -> Integer.compare(b[0], a[0]));
        for(int num: nums) {
            int diff = Math.abs(target - num);
            if(que.size() < k) {
                que.offer(new int[]{-diff, num});
            } else if(diff < -(que.peek()[0])) {
                que.poll();
                que.offer(new int[]{-diff, num});
            }
        }
        int[] ret = new int[k];
        while(!que.isEmpty()) {
            System.out.println(Arrays.toString(que.peek()));
            ret[--k] = que.poll()[1];
        }
        Stack<String> st = new Stack<>();
        LinkedList<Integer> list2= new LinkedList<>();
        st.search("a");
        Map b = null;
        Set set = new LinkedHashSet();
        return Arrays.stream(ret).boxed().collect(Collectors.toList());
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> ret = new ArrayList<>();
        int start=0, end = k-1;
        while(end < nums.length) {
            int max = Integer.MIN_VALUE;
            for(int i=start; i<=end; i++) {
                max = Math.max(max, nums[i]);
            }
            ret.add(max);
            start++;
            end++;
        }
        return ret.stream().mapToInt(i->i).toArray();
    }

    void fn() {
        triangleNumber(null);
    }

     static Integer triangleNumber(int[] heights) {
        Arrays.sort(heights);
        return recFn(0, new ArrayList<>(), heights);
    }
    static Integer recFn(int idx, List<Integer> arr, int[] heights) {

        if(arr.size() == 3 || idx == heights.length) {
            if(arr.size() == 3 && arr.get(0) + arr.get(1) > arr.get(2)) {
                System.out.println(arr);
                return 1;
            } else {
                return 0;
            }
        }
        arr.add(heights[idx]);
        int pick = recFn(idx + 1, arr, heights);
        arr.remove(arr.size() -1);
        int ntPick = recFn(idx + 1, arr, heights);
        return pick + ntPick;
    }
    static int triangleNumber1(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for(int i= nums.length-1; i>1;i--) {
            int left = 0, right = i-1;
            while(left < right) {
                if(nums[left] + nums[right] > nums[i]) {
                    count+= right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }

}
