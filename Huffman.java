package pkg1;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

    public static class HuffmanNode implements Comparable<HuffmanNode> {
        int count;
        Character character;
        HuffmanNode left;
        HuffmanNode right;

        HuffmanNode(Character c, int count) {
            this.count = count;
            this.character = c;
        }

        public int compareTo(HuffmanNode other) {
            return Integer.compare(this.count, other.count);
        }
    }

    private static HuffmanNode join(HuffmanNode n1, HuffmanNode n2) {
        HuffmanNode joinRoot = new HuffmanNode(null, n1.count + n2.count);
        joinRoot.left = n1;
        joinRoot.right = n2;
        return joinRoot;
    }

    public static HuffmanNode huffmanEncoding(String str) {

        Map<Character, Integer> freq = new HashMap<>();

        for (char c : str.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> heap = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            HuffmanNode node = new HuffmanNode(entry.getKey(), entry.getValue());
            heap.add(node);
        }

        while (heap.size() > 1) {
            HuffmanNode n1 = heap.remove();
            HuffmanNode n2 = heap.remove();
            HuffmanNode joined = Huffman.join(n1, n2);
            heap.add(joined);
        }
        return heap.remove();
    }

    public static void printTree(HuffmanNode node) {
        printTree(node, 0);
    }

    private static void printTree(HuffmanNode node, int level) {
        if (node == null) return;

        // Print right child first (so the tree appears rotated)
        printTree(node.right, level + 1);

        // Print current node
        for (int i = 0; i < level; i++) {
            System.out.print("    "); // 4 spaces per level
        }
        if (node.character != null) {
            System.out.println("'" + node.character + "' (" + node.count + ")");
        } else {
            System.out.println("* (" + node.count + ")");
        }

        // Print left child
        printTree(node.left, level + 1);
    }
}
