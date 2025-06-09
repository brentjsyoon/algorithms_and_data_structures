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

    // Pretraversal DFS
    public void preDFS(TreeNode<T> root) {
        if (root != null) {
            System.out.println(root.data);
            for (TreeNode<T> child : root.children) {
                preDFS(child);
            }
        }
    }

    // Posttraversal DFS
    public void postDFS(TreeNode<T> root) {
        if (root != null) {
            for (TreeNode<T> child : root.children) {
                postDFS(child);
            }
            System.out.println(root.data);
        }
    }
    
    // Pretraversal BFS (Level-order)
    public void preBFS(TreeNode<T> root) {
        if (root == null) return;

        List<TreeNode<T>> queue = new ArrayList<>();
        queue.add(root);

        int index = 0;
        while (index < queue.size()) {
            TreeNode<T> current = queue.get(index++);
            System.out.println(current.data);

            queue.addAll(current.children);
        }
    }

    // Posttraversal BFS (Level-order, printed in reverse)
    public void postBFS(TreeNode<T> root) {
        if (root == null) return;

        List<TreeNode<T>> queue = new ArrayList<>();
        queue.add(root);

        int index = 0;
        while (index < queue.size()) {
            TreeNode<T> current = queue.get(index++);
            queue.addAll(current.children);
        }

        // Print nodes in reverse order
        for (int i = queue.size() - 1; i >= 0; i--) {
            System.out.println(queue.get(i).data);
        }
    }
}
