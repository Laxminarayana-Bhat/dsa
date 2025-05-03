package hashtable;

import java.util.*;

public class HashTable {
    //linear probing & separate chaining are the 2 techniques to handle collision, here we are using 2nd one with LLs

    private Node[] dataMap;

    class Node {
        String key;
        int value;
        Node next;

        Node(String k, int v) {
            this.value = v;
            this.key = k;
        }
    }

    public HashTable() {
        dataMap = new Node[7];
    }

    public void printTable() {
        for (int i = 0; i < dataMap.length; i++) {
            System.out.println(i + "-");
            Node temp = dataMap[i];
            while (temp != null) {
                System.out.println(" { " + temp.key + " : " + temp.value + " }");
                temp = temp.next;
            }
        }
    }

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash + key.charAt(i) * 23) % key.length();
        }
        return hash;
    }
//    Arithmetic operation - char	Becomes int
//    Concatenated with string - char	Remains char
//    Printed directly - char	Remains char
//    Passed to numeric context - char Converted to int

    public void set(String key, int value) {
        int index = hash(key);
        Node node = new Node(key, value);
        if (dataMap[index] == null) {
            dataMap[index] = node;
        } else {
            Node temp = dataMap[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    public int get(String key) {
        int index = hash(key);
        Node temp = dataMap[index];
        while (temp != null) {
            if (temp.key == key) {
                return temp.value;
            }
            temp = temp.next;
        }
        return -1;
    }

    public ArrayList<?> keys() {
        ArrayList<Integer> list = new ArrayList<>();
        for (Node node : dataMap) {
            Node temp = node;
            while (temp != null) {
                list.add(temp.value);
                temp = temp.next;
            }
        }
        return list;
    }

    public static List<Integer> findDuplicates(int[] nums) {

        // Create a new HashMap to store the count of occurrences
        // of each integer.
        Map<Integer, Integer> numCounts = new HashMap<>();
        // Loop through each integer in the input array and update
        // its count in the HashMap.
        for (int num : nums) {
            numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
        }
        // Create a new ArrayList to store the duplicate integers.
        List<Integer> duplicates = new ArrayList<>();
        // Loop through each entry in the HashMap and check if the
        // count of occurrences is greater than 1.
        for (Map.Entry<Integer, Integer> entry : numCounts.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
            }
        }
        // Return the ArrayList of duplicate integers.
        return duplicates;
    }

    public static Character firstNonRepeatingChar(String string) {
        Map<Character, Integer> charCounts = new HashMap<>();
        for(Integer i:charCounts.values()){
            System.out.println(i);
        }

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (charCounts.get(c) == 1) {
                return c;
            }
        }

        return null;
    }


    public static List<List<String>> groupAnagrams(String[] strings) {
        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String string : strings) {
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            String canonical = new String(chars);

            if (anagramGroups.containsKey(canonical)) {
                anagramGroups.get(canonical).add(string);
            } else {
                List<String> group = new ArrayList<>();
                group.add(string);
                anagramGroups.put(canonical, group);
            }
        }

        return new ArrayList<>(anagramGroups.values());
    }

    public static int[] twoSum(int[] nums, int target) {
        // Create a map to store numbers and their indices
        Map<Integer, Integer> numMap = new HashMap<>();

        // Iterate over each number in the input array
        for (int i = 0; i < nums.length; i++) {
            // Get the current number
            int num = nums[i];
            // Calculate the complement needed to reach the target
            int complement = target - num;

            // Check if the map contains the complement
            if (numMap.containsKey(complement)) {
                // Return the indices of the complement and current number
                return new int[]{numMap.get(complement), i};
            }
            // Store the current number and its index in the map
            numMap.put(num, i);
        }

        // Return an empty array if no pair was found
        return new int[]{};
    }

    public static int[] subarraySum(int[] nums, int target) {
        // Create a HashMap to store cumulative sum and index
        Map<Integer, Integer> sumIndex = new HashMap<>();
        // Initialize the HashMap with 0 sum and index -1
        sumIndex.put(0, -1);
        // Initialize the current sum to 0
        int currentSum = 0;

        // Iterate through the input array
        for (int i = 0; i < nums.length; i++) {
            // Calculate the cumulative sum
            currentSum += nums[i];
            // Check if the required subarray sum exists
            if (sumIndex.containsKey(currentSum - target)) {
                // Return the start and end indices of the subarray
                return new int[]{sumIndex.get(currentSum - target) + 1, i};
            }
            // Store the current sum and its index in the HashMap
            sumIndex.put(currentSum, i);
        }

        // Return an empty array if no subarray is found
        return new int[]{};
    }
}
