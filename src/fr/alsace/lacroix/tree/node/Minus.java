package fr.alsace.lacroix.tree.node;

import fr.alsace.lacroix.tree.Node;

/**
 *
 * @author Florent
 */
public class Minus extends Node {

    public Minus(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    @Override
    public Double eval() {
        return this.left.eval() - this.right.eval();
    }
    
}
