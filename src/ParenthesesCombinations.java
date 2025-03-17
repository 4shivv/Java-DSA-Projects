import java.util.*;

// Author: Shivaganesh Nagamandla
// Course: CS2
// Semester: Spring 2025

public class ParenthesesCombinations {
    
    // Method to generate all valid parentheses combinations
    public List<String> generateParentheses(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }
    
    // Helper method to perform backtracking
    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        if (current.length() == max * 2) { // Base case: valid combination of n pairs
            result.add(current.toString());
            return;
        }
        
        if (open < max) { // Add opening parenthesis if count is less than n
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // Undo move
        }
        
        if (close < open) { // Add closing parenthesis only if open count is greater
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // Undo move
        }
    }
}
