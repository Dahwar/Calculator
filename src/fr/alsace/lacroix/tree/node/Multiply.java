/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.alsace.lacroix.tree.node;

import fr.alsace.lacroix.tree.Node;

/**
 *
 * @author Florent
 */
public class Multiply extends Node {

    public Multiply(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    @Override
    public Double eval() {
        return this.left.eval() * this.right.eval();
    }
    
}
