public class StringEasy{
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Main {
    public static void main(String[] args) {
        System.out.println("BASIC AND EASY STRING PROBLEMS : ");
        
        String str1 = "()(()())(())";
        System.out.println("1. Remove Outermost Parentheses : " + removeOuterParentheses(str1));
        System.out.println("---------------------------------------------------------------------------");
        
        String str2 = " amazing coding skills ";
        System.out.println("2. Reverse Every word in a String :" + reverseWords(str2));
        System.out.println("--------------------- TWO POINTER WORD REVERSAL ----------------------------");
        
        String str3 = "0214638";
        System.out.println("3. Largest Odd number in a String : " + largestOddNumber(str3));
        System.out.println("---------------------------------------------------------------------------");
        
        String[] str4 = {"flower", "flow", "fly", "flight"};
        System.out.println("4. Longest Common Prefix : " + longestPrefix(str4));
        System.out.println("---------VERTICAL SCANNING (IMPLEMENTATION ) --> HORIZONTAL SCALING ALSO THERE --------");
        
        String str51 = "egg";
        String str52 = "add";
        System.out.println("5. is Isomorphic : " + isIsoMorphic(str51, str52));
        System.out.println("--------------------- ASCII INDEX MAPPER ---------------------");
        
        String s = "abcde";
        String goal = "cdeab";
        System.out.println("6. Rotate String : " + isRotated(s, goal));
        System.out.println("------------------- CONCATENATION -----------------------");
        
        String st = "anagram";
        String ts = "nagaram";
        System.out.println("7. Valid Anagram : " + isValidAnagram(st, ts));
        
    }
    
    private static String removeOuterParentheses(String str){
        
        //Sample Depth Test : (()())
        //TC: O(N) / SC : O(N)
        
        if(str == null || str.length() == 0) return str;
        
        StringBuilder res = new StringBuilder();
        int depth = 0;
        
        for(char ch : str.toCharArray()){
            
            if(ch == '('){
                
                if(depth > 0){
                    res.append(ch);
                }
                depth++;
            }
            else if(ch == ')'){
                depth--;
                
                if(depth > 0){
                    res.append(ch);
                }
            }
        }
        
        return res.toString();
    }
    private static String reverseWords(String str){
        
        //TC : O(N) / SC : O(N)
        
        if(str == null || str.length() == 0) return str;
        
        StringBuilder res = new StringBuilder();
        int i = str.length() -1;
        
        while(i >= 0){
            
            // Step 1: Removing all the gaps from right for `i`
            while(i >= 0 && str.charAt(i) == ' '){
                i--;
            }
            // Checking the `i` went to negative value
            if(i < 0) break;
            
            //Step 2: Marking the current value as j for end of the word
            int j = i;
            
            //Step 3: Loop through to the left until see another gap
            while(i >= 0 && str.charAt(i) != ' '){
                i--;
            }
            
            //Step 4: Append the gap before adding the word
            if(res.length() > 0) res.append(" ");
            
            res.append(str.substring(i+1, j+1));
        }
        
        return res.toString();
    }
    private static String largestOddNumber(String str){
        
        if(str == null || str.length() == 0) return str;
        
        //Step 1: Scan from right-to-left for last index
        int right = str.length() - 1;
        for(int i = str.length() -1; i >= 0 ; i--){
            
            int num = str.charAt(i) - '0';
            if(num % 2 != 0){
                right = i;
                break;
            }
        }
        
        //Step 2: Scan from left-to-right for non-zero index
        int left = 0;
        while(left <= right && str.charAt(left) == '0'){
            left++;
        }
        
        return str.substring(left, right+1);
        
    }
    private static String longestPrefix(String[] str){
        
        if(str == null || str.length == 0) return "";
        
        //Step 1: Loop through every character in 1st String
        for(int i = 0; i < str[0].length(); i++){
            
            char ch = str[0].charAt(i);
            
            //Step 2: Checking the character in every other string at the i`th index
            for(int j = 1; j < str.length; j++){
                
                //Step 3: Breaking Condition
                if(i == str[j].length() || str[j].charAt(i) != ch){
                    return str[0].substring(0, i);
                }
            }
        }
        
        return "";
    }
    private static boolean isIsoMorphic(String str1, String str2){
        
        if(str1.length() != str2.length()) return false;
        
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        
        for(int i = 0; i < str1.length(); i++){
            
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            
            if(map1[c1] != map2[c2]) return false;
            
            // Here We need to store index rather than count
            // Cuz aba != baa and this scenario will fail here
            map1[c1] = i+1;
            map2[c2] = i+1;
        }
        
        return true;
    }
    private static boolean isRotated(String s, String goal){
        
        if(s.length() != goal.length()) return false;
        
        String added = s+s;
        return added.contains(goal);
    }
    private static boolean isValidAnagram(String st, String ts){
        
        if(st.length() != ts.length()) return false;
        
        int[] count = new int[26];
        
        //Step 1: Incrementing the first string and drecrementing the second string
        for(int i = 0; i < st.length(); i++){
            
            count[st.charAt(i) - 'a']++;
            count[ts.charAt(i) - 'a']--;
        }
        
        //Step 2: Checking whether anything is having more than 0
        for(int i = 0; i < 26; i++){
            if(count[i] > 0) return false;
        }
        
        return true;
    }
}
}