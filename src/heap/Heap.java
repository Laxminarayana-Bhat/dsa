package heap;

import java.util.*;

public class Heap {

    //PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); - min heap
    //PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0])); - max heap
    //To create a PriorityQueue of int[] arrays without a comparator. Since int[] does not implement Comparable, Java throws:
    //ClassCastException: class [I cannot be cast to class java.lang.Comparable
    //This happens because Java needs to know how to compare int[] objects, and it doesn't know what to do unless you explicitly tell it with a comparator

    //O(log n)
    private List<Integer> heap;

    public Heap() {
        this.heap = new ArrayList<>();
    }

    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swapIndex(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void insert(int value) {
        heap.add(value);
        int currentIdx = heap.size() - 1;
        while (currentIdx > 0 && heap.get(currentIdx) > heap.get(parent(currentIdx))) {
            swapIndex(currentIdx, parent(currentIdx));
            currentIdx = parent(currentIdx);
        }
    }

    public Integer remove() {
        if (heap.isEmpty()) return null;
        if (heap.size() == 1) return heap.remove(0);
        int maxVal = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        sinkDown(0);
        return maxVal;
    }

    private void sinkDown(int index) {
        int maxIndex = index;
        while (true) {
            int leftIndex = leftChild(index);
            int rightIndex = rightChild(index);
            if (leftIndex < heap.size() && heap.get(leftIndex) > heap.get(maxIndex)) {
                maxIndex = leftIndex;
            }
            if (rightIndex < heap.size() && heap.get(rightIndex) > heap.get(maxIndex)) {
                maxIndex = rightIndex;
            }
            if (maxIndex != index) {
                swapIndex(index, maxIndex);
                index = maxIndex;
            } else {
                return;
            }
        }
    }

    private void insertMinHeap(int value) {
        heap.add(value);
        int index = heap.size() - 1;
        while (index > 0 && heap.get(index) < heap.get(parent(index))) {
            swapIndex(index, parent(index));
            index = parent(index);
        }
    }

    public Integer removeFromMinHeap() {
        if (heap.isEmpty()) return null;
        if (heap.size() == 1) return heap.remove(0);
        Integer elem = heap.set(0, heap.remove(heap.size() - 1));
        sinkDownMinHeap(0);
        return elem;
    }

    public void sinkDownMinHeap(int index) {
        int swappableIndex = index;
        while (true) {
            int n = heap.size();
            int left = leftChild(index);
            int right = rightChild(index);
            if (left < n && heap.get(index) > heap.get(left)) {
                swappableIndex = left;
            }
            if (right < n && heap.get(index) > heap.get(right)) {
                swappableIndex = right;
            }
            if (swappableIndex != index) {
                swapIndex(index, swappableIndex);
                index = swappableIndex;
            } else return;
        }
    }

    public void convertToMinHeap() {
        List<Integer> minHeap = new ArrayList<>(getHeap());
        heap = new ArrayList<>();
        for (int i : minHeap) {
            insertMinHeap(i);
        }
    }

    public void convertToMaxHeap() {
        List<Integer> maxHeap = new ArrayList<>(getHeap());
        heap = new ArrayList<>();
        for (int i : maxHeap) {
            insert(i);
        }
    }

    public static int findKthSmallest(int[] nums, int k) {
        // Initialize a new Heap instance.
        Heap maxHeap = new Heap();

        // Iterate over every number in the input array.
        for (int num : nums) {
            // Insert current number into the heap.
            // Heap property is maintained after each insertion.
            maxHeap.insert(num);

            // Check if heap size exceeds 'k'.
            if (maxHeap.getHeap().size() > k) {
                // If size exceeds 'k', remove the largest number.
                // Heap property is maintained after each removal.
                maxHeap.remove();
            }
        }

        // At this point, the heap contains the smallest 'k' numbers.
        // The largest number in the heap is the kth smallest number in the array.
        // Therefore, we remove and return it.
        return maxHeap.remove();
    }

    public static List<Integer> streamMax(int[] nums) {
        Heap maxHeap = new Heap();
        List<Integer> maxStream = new ArrayList<>();

        for (int num : nums) {
            maxHeap.insert(num);
            // The heap's root is always the maximum, so we add it to the result list
            maxStream.add(maxHeap.getHeap().get(0));
        }

        return maxStream;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i:nums){
            pq.add(i);
        }
        while(pq.size()>k){
            pq.poll();
        }
        return pq.poll();
    }

    public int[][] kClosest(int[][] points, int k) {
        int[][] ans=new int[k][2];
        PriorityQueue<int[]> pq=new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for(int i=0;i<points.length;i++){
            int l=points[i][0];
            int r=points[i][1];
            pq.offer(new int[]{(l*l+r*r),i});
        }
        for(int i=0;i<k;i++){
            ans[i]=points[Objects.requireNonNull(pq.poll())[1]];
        }
        return ans;
    }
}
