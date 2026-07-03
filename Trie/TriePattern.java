// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.List;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        System.out.println("---------INDEX PAIRS OF A STRING --------- ");
        
        String text = "bluebirdskyscraper";
        String[] words = {"blue", "bird", "sky"};
        
        Indices ind = new Indices();
        int[][] output = ind.getIndices(text, words);
        
        for(int i = 0; i < output.length; i++){
            for(int j = 0; j < output[i].length; j++){
                System.out.print(output[i][j] + ", ");
            }
            System.out.println();
        }
        
        System.out.println(" ----------  DESIGN ADD AND SEARCH WITH A DOT ----------------");
        Dotted.Trie dot = new Dotted.Trie();
        dot.insert("bad");
        dot.insert("dad");
        dot.insert("mad");
        System.out.println(dot.search("pad"));
        System.out.println(dot.search("bad"));
        System.out.println(dot.search(".ad"));
        System.out.println(dot.search("b.."));
        
        System.out.println("---------- EXTRA CHARACTERS IN A STRING ---------------------");
        String s = "amazingracecar";
        String[] dictionary = {"race", "car"};
        Extra extra  = new Extra();
        System.out.println("Extra Characters : " + extra.extraCharacters(s, dictionary));
        
    }
    
    static class Indices{
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
                Node node  = root;
                
                for(char ch : word.toCharArray()){
                    
                    if(node.children[ch - 'a'] == null){
                        node.children[ch - 'a'] = new Node();
                    }
                    
                    node = node.children[ch - 'a'];
                }
                
                node.isEnd = true;
            }
        }
        
        public int[][] getIndices(String text, String[] words){
            
            //Step 1: Put the words into the trie
            Trie trie = new Trie();
            
            for(String word: words){
                trie.insert(word);
            }
            
            //Step 2: Looping through the text for overlapping elements
            int n = text.length();
            List<int[]> result = new ArrayList<>();
            
            for(int i = 0; i < n; i++){
                
                Node node = trie.root;
                
                for(int j = i; j < n; j++){
                    
                    char ch = text.charAt(j);
                    
                    if(node.children[ch - 'a'] == null){
                        break;
                    }
                    
                    node = node.children[ch - 'a'];
                    
                    if(node.isEnd){
                        result.add(new int[]{i, j});
                    }
                }
            }
            
            int[][] finalResults = new int[result.size()][2];
            
            for(int i = 0; i < result.size(); i++){
                finalResults[i] = result.get(i);
            }
            
            return finalResults;
        }
    }
    static class Dotted{
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
            
            public boolean search(String word){
                return searchHelper(word, 0, root);
            }
            private boolean searchHelper(String word, int index, Node node){
                
                if(index == word.length()){
                    return node.isEnd;
                }
                
                char ch = word.charAt(index);
                
                if(ch == '.'){
                    
                    for(int i = 0; i < 26; i++){
                        
                        if(node.children[i]  != null){
                            
                            if(searchHelper(word, index+1, node.children[i])){
                                return true;
                            }
                        }
                    }
                    
                    return false;
                }
                else{
                    
                    if(node.children[ch - 'a'] == null) return false;
                    
                    return searchHelper(word, index+1, node.children[ch - 'a']);
                }
            }
        }
    }
    static class Extra{
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
            public Trie(){
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
        }
        
        public int extraCharacters(String s, String[] dic){
            
            //Step 1: Insert the words into the trie
            Trie trie = new Trie();
            
            for(String d : dic){
                trie.insert(d);
            }
            
            //Step 2: Create a dp
            int n = s.length();
            int[] dp = new int[n+1];
            dp[n] = 0;
            
            //Step 3: Loop from the last element
            for(int i = n-1; i >= 0; i--){
                Node node = trie.root;
                
                dp[i] = dp[i+1] +1;
                
                //Step 4: Loop through from element to n;
                for(int j = i; j < n; j++){
                    
                    char ch = s.charAt(j);
                    
                    if(node.children[ch - 'a'] == null){
                        break;
                    }
                    
                    node = node.children[ch - 'a'];
                    
                    if(node.isEnd){
                        dp[i] = Math.min(dp[i], dp[j+1]);
                    }
                }
            }
            
            return dp[0];
        }
    }
}