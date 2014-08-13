package fr.alsace.lacroix.tree.leaf;

import fr.alsace.lacroix.tree.Leaf;
import fr.alsace.lacroix.tree.Node;

/**
 *
 * @author Florent
 */
public class SquareRoot extends Leaf {

    Node value;
    
    public SquareRoot(Node parent, Node value) {
        super(null, null, parent);
        this.value = value;
    }

    @Override
    public Double eval() {
        return Math.sqrt(value.eval());
    }
    
}
