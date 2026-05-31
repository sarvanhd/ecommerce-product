package com.sarvan.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }
}
@AllArgsConstructor
@Data
class Technology {
    private String techName;
    private boolean isBackend;
}
@AllArgsConstructor
@Data
class Employee {
    private String employeeName;
    private List<Technology> technologies;
}

public class Test {
    static  Map<Character, Integer> mapping = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);
    static  Map<Integer, Character> mapping1 = Map.of(1, 'I',  5,'V',  10,'X',  50,'L',  100,'C',  500,'D',  1000,'M');
    static List<Integer> values= mapping1.keySet().stream().toList();
    public static void main(String[] args) {
       /* int[] arr = {1,2,5};
        System.out.println(coinChange(arr, 11));//3
        System.out.println(coinChangeRec(new int[]{2}, 3));//-1
        System.out.println(coinChangeRec(new int[]{1}, 0));//0
        System.out.println(coinChangeRec(new int[]{186,419,83,408}, 6249));//20
        *//*System.out.println(isValid1("()"));
        System.out.println(isValid1("()[]{}"));
        System.out.println(isValid1("[(){()}]"));
        System.out.println(isValid1("([)]"));*//*
        int[] ret = new int[]{1,1,2};
        int[] ret1 = new int[]{6,6,6,6};
        System.out.println(("abc".toCharArray().equals("abc".toCharArray())));
        Map map = new HashMap();
        List<Integer> ints = new ArrayList<>();
        ints.stream().collect(Collectors.summingInt(Integer::intValue));*/
        /*System.out.println(removeDuplicates(ret));
        System.out.println(Arrays.toString(Arrays.copyOfRange(new int[]{1,1,2}, 1,3)));*/
        /*System.out.println(removeElement(new int[]{3,2,2,3}, 3));
        System.out.println(removeElement(new int[] {0,1,2,2,3,0,4,2}, 2));*/
        //System.out.println("ab" + String.format("%-" + 3 + "s", "") + "ab");

        //fullJustify(new String[]{"a"}, 2).forEach((str)-> System.out.println("'" + str + "'"));
        //System.out.println("'" + justifyContent("Science is what we", 20, false) + ",");
        //System.out.println(palindrome("sasbas"));
        //System.out.println(combinationsSum(new int[] {2,3,5,6,7}, 7));
        //System.out.println(combinationsSum(new int[] {1,2,5}, 11));
        /*System.out.println(findFib(6));//13
        System.out.println(coinChange(new int[]{1,2,5}, 11));
        System.out.println(coinChangeRec(new int[]{1,2,5}, 11));
        System.out.println(coinChangeRec(new int[]{2}, 3));*/
        //System.out.println(permutations1(new int[] {1,2,3}))
        //System.out.println(climbStairs(3));
        //System.out.println(minGridPath(3,3, new int[][]{{5,1,6},{3,8,2},{11,5,2}}));
        //System.out.println(nQueen(4));
        //System.out.println(findMaxSumSubArray(3, new int[]{2, 1, 5, 1, 3, 2}));
        //System.out.println(findMaxSumSubArray(1, new int[]{2, 3, 4, 1, 5}));
       /* System.out.println(merge(new ArrayList<>(){{
            add(new Interval(1,4));
            add(new Interval(2,5));
            add(new Interval(7,9));
        }}));
        System.out.println(merge(new ArrayList<>(){{
            add(new Interval(6,7));
            add(new Interval(2,4));
            add(new Interval(5,9));
        }}));
        System.out.println(merge(new ArrayList<>(){{
            add(new Interval(1,4));
            add(new Interval(2,6));
            add(new Interval(3,5));
        }}));*/
        //System.out.println(longestSubstringWithoutRepeat("substring"));
        //System.out.println(bfs(new int[]{12,31,42,54,66,73,89,93, 99}, 89));
        //System.out.println(letterCombinations("23"));
        //System.out.println(letterCombinations("234"));
        //System.out.println(letterCombinations("2"));
        /*List<String> stringList = Arrays.asList("red","blue", "red", "red", "blue", "white");
        List<Integer> intList = Arrays.asList(1,4,2,6,7,4,11);
        System.out.println("foobarfoobar".replaceFirst("foobar", ""));



        String abc = "aaabbbdeeff";
        Map<Character, Long> counts = abc.chars().mapToObj((i) -> (char)i).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        //System.out.println(stringList.stream().collect(Collectors.joining(", ")));
        System.out.println(intList.stream().collect(Collectors.summingInt(s -> s)));*/
        /*System.out.println(findSubstring("foobarfoobar", new String[]{"foo","bar"}));
        System.out.println(String.format("%" + 5 + "s", "").replace(' ', '*'));
        List<Integer> list = new ArrayList<>();
        int[] arr1 = new int[]{1,2,3,4};*/
        //System.out.println(coinChangeRec(new int[]{1,2,5}, 11));
        //System.out.println(combinationsSum(new int[] {2,3,5,6,7}, 7));
        /*LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.offerFirst(4);
        list.remove();
        System.out.println(list);
        System.out.println(1 << 4);
        System.out.println(Arrays.stream("abc abc cbd".split(" ")).reduce((accl, curr) -> {
           if(!accl.endsWith(curr)) {
               accl +=  " " + curr;
           }
           return accl;
        }).get());*/
        List<Employee> list = new ArrayList<>() {{
            add(new Employee("emp1", new ArrayList<>(){{
                add(new Technology("java", true));
                add(new Technology("react", false));
            }}));
            add(new Employee("emp2", new ArrayList<>(){{
                add(new Technology("python", true));
                add(new Technology("angular", false));
            }}));
            add(new Employee("emp3", new ArrayList<>(){{
                add(new Technology("java", true));
                add(new Technology("angular", false));
            }}));
        }};
        Set<String> list1 = list.stream().flatMap((emp) -> emp.getTechnologies().stream()).map(Technology::getTechName).collect(Collectors.toSet());
        System.out.println(list1);
        List<Integer> list3 = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60));
    int n = list3.size();
        for (int i = n - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                list3.remove(i);
            }
        }

        System.out.println(list3);
    }
    public static List<Integer> findSubstring(String s, String[] words) {
        List<String> combinations = new ArrayList<>();
        List<Integer> ret = new ArrayList<>();
        boolean[] flag = new boolean[words.length];
        findComb(words, "", combinations, flag, 0);
        for(String str: combinations) {
            int idx = s.indexOf(str);
            while(idx >= 0){
                if(!ret.contains(idx)) {
                    ret.add(idx);
                }
                idx = s.indexOf(str, idx + 1);
            }
        }
        return ret;
    }
    static void findComb(String[] words,String temp,List<String> combinations, boolean[] flag, int counter) {
        if(counter == words.length){
            combinations.add(temp);
            return;
        }
        for(int i=0;i<words.length; i++) {
            if(!flag[i]) {
                flag[i] = true;
                findComb(words, temp + words[i], combinations, flag, counter + 1);
                flag[i] = false;
            }
        }
    }
    static Map<Character, List<String>> phoneMap = new HashMap<>(){{
        put('2', new ArrayList<>(){{
            add("a");
            add("b");
            add("c");
        }});
        put('3', new ArrayList<>(){{
            add("d");
            add("e");
            add("f");
        }});
        put('4', new ArrayList<>(){{
            add("g");
            add("h");
            add("i");
        }});
        put('5', new ArrayList<>(){{
            add("j");
            add("k");
            add("l");
        }});
        put('6', new ArrayList<>(){{
            add("m");
            add("n");
            add("o");
        }});
        put('7', new ArrayList<>(){{
            add("p");
            add("q");
            add("r");
            add("s");
        }});
        put('8', new ArrayList<>(){{
            add("t");
            add("u");
            add("v");
        }});
        put('9', new ArrayList<>(){{
            add("w");
            add("x");
            add("y");
            add("z");
        }});

        
    }};

    public static List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<>();
        findDigitComb(0, "", digits,ret);
        return ret;
    }

    public static void findDigitComb(int idx, String temp, String digits, List<String> ret) {
        if(temp.length() == digits.length()) {
            ret.add(temp);
            return;
        }
        for(String letter: phoneMap.get(digits.charAt(idx))) {
            findDigitComb(idx +1, temp + letter, digits, ret);
        }
    }

    public static int bfs(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while(left <= right) {
            int mid = (right + left) /2;
            if(nums[mid] == target)
                return mid;
            if(nums[mid] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return -1;
    }

    public static Integer longestSubstringWithoutRepeat(String s) {
        int length = 0, ind=0;
        // Your code goes here
        String temp = "";
        while (ind < s.length()) {
            String c = s.charAt(ind) + "";
            if(temp.contains(c)) {
                length = Math.max(length, temp.length());
                temp = temp.substring(temp.indexOf(c) + 1);
            }
            temp += c;
            ind++;
        }
        return length;
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> mergedIntervals = new ArrayList<>();
        // TODO: Write your code here
        for(Interval inter: intervals) {
            if(mergedIntervals.size() == 0) {
                mergedIntervals.add(inter);
                continue;
            }
            Optional<Interval> interval = mergedIntervals.stream().filter((elem) -> {
                if((elem.start < inter.start && inter.start < elem.end) || (inter.start < elem.start && elem.start < inter.end))
                    return true;
                return false;
            }).findFirst();
            if(interval.isPresent()) {
                Interval exis = interval.get();
                if(exis.start > inter.start)
                    exis.start = inter.start;
                if(exis.end < inter.end)
                    exis.end = inter.end;
            } else {
                mergedIntervals.add(inter);
            }

        }
        return mergedIntervals;
    }

    public static int findMaxSumSubArray(int k, int[] arr) {
        // TODO: Write your code here
        int max = -1;
        for(int i=0;i<arr.length;i++) {
            int sum = 0;
                for (int j = 0; j < k && i+j < arr.length; j++) {
                    sum += arr[i + j];
                }
            max = Math.max(max,sum);
        }
        return max;
    }

    public static List<List<Integer>> searchTriplets(int[] nums) {
        Set<List<Integer>> triplets = new HashSet<>();
        // TODO: Write your code here
        findTriplets(0, triplets, new ArrayList<>(), nums);
        return triplets.stream().toList();
    }
    static void findTriplets(int ind, Set<List<Integer>> triplets, List<Integer> obj, int[] arr) {
        if(obj.size() == 3 || ind >= arr.length){
            if(obj.size() == 3) {
                int temp = obj.stream().collect(Collectors.summingInt(Integer::intValue));
                if(temp == 0) {
                    List tem = new ArrayList(obj);
                    Collections.sort(tem);
                    triplets.add(tem);
                }
            }
            return;
        }
        obj.add(arr[ind]);
        findTriplets(ind+1, triplets, obj, arr);
        obj.remove(obj.size() -1);
        findTriplets(ind+1, triplets, obj, arr);
    }
    static List<List<String>> nQueen(int n) {
        List<List<String>> ret = new ArrayList<>();
        char[][] obj = new char[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(obj[i], '.');
        }
        nQueenRecur(n, 0, obj, ret);
        return ret;
    }
    static boolean isValidQueen(int i,int j,char[][] obj, int n) {
        int tempj = j, tempi = i;
        while(tempj>=0){
            if(obj[i][tempj] == 'Q')
                return false;
            tempj--;
        }
        tempj = j;
        while(tempj>=0 && tempi>=0){
            if(obj[tempi][tempj] == 'Q')
                return false;
            tempi--;
            tempj--;
        }
        tempi = i;
        tempj = j;
        while(tempj>=0 && tempi< n){
            if(obj[tempi][tempj] == 'Q')
                return false;
            tempi++;
            tempj--;
        }
        return true;
    }
    static void nQueenRecur(int n, int col, char[][] obj, List<List<String>> ret) {
        if(col == n){
            List<String> temp = new ArrayList<>();
            for(int i=0;i<obj.length; i++) {
                String str = "";
                for(int j=0;j< obj[i].length; j++) {
                    str = String.join(",", str, obj[i][j] + "");
                }
                temp.add(str);
            }
            ret.add(temp);
            return;
        }
        for(int i=0;i<n; i++) {
                if(isValidQueen(i, col, obj, n)) {
                    obj[i][col] = 'Q';
                    nQueenRecur(n, col +1, obj, ret);
                    obj[i][col] = '.';
            }
        }
    }
    static int minGridPath(int m, int n, int[][] vals) {
        return minGridPathRec(m-1,n-1, vals);
    }
    static int minGridPathRec(int m, int n, int[][] vals) {
        if(m == 0 && n == 0) {
            return vals[m][n];
        }
        if(m < 0 || n < 0) return 100;
        int up = vals[m][n] + minGridPathRec(m-1,n, vals);
        int left = vals[m][n] + minGridPathRec(m,n-1, vals);
        return Math.min(up, left);
    }
    static void climb(List<List<Integer>> ret, List<Integer> obj,int n, int sum) {
        if(sum >= n) {
            if(sum == n)
            ret.add(new ArrayList<>(obj));
            return;
        }
        for(int i=1;i<=2; i++) {
            obj.add(i);
            climb(ret, obj, n, sum + i);
            obj.remove(obj.size() -1);
        }

    }
    static List<List<Integer>> climbStairs(int n) {
        List<List<Integer>> ret = new ArrayList<>();
        climb(ret, new ArrayList<>(), n, 0);
        return ret;
    }

    static void perm(int[] arr, List<Integer> ds, List<List<Integer>> ret, boolean[] flag) {
        if(ds.size() == arr.length) {
            ret.add(new ArrayList<>(ds));
            return;
        }
        for(int i=0; i< arr.length;i++) {
            if(!flag[i]) {
                ds.add(arr[i]);
                flag[i] = true;
                perm(arr, ds, ret, flag);
                ds.remove(ds.size() -1);
                flag[i] = false;
            }
        }
    }

    static List<List<Integer>> permutations(int[] arr) {
        List<List<Integer>> ret = new ArrayList<>();
        boolean[] flag = new boolean[arr.length];
        perm(arr, new ArrayList<>(), ret, flag);
        return ret;
    }
    //[ab, cd, ef]
    static void perm1(int[] arr, List<List<Integer>> ret, int index) {
        if(index == arr.length) {
            List<Integer> temp = Arrays.stream(arr).boxed().toList();
            ret.add(temp);
            return;
        }
        for(int i=index; i< arr.length; i++) {
            swap(index, i, arr);
            perm1(arr, ret, index + 1);
            swap(index, i, arr);
        }
    }
    static void swap(int i, int j, int[] nums) {
        int temp= nums[i];
        nums[i] = nums[j];
        nums[j]=temp;
    }
    static List<List<Integer>> permutations1(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        perm1(nums,  ret, 0);
        return ret;
    }

    static int findFib (int n) {
        /*if(n <= 1) {
            return n;
        }
        return findFib(n-1) + findFib(n-2);*/
        int[] ret = new int[n +1];
        ret[0] = 0;
        ret[1] = 1;
        int curr=1, prev =0;
        for(int i=2;i<n+1;i++) {
            int temp = curr;
            curr = curr + prev;
            prev = temp;
            ret[i] = ret[i-1] + ret[i -2];
        }
        //return ret[n];
        return curr;
    }

    static List<List<Integer>> combinationsSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        findCombinations(0, nums, target, ret, new ArrayList<>());
        return ret;
    }
    static List<List<Integer>> combinationsSum1(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        findCombinations1(0, nums, target, ret, new ArrayList<>());
        return ret;
    }

    static int coinChangeRec(int[] coins, int target) {
        List<List<Integer>> combinations = combinationsSum1(coins, target);
        if(combinations.size() == 0)
            return -1;
        Integer minSize = combinations.stream().map((list1) -> list1.size()).min(Integer::compare).orElse(-1);
        return minSize;
    }

    static void findCombinations(int ind, int[] nums, int target, List<List<Integer>> ret, List<Integer> obj) {
        if(ind == nums.length) {
            if(target == 0) {
                ret.add(new ArrayList<>(obj));
            }
            return;
        }
        if(nums[ind] <= target) {
            obj.add(nums[ind]);
            findCombinations(ind +1, nums, target-nums[ind], ret, obj);
            obj.remove(obj.size() -1);
        }
        findCombinations(ind +1, nums, target, ret, obj);
    }
    static void findCombinations1(int ind, int[] nums, int target, List<List<Integer>> ret, List<Integer> obj) {
        if(ind == nums.length) {
            if(target == 0) {
                ret.add(new ArrayList<>(obj));
            }
            return;
        }
        if(nums[ind] <= target) {
            obj.add(nums[ind]);
            findCombinations1(ind, nums, target-nums[ind], ret, obj);
            obj.remove(obj.size() -1);
        }
        findCombinations1(ind +1, nums, target, ret, obj);
    }

    static boolean palindrome(String s) {
        for(int i=0, j=s.length()-1; i<j;i++,j--) {
            if(s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }

    static List<List<Integer>> threeSum(int[] nums) {
        Map<String, List<Integer>> ret = new HashMap<>();
        for(int i=0;i<nums.length; i++) {
            for(int j=i+1;j<nums.length; j++) {
                for (int k = j+ 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> add = new ArrayList<>();
                        add.add(nums[i]);
                        add.add(nums[j]);
                        add.add(nums[k]);
                        Collections.sort(add);
                        String key = add.stream().map(Object::toString)
                                .collect(Collectors.joining(","));
                        if(!ret.containsKey(key))
                        ret.put(key, add);
                    }
                }
            }

        }
        return ret.values().stream().toList();
    }

    static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ret = new ArrayList<>();
        for(int i=0, ctr=0;i<words.length; i++) {
            String prev = ret.size()-1 == ctr? ret.get(ctr): "";
            String curr = prev + (prev.isEmpty()? "": " ") + words[i];
            if(curr.length() <= maxWidth) {
                curr = (i==words.length-1)? justifyContent(curr, maxWidth, true): curr;
                if(ret.isEmpty())ret.add(ctr, curr); else ret.set(ctr, curr);
            } else {
                ret.set(ctr, justifyContent(prev, maxWidth, false));
                ret.add(i==words.length-1? justifyContent(words[i], maxWidth, true): words[i]);
                ctr++;
            }
        }
        return ret;
    }

    static String justifyContent(String s, int maxWidth, boolean isLast) {
        if(s.length() == maxWidth)
            return s;
        String[] elems = s.split(" ");
        int spaces = maxWidth - s.replaceAll(" ", "").length();
        if(elems.length == 1 || isLast) {
            return s + String.format("%-" + (maxWidth-s.length()) + "s", "");
        }
        int div = spaces / (elems.length-1);
        int mod = spaces % (elems.length-1);
        String ret = "";
        for(int i=0;i< elems.length;i++){
            ret += elems[i];
            if(i < elems.length -1) {
                ret += String.format("%-" + div + "s", "");
                spaces -= div;
                if(mod > 0) {
                    ret += String.format("%-" + 1 + "s", "");
                    mod -= 1;
                }
            }
        }
        return ret;
    }

    static int[] plusOne(int[] digits) {
        int rem = 1;
        int lastIndex = digits.length-1;
        int lastAdd = digits[lastIndex] + 1;
        if(lastAdd < 10) {
            digits[lastIndex]= lastAdd;
            return digits;
        }else {
            digits[lastIndex] = 0;
        }
        for(int i=lastIndex-1;i>= 0;i--) {
            if(rem != 0) {
                if(digits[i] + rem < 10) {
                    digits[i] += rem;
                    return digits;
                } else {
                    digits[i] = 0;
                }
            }
        }
        if(rem !=0) {
            int[] retArr = new int[digits.length + 1];
            retArr[0] = rem;
            System.arraycopy(digits, 0, retArr, 1, digits.length);
            return retArr;
        }
        return digits;

    }
    /*static int coinChange(int[] coins, int amount) {
        if(amount == 0)
            return 0;
        int ret =-1;
        Integer[] arr = IntStream.of(coins).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        for(int j=0; j< arr.length;j++) {
            int count = 0, i=j, sum=0;
            while (!(sum == amount || i == arr.length)) {
                if (arr[i] + sum > amount) {
                    i++;
                } else {
                    sum += arr[i];
                    count++;
                }
            }
            if(sum == amount && (ret == -1 || count < ret)){
                ret = count;
            }
        }
        return ret;
    }*/

    static int strStr(String haystack, String needle) {
        //return haystack.indexOf(needle);
        for(int i=0;i<haystack.length(); i++){
            if (needle.startsWith(haystack.charAt(i) + "") && haystack.substring(i, i+needle.length()).equals(needle)) {
                return i;
            }
        }
        return 0;

    }

    static int coinChange(int[] coins, int amount) {
        int[] minCoins = new int[amount + 1];
        Arrays.fill(minCoins, amount + 1);
        minCoins[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    minCoins[i] = Math.min(minCoins[i], 1 + minCoins[i - coins[j]]);
                }
            }
        }

        return minCoins[amount] != amount + 1 ? minCoins[amount] : -1;
    }

    static boolean isValid(String s) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
                ret.append(s.charAt(i));
            } else {
                String otherChar = "";
                switch (s.charAt(i)) {
                    case '}':
                        otherChar = "{";
                        break;
                    case ')':
                        otherChar = "(";
                        break;
                    case ']':
                        otherChar = "[";
                        break;
                }
                int index = ret.lastIndexOf(otherChar);
                if(index < 0)
                    return false;
                ret.deleteCharAt(index);
            }
        }
        return ret.toString().length() ==0;

    }

    static boolean isValid1(String s) {
        String ret = "";
        for(int i=0;i<s.length();i++) {
            String temp = String.valueOf(s.charAt(i));
            if("{([".contains(temp)) {
                ret += temp;
            } else {
                String reverseChar = "";
                switch (s.charAt(i)) {
                    case '}':
                        reverseChar = "{";
                        break;
                    case ')':
                        reverseChar = "(";
                        break;
                    case ']':
                        reverseChar = "[";
                        break;
                }
                if(!ret.endsWith(reverseChar))
                    return false;
                ret = ret.substring(0, ret.length() -1);
            }
        }
        return ret.length() == 0;
    }

    static int removeDuplicates(int[] nums) {
        int[] ret = new int[nums.length];
        int ctr = 0;
        Arrays.fill(ret, -101);
        for (int i=0;i< nums.length;i++) {
            if(ctr == 0 || nums[i] != nums[ctr -1]) {
                nums[ctr] = nums[i];
                ctr++;
            }
        }
        for(int i=0;i< ctr;i++)
        nums[i]=ret[i];
        System.out.println(Arrays.toString(nums));
        return ctr;
    }
    static int removeElement(int[] nums, int val) {
        int ctr = 0;
        for (int i=0;i<nums.length;i++) {
            if(nums[i] != val)
                nums[ctr++] = nums[i];
        }
        return ctr;
    }

}

class LRUCache {

    Map<Integer, Integer> cache;
    LinkedList<Integer> lru;
    int capacity;
    PriorityQueue pq;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.lru = new LinkedList<>();
    }

    public int get(int key) {
        if(!cache.containsKey(key))
            return -1;
        lru.remove(Integer.valueOf(key));
        lru.addFirst(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
        } else {
            if(cache.size() == capacity){
                Integer removed = lru.removeLast();
                cache.remove(removed);
            }
            cache.put(key, value);
            lru.addFirst(key);
        }

    }
}
