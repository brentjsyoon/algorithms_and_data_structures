package pkg1;

import java.util.ArrayList;
import java.util.Collections;

public class Heap<T extends Comparable<T>> {

    private ArrayList<T> list;

    public Heap() {
        list = new ArrayList<>();
    }

    public void printTree() {
        printTreeHelper(0, 0);
    }

    private void printTreeHelper(int index, int depth) {
        if (index >= list.size()) return;

        int right = 2 * index + 2;
        int left = 2 * index + 1;

        // Print right subtree first (so it appears on top when rotated)
        printTreeHelper(right, depth + 1);

        // Print current node with indentation
        for (int i = 0; i < depth; i++) System.out.print("    ");
        System.out.println(list.get(index));

        // Print left subtree
        printTreeHelper(left, depth + 1);
    }

    public void add(T key) {
        this.list.add(key);

        int cur = list.size() - 1;

        while (cur > 0 && list.get(cur).compareTo(list.get((cur - 1) / 2)) < 0) {
            //swap with parent
            int parent = (cur - 1) / 2;
            T temp = list.get(cur);
            list.set(cur, list.get(parent));
            list.set(parent, temp);

            cur = parent;
        }
    }

    public T removeMin() {
        if (list.isEmpty()) return null;

        T min = list.get(0);

        T last = list.remove(list.size() - 1);
            if (list.isEmpty()) return min;

        list.set(0, last);

        // Start heapify-down from root
        int cur = 0;
        int size = list.size();

        while (true) {
            int left = 2 * cur + 1;
            int right = 2 * cur + 2;
            int smallest = cur;

            // Find the smallest among current, left, and right
            if (left < size && list.get(left).compareTo(list.get(smallest)) < 0) {
                smallest = left;
            }

            if (right < size && list.get(right).compareTo(list.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest == cur) break; // Heap property satisfied

            // Swap with smaller child
            T temp = list.get(cur);
            list.set(cur, list.get(smallest));
            list.set(smallest, temp);

            cur = smallest; // continue down
        }

        return min;
    }

    public void downHeap(int startIndex, int maxIndex) {
        int i = startIndex;
        while (2*i+1 <= maxIndex) {
            int child = 2*i+1;
            if (child < maxIndex) {
                if (this.list.get(child+1).compareTo(this.list.get(child)) < 0) {
                    child = child + 1;
                }
            }
            if (this.list.get(child).compareTo(this.list.get(i)) < 0) {
                T temp = this.list.get(child);
                this.list.set(child, this.list.get(i));
                this.list.set(i, temp);
                i = child;
            }
            else break;
        }
    }

    public void buildHeap(ArrayList<T> heap) {
        this.list = new ArrayList<>(heap);

        for (int k = (heap.size()-1)/2; k >= 0; k--) {
            downHeap(k, heap.size()-1);
        }
    }

    public void heapSort() {
        int heapSize = this.list.size();
        for (int i = heapSize - 1; i > 0; i--) {
            T temp = this.list.get(0);
            this.list.set(0, this.list.get(i));
            this.list.set(i, temp);

            minHeapify(0, i);
        }

        Collections.reverse(this.list);
    }

    public void minHeapify(int i, int heapSize) {
        T parent = this.list.get(i);
        int left = i*2+1;
        int right = i*2+2;
        int smallest = i;
        if (left < heapSize && parent.compareTo(this.list.get(left)) > 0) {
            smallest = left;
        }
        if (right < heapSize && this.list.get(smallest).compareTo(this.list.get(right)) > 0) {
            smallest = right;
        }
        if (smallest != i) {
            T temp = this.list.get(i);
            this.list.set(i, this.list.get(smallest));
            this.list.set(smallest, temp);

            minHeapify(smallest, heapSize);
        }
    }

    public void printList() {
        System.out.println(this.list);
    }
}
