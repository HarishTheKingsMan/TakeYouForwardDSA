public class BS1D{
    
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

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
        
        int[] num1 = {-1,0,3,5,9,12};
        int target1 = 9;
        System.out.println("1. Search X in SortedArray : " + binarySearch(num1, target1));
        System.out.println("------------- STANDARD BINARY SEARCH ----------");
        
        int[] num2 = {1, 2, 2, 3};
        System.out.println("2. Lower Bound :" + lowerBound(num2, 2));
        System.out.println("------------ MODIFIED BINARY SEARCH (RECORD AND SLIDE) --------------");
        
        int[] num3 = {1, 2, 2, 3};
        System.out.println("3. Upper Bound :" + upperBound(num3, 2));
        System.out.println("--------------- MODIFED BINARY SEARCH (RECORD AND SLIDE LEFT) -----------");
        
    
        int[] num4 = {1, 3, 6, 7};
        int target4 = 5;
        System.out.println("4. Search Insertion Position :" + findInsertion(num4, target4));
        System.out.println("---------------- LOWER BOUND TEMPLATE ----------------");
        
        
        
        int[] num5 = {3, 4, 4, 7, 8, 10};
        System.out.println("5. Floor and Ceil in an Array :");
        printArray(floorAndCeil(num5, 5));
        System.out.println("-------------- TWO INDEPENDENT BINARY SEARCH -----------------");
        
        int[] num6 = {5, 7, 7, 8, 8, 10};
        System.out.println("6. First and Last occurance :");
        printArray(firstAndLastOccurance(num6, 8));
        System.out.println("---------- TWO MODIFIED BINARY SEARCH ------------------");
        
        int[] num7 = {0, 0, 1, 1, 1, 2, 3};
        System.out.println("7. Count Occurance : " + count(num7, 1));
        System.out.println("---------- FIRST AND LAST OCCURANCE + BASIC MATH ------------");
        
        int[] num8 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("8. Search in rotated :" + searchRotate(num8, 0));
        System.out.println("--------- ONE HALF IS ALWAYS SORTED RULE ------------");
        
        int[] num9 = {7, 8, 1, 2, 3, 3, 3, 4, 5, 6};
        System.out.println("9. Search in rotated Array ii :" + searchRotateII(num9, 3));
        System.out.println(" ----------- BIANRY SEARCH WITH EDGE CASE SHRINKING -----------");
        
        int[] num10 = {4, 5, 6, 7, 0, 1, 2, 3};
        System.out.println("10. Find minimum in Rotated Array :" + findMin(num10));
        System.out.println("--------- FIND, RECORD & DISCARD -------------");
        
        int[] num11 = {4, 5, 6, 7, 0, 1, 2, 3};
        System.out.println("11. Find out How many times the array is rotated : " + countRotation(num11));
        System.out.println(" ------------ BINARY SEARCH (TRACK MINIMUM AND INDEX) ------------");
        
        int[] num12 = {1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6};
        System.out.println("12. Single Element in Sorted Array : " + singleElement(num12));
        System.out.println("---------- BINARY SEARCH WITH INDEX PARITY -------------");
        
        int[] num13 = {1, 2, 3, 4, 5, 6, 7, 8, 5, 1};
        System.out.println("13. Find Peak Element Index : " + peakIndex(num13));
        System.out.println(" --------------- BINARY SEARCH ON GRADIENTS -----------");
        
        
        
        
    }
    
    private static int binarySearch(int[] arr, int target){
        
        if(arr == null || arr.length == 0) return -1;
        
        int low = 0;
        int high = arr.length -1;
        
        while(low <= high){
            
            int mid = low + (high - low) /2;
            
            if(arr[mid] == target){
                return mid;
            }
            else if(arr[mid] < target){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        
        return -1;
    }
    private static int lowerBound(int[] arr, int x){
        int n = arr.length;
        int low = 0;
        int high = arr.length-1;
        
        int ans = n;
        
        while(low <= high){
            
            int mid = low + (high - low) /2;
            
            if(arr[mid] >= x){
                ans = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        
        return ans;
    }
    private static int upperBound(int[] arr, int x){
        
        int n = arr.length;
        int low = 0;
        int high = arr.length -1;
        
        int ans = n;
        
        while(low <= high){
            
            int mid = low + (high - low) / 2;
            
            if(arr[mid] > x){
                ans = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return ans;
    }
    private static int findInsertion(int[] arr, int target){
        
        int n = arr.length;
        int low = 0;
        int high = arr.length -1;
        
        int ans = n;
        
        while(low <= high){
            
            int mid = low + (high - low) / 2;
            
            if(arr[mid] >= target){
                ans = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        
        return ans;
    }
    private static int[] floorAndCeil(int[] arr, int x){
        
        int floor = findBound(arr, x, true);
        int ceil = findBound(arr, x, false);
        
        return new int[]{floor, ceil};
    }
    private static int findBound(int[] arr, int x, boolean bound){
        
        int n = arr.length;
        int low = 0;
        int high = arr.length -1;
        
        int ans = -1;
        
        while(low <= high){
            
            int mid = low + (high - low) / 2;
            
            if(bound){
                if(arr[mid] <= x){
                    ans = arr[mid];
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }else{
                if(arr[mid] >= x){
                    ans = arr[mid];
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }
        }
        
        return ans;
    }
    private static int[] firstAndLastOccurance(int[] arr, int target){
        
        int[] res = new int[]{-1, -1};
        
        if(arr == null || arr.length == 0) return res;
        
        res[0] = occurance(arr, target, true);
        
        if(res[0] != -1){
            res[1] = occurance(arr, target, false);
        }
        
        return res;
    }
    
    private static int occurance(int[] arr, int target, boolean isFirst){
        
        int n = arr.length;
        int low = 0;
        int high = arr.length-1;
        
        int ans = -1;
        
        while(low <= high){
            
            int mid = low + (high - low) / 2;
            
            if(arr[mid] == target){
                ans = mid;
                if(isFirst){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }else if(arr[mid] < target){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        
        return ans;
        
    }
    
    private static int count(int[] arr, int target){
        
        if(arr == null || arr.length == 0) return 0;
        
        int[] occurance = firstAndLastOccurance(arr, target);
        
        if(occurance[0] == -1) return 0;
        
        return occurance[1] - occurance[0] + 1;
    }
    private static int searchRotate(int[] arr, int target){
        
        int n = arr.length;
        int low = 0;
        int high = arr.length -1;
        
        while(low <= high){
            
            int mid = low + (high - low) / 2;
            
            if(arr[mid] == target) return mid;
            
            if(arr[low] <= arr[mid]){
                
                if(arr[low] <= target && target <= arr[mid]){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }
            else{
                
                if(arr[mid] <= target && target <= arr[mid]){
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }
        }
        
        return -1;
    }
    private static boolean searchRotateII(int[] arr, int target){
        
        if(arr == null || arr.length == 0) return false;
        
        int n = arr.length;
        int low = 0;
        int high = arr.length -1;
        
        while(low <= high) {
            
            int mid = low + (high - low) / 2;
            
            if(arr[mid] == target) return true;
            
            if(arr[low] == arr[mid] && arr[mid] == arr[high]){
                low++;
                high--;
                continue;
            }
            
            if(arr[low] <= arr[mid]){
                if(arr[low] <= target && target <= arr[mid]){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }
            else{
                if(arr[mid] <= target && target <= arr[high]){
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }
        }
        
        return false;
    }
    private static int findMin(int[] arr){
        
        int n = arr.length;
        int low = 0;
        int high = arr.length -1;
        
        int min = Integer.MAX_VALUE;
        
        while(low <= high){
            
            int mid = low + (high - low)/2;
            
            if(arr[low] <= arr[high]){
                min = Math.min(min, arr[low]);
                break;
            }
            
            if(arr[low] <= arr[mid]){
                min = Math.min(min, arr[low]);
                low = mid+1;
            }else{
                min = Math.min(min, arr[mid]);
                high = mid-1;
            }
        }
        
        return min;
    }
    private static int countRotation(int[] arr){
        if(arr == null || arr.length == 0) return 0;
        
        int n = arr.length;
        int low = 0;
        int high = arr.length-1;
        
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;
        
        while(low <= high){
            
            int mid = low + (high - low) / 2;
            
            if(arr[low] <= arr[high]){
                if(arr[low] < minValue){
                    minValue = arr[low];
                    minIndex = low;
                }
                break;
            }
            
            if(arr[low] <= arr[mid]){
                if(arr[low] < minValue){
                    minValue = arr[low];
                    minIndex = low;
                }
                low = mid+1;
            }
            else{
                if(arr[mid] < minValue){
                    minValue = arr[mid];
                    minIndex = mid;
                }
                high = mid-1;
            }
        }
        
        return n - minIndex; // Left Rotation and for right rotation it is minIndex only
    }
    private static int singleElement(int[] arr){
        
        int n = arr.length;
        
        //EC1: Checking the total no of element
        if(n == 1) return arr[0];
        //EC2: Checking the first element
        if(arr[0] != arr[1]) return arr[0];
        //EC3: Checking the last element
        if(arr[n-1] != arr[n-2]) return arr[n-1];
        
        int low = 1;
        int high = arr.length -2;
        
        while(low <= high){
            
            int mid = low + (high - low) / 2;
            
            if(arr[mid] != arr[mid+1] && arr[mid] != arr[mid-1]){
                return arr[mid];
            }
            
            if((mid%2 == 0 && arr[mid] == arr[mid+1]) ||
                (mid%2 == 1 && arr[mid] == arr[mid-1])){
                    low = mid+1;
                }
            else{
                high = mid-1;
            }
        }
        
        return -1;
    }
    private static int peakIndex(int[] arr){
        
        if(arr == null || arr.length == 0) return -1;
        int n = arr.length;
        
        //EC 1: Checking the length
        if(n == 1) return 0;
        //EC 2: Checking the first element
        if(arr[0] > arr[1]) return arr[0];
        //EC 3: Checking the last element
        if(arr[n-1] > arr[n-2]) return arr[n-1];
        
        int low = 1;
        int high = n-2;
        
        while(low <= high){
            
            int mid = low + (high - low) / 2;
            
            if(arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]){
                return mid;
            }
            
            if(arr[mid] > arr[mid-1]){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        
        return -1;
    }
    
    
}
}