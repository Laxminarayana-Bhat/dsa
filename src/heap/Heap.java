package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Heap {

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
}
