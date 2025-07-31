package pkg1;

public class AVL {

    private class Node {
        int key, height;
        Node left, right;

        private Node(int key) {
            this.key = key;
            height = 1;
        }
    }

    private Node root;

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        
        if (key < node.key) {
            node.left = insert(node.left, key);
        }
        else {
            node.right = insert(node.right, key);
        }

        updateHeight(node);

        return balance(node);
    }

    private int getHeight(Node n) {
        return n == null ? 0 : n.height;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private int getBalance(Node n) {
        return n == null ? 0 : getHeight(n.left) - getHeight(n.right);
    }

    private Node balance(Node n) {
        int nodeBalance = getBalance(n);

        // tree is left heavy
        if (nodeBalance > 1) {
            // left child is right heavy (zig-zag)
            if (getBalance(n.left) < 0) {
                n.left = rotateLeft(n.left);
            }
            return rotateRight(n);
        }
        // tree is right heavy
        else if (nodeBalance < -1) {
            if (getBalance(n.right) > 0) {
                n.right = rotateRight(n.right);
            }
            return rotateLeft(n);
        }

        return n;
    }

    private Node rotateLeft(Node n) {
        Node rightSubtree = n.right;
        Node rightsLeft = rightSubtree.left;

        rightSubtree.left = n;
        n.right = rightsLeft;


        updateHeight(rightSubtree);
        updateHeight(n);

        return rightSubtree;
    }

    private Node rotateRight(Node n) {
        Node leftSubtree = n.left;
        Node leftsRight = leftSubtree.right;

        leftSubtree.right = n;
        n.left = leftsRight;

        updateHeight(leftSubtree);
        updateHeight(n);

        return leftSubtree;
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node node, int level) {
        if (node == null) return;

        // Print right child first (it appears on top)
        printTree(node.right, level + 1);

        // Indent based on level
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.key);

        // Then print left child
        printTree(node.left, level + 1);
    }

    public void inOrderTraversal() {
        inOrderTraversal(this.root);
        System.out.println();
    }


    private void inOrderTraversal(Node n) {
        if (n != null) {
            inOrderTraversal(n.left);
            System.out.print(n.key + " ");
            inOrderTraversal(n.right);
        }
    }
}
