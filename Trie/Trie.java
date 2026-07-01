import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

class Main { 
    public static void main(String[] args) { 
        System.out.println("----------TRIES--------------------"); 
        
        System.out.println("----------- TRIE IMPLEMENTATION AND OPRTATION ---------------------");
        Trie trie = new Trie(); 
        trie.insert("apple"); 
        System.out.println(trie.search("apple"));    // returns true
        System.out.println(trie.search("app"));      // returns false
        System.out.println(trie.startsWith("app"));  // returns true
        trie.insert("app"); 
        System.out.println(trie.search("app"));      // returns true
        
        System.out.println("--------------- TRIE IMPLEMENTATION AND ADVANCED OPERATION ---------------------");
        
        Triee triee = new Triee();
        triee.insert("apple");
        System.out.println(triee.countWordsEqualTo("apple"));  // return 1
        triee.insert("app"); 
        System.out.println(triee.countWordsStartingWith("app")); // return 2
        triee.erase("apple");
        System.out.println(triee.countWordsStartingWith("app"));  // return 1
        
        
        System.out.println(" -------------------- LONGEST WORD WITH ALL PREFIXES ----------------------");
        
        List<String> arr = List.of("n", "ni", "nin", "ninj" , "ninja" , "nil");
        Longest longest = new Longest();
        System.out.println("Longest with all prefixes : " + longest.longestPrefix(arr));
        
        
        System.out.println("---------------------- NUMBER OF DISTINCT SUBSTRINGS IN A STRING ---------------");
        String str = "aba";
        Unique uni = new Unique();
        System.out.println("Unique Substring : " + uni.uniqueSubstring(str));
        
        System.out.println(" ---------------  MAXIMUM XOR OF TWO NUMBERS FROM THE ARRAY ---------------------");
        int[] arr1 = {3, 9, 10, 5, 1};
        MaxXor max = new MaxXor();
        System.out.println("Max XOR  : " + max.maxXor(arr1));
        
        System.out.println(" ----------------- MAXIMUM XOR WITH AN ELEMENT IN AN ARRAY --------------------------");
        int[] arr2 = {4, 9, 2, 5, 0, 1};
        int[][] queries = {{3, 0}, {3, 10}, {7, 5}, {7, 9}};
        MaxXor2 max2 = new MaxXor2();
        System.out.println("Solution :" + max2.maximizeXor(arr2, queries));
        
    } 

    static class Trie {
        // Correct: static nested class is good for memory
        private static class Node {
            private Node[] children; 
            private boolean isEnd; 

            Node() { 
                children = new Node[26]; 
                isEnd = false; 
            } 

            public boolean containKey(char ch) { 
                return children[ch - 'a'] != null; 
            } 

            public void put(char ch, Node node) { 
                children[ch - 'a'] = node; 
            } 

            public Node get(char ch) { 
                return children[ch - 'a']; 
            } 

            public void setEnd() { 
                isEnd = true; 
            } 

            public boolean isEnd() { 
                return isEnd; 
            } 
        } 

        // Correct: Non-static instance variable
        private Node root; 

        public Trie() { 
            root = new Node(); 
        } 

        // Correct: Non-static instance method
        public void insert(String word) { 
            Node node = root; 
            for (char ch : word.toCharArray()) { 
                if (!node.containKey(ch)) { 
                    node.put(ch, new Node()); // Fixed: Using put method
                } 
                node = node.get(ch); // Fixed: Using get method
            } 
            node.setEnd(); // Fixed: Correctly setting the end flag
        } 

        public boolean search(String word) { 
            Node node = root; 
            for (char ch : word.toCharArray()) { 
                if (!node.containKey(ch)) { 
                    return false; 
                } 
                node = node.get(ch); 
            } 
            return node.isEnd(); 
        } 

