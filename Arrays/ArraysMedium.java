public class ArraysMedium{
    

/// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.HashMap;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Main {
    private static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
        System.out.println("");
    }
    private static void reverse(int[] arr, int l, int r){
        while(l < r){
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            
            l++;
            r--;
        }
    }
    private static void swap(int[] arr, int l, int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
    public static void main(String[] args) {
        System.out.println("Start small. Ship something.");
        
        int[] num1 = {1, 6, 2, 10, 3};
        int k = 7;
        System.out.println("1. Two Sum : " );
        printArray(twoSum(num1, k));
        System.out.println("---------- STATE HASHING (HASHMAP) ------------");
        
        int[] num2 = {1, 0, 2, 1, 0};
        System.out.println("2. Three Pointers : " );
        threeSum(num2);
        printArray(num2);
        System.out.println("------------- Three Pointer (Dutch National Flag) ------------");
        
        int[] num3 = {7, 0, 0, 1, 7, 7, 2, 7, 7};
        System.out.println("3. Majority Element I :" + majorityElement(num3));
        System.out.println("----------- MOORE'S VOTING ALGORITHM ---------------");
        
        int[] num4 = {2, 3, 5, -2, 7, -4};
        System.out.println("4. Kadane's Algo : " + kadane(num4));
        System.out.println(" --------------- KADANE's ALGORITHM (CARRY-FORWARD LOGIC) ----------------");
        
        
        int[] num5 = {10, 7, 5, 8, 11, 9};
        System.out.println("5. Best time to buy and sell stocks: "  + buyAndSell(num5));
        
        System.out.println("------------------ STATE TRACKING (SINGLE PASS) ---------------");
        
        int[] num6 = {2, 4, 5, -1, -3, -4};
        System.out.println("6. Rearrange array elements by sign :");
        printArray(reArrange(num6));
        System.out.println("------------- TWO POINTERS (INDEX TRACKING) ------------");
        
        int[] num7 = {1, 2, 3};
        System.out.println("7. Next Permutation : ");
        nextPermutation(num7);
        printArray(num7);
        System.out.println(" -------- THREE STEP SUFFIX MANIPULATION (NARAYANA PANDITA's ALGORITHM) -----------");
        
        int[] num8 = {1, 2, 5, 3, 1, 2};
        System.out.println(" 8. Leaders in an array : " + leaders(num8));
        System.out.println(" ------ Reverse Traversal (Right-Left-Scan)");
        
        
        
        int[] num9 = {100, 4, 200, 1, 3, 2};
        System.out.println(" 9. Longest Consecutive Sequence in an Array :" + longestCons(num9));
        System.out.println(" ------- Intelligent Sequence Building -------------");    
        
        int[][] num10 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        matrixZeros(num10);
        System.out.println("10. Set Matrix Zeros : ");
        printArray(num10[0]);
        printArray(num10[1]);
        printArray(num10[2]);
        
        
        int[][] num11 = {{1,2,3},{4,5,6},{7,8,9}};
        rotate90(num11);
        System.out.println("11. Rotate 90 degree : ");
        printArray(num11[0]);
        printArray(num11[1]);
        printArray(num11[2]);
        
        int[][] num12 = {{1, 2, 3}, {4 ,5 ,6}, {7, 8, 9}};
        System.out.println("12. Spiral Manner : " + spiral(num12));
        System.out.println("----------- FOUR BOUNDARIES (SHRINKING WALL) --------------");
        
        int[] num13 = {1, 1, 1};
        System.out.println("13. Count Subarrays with Given sum :" + countSubArray(num13, 2));
        System.out.println("---------- PREFIX SUM WITH FREQUENCY CACHING (HASHMAP) ------------");
        
        
        
    }
    private static int[] twoSum(int[] arr, int k){
        
        HashMap<Integer, Integer> hash = new HashMap<>();
        
        for(int i = 0; i < arr.length; i++){
            
            int remainder = k - arr[i];
            
            if(remainder != arr[i] && hash.containsKey(remainder)){
                return new int[]{hash.get(remainder), i};
            }
            
            hash.put(arr[i], i);
        }
        
        return new int[] {-1, -1};
    }
    private static void threeSum(int[] arr){
        if(arr.length == 0) return;
        
        int low = 0;
        int mid = 0;
        int high = arr.length -1;
        
        while(mid <= high){
            
            if(arr[mid] == 0){
                swap(arr, low, mid);
                low++;
                mid++;
            }else if(arr[mid] == 1){
                mid++;
            }else if(arr[mid] == 2){
                swap(arr, mid, high);
                high--;
            }
        }
    }
    private static int majorityElement(int[] arr){
        if(arr.length == 0) return -1;
        
        int count = 0;
        int candidate = 0;
        
        //Step 1: Selecting the candidate
        for(int i = 0; i < arr.length; i++){
            if(count == 0){
                candidate = arr[i];
            }
            count += (arr[i] == candidate) ? 1 : -1;
        }
        
        //Step 2: Cross Checking
        count = 0;
        for(int num : arr){
            count += (num == candidate) ? 1 : 0;
        }

        int threshold = arr.length / 2;
        if(count > threshold){
            return candidate;
        }
        return -1;
    }
    private static int kadane(int[] arr){
        
        int maxSum = arr[0];
        int currentSum = 0;
        
        for(int i = 0; i < arr.length; i++){
            
            currentSum += arr[i];
            
            maxSum = (currentSum > maxSum) ? currentSum : maxSum;
            
            if(currentSum < 0) currentSum = 0;
        }
        
        return maxSum;
    }
    private static int buyAndSell(int[] arr){
        
        int minSoFar = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;
        
        for(int i = 0; i < arr.length; i++){
            
            minSoFar = Math.min(minSoFar, arr[i]);
            
            int profit = (arr[i] - minSoFar);
            
            maxProfit = profit > maxProfit ? profit : maxProfit;
        }
        
        return maxProfit;
    }
    private static int[] reArrange(int[] arr){
        
        int[] result = new int[arr.length];
        
        int posIndex = 0;
        int negIndex = 1;
        
        for(int i = 0; i < arr.length; i++){
            
            if(arr[i] > 0){
                result[posIndex] = arr[i];
                posIndex=posIndex+2;
            }else{
                result[negIndex] = arr[i];
                negIndex=negIndex+2;
            }
        }
        
        return result;
    }
    private static void nextPermutation(int[] arr){
        
        int n = arr.length;
        
        //Step 1: Find the break point
        int i = n-2;
        while(i >=0 && arr[i] >= arr[i+1]){
            i--;
        }
        
        //Step 2: Find the swap element and swap
        if(i >= 0){
            int j = n-1;
            while(i >= 0 && arr[j] <= arr[i]){
                j--;
            }
            swap(arr, i, j);
        }
        
        //Step 3: Reverse the elements
        reverse(arr, i+1, n-1);
    }
    private static List<Integer> leaders(int[] arr){
        
        List<Integer> result = new ArrayList<>();
        
        int pointer = arr.length - 1;
        result.add(arr[pointer]);
        
        int maxSoFar = arr[pointer];
        
        while(pointer >= 0){
            if(arr[pointer] > maxSoFar){
                result.add(arr[pointer]);
                maxSoFar = arr[pointer];
            }
            pointer--;
        }
        
        Collections.reverse(result);
        
        return result;
    }
    private static int longestCons(int[] arr){
        
        HashSet<Integer> hash = new HashSet<>();
        
        //Step 1: Put all the values into the hash
        for(int num : arr){
            hash.add(num);
        }
        
        int longest = 0;
        
        //Step 2: Looping through
        for(int i = 0; i < arr.length; i++){
            
            if(!hash.contains(arr[i] -1)){
                
                int max = 1;
                
                while(hash.contains(arr[i] + max)){
                    max++;
                }
                
                longest = Math.max(longest, max);
            }
        }
        
        return longest;
    }
    private static void matrixZeros(int[][] arr){
        
        int n = arr.length;
        int m = arr[0].length;
        boolean isFirstRow = false;
        boolean isFirstCol = false;
        
        //Step 1: Marking the first row and col
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                
                int val = arr[i][j];
                
                if(val == 0){
                    
                    if(i == 0) isFirstRow = true;
                    if(j == 0) isFirstCol = true;
                    
                    arr[i][0] = 0;
                    arr[0][j] = 0;
                }
            }
        }
        
        //Step 2: Filling the remaining rows as 0
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                
                if(arr[i][0] == 0|| arr[0][j] == 0){
                    arr[i][j] = 0;
                }
            }
        }
        
        //Step 3: Fill the first and last row if applicable
        if(isFirstRow){
            for(int j = 0; j < m; j++){
                arr[0][j] = 0;
            }
        }
        
        if(isFirstCol){
            for(int i = 0; i < n; i++){
                arr[i][0] = 0;
            }
        }
    }
    private static void rotate90(int[][] arr){
        
        int n = arr.length;
        
        //Step 1: Traverse
        for(int i = 0; i < arr.length; i++){
            for(int j = i+1; j < arr[i].length; j++){
                
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        
        //Step 2: Reverse
        for(int i = 0; i < n; i++){
            reverse(arr[i], 0, n-1);
        }
    }
    private static List<Integer> spiral(int[][] arr){
        List<Integer> result = new ArrayList<>();
        if(arr.length == 0 ) return result;
        
        int n = arr.length;
        int m = arr[0].length;
        
        int top = 0;
        int bottom = n -1;
        int left = 0;
        int right = m-1;
        
        while(left <= right && top <= bottom){
            
            //1. Left to right
            for(int i = left; i <= right; i++){
                result.add(arr[top][i]);
            }
            top++;
            
            //2. Top to bottom
            for(int i = top; i <= bottom; i++){
                result.add(arr[i][right]);
            }
            right--;
            
            //3. Right to left
            if(top <= bottom){
                for(int i = right; i >= left; i--){
                    result.add(arr[bottom][i]);
                }
                bottom--;
            }
            
            //4. Bottom to Top
            if(left <= right){
                for(int i = bottom; i >= top; i--){
                    result.add(arr[i][left]);
                }
                left++;
            }
        }
        
        return result;
    }
    private static int countSubArray(int[] arr, int k){
        
        int count = 0;
        int currentSum = 0;
        
        HashMap<Integer, Integer> hash = new HashMap<>();
        hash.put(0, 1);
        
        for(int i = 0; i < arr.length; i++){
            
            currentSum += arr[i];
            
            int remainder = currentSum - k;
            if(hash.containsKey(remainder)){
                count += hash.get(remainder);
            }
            
            hash.put(currentSum, hash.getOrDefault(currentSum, 0)+1);
        }
        
        return count;
    }
}
}