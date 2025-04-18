package stack;

import java.util.ArrayList;
import java.util.Arrays;

public class Stack<T> {

    private ArrayList<T> stackList = new ArrayList<>();

    public ArrayList<T> getStackList() {
        return stackList;
    }

    public void printStack() {
        for (int i = stackList.size() - 1; i >= 0; i--) {
            System.out.println(stackList.get(i));
        }
    }

    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return stackList.get(stackList.size() - 1);
        }
    }

    public int size() {
        return stackList.size();
    }

    public void push(T t) {
        stackList.add(t);
        String s = "";
    }

    public static String reversedString(String str) {
        StringBuilder temp = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            temp.append(str.charAt(i));
        }
        return temp.toString();
    }

    public T pop() {
        if (stackList.isEmpty()) return null;
        return stackList.remove(stackList.size() - 1);
    }

    public static boolean isBalancedParentheses(String parentheses) {
        // Create an empty stack of characters
        Stack<Character> stack = new Stack<>();

        // Iterate through each character in the input string
        for (char p : parentheses.toCharArray()) {
            // If the current character is an opening parenthesis, push it onto the stack
            if (p == '(') {
                stack.push(p);
            }
            // If the current character is a closing parenthesis, check if the stack is empty or
            // if the top element of the stack is not an opening parenthesis. If either of these
            // conditions is true, return false, because the parentheses are not balanced.
//            else if (p == ')') {
//                if (stack.isEmpty() || stack.pop() != '(') {
//                    return false;
//                }
//            }
            if (p == ')' && (stack.size() == 0 || stack.pop() != '(')) {
                return false;
            }
        }

        // After iterating through all the characters in the input string, check if the stack is
        // empty. If the stack is empty, return true, because all the opening parentheses have been
        // matched with closing parentheses. Otherwise, return false, because there are still some
        // opening parentheses left on the stack that have not been matched with closing parentheses.
        return stack.isEmpty();
    }

}