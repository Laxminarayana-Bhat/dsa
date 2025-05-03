package set;

import java.util.*;

public class Hashset {

    public static List<Integer> removeDuplicates(List<Integer> myList){
        Set<Integer> set = new HashSet<>(myList);
        return set.stream().toList();
    }

    public static List<Integer> removeDuplicatesWithArrayList(List<Integer> myList){
        Set<Integer> set=new HashSet<>(myList);

        return new ArrayList<>(set);
    }

    public static boolean hasUniqueChars(String string) {
        Set<Character> charSet = new HashSet<>();

        for (char ch : string.toCharArray()) {
            if (charSet.contains(ch)) {
                return false;
            }
            charSet.add(ch);
        }

        return true;
    }

    public void operations() {
        Set<Integer> mySet = new HashSet<>();
        mySet.add(6);

// Removing an element from a set
        mySet.remove(3);

        // Union of two sets
        Set<Integer> otherSet = new HashSet<>();
        otherSet.add(3);
        otherSet.add(4);
        otherSet.add(5);
        otherSet.add(6);

        Set<Integer> unionSet = new HashSet<>(mySet);
        unionSet.addAll(otherSet);

        // Intersection of two sets
        Set<Integer> intersectionSet = new HashSet<>(mySet);
        intersectionSet.retainAll(otherSet);

        // Difference between two sets
        Set<Integer> differenceSet = new HashSet<>(mySet);
        differenceSet.removeAll(otherSet);

// Checking if an element is in a set
        if (mySet.contains("hello")) {
            System.out.println("Found hello in mySet");
        }
    }

    public static List<int[]> findPairs(int[] arr1, int[] arr2, int target) {
        Set<Integer> mySet = new HashSet<>();
        List<int[]> pairs = new ArrayList<>();

        for (int num : arr1) {
            mySet.add(num);
        }

        for (int num : arr2) {
            int complement = target - num;
            if (mySet.contains(complement)) {
                pairs.add(new int[]{complement, num});
            }
        }

        return pairs;
    }

    public static int longestConsecutiveSequence(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