        // Fixed: Renamed from prefix to startsWith to match your main method
        public boolean startsWith(String pref) { 
            Node node = root; 
            for (char ch : pref.toCharArray()) { 
                if (!node.containKey(ch)) { 
                    return false; 
                } 
                node = node.get(ch); 
            } 
            return true; 
        } 
    } 
    static class Triee{
        static class Node{
            private Node[] children;
            private int prefixNum;
            private int endWord;
            
            Node(){
                children = new Node[26];
                prefixNum = 0;
                endWord = 0;
            }
            
            public boolean containKey(char ch){
                return children[ch - 'a'] != null;
            }
            
        }
        
        private Node root;
        public Triee(){
            root = new Node();
        }
        
        public void insert(String word){
            Node node = root;
            
            for(char ch : word.toCharArray()){
                
                if(!node.containKey(ch)){
                    node.children[ch - 'a'] = new Node();
                }
                node = node.children[ch - 'a'];
                node.prefixNum++;
            }
            
            node.endWord++;
        }
        
        public int countWordsEqualTo(String word){
            Node node  = root;
            
            for(char ch : word.toCharArray()){
                
                if(!node.containKey(ch)){
                    return 0;
                }
                
                node = node.children[ch - 'a'];
            }
            
            return node.endWord;
        }
        
        public int countWordsStartingWith(String prefix){
            Node node = root;
            
            for(char ch : prefix.toCharArray()){
                
                if(!node.containKey(ch)){
                    return 0;
                }
                
                node = node.children[ch - 'a'];
            }
            
            return node.prefixNum;
        }
        
        public void erase(String word){
            Node node = root;
            
            for(char ch : word.toCharArray()){
                
                if(!node.containKey(ch)){
                    return;
                }
                node.prefixNum--;
                node = node.children[ch - 'a'];
            }
            
            node.endWord--;
        }
    }
    static class Longest{
        public String longestPrefix(List<String> word){
            
            //Step 1 : Insertion
            Trie trie = new Trie();
            for(String ele : word){
                trie.insert(ele);
            }
            
            //Step 2 : Checking the longestPref
            String res = "";
            for(String ele : word){
                
                boolean isPrefixSatisfied = trie.checkAllPrefixPresent(ele);
                
                if(isPrefixSatisfied){
                    
                    if(ele.length() > res.length()){
                        res = ele;
                    }
                    else if(ele.length() == res.length() && ele.compareTo(res) < 0){
                        res = ele;
                    }
                }
            }
            
            //Step 3:
            return res.isEmpty() ? "None" : res;
            
        }
        
        static class Node{
            private Node[] children;
            private boolean isEnd;
            
            Node(){
                children = new Node[26];
                isEnd = false;
            }
        }
        static class Trie{
            private Node root;
            Trie(){
                root = new Node();
            }
            
            public void insert(String word){
                Node node = root;
                
                for(char ch : word.toCharArray()){
                    
                    if(node.children[ch - 'a'] == null){
                        node.children[ch - 'a'] = new Node();
                    }
                    
                    node = node.children[ch - 'a'];
                }
                
                node.isEnd = true;
            }
            
            public boolean checkAllPrefixPresent(String word){
                Node node = root;
                
                for(char ch : word.toCharArray()){
                    
                    //No need of this condition
                    if(node.children[ch - 'a'] == null){
                        return false;
                    }
                    
                    node = node.children[ch - 'a'];
                    
                    if(!node.isEnd) return false;
                }
                
                return true;
            }
        }
    }
    static class Unique{
        static class Node{
            private Node[] children;
            public Node(){
                children = new Node[26];
            }
        }
        public int uniqueSubstring(String str){
            Node root = new Node();
            int n = str.length();
            int distinct = 0;
            
            for(int i = 0; i < n; i++){
                Node node = root;
                
                for(int j = i; j < n; j++){
                    
                    char ch = str.charAt(j);
                    
                    if(node.children[ch - 'a'] == null){
                        node.children[ch - 'a'] = new Node();
                        distinct++;
                    }
                    
                    node = node.children[ch - 'a'];
                }
            }
            
            return distinct;
        }
    }
    static class MaxXor{
        static class Node{
            private Node[] children;
            Node(){
                children = new Node[2];
            }
        }
        static class Trie{
            private Node root;
            public Trie(){
                root = new Node();
            }
            
