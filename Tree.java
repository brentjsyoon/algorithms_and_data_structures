package pkg1;
import java.util.ArrayList;
import java.util.List;

public class Tree<T> {

    // Inner TreeNode class
    public static class TreeNode<T> {
        private T data;
        private TreeNode<T> parent;
        private List<TreeNode<T>> children;

        public TreeNode(T data) {
            this.data = data;
            this.children = new ArrayList<>();
            this.parent = null;
        }

        public T getData() {
            return data;
        }

        public TreeNode<T> getParent() {
            return parent;
        }

        public List<TreeNode<T>> getChildren() {
            return children;
        }

        public void addChild(TreeNode<T> child) {
            child.parent = this;
            this.children.add(child);
        }

        public void removeChild(TreeNode<T> child) {
            child.parent = null;
            this.children.remove(child);
        }

        public boolean isLeaf() {
            return children.isEmpty();
        }

        public int getLevel() {
            int level = 0;
            TreeNode<T> current = this.parent;
            while (current != null) {
                level++;
                current = current.parent;
            }
            return level;
        }

        public void printTree(String indent) {
            System.out.println(indent + data);
            for (TreeNode<T> child : children) {
                child.printTree(indent + "  ");
            }
        }
    }

    private TreeNode<T> root;

    public Tree(T rootData) {
        this.root = new TreeNode<>(rootData);
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void printTree() {
        if (root != null) {
            root.printTree("");
        }
    }
}