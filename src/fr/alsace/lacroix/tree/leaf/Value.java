package fr.alsace.lacroix.tree.leaf;

import fr.alsace.lacroix.tree.Leaf;
import fr.alsace.lacroix.tree.Node;

/**
 *
 * @author Florent
 */
public class Value extends Leaf {

    private String value;
    
    public Value(Node parent, String value) {
        super(null, null, parent);
        this.value = value;
    }

    @Override
    public Double eval() {
        return Double.parseDouble(this.value);
    }
    
}
