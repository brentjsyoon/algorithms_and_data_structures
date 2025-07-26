package pkg1;

import java.util.ArrayList;

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
}
