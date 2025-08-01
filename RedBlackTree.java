package pkg1;

public class RedBlackTree {

    class Node {
        int key;
        String colour; // "RED" or "BLACK"
        Node parent, left, right;

        public Node(int key, String colour, Node nil) {
            this.key = key;
            this.colour = colour;
            this.left = nil;
            this.right = nil;
            this.parent = nil;
        }
    }

    private final Node NIL = new Node(-1, "BLACK", null);
    private Node root = NIL;

    public void insert(int key) {
        Node z = new Node(key, "RED", NIL);
        Node y = NIL;
        Node x = this.root;
        while (x != NIL) {
            y = x;
            if (key < x.key) {
                x = x.left;
            }
            else {
                x = x.right;
            }
        }

        z.parent = y;

        if (y == NIL) {
            this.root = z;
        }
        else if (key < y.key) {
            y.left = z;
        }
        else {
            y.right = z;
        }

        z.left = NIL;
        z.right = NIL;

        fixRBTree(z);
    }

    private void fixRBTree(Node z) {
        while (z.parent.colour.equals("RED")) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;
                if (y.colour.equals("RED")) {
                    z.parent.colour = "BLACK";
                    y.colour = "BLACK";
                    z.parent.parent.colour = "RED";
                    z = z.parent.parent;
                }
                else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.colour = "BLACK";
                    z.parent.parent.colour = "RED";
                    rightRotate(z.parent.parent);
                }
            }
            else {
                Node y = z.parent.parent.left;
                if (y.colour.equals("RED")) {
                    z.parent.colour = "BLACK";
                    y.colour = "BLACK";
                    z.parent.parent.colour = "RED";
                    z = z.parent.parent;
                }
                else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.colour = "BLACK";
                    z.parent.parent.colour = "RED";
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.colour = "BLACK";
    }

    private void rightRotate(Node z) {
        Node leftSubtree = z.left;
        z.left = leftSubtree.right;
        
        if (leftSubtree.right != NIL) {
            leftSubtree.right.parent = z;
        }

        leftSubtree.parent = z.parent;

        if (z.parent == NIL) {
            this.root = leftSubtree;
        }
        else if (z == z.parent.right) {
            z.parent.right = leftSubtree;
        }
        else {
            z.parent.left = leftSubtree;
        }

        leftSubtree.right = z;
        z.parent = leftSubtree;
    }

    private void leftRotate(Node z) {
        Node rightSubtree = z.right;
        z.right = rightSubtree.left;
        
        if (rightSubtree.left != NIL) {
            rightSubtree.left.parent = z;
        }

        rightSubtree.parent = z.parent;

        if (z.parent == NIL) {
            this.root = rightSubtree;
        }
        else if (z == z.parent.left) {
            z.parent.left = rightSubtree;
        }
        else {
            z.parent.right = rightSubtree;
        }

        rightSubtree.left = z;
        z.parent = rightSubtree;
    }

    public void inorderTraversal() {
        inorderHelper(this.root);
    }

    private void inorderHelper(Node node) {
        if (node != NIL) {
            inorderHelper(node.left);
            System.out.println(node.key + " (" + node.colour + ")");
            inorderHelper(node.right);
        }
    }

    public void printVerticalTree() {
        printTree(root, 0);
    }

    private void printTree(Node node, int level) {
        if (node != NIL) {
            printTree(node.right, level + 1);
            System.out.println("    ".repeat(level) + node.key + "(" + node.colour.charAt(0) + ")");
            printTree(node.left, level + 1);
        }
    }
}