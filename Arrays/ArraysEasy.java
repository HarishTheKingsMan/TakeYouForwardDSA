public class ArraysEasy{

    

// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.HashMap;

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
        
        int[] nums1 = {3, 3, 6, 1};
        System.out.println("1. Largest Element : " + largest(nums1));
        
        int[] nums2 = {8, 8, 7, 6, 5};
        System.out.println("2. Second Largest Element : "+ secondLargest(nums2));
        
        int[] nums3 = {1, 2, 1, 4, 5};
        System.out.println("3. Check if Sorted : " + checkSorted(nums3));
        
        int[] nums4 = {0, 0, 3, 3, 5, 6};
        System.out.println("4. Remove Duplicates from Sorted : " + removeDuplicates(nums4));
        
        int[] nums5 = {1, 2, 3, 4, 5};
        System.out.println("5. Left Rotate by one : " );
        leftRotate(nums5);
        printArray(nums5);
        
        int[] nums6 =  {1, 2, 3, 4, 5, 6};
        int k1 = 2;
        System.out.println("6. Left Rotate by k : ");
        leftRotatek(nums6, k1);
        printArray(nums6);
        
        int[] nums7 = {0, 1, 4, 0, 5, 2};
        System.out.println("7. Move Zeros to the Right : " + moveZeros(nums7));
        printArray(nums7);
        
        int[] nums81 = {3, 4, 6, 7, 9, 9};
        int[] nums82 = {1, 5, 7, 8, 8};
        System.out.println("8. Merge Two Integer Array : ");
        int[] res1 = mergeArray(nums81, nums82);
        printArray(res1);
        
        int[] nums9 = {0, 2, 5, 1, 4};
        System.out.println("9. Find Missing Number : " + missingNumber(nums9));
        
        int[] num10 = {1, 1, 0, 0, 1, 1, 1, 0};
        System.out.println("10. Maximum Consecutive Ones : " + maxConsOne(num10));
        System.out.println("---------STATE TRACKING ------------");
        
        int[] num11 = {1, 2, 2, 4, 3, 1, 4};
        System.out.println("11. Find Num Appears Once : " + appearOnce(num11));
        System.out.println("--------- BIT MANIPULATION (XOR) -----------------");
        
        int[] num12 = {10, 5, 2, 7, 1, 9};
        int k12 = 15;
        System.out.println("12. Longest SubArray with Sum K : " + longestSubArray(num12, k12));
        System.out.println("--------- PREFIX SUM WITH STATE CACHING ( HASHMAP ) ------------");
        
        
        
    }
    private static int largest(int[] arr){
        
        if(arr.length == 0){
            return -1;
        }
        
        int largest = -1;
        
        for(int i = 0; i < arr.length; i++){
            
            largest = Math.max(largest, arr[i]);
        }
        
        return largest;
    }
    private static int secondLargest(int[] arr){
        if(arr.length <= 1){
            return -1;
        }
        int largest = -1;
        int second = -1;
        for(int i = 0; i < arr.length; i++){
            
            if(arr[i] > largest){
                second = largest;
                largest = arr[i];
            }else if(arr[i] < largest && arr[i] > second){
                second = arr[i];
            }
        }
        
        return second;
    }
    private static boolean checkSorted(int[] arr){
        if(arr.length == 0) return true;
        
        for(int i = 1; i < arr.length; i++){
            if(arr[i] < arr[i - 1]) return false;
        }
        
        return true;
    }
    private static int removeDuplicates(int[] arr){
        if(arr.length ==0 ) return 0;
        
        int pointer = 1;
        
        for(int i = 1; i < arr.length; i++){
            if(arr[i] != arr[i-1]){
                arr[pointer] = arr[i];
                pointer++;
            }
        }
        
        return pointer;
    }
    private static void leftRotate(int[] arr){
        if(arr.length <= 1) return;
        int n = arr.length;
        
        int temp = arr[0];
        for(int i = 1; i < arr.length; i++){
            arr[i-1] = arr[i];
        }
        arr[n-1] = temp;
    }
    private static void leftRotatek(int[] arr, int k){
        if(arr.length == 0) return;
        int n = arr.length;
        k = k%n;
        
        reverse(arr, 0, k-1);
        reverse(arr, k, n-1);
        reverse(arr, 0, n-1);
    }
    private static int moveZeros(int[] arr){
        if(arr.length == 0) return -1;
        
        int pointer = 0;
        
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 0){
                swap(arr, i, pointer);
                pointer++;
            }
        }
        
        return pointer;
    }
    private static int[] mergeArray(int[] num1, int[] num2){
        if(num1.length == 0)return num2;
        if(num2.length == 0)return num1;
        
        int n = num1.length;
        int m = num2.length;
        
        int i = 0;
        int j = 0;
        int k = 0;
        
        int[] merge = new int[n+m];
        
        while(i < n && j < m){
            
            if(num1[i] < num2[j]){
                if(k == 0 || merge[k -1] != num1[i]){
                    merge[k] = num1[i];
                    k++;
                }
                i++;
            }else{
                if(k == 0 || merge[k -1] != num2[j]){
                    merge[k] = num2[j];
                    k++;
                }
                j++;
            }
        }
        
        while(i < n){
            if(k == 0 || merge[k-1] != num1[i]){
                merge[k] = num1[i];  
                k++;
            }
            i++;
        }
        while(j < m){
            if(k == 0 || merge[k-1] != num2[j]){
                merge[k] = num2[j];
                k++;
            }
            j++;
        }
        
        return java.util.Arrays.copyOf(merge, k);
    }
    private static int missingNumber(int[] arr){
        if(arr.length == 0 )return -1;
        int n = arr.length;
        
        long expectedNumber = n * (n+1) / 2;
        long actualNumber = 0;
        
        for(int num : arr){
            actualNumber += num;
        }
        
        return (int) (expectedNumber - actualNumber);
    }
    private static int maxConsOne(int[] arr){
        
        if(arr.length == 0) return 0;
        
        int currentMax = 0;
        int maxSoFar = 0;
        
        for(int i = 0;i < arr.length; i++){
            
            if(arr[i] == 1){
                currentMax++;
                maxSoFar = Math.max(maxSoFar, currentMax);
            }else{
                currentMax = 0;
            }
        }
        
        return maxSoFar;
    }
    private static int appearOnce(int[] arr){
        
        if(arr.length == 0) return -1;
        
        int temp = 0;
        
        for(int num : arr){
            temp = num ^ temp;
        }
        
        return temp;
    }
    private static int longestSubArray(int[] arr, int k){
        
        if(arr.length == 0) return 0;
        HashMap<Long, Integer> hash = new HashMap<>();
        long currentSum = 0;
        int maxLen = 0;
        
        for(int i = 0; i < arr.length; i++){
            currentSum += arr[i];
            if(currentSum == k) maxLen = i+1;
            
            long remainder = currentSum - k;
            if(hash.containsKey(remainder)){
                
                int len = i - hash.get(remainder);
                maxLen = Math.max(maxLen, len);
            }
            
            hash.put(currentSum, i);
        }
        
        return maxLen;
    }
    
    
    
}
}