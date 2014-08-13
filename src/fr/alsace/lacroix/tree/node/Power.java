package fr.alsace.lacroix.tree.node;

import fr.alsace.lacroix.tree.Node;

/**
 *
 * @author Florent
 */
public class Power extends Node {

    public Power(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    @Override
    public Double eval() {
        return Math.pow(this.left.eval(), this.right.eval());
    }
    
}
