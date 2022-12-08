/* 
import java.util.ArrayList;

public class main {
    
    public static void main (String[] args) {
        ArrayList<String> parens = possibleParens(2);
    }

     

    public static ArrayList<String> possibleParens(int n) {
        ArrayList<String> par = new ArrayList<String>();  
        int open = 0;
        int closed = 0;
        String o = "(";
        String c = ")";
        int attempts = 0;
        
        while (attempts < n*5) {
            String temp = "";
            for (int i=0; i<n*5; i++) {
                int rand = (int) (Math.random() * 2 + 1); // will return either 1 or 2
                System.out.println(rand);
                if (rand == 1 && open < n){
                    temp += o;
                    open ++;
                    System.out.println(temp);
                } else if (rand == 2 && closed < n){
                    temp += c;
                    if (temp.substring(0).equals(o)) {
                        temp = "";
                        open = 0;
                        closed = 0;
                        System.out.println("frick it failed");
                    } else {
                        closed++;
                        System.out.println(temp);
                    }
                } else if (open == n  && closed == n) {
                    open = 0;
                    closed = 0;
                    for (int j=0; j<par.size(); j++)
                    {
                        if (temp.equals(par.get(j)))
                        {
                            par.add(temp);
                            System.out.print(temp);
                            break;
                        }
                    }
                    
                }
            }
            attempts++;
        }
        return par;
    } 
}*/  //? end of my attempt

// Java program to print all the combinations of balanced
// parenthesis.
import java.util.*;
 
class main {
 
    // function which generates all possible n pairs of
    // balanced parentheses.
    // open : count of the number of open parentheses used
    // in generating the current string s. close : count of
    // the number of closed parentheses used in generating
    // the current string s. s : currently generated string/
    // ans : a vector of strings to store all the valid
    // parentheses.
    public static void
    generateParenthesis(int n, int open, int close,
                        String s, ArrayList<String> ans)
    {
 
        // if the count of both open and close parentheses
        // reaches n, it means we have generated a valid
        // parentheses. So, we add the currently generated
        // string s to the final ans and return.
        if (open == n && close == n) {
            ans.add(s);
            return;
        }
 
        // At any index i in the generation of the string s,
        // we can put an open parentheses only if its count
        // until that time is less than n.
        if (open < n) {
            generateParenthesis(n, open + 1, close, s + "{",
                                ans);
        }
 
        // At any index i in the generation of the string s,
        // we can put a closed parentheses only if its count
        // until that time is less than the count of open
        // parentheses.
        if (close < open) {
            generateParenthesis(n, open, close + 1, s + "}",
                                ans);
        }
    }
 
    public static void main(String[] args)
    {
        int n = 3;
 
        // vector ans is created to store all the possible
        // valid combinations of the parentheses.
        ArrayList<String> ans = new ArrayList<>();
 
        // initially we are passing the counts of open and
        // close as 0, and the string s as an empty string.
        generateParenthesis(n, 0, 0, "", ans);
 
        // Now, here we print out all the combinations.
        for (String s : ans) {
            System.out.println(s);
        }
    }
}