            public void insert(int num){
                Node node = root;
                
                for(int i = 31; i >= 0; i--){
                    
                    int bit = (num >> i) & 1;
                    
                    if(node.children[bit] == null){
                        node.children[bit] = new Node();
                    }
                    
                    node = node.children[bit];
                }
            }
            
            public int getMax(int num){
                Node node = root;
                int maxNum = 0;
                
                for(int i = 31; i >= 0 ; i--){
                    
                    int bit = (num >> i) & 1;
                    
                    int opp = 1 - bit;
                    
                    if(node.children[opp] != null){
                        maxNum = maxNum | (1 << i);
                        node = node.children[opp];
                    }else{
                        node = node.children[bit];
                    }
                }
                
                return maxNum;
            }
        }
        
        public int maxXor(int[] arr){
            
            Trie trie = new Trie();
            
            //Step 1 : insert
            for(int i = 0; i < arr.length; i++){
                trie.insert(arr[i]);
            }
            
            //Step 2: Get max
            int max = 0;
            for(int i = 0; i < arr.length; i++){
                max = Math.max(max, trie.getMax(arr[i]));
            }
            
            return max;
        }
        
    }
    static class MaxXor2{
        static class Node{
            private Node[] children;
            public Node(){
                children = new Node[2];
            }
        }
        static class Trie{
            private Node root;
            public Trie(){
                root = new Node();
            }
            public void insert(int num){
                Node node = root;
                
                for(int i = 31; i >= 0; i--){
                    
                    int bit = (num >> i) & 1;
                    
                    if(node.children[bit] == null){
                        node.children[bit] = new Node();
                    }
                    
                    node = node.children[bit];
                }
            }
            public int getMax(int num){
                Node node = root;
                int maxSum = 0;
                
                for(int i = 31; i >= 0; i--){
                    
                    int bit = (num >> i) & 1;
                    int oppositeBit = 1 - bit;
                    
                    if(node.children[oppositeBit] != null){
                        
                        //If Opp is present, then opp values of xor is 1
                        maxSum = maxSum | (1 << i);
                        node = node.children[oppositeBit];
                    }else{
                        node = node.children[bit];
                    }
                }
                return maxSum;
            }
        }
        
        public List<Integer> maximizeXor(int[] arr, int[][] queries){
            
            //Step 1: Sort the Array num
            Arrays.sort(arr);
            int n = arr.length;
            
            //Step 2: Sort the Queries based on m
            int q = queries.length;
            int[][] offlineQueries = new int[q][3];
            for(int i = 0; i < q; i++){
                offlineQueries[i][0] = queries[i][0];
                offlineQueries[i][1] = queries[i][1];
                offlineQueries[i][2] = i;
            }
            
            Arrays.sort(offlineQueries, Comparator.comparingInt(a -> a[1]));
            Integer[] res = new Integer[q];
                            
            int currentIndex = 0;
            Trie trie = new Trie();

            
            //Step 3: Loop through the queries on partial insertion of num values in tries
            for(int i = 0; i < q; i++){
                
                int x = offlineQueries[i][0];
                int m = offlineQueries[i][1];
                int ogIndex = offlineQueries[i][2];

                
                //Insert it into the Trie;
                while(currentIndex < n && arr[currentIndex] <= m){
                    trie.insert(arr[currentIndex]);
                    currentIndex++;
                }
                
                if(currentIndex != 0){
                    res[ogIndex] = trie.getMax(x);
                }else{
                    res[ogIndex] = -1;
                }
            }
            
            return Arrays.asList(res);
        }
    }

}
