package pkg1;

public class BinaryTree<T> {

    BTNode<T> root;

    public static class BTNode<T> {
        T element;
        BTNode<T> leftChild;
        BTNode<T> rightChild;

        public BTNode (T data) {
            this.element = data;
            this.leftChild = null;
            this.rightChild = null;
        }

        public T getData() {
            return element;
        }

        public BTNode<T> getLeftChild() {
            return leftChild;
        }

        public BTNode<T> getRightChild() {
            return rightChild;
        }

        public void addLeft(BTNode<T> node) {
            leftChild = node;
        }

        public void addRight(BTNode<T> node) {
            rightChild = node;
        }

        public void removeLeft() {
            leftChild = null;
        }

        public void removeRight() {
            rightChild = null;
        }
    }

    public BinaryTree(T rootData) {
        this.root = new BTNode<>(rootData);
    }

    public BTNode<T> getRoot() {
        return root;
    }

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(BTNode<T> node, String prefix, boolean isTail) {
        if (node == null) return;

        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getData());

        boolean hasLeft = node.getLeftChild() != null;
        boolean hasRight = node.getRightChild() != null;

        if (hasLeft || hasRight) {
            if (hasRight) {
                printTree(node.getRightChild(), prefix + (isTail ? "    " : "│   "), !hasLeft);
            }
            if (hasLeft) {
                printTree(node.getLeftChild(), prefix + (isTail ? "    " : "│   "), true);
            }
        }
    }
}
