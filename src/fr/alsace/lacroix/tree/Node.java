package fr.alsace.lacroix.tree;

/**
 *
 * @author Florent
 */
public abstract class Node {
    protected Node left;
    protected Node right;
    protected Node parent;

    public Node(Node left, Node right, Node parent) {
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
    
    public Node getLeft() {
        return this.left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    public abstract Double eval();
    
}
